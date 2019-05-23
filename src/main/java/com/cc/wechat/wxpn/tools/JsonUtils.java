package com.cc.wechat.wxpn.tools;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * created by CC on 2019/5/23
 * mail 279020185@qq.com
 */
public class JsonUtils {

    private static ObjectMapper mapper = new ObjectMapper();

    public static <T> T convertStr2Object(String jsonStr,Class<T> clazz) throws IOException {
        return mapper.readValue(jsonStr,clazz);
    }

    public static String convertObject2Str(Object o) throws JsonProcessingException {
        return mapper.writeValueAsString(o);
    }
}
