package com.cc.wechat.wxpn.service;

import com.cc.wechat.wxpn.dto.req.TextMessage;
import com.cc.wechat.wxpn.tools.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * created by CC on 2019/5/23
 * mail 279020185@qq.com
 */
@Service
public class DispatherService {

    private final String MSG_TYPE_KEY = "MsgType";
    private final String EVENT_KEY = "Event";

    @Autowired
    private MsgProcessService msgProcessService;

    public String dispathMessage(Map<String, String> params) throws Exception {
        String resp = "";
        String msgType = params.get(MSG_TYPE_KEY);
        //进入事件处理
        if(MessageUtil.REQ_MESSAGE_TYPE_EVENT.equals(msgType)){
            resp = processEvent(params);
        }else{
            resp = processMessage(params);
        }
        return resp;
    }


    private String processEvent(Map<String, String> map) throws Exception {
        String respMsg = "";
        String openid=map.get("FromUserName"); //用户 openid
        String mpid=map.get("ToUserName");   //公众号原始 ID

        if (map.get(EVENT_KEY).equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) { //关注事件
            System.out.println("==============这是关注事件！");
            TextMessage txtmsg=new TextMessage();
            txtmsg.setFromUserName(openid);
            txtmsg.setToUserName(mpid);
            return msgProcessService.processSubscribe(txtmsg);
        } else if (map.get(EVENT_KEY).equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) { //取消关注事件
            System.out.println("==============这是取消关注事件！");
        }else if (map.get(EVENT_KEY).equals(MessageUtil.EVENT_TYPE_SCAN)) { //扫描二维码事件
            System.out.println("==============这是扫描二维码事件！");
        }else if (map.get(EVENT_KEY).equals(MessageUtil.EVENT_TYPE_LOCATION)) { //位置上报事件
            System.out.println("==============这是位置上报事件！");
        }else if (map.get(EVENT_KEY).equals(MessageUtil.EVENT_TYPE_CLICK)) { //自定义菜单点击事件
            System.out.println("==============这是自定义菜单点击事件！");
        } else if (map.get(EVENT_KEY).equals(MessageUtil.EVENT_TYPE_VIEW)) { //自定义菜单 View 事件
            System.out.println("==============这是自定义菜单 View 事件！");
        }

        return respMsg;
    }

    private String processMessage(Map<String, String> map) throws Exception {
        String respMsg = "";
        String openid=map.get("FromUserName"); //用户 openid
        String mpid=map.get("ToUserName");   //公众号原始 ID

        if (map.get(MSG_TYPE_KEY).equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) { // 文本消息
            String content=map.get("Content");
            System.out.println("==============这是文本消息！接受信息为 ："+content);
            //普通文本消息
            TextMessage txtmsg=new TextMessage();
            txtmsg.setFromUserName(openid);
            txtmsg.setToUserName(mpid);
            txtmsg.setContent(content);
            return msgProcessService.processTextTypeMsg(txtmsg);
        }else if (map.get(MSG_TYPE_KEY).equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) { // 图片消息
            System.out.println("==============这是图片消息！");
        }else if (map.get(MSG_TYPE_KEY).equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) { // 链接消息
            System.out.println("==============这是链接消息！");
        }else if (map.get(MSG_TYPE_KEY).equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) { // 位置消息
            System.out.println("==============这是位置消息！");
        }else if (map.get(MSG_TYPE_KEY).equals(MessageUtil.REQ_MESSAGE_TYPE_VIDEO)) { // 视频消息
            System.out.println("==============这是视频消息！");
        }else if (map.get(MSG_TYPE_KEY).equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) { // 语音消息
            System.out.println("==============这是语音消息！");
        }else if (map.get(MSG_TYPE_KEY).equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) { // 时间推送
            System.out.println("==============这是时间推送！");
        }

        return respMsg;
    }
}
