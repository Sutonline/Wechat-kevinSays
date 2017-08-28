package cn.kevin.wechat;

import cn.kevin.wechat.domain.TextMessage;
import cn.kevin.wechat.util.XmlUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * CESHI
 * Created by yongkang.zhang on 2017/8/28.
 */
@Slf4j
public class XmlUtilTest {

    @Test
    public void test() {
        TextMessage message = TextMessage.builder()
                .Content("啦啦啦")
                .CreateTime("2222")
                .FromUserName("dddd")
                .ToUserName("LALALA")
                .MsgId("1111")
                .MsgType("text").build();
        String xmlStr = XmlUtil.marshal(message);
        log.info("序列化结果是: \n{}", xmlStr);
        TextMessage unmarshal = XmlUtil.unmarshal(TextMessage.class, xmlStr);
        log.info("反序列化结果是: \n{}", unmarshal);
    }

}
