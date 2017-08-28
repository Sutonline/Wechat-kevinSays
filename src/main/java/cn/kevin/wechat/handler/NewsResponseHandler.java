package cn.kevin.wechat.handler;

import cn.kevin.wechat.enums.TulingResponseTypeEnum;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 新闻功能，暂时不展示
 * Created by yongkang.zhang on 2017/8/28.
 */
@Component
public class NewsResponseHandler implements TulingResponseHandler {


    @Override
    public TulingResponseTypeEnum getDesiredType() {
        return TulingResponseTypeEnum.NEWS;
    }

    @Override
    public String handle(Map<String, String> map) {
        return "暂时不能给这个回复哦";
    }
}
