package com.cc.wechat.wxpn.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * created by CC on 2019/5/23
 * mail 279020185@qq.com
 */
@Entity
@Table(name = "user_info")
@Data
public class User {

    @Id
    private String openid;

    private Integer sex;
    private String country;
    private String province;
    private String city;
    private String nickname;



}
