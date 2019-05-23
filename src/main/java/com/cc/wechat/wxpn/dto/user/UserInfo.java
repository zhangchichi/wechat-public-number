package com.cc.wechat.wxpn.dto.user;

import lombok.Data;

import java.util.List;

/**
 * created by CC on 2019/5/22
 * mail 279020185@qq.com
 */
@Data
public class UserInfo {
    private int subscribe;
    private String openid;
    private int sex;
    private String language;
    private String city;
    private String province;
    private String country;
    private String subscribe_time;
    private String remark;
    private int groupid;
    private List<Integer> tagid_list;
    private String subscribe_scene;
    private int qr_scene;
    private String qr_scene_str;
    private String nickname;
    private String headimgurl;

}
