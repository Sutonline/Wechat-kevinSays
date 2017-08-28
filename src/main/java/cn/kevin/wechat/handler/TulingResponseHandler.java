package cn.kevin.wechat.handler;

import cn.kevin.wechat.enums.TulingResponseTypeEnum;

import java.util.Map;

/**
 * 图灵回复的handler
 * Created by yongkang.zhang on 2017/8/28.
 */
public interface TulingResponseHandler {

    TulingResponseTypeEnum getDesiredType();

    String handle(Map<String, String> map);
}
