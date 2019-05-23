package com.cc.wechat.wxpn.dto.req;

/**
 * created by CC on 2019/5/22
 * mail 279020185@qq.com
 */
public class LocationMessage extends BaseMessage {
    // 地理位置维度
    private String Location_X;
    // 地理位置经度
    private String Location_Y;
    // 地图缩放大小
    private String Scale;
    // 地理位置信息
    private String Label;

}
