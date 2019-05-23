package com.cc.wechat.wxpn.dto.req;

import lombok.Data;

/**
 * created by CC on 2019/5/22
 * mail 279020185@qq.com
 */
@Data
public class ImageMessage extends BaseMessage {

    // 图片链接
    private String PicUrl;

    //媒质id
    private String MediaId;


}
