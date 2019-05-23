package com.cc.wechat.wxpn.controller;

import com.cc.wechat.wxpn.config.WechatConstant;
import com.cc.wechat.wxpn.service.DispatherService;
import com.cc.wechat.wxpn.service.MsgProcessService;
import com.cc.wechat.wxpn.tools.MessageUtil;
import com.cc.wechat.wxpn.tools.SignUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * created by CC on 2019/5/6
 * mail 279020185@qq.com
 *
 *
 */
@Controller
@RequestMapping("/")
public class TokenController {

    @Autowired
    private WechatConstant wechatConstant;

    @Autowired
    private DispatherService dispatherService;

    @GetMapping("token")
    @ResponseBody
    public String token(String signature,String timestamp,String nonce,String echostr) {

        System.out.println("######start one token######");
        System.out.println(signature);
        System.out.println(timestamp);
        System.out.println(nonce);
        System.out.println(echostr);
        if (SignUtil.checkSignature(signature, timestamp, nonce)) {
            return echostr;
        }
        return null;
    }


    @RequestMapping(value = "token" , method = RequestMethod.POST , produces = "text/html;charset=UTF-8")
    public void processPost(HttpServletRequest request, HttpServletResponse response){
        System.out.println("#################  start process post method");
        response.setCharacterEncoding("UTF-8");
        ServletOutputStream out  = null;
        try {
            Map<String, String> map= MessageUtil.parseXml(request);
            //处理消息
            String returnMsg = dispatherService.dispathMessage(map);
            //返回
            out = response.getOutputStream();
            out.write(returnMsg.getBytes("UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (out!=null){
                    out.flush();
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
