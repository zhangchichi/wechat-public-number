package com.cc.wechat.wxpn.dto.resp;

import lombok.Data;

/**
 * created by CC on 2019/5/22
 * mail 279020185@qq.com
 */
@Data
public class BaseMessage {

    // 接收方帐号（收到的 OpenID）
    private String ToUserName;
    // 开发者微信号
    private String FromUserName;
    // 消息创建时间 （整型）
    private long CreateTime;
    // 消息类型（text/music/news）
    private String MsgType;

}
