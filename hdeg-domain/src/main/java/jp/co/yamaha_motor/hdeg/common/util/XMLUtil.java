package jp.co.yamaha_motor.hdeg.common.util;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jp.co.yamaha_motor.hdeg.common.constants.CommonConstants;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.UtilityClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.util.List;

/**
 * ListオブジェクトをXML文字列に変換するユーティリティクラス
 */
@UtilityClass
public class XMLUtil {

    private static final Logger LOG = LoggerFactory.getLogger(XMLUtil.class);

    /**
     * ListオブジェクトをXML文字列に変換する
     *
     * @param list:     変換対象の結果リスト
     * @param beanName: リスト内の要素となるBeanのクラスオブジェクト
     * @param <T>       リスト内の要素の型
     * @return 生成されたXML文字列
     */
    public static <T> String convDao2Xml(List<T> list, Class<T> beanName) {

        try {
            // JAXBコンテキストの初期化
            JAXBContext jaxbContext = JAXBContext.newInstance(ResultSetWrapper.class, beanName);

            // Marshallerの作成
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true); // インデントを有効にする
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true); // XMLヘッダーを自動生成しない

            // マーシャリング前にNULLまたは空文字列のフィールドをクリアするリスナーを設定
            marshaller.setListener(new Marshaller.Listener() {
                @Override
                public void beforeMarshal(Object source) {
                    if (source != null && !source.getClass().equals(ResultSetWrapper.class)) {
                        filterNullFields(source);
                    }
                }
            });

            ResultSetWrapper<T> wrapper = new ResultSetWrapper<>(list);

            StringWriter writer = new StringWriter();
            marshaller.marshal(wrapper, writer);
            String xml = writer.toString();
            String crlf = System.getProperty("line.separator");
            return CommonConstants.XML_HEADER + crlf + xml;

        } catch (Exception e) {
            e.printStackTrace();
            return ("XMLへの変換に失敗しました");
        }
    }

    /**
     * NULLまたは空文字列のフィールドをクリアする
     *
     * @param obj 対象オブジェクト
     */
    private static void filterNullFields(Object obj) {
        if (obj == null)
            return;
        Class<?> clazz = obj.getClass();

        for (Method method : clazz.getMethods()) {
            if (method.getName().startsWith("get")
                    && method.getParameterCount() == 0
                    && !Void.TYPE.equals(method.getReturnType())) {
                try {
                    Object value = method.invoke(obj);
                    if (value == null || (value instanceof String str && str.trim().isEmpty())) {
                        String setterName = "set" + method.getName().substring(3);
                        Method setter = clazz.getMethod(setterName, method.getReturnType());
                        setter.invoke(obj, (Object) null);
                    }
                } catch (Exception e) {
                    LOG.error("フィールド{}の処理に失敗しました", method.getName(), e);
                }
            }
        }
    }

    /**
     * ラッパークラス
     */
    @NoArgsConstructor
    @AllArgsConstructor
    @XmlRootElement(name = "resultSet")
    private static class ResultSetWrapper<T> {
        @XmlElement(name = "row")
        private List<T> data;
    }
}