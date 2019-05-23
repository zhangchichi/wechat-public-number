package com.cc.wechat.wxpn.service;

import com.cc.wechat.wxpn.config.WechatConstant;
import com.cc.wechat.wxpn.dto.WeChatAccessToken;
import com.cc.wechat.wxpn.dto.user.UserInfo;
import com.cc.wechat.wxpn.tools.HttpUtils;
import com.cc.wechat.wxpn.tools.JsonUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * created by CC on 2019/5/22
 * mail 279020185@qq.com
 */
@Service
public class UserInfoService {

    @Autowired
    private WechatConstant wechatConstant;

    public UserInfo getUserInfoByOpenId(String openid)
            throws Exception {
        WeChatAccessToken weChatAccessToken = wechatConstant.currentAccessToken();
        System.out.println("weChat token :"+ weChatAccessToken.getAccessToken());

        HashMap<String, String> params = new HashMap<String, String>();
        params.put("access_token", weChatAccessToken.getAccessToken());
        params.put("openid", openid);  //需要获取的用户的 openid
        params.put("lang", "zh_CN");
        String subscribers = HttpUtils.sendGet(
                wechatConstant.getUserInfoUrl(), params);
        System.out.println("微信返回用户信息: "+subscribers);
        params.clear();
        UserInfo  userInfo = JsonUtils.convertStr2Object(subscribers,UserInfo.class);
        return userInfo;
    }
}
