package cn.kevin.wechat.service;

import cn.kevin.wechat.domain.TextMessage;

/**
 * service
 * Created by yongkang.zhang on 2017/8/28.
 */
public interface WechatService {

    TextMessage replyMessage(TextMessage fromMessage);
}
