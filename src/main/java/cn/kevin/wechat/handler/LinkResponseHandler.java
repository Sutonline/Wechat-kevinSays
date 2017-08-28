package cn.kevin.wechat.handler;

import cn.kevin.wechat.enums.TulingResponseTypeEnum;
import org.assertj.core.util.Strings;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 链接类的回复
 * Created by yongkang.zhang on 2017/8/28.
 */
@Component
public class LinkResponseHandler implements TulingResponseHandler {

    @Override
    public TulingResponseTypeEnum getDesiredType() {
        return TulingResponseTypeEnum.LINK;
    }

    @Override
    public String handle(Map<String, String> map) {
        String text = map.get("text");
        String url = map.get("url");
        if (Strings.isNullOrEmpty(text) || Strings.isNullOrEmpty(url)) {
            return "未找到相关链接哦";
        }
        return text + ":" + url;
    }
}
