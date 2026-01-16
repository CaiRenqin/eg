package jp.co.yamaha_motor.hdeg.test.feature.hdegz13;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MvcResult;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.stream.Stream;
import java.util.List;
import java.util.Map;
import org.springframework.http.MediaType;
import org.assertj.core.api.Assertions;

import jp.co.yamaha_motor.hdeg.test.HdegTestApplication;
import jp.co.yamaha_motor.hdeg.test.common.BaseFeatureTest;

@SpringBootTest(classes = HdegTestApplication.class)
@ActiveProfiles({ "development-test", "psql-test" })
@AutoConfigureMockMvc
class HDEGZ13Test extends BaseFeatureTest {

        @Autowired
        private JdbcTemplate jdbcTemplate;

        @Value("classpath:db/hdegz13/HDEGZ13Init.sql")
        private Resource initData;

        @Value("classpath:db/hdegz13/HDEGZ13Update.sql")
        private Resource updateData;

        @BeforeEach
        void Hdegz12Setup() {
                seedUp(initData);
        }

        private String url = "/hdegz13/HDEGZ13UpdateSheetNumbering";

        String queryInitialSql = """
                        SELECT num
                        FROM numbering
                        WHERE table_name = ?
                        """;

        /**
         * バージョン情報取得APIを呼び出し、XMLレスポンスを返却
         */
        private String getXMLResult(String tableName) throws Exception {
                MvcResult mvcResult = mockMvc.perform(
                                post(url)
                                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                                .param("screenModel.tableName", tableName)
                                                .accept(MediaType.APPLICATION_XML))
                                .andExpect(status().isOk())
                                .andReturn();

                return mvcResult.getResponse().getContentAsString();
        }

        /**
         * 番号IDから数値部分を抽出して整数で返却
         *
         * @param numberingId 番号ID
         * @param prefix      接頭辞
         * @return 数値部分
         */
        private Integer getNumberFromId(String numberingId, String prefix) {
                String numberStr = numberingId.substring(prefix.length());
                String numberWithoutZeroHead = numberStr.replaceFirst("^0+(?!$)", "");
                return numberWithoutZeroHead.isEmpty() ? 0 : Integer.parseInt(numberWithoutZeroHead);
        }

        /**
         * XMLレスポンスから番号IDを抽出して返却
         *
         * @param result XMLレスポンス
         * @return 番号ID
         */
        private String getNumberIdFromResult(String result) {
                String startTag = "<numberingId>";
                String endTag = "</numberingId>";
                int startIdx = result.indexOf(startTag) + startTag.length();
                int endIdx = result.indexOf(endTag);
                return result.substring(startIdx, endIdx);

        }

        /**
         * 採番フォーマットのパラメータデータを提供
         *
         * @return パラメータストリーム
         */
        private static Stream<Arguments> numberingFormatData() {
                return Stream.of(
                                Arguments.of("PRODUCT", "PD", 6, "^PD\\d{6}$"), // PD+6桁
                                Arguments.of("COMPONENT_DATA", "CD", 6, "^CD\\d{6}$"), // CD+6桁
                                Arguments.of("DATA", "DID", 7, "^DID\\d{7}$"), // DID+7桁
                                Arguments.of("PART", "P", 7, "^P\\d{7}$"), // P+7桁
                                Arguments.of("MODEL", "MD", 4, "^MD\\d{4}$") // MD+4桁
                );
        }

        /**
         * 採番テーブルのスナップショットを取得する
         *
         * @return 採番テーブルのスナップショット
         */
        private List<Map<String, Object>> getNumberingTableSnapshot() {
                String sql = "SELECT * FROM numbering ORDER BY table_name";
                return jdbcTemplate.query(sql, new ColumnMapRowMapper());
        }

        /**
         * 正常系テーブルの採番を実行する
         *
         * @param tableName 採番対象のテーブル名
         * @param prefix    接頭辞
         * @param digit     桁数
         * @param regex     正規表現
         * @throws Exception
         */
        @ParameterizedTest
        @MethodSource("numberingFormatData")
        void dataCheck(String tableName, String prefix, int digit, String regex) throws Exception {
                Integer initialNumber = jdbcTemplate.queryForObject(
                                queryInitialSql,
                                Integer.class,
                                tableName);

                String result = getXMLResult(tableName);

                Assertions.assertThat(result)
                                .contains("<resultSet>")
                                .contains("<row")
                                .contains("<numberingId>");

                String numberingId = getNumberIdFromResult(result);

                Assertions.assertThat(numberingId)
                                .matches(regex)
                                .startsWith(prefix)
                                .hasSize(prefix.length() + digit);

                Integer number = getNumberFromId(numberingId, prefix);
                Assertions.assertThat(number).isEqualTo(initialNumber);
        }

        /**
         * 正常採番後の採番テーブル更新確認
         *
         * @param targetTableName 採番対象のテーブル名
         * @throws Exception
         */
        @ParameterizedTest
        @ValueSource(strings = { "PRODUCT", "COMPONENT_DATA", "DATA", "PART", "MODEL" })
        void testNumberingTableUpdate(String targetTableName) throws Exception {
                Integer initialNumber = jdbcTemplate.queryForObject(
                                queryInitialSql,
                                Integer.class,
                                targetTableName);

                getXMLResult(targetTableName);

                Integer latestNumber = jdbcTemplate.queryForObject(
                                queryInitialSql,
                                Integer.class,
                                targetTableName);

                Assertions.assertThat(latestNumber).isEqualTo(initialNumber + 1);
        }

        /**
         * MODELテーブルの番号が桁数いっぱいに達した状態で採番を実行する
         *
         * @throws Exception
         */
        @Test
        void dataCheckProduct() throws Exception {
                seedUp(updateData);
                dataCheck("MODEL", "MD", 5, "^MD\\d{5}$");
        }

        /**
         * 異常系テーブル名で採番を実行する
         *
         * @param targetTableName 採番対象のテーブル名
         * @throws Exception
         */
        @ParameterizedTest
        @ValueSource(strings = { "NON_EXIST_TABLE", "" })
        void invalidTableName(String targetTableName) throws Exception {

                List<Map<String, Object>> snapshotBefore = getNumberingTableSnapshot();
                String result = getXMLResult(targetTableName);

                Assertions.assertThat(result)
                                .contains("<resultSet>")
                                .contains("<row")
                                .doesNotContain("<numberingId>");

                List<Map<String, Object>> snapshotAfter = getNumberingTableSnapshot();

                Assertions.assertThat(snapshotAfter).hasSize(snapshotBefore.size());
                Assertions.assertThat(snapshotAfter).isEqualTo(snapshotBefore);
        }
}