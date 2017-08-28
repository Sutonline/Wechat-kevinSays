package cn.kevin.wechat.service.impl;

import cn.kevin.wechat.domain.TextMessage;
import cn.kevin.wechat.service.TulingService;
import cn.kevin.wechat.service.WechatService;
import org.assertj.core.util.Strings;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * wechatServiceImpl
 * Created by yongkang.zhang on 2017/8/28.
 */
@Service
public class WechatServiceImpl implements WechatService {

    private DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd");

    private TulingService tulingService;

    @Override
    public TextMessage replyMessage(TextMessage fromMessage) {
        TextMessage textMessage;
        TextMessage.TextMessageBuilder builder = TextMessage.builder()
                .CreateTime(fromMessage.getCreateTime())
                .MsgType(fromMessage.getMsgType())
                .MsgId(fromMessage.getMsgId())
                .FromUserName(fromMessage.getToUserName())
                .ToUserName(fromMessage.getFromUserName());

        String content = fromMessage.getContent();
        if (Strings.isNullOrEmpty(content)) {
            textMessage = builder.Content("").build();
        } else {
            String replyMessage = processTicketDateQueryFilter(content, fromMessage.getFromUserName());
            textMessage = builder.Content(replyMessage).build();
        }
        return textMessage;
    }


    private String processTicketDateQueryFilter(String content, String fromUser) {
        if (Strings.isNullOrEmpty(content) || !content.contains("今天") || !content.contains("订") || !content.contains("火车票")) {
            return tulingService.getTulingReply(content, fromUser);
        }
        LocalDate localDate = LocalDate.now();
        LocalDate targetDate = localDate.plusDays(29);
        return "今天能订: " + dateTimeFormatter.print(targetDate) + "的火车票";
    }

    @Autowired
    public void setTulingService(TulingService tulingService) {
        this.tulingService = tulingService;
    }
}
