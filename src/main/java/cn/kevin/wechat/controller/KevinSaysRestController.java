package cn.kevin.wechat.controller;

import cn.kevin.wechat.domain.TextMessage;
import cn.kevin.wechat.service.WechatService;
import cn.kevin.wechat.util.Sha1Util;
import cn.kevin.wechat.util.XmlUtil;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * kevinSays订阅号公众号服务
 * Created by yongkang.zhang on 2017/8/28.
 */
@RestController
@Slf4j
public class KevinSaysRestController {

    @Value("${wechat.token}")
    private String token;

    private WechatService wechatService;

    /**
     * @param signature 签名
     * @param timestamp 时间戳
     * @param nonce 随机数
     * @param echostr 随机字符串
     * @return 如果验证成功返回echostr
     */
    @RequestMapping(value = "/connect", method = RequestMethod.GET)
    public String connect(@RequestParam String signature, @RequestParam String timestamp, @RequestParam String nonce, @RequestParam String echostr) {
        Assert.isTrue(!Strings.isNullOrEmpty(token), "token 不可以为空");
        String[] plainStrArray = new String[]{timestamp, token, nonce};
        String concatStr = Arrays.stream(plainStrArray).sorted().collect(Collectors.joining());
        String encryptStr = Sha1Util.encrypt(concatStr);
        if (Objects.equals(encryptStr, signature)) {
            log.info("请求成功");
            return echostr;
        }

        throw new IllegalArgumentException("消息非法!");
    }

    @RequestMapping(value = "/connect", method = RequestMethod.POST)
    public String replyMessage(HttpServletRequest request) {
        try {
            String content = new BufferedReader(new InputStreamReader(request.getInputStream())).lines().collect(Collectors.joining("\n"));
            TextMessage m = XmlUtil.unmarshal(TextMessage.class, content);
            TextMessage replyMessage = wechatService.replyMessage(m);
            return XmlUtil.marshal(replyMessage);
        } catch (IOException e) {
            log.error("解析消息发生错误", e);
            throw new RuntimeException("解析消息错误", e);
        }
    }

    @RequestMapping("/hello")
    public String hello() {
        return "hello world";
    }


    @Autowired
    public void setWechatService(WechatService wechatService) {
        this.wechatService = wechatService;
    }
}
