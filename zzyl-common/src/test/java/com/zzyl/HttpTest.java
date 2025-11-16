package com.zzyl;

import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.http.Method;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: AngelaEzioHe
 * @Date: 2025/11/16 03:08
 */

public class HttpTest {
    @Test
    public void testGet() {
        String result = HttpUtil.get("https://www.baidu.com");
        System.out.println(result);
    }

    @Test
    public void testGetByParam(){
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("pageNum",1);
        paramMap.put("pageSize",5);
        String result = HttpUtil.get("http://localhost:8080/nursing/project/list", paramMap);
        System.out.println(result);
    }

    @Test
    public void testCreateGetRequest(){
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("pageNum",1);
        paramMap.put("pageSize",5);
        HttpResponse response = HttpUtil.createRequest(Method.GET, "http://localhost:8080/nursing/project/list")
                .form(paramMap)
                .header("authorization", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImxvZ2luX3VzZXJfa2V5IjoiM2VlMTZlMzAtODc0MS00NTgyLThjY2QtOTE0MDNjMmRjNjQwIn0.Rz5SqB0e4wDQCqw3EUqE-CWBjqh_GMu7rIXesnhjcVI7N2es4_BDcNevdrq3cC8HmONqAw9AxrOlx1rRcSKrUA")
                .execute();
        if(response.isOk()){
            System.out.println(response.body());
        }
    }
}
