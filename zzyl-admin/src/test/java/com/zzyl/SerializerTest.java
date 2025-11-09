package com.zzyl;

import com.zzyl.nursing.domain.NursingProject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.math.BigDecimal;

/**
 * @Author: AngelaEzioHe
 * @Date: 2025/11/9 05:59
 */

@SpringBootTest
public class SerializerTest {

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Test
    public void testObjectSerializer() {
        NursingProject np = new NursingProject();
        np.setId(1L);
        np.setName("测试");
        np.setOrderNo(1);
        np.setUnit("次");
        np.setPrice(new BigDecimal("10.00"));

        //将对象保存在Redis
        redisTemplate.opsForValue().set("nursingProject", np);

        NursingProject np1=(NursingProject) redisTemplate.opsForValue().get("nursingProject");
        System.out.println(np1);

    }
}
