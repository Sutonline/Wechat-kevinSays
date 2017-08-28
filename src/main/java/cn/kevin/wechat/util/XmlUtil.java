package cn.kevin.wechat.util;

import lombok.extern.slf4j.Slf4j;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * XML解析和序列化工具
 * Created by yongkang.zhang on 2017/8/28.
 */
@SuppressWarnings("unchecked")
@Slf4j
public class XmlUtil {

    public static <T> T unmarshal(Class<T> clazz, String content) {
        JAXBContext jc = null;
        try {
            jc = JAXBContext.newInstance(clazz);
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            return (T) unmarshaller.unmarshal(new StringReader(content));
        } catch (JAXBException e) {
            log.error("反序列化发生了错误", e);
            throw new RuntimeException("反序列化错误", e);
        }
    }

    public static String marshal(Object obj, String encoding) {
        String result;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(obj.getClass());
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, encoding);
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
            StringWriter stringWriter = new StringWriter();
            marshaller.marshal(obj, stringWriter);
            result = stringWriter.toString();
        } catch (JAXBException e) {
            log.error("序列化发生了错误", e);
            throw new RuntimeException("序列化错误", e);
        }
        return result;
    }

    public static String marshal(Object obj) {
        return marshal(obj, "utf-8");
    }
}
