package cn.kevin.wechat;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 *
 * Created by yongkang.zhang on 2017/8/28.
 */
@SpringBootApplication
public class WechatApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(WechatApplication.class).bannerMode(Banner.Mode.OFF);
    }
    
    public static void main(String[] args) {
        SpringApplication.run(WechatApplication.class, args);
    }
}
