package com.cc.wechat.wxpn.dto.req;

import lombok.Data;

/**
 * created by CC on 2019/5/22
 * mail 279020185@qq.com
 */
@Data
public class BaseMessage {

    // 开发者微信号
    private String ToUserName;
    // 发送方帐号（一个 OpenID）
    private String FromUserName;
    // 消息创建时间 （整型）
    private long CreateTime;
    // 消息类型（text/image/location/link/video/shortvideo）
    private String MsgType;
    // 消息 id，64 位整型
    private long MsgId;

}
