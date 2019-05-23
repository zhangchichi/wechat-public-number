package com.cc.wechat.wxpn.dao;

import com.cc.wechat.wxpn.entities.User;
import org.springframework.data.repository.CrudRepository;

/**
 * created by CC on 2019/5/23
 * mail 279020185@qq.com
 */
public interface UserDao extends CrudRepository<User, String> {

    User findByopenid(String openid);

}
