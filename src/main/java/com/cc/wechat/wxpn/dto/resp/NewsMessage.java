package com.cc.wechat.wxpn.dto.resp;

import lombok.Data;

import java.util.List;

/**
 * created by CC on 2019/5/22
 * mail 279020185@qq.com
 */
@Data
public class NewsMessage extends BaseMessage {
    // 图文消息个数，限制为 10 条以内
    private int ArticleCount;
    // 多条图文消息信息，默认第一个 item 为大图
    private List<Article> Articles;

}
