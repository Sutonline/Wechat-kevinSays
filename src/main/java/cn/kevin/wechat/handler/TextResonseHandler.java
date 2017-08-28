package cn.kevin.wechat.handler;

import cn.kevin.wechat.enums.TulingResponseTypeEnum;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 文本消息的回复
 * Created by yongkang.zhang on 2017/8/28.
 */
@Component
public class TextResonseHandler implements TulingResponseHandler {

    @Override
    public TulingResponseTypeEnum getDesiredType() {
        return TulingResponseTypeEnum.TEXT;
    }

    @Override
    public String handle(Map<String, String> map) {
        return map.get("text");
    }
}
