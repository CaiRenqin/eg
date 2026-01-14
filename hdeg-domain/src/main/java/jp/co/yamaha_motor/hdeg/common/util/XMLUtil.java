package jp.co.yamaha_motor.hdeg.common.util;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jp.co.yamaha_motor.hdeg.constants.CommonConstants;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.UtilityClass;

import java.io.StringWriter;
import java.util.List;

/**
 * ListオブジェクトをXML文字列に変換するユーティリティクラス
 */
@UtilityClass
public class XMLUtil {

    /**
     * ListオブジェクトをXML文字列に変換する
     *
     * @param list:     変換対象の結果リスト
     * @param beanName: リスト内の要素となるBeanのクラスオブジェクト
     * @param <T>       リスト内の要素の型（ジェネリックパラメータ）
     * @return 生成されたXML文字列
     */
    public static <T> String convDao2Xml(List<T> list, Class<T> beanName) {

        try {
            // JAXBコンテキストの初期化（ラッパークラスと対象Beanクラスを含む）
            JAXBContext jaxbContext = JAXBContext.newInstance(ResultSetWrapper.class, beanName);

            // Marshallerの作成（JAXBによるシリアライズツール）
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true); // インデントを有効にする（元のXStreamと同じ形式）
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true); // XMLヘッダーを自動生成しない（独自に拼接するため）

            // Listをラップし、元のXStreamのエイリアスロジックを実現
            ResultSetWrapper<T> wrapper = new ResultSetWrapper<>(list);

            // ListをXML文字列にシリアライズ
            StringWriter writer = new StringWriter();
            marshaller.marshal(wrapper, writer);
            String xml = writer.toString();

            // 改行コードを取得し、XMLヘッダーと拼接する（元のロジックを保持）
            String crlf = System.getProperty("line.separator");
            return CommonConstants.XML_HEADER + crlf + xml;

        } catch (Exception e) {
            return ("XMLへの変換に失敗しました");
        }
    }

    /**
     * ラッパークラス：元のXStreamのエイリアス設定に対応
     * - @XmlRootElement(name = "resultSet") → 元のxs.alias("resultSet",
     * List.class)に対応
     * - @XmlElement(name = "row") → 元のxs.alias("row", beanName)に対応
     *
     * @param <T> リスト内の要素の型
     */
    @NoArgsConstructor
    @AllArgsConstructor
    @XmlRootElement(name = "resultSet")
    private static class ResultSetWrapper<T> {
        @XmlElement(name = "row")
        private List<T> data;
    }
}