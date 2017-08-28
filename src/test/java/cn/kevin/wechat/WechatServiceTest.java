package cn.kevin.wechat;

import cn.kevin.wechat.domain.TextMessage;
import cn.kevin.wechat.service.WechatService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 测试啦
 * Created by yongkang.zhang on 2017/8/28.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = WechatApplication.class)
@Slf4j
public class WechatServiceTest {

    private WechatService wechatService;

    @Test
    public void test() {
        TextMessage textMessage = new TextMessage();
        textMessage.setToUserName("kevin");
        textMessage.setFromUserName("123456");
        textMessage.setMsgType("text");
        textMessage.setMsgId("1111");
        textMessage.setContent("今天能订什么时间的火车票");
        textMessage.setCreateTime("15443232345");
        TextMessage replyMessage = wechatService.replyMessage(textMessage);
        log.info("回复消息是: {}", replyMessage);
    }

    @Autowired
    public void setWechatService(WechatService wechatService) {
        this.wechatService = wechatService;
    }
}
