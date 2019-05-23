package com.cc.wechat.wxpn.service;

import com.cc.wechat.wxpn.dao.UserDao;
import com.cc.wechat.wxpn.dto.req.TextMessage;
import com.cc.wechat.wxpn.dto.resp.Article;
import com.cc.wechat.wxpn.dto.resp.NewsMessage;
import com.cc.wechat.wxpn.dto.user.UserInfo;
import com.cc.wechat.wxpn.entities.User;
import com.cc.wechat.wxpn.tools.MessageUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * created by CC on 2019/5/23
 * mail 279020185@qq.com
 */
@Service
public class MsgProcessService {

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private UserDao userDao;

    public String processTextTypeMsg(TextMessage textMessage) throws Exception {
        if (StringUtils.isNotEmpty(textMessage.getContent())){
            //返回用户自己的信息
            if (textMessage.getContent().equals("mp")){
                UserInfo userInfo = userInfoService.getUserInfoByOpenId(textMessage.getFromUserName());
                com.cc.wechat.wxpn.dto.resp.TextMessage responseMsg = new com.cc.wechat.wxpn.dto.resp.TextMessage();
                responseMsg.setContent(userInfo.toString());
                responseMsg.setCreateTime(new Date().getTime());
                responseMsg.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
                responseMsg.setFromUserName(textMessage.getToUserName());
                responseMsg.setToUserName(textMessage.getFromUserName());

                return MessageUtil.textMessageToXml(responseMsg);
            }//返回最近一篇文章信息
            else if (textMessage.getContent().equals("wz")){
                NewsMessage newmsg=new NewsMessage();
                newmsg.setToUserName(textMessage.getFromUserName());
                newmsg.setFromUserName(textMessage.getToUserName());
                newmsg.setCreateTime(new Date().getTime());
                newmsg.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
                Article article=new Article();
                article.setDescription("可以自定义的描述，(●'◡'●)！"); //图文消息的描述
                article.setPicUrl(""); //图文消息图片地址
                article.setTitle("优必学全套26册AI实体教材，填补中小学AI教材空白");  //图文消息标题
                article.setUrl("www.maythe4th.club/test.html");  //图文 url 链接
                List<Article> list=new ArrayList<Article>();
                list.add(article);     //这里发送的是单图文，如果需要发送多图文则在这里 list 中加入多个 Article 即可！
                newmsg.setArticleCount(list.size());
                newmsg.setArticles(list);
                return MessageUtil.newsMessageToXml(newmsg);
            }
        }

        return null;
    }

    /**
     * 处理关注事件
     * @param textMessage
     * @return
     * @throws Exception
     */
    public String processSubscribe(TextMessage textMessage) throws Exception {
        User user = userDao.findById(textMessage.getFromUserName()).orElse(null);
        UserInfo userInfo = userInfoService.getUserInfoByOpenId(textMessage.getFromUserName());
        //新增或者更新
        if (user == null){
            user = new User();
            user.setOpenid(textMessage.getFromUserName());
            user.setCity(userInfo.getCity());
            user.setCountry(userInfo.getCountry());
            user.setNickname(userInfo.getNickname());
            user.setProvince(userInfo.getProvince());
            user.setSex(userInfo.getSex());
        }else{
            user.setCity(userInfo.getCity());
            user.setCountry(userInfo.getCountry());
            user.setNickname(userInfo.getNickname());
            user.setProvince(userInfo.getProvince());
            user.setSex(userInfo.getSex());
        }
        userDao.save(user);

        //回复欢迎信息
        com.cc.wechat.wxpn.dto.resp.TextMessage responseMsg = new com.cc.wechat.wxpn.dto.resp.TextMessage();
        StringBuffer sb = new StringBuffer();
        sb.append("欢迎 : "+ user.getNickname()+"，可以使用下列回复：");
        sb.append("\n");
        sb.append("mp : 查看自己名片信息");
        sb.append("\n");
        sb.append("wz : 查看样例文章");

        responseMsg.setContent( sb.toString() );
        responseMsg.setCreateTime(new Date().getTime());
        responseMsg.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
        responseMsg.setFromUserName(textMessage.getToUserName());
        responseMsg.setToUserName(textMessage.getFromUserName());

        return MessageUtil.textMessageToXml(responseMsg);
    }


}
