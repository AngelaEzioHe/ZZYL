package com.zzyl.nursing.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.zzyl.common.exception.base.BaseException;
import com.zzyl.nursing.service.WechatService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: AngelaEzioHe
 * @Date: 2025/11/16 05:44
 */

@Service
public class WechatServiceImpl implements WechatService {
    // 登录
    private static final String REQUEST_URL = "https://api.weixin.qq.com/sns/jscode2session?grant_type=authorization_code";

    // 获取token
    private static final String TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential";

    // 获取手机号
    private static final String PHONE_REQUEST_URL = "https://api.weixin.qq.com/wxa/business/getuserphonenumber?access_token=";

    @Value("${wechat.appId}")
    private String appId;

    @Value("${wechat.appSecret}")
    private String appSecret;

    /**
     * 获取openId
     *
     * @param code 小程序端临时登录凭证
     * @return openId
     */
    @Override
    public String getOpenId(String code) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("appid", appId);
        paramMap.put("secret", appSecret);
        paramMap.put("js_code", code);
        String result = HttpUtil.get(REQUEST_URL, paramMap);
        //判断接口响应是否出错
        //将结果解析为JSON对象
        JSONObject jsonObject = JSONUtil.parseObj(result);
        if(ObjectUtil.isNotEmpty(jsonObject.getInt("errcode"))){
            throw new BaseException(jsonObject.getStr("errmsg"));
        }
        //解析结果中的openId并返回
        return jsonObject.getStr("openid");
    }

    /**
     * 获取手机号
     *
     * @param detailCode 小程序端手机号临时登录凭证
     * @return 手机号
     */
    @Override
    public String getPhone(String detailCode) {
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("code", detailCode);
        String result = HttpUtil.post(PHONE_REQUEST_URL + getAccessToken(), JSONUtil.toJsonStr(paramMap));
        JSONObject jsonObject = JSONUtil.parseObj(result);
        //判断接口响应是否出错
        if(!ObjectUtil.equals(jsonObject.getInt("errcode"), 0)){
            throw new BaseException(jsonObject.getStr("errmsg"));
        }
        //解析结果
        return jsonObject.getJSONObject("phone_info").getStr("phoneNumber");
    }

    /**
     * 获取微信接口调用凭证
     *
     * @return 返回微信接口调用凭证
     */
    private String getAccessToken() {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("appid", appId);
        paramMap.put("secret", appSecret);
        String result = HttpUtil.get(TOKEN_URL, paramMap);

        //判断接口响应是否出错
        //将结果解析为JSON对象
        JSONObject jsonObject = JSONUtil.parseObj(result);
        if(ObjectUtil.isNotEmpty(jsonObject.getInt("errcode"))){
            throw new BaseException(jsonObject.getStr("errmsg"));
        }
        return jsonObject.getStr("access_token");
    }
}
