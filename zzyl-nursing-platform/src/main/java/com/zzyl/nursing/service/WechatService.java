package com.zzyl.nursing.service;

/**
 * @Author: AngelaEzioHe
 * @Date: 2025/11/16 05:43
 */
public interface WechatService {
    /**
     * 获取openId
     * @param code 小程序端临时登录凭证
     * @return openId
     */
    String getOpenId(String code);

    /**
     * 获取手机号
     * @param detailCode 小程序端手机号临时登录凭证
     * @return 手机号
     */
    String getPhone(String detailCode);
}
