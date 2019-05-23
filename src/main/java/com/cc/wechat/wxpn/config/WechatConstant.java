package com.cc.wechat.wxpn.config;

import com.cc.wechat.wxpn.dto.WeChatAccessToken;
import com.cc.wechat.wxpn.tools.HttpUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * created by CC on 2019/5/22
 * mail 279020185@qq.com
 */
@ConfigurationProperties(prefix = "weixin")
@Data
public class WechatConstant {

    private String appid;

    private String appsecret;

    private String tokenUrl;

    private String userInfoUrl;

    private WeChatAccessToken accessToken;


    public WeChatAccessToken currentAccessToken(){
        if (accessToken==null || !isValidateToken(accessToken)){
            accessToken = getNewAccessToken();
        }
        return accessToken;
    }

    private boolean isValidateToken(WeChatAccessToken token){
        if (token!=null &&
                (token.getStartTime()+token.getExpiresIn())*1000>System.currentTimeMillis()){
            return true;
        }
        return false;
    }

    private WeChatAccessToken getNewAccessToken(){
        WeChatAccessToken accessToken = null;
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> params = new HashMap<String, String>();
        params.put("grant_type", "client_credential");
        params.put("appid", appid);
        params.put("secret", appsecret);
        try {
            String jsToken = HttpUtils.sendGet(tokenUrl, params);
            System.out.println("request new access token :" +jsToken);
            Map<String,Object> infos = mapper.readValue(jsToken,Map.class);
            accessToken = new WeChatAccessToken();
            accessToken.setAccessToken(infos.get("access_token").toString());
            accessToken.setStartTime(System.currentTimeMillis()/1000);
            if (infos.get("expires_in")!=null){
                accessToken.setExpiresIn((Integer)infos.get("expires_in"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return accessToken;
    }

}
