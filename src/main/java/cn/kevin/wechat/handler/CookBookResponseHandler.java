package cn.kevin.wechat.handler;

import cn.kevin.wechat.enums.TulingResponseTypeEnum;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 菜谱
 * Created by yongkang.zhang on 2017/8/28.
 */
@Component
public class CookBookResponseHandler implements TulingResponseHandler {

    @Override
    public TulingResponseTypeEnum getDesiredType() {
        return TulingResponseTypeEnum.COOKBOOK;
    }

    @Override
    public String handle(Map<String, String> map) {
        return "暂时不支持菜谱查询哦";
    }
}
