package com.cc.wechat.wxpn.dto;

import lombok.Data;

/**
 * created by CC on 2019/5/22
 * mail 279020185@qq.com
 */
@Data
public class WeChatAccessToken {
    private String accessToken;

    int expiresIn =7200;//秒

    long startTime; //开始时间某单位为s

}
