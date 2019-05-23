package com.cc.wechat.wxpn.dto.resp;

import lombok.Data;

/**
 * created by CC on 2019/5/22
 * mail 279020185@qq.com
 */
@Data
public class TextMessage extends BaseMessage {
    // 回复的消息内容
    private String Content;
}
