package com.zzyl;

import com.zzyl.nursing.service.WechatService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: AngelaEzioHe
 * @Date: 2025/11/16 05:57
 */

@SpringBootTest
public class WechatServiceImplTest {
    @Autowired
    private WechatService wechatService;

    @Test
    public void testGetOpenId() {
        String openId = wechatService.getOpenId("0d1cL0000K2okV1ROO300ouxqr0cL00v");
        System.out.println(openId);
    }

    @Test
    public void testGetPhone() {
        String phone = wechatService.getPhone("65d6e1a612a2bec2d2df24172c46aa58e2d37d0069ab93802d02fce370f05bf3");
        System.out.println(phone);
    }
}
