package cn.kevin.wechat;

import cn.kevin.wechat.service.TulingService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 图灵测试
 * Created by yongkang.zhang on 2017/8/28.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = WechatApplication.class)
@Slf4j
public class TulingServiceTest {

    private TulingService tulingService;

    @Test
    public void test() {
        String message = tulingService.getTulingReply("订火车票", "123456");
        log.info("回复信息是: {}", message);
    }

    @Autowired
    public void setTulingService(TulingService tulingService) {
        this.tulingService = tulingService;
    }
}
