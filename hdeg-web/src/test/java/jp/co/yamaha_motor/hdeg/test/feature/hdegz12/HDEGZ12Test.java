package jp.co.yamaha_motor.hdeg.test.feature.hdegz12;

import jp.co.yamaha_motor.hdeg.test.HdegTestApplication;
import jp.co.yamaha_motor.hdeg.test.common.BaseFeatureTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * HDEGZ12 バージョン情報取得 機能テスト
 */
@SpringBootTest(classes = HdegTestApplication.class)
@ActiveProfiles({ "development-test", "psql-test" })
@AutoConfigureMockMvc
@Transactional
class HDEGZ12Test extends BaseFeatureTest {
        private String url = "/hdegz12/HDEGZ12UpdateSheetGetVersionList";

        @Value("classpath:db/hdegz12/hdegz12Clear.sql")
        private Resource clearData;

        @Value("classpath:db/hdegz12/hdegz12Init.sql")
        private Resource initDataSingle;

        @Value("classpath:db/hdegz12/hdegz12InitMulti.sql")
        private Resource initDataMulti;

        @BeforeEach
        void Hdegz12Setup() {
                seedUp(clearData);
        }

        private String getXMLResult() throws Exception {
                mvcResult = mockMvc.perform(
                                post(url).header("Content-Type", "application/xml"))
                                .andExpect(status().isOk())
                                .andReturn();
                return mvcResult.getResponse().getContentAsString();
        }

        /**
         * 正常系シナリオの基礎XML構造を断言
         */
        private void assertNormalXmlStructure(String result) {
                assertThat(result)
                                .contains("<resultSet>")
                                .contains("<row")
                                .contains("<majorVer>")
                                .contains("<minorVer")
                                .contains("<releaseVer>")
                                .contains("<updateDate");
        }

        /**
         * 空シナリオの基礎XML構造を断言
         */
        private void assertEmptyXmlStructure(String result) {
                assertThat(result)
                                .contains("<resultSet>")
                                .contains("<row")
                                .doesNotContain("<majorVer>")
                                .doesNotContain("<minorVer>")
                                .doesNotContain("<releaseVer>")
                                .doesNotContain("<updateDate class=\"sql-timestamp\">");
        }

        /**
         * バージョン管理テーブルに0件のデータが存在する状態でバージョン情報を取得する
         */
        @Test
        void dataCheckEmpty() throws Exception {
                String result = getXMLResult();

                assertEmptyXmlStructure(result);

                assertThat(result.split("<majorVer\\s*").length - 1).isZero();
        }

        /**
         * バージョン管理テーブルに1件のデータが存在する状態でバージョン情報を取得する
         */
        @Test
        void dataCheckSingle() throws Exception {
                seedUp(initDataSingle);

                String result = getXMLResult();

                assertNormalXmlStructure(result);

                assertThat(result.split("<majorVer\\s*").length - 1).isEqualTo(1);
                assertThat(result)
                                .contains("<majorVer>2</majorVer>")
                                .contains("<minorVer>0</minorVer>")
                                .contains("<releaseVer>5</releaseVer>")
                                .contains("<updateDate class=\"sql-timestamp\">2026-01-01 00:00:00.0</updateDate>");
        }

        /**
         * バージョン管理テーブルに複数件のデータが存在する状態でバージョン情報を取得する
         */
        @Test
        void dataCheckMulti() throws Exception {
                seedUp(initDataMulti);

                String result = getXMLResult();

                assertNormalXmlStructure(result);

                assertThat(result.split("<majorVer\\s*").length - 1).isEqualTo(1);
                assertThat(result)
                                .contains("<majorVer>3</majorVer>")
                                .contains("<minorVer>1</minorVer>")
                                .contains("<releaseVer>6</releaseVer>")
                                .contains("<updateDate class=\"sql-timestamp\">2026-01-15 00:00:00.0</updateDate>");
        }
}