package com.cc.wechat.wxpn.dto.req;

import lombok.Data;

/**
 * created by CC on 2019/5/22
 * mail 279020185@qq.com
 */
@Data
public class TextMessage extends BaseMessage {
    // 消息内容
    private String Content;
}
