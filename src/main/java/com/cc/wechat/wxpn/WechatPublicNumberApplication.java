package com.cc.wechat.wxpn;

import com.cc.wechat.wxpn.config.WechatConstant;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({WechatConstant.class})
public class WechatPublicNumberApplication {

    public static void main(String[] args) {
        SpringApplication.run(WechatPublicNumberApplication.class, args);
    }

}
