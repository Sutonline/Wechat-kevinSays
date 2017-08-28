package cn.kevin.wechat.service.impl;

import cn.kevin.wechat.enums.TulingResponseTypeEnum;
import cn.kevin.wechat.handler.TulingResponseHandler;
import cn.kevin.wechat.service.TulingService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * tuling service
 * Created by yongkang.zhang on 2017/8/28.
 */
@Service
public class TulingServiceImpl implements TulingService {

    private static String apiUrl;
    private static String apiKey;
    private static String secret;
    private List<TulingResponseHandler> handlerList;
    private Map<String, TulingResponseHandler> handlerMap;
    private RestTemplate restTemplate;

    @Override
    public String getTulingReply(String message, String userid) {
        String retStr;
        Map<String, String> map = new HashMap<>();
        map.put("key", apiKey);
        map.put("userid", userid);
        map.put("info", message);
        String json = JSON.toJSONString(map);
        String responseStr = restTemplate.postForObject(apiUrl, json, String.class);
        Map<String, String> stringStringMap = JSON.parseObject(responseStr, new TypeReference<Map<String, String>>(){});
        TulingResponseHandler handler = handlerMap.get(stringStringMap.get("code"));
        if (handler == null) {
            retStr = "您的问题太高深了，小kevin还不会啊";
        } else {
            retStr = handler.handle(stringStringMap);
        }
        return retStr;
    }

    @Autowired
    public void setTemplate(RestTemplate template) {
        this.restTemplate = template;
    }

    @Value(value = "${tuling.apiUrl}")
    public void setApiUrl(String apiUrl) {
        TulingServiceImpl.apiUrl = apiUrl;
    }

    @Value(value = "${tuling.apiKey}")
    public void setApiKey(String apiKey) {
        TulingServiceImpl.apiKey = apiKey;
    }

    @Value(value = "${tuling.secret}")
    public void setSecret(String secret) {
        TulingServiceImpl.secret = secret;
    }

    @Autowired
    public void setHandlerList(List<TulingResponseHandler> handlerList) {
        this.handlerList = handlerList;
        this.handlerMap = this.handlerList.stream().collect(Collectors.toMap(handler -> handler.getDesiredType().getCode(), handler -> handler));
    }

}
