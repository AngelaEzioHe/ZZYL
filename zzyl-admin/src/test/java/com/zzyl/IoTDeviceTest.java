package com.zzyl;

import com.huaweicloud.sdk.iotda.v5.IoTDAClient;
import com.huaweicloud.sdk.iotda.v5.model.ListProductsRequest;
import com.huaweicloud.sdk.iotda.v5.model.ListProductsResponse;
import com.huaweicloud.sdk.iotda.v5.model.ProductSummary;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @Author: AngelaEzioHe
 * @Date: 2025/11/18 01:41
 */

@SpringBootTest
public class IoTDeviceTest {
    @Autowired
    private IoTDAClient iotdaclient;

    @Test
    public void testGetProductList() {
        ListProductsRequest request=new ListProductsRequest();
        request.setLimit(50);
        ListProductsResponse response = iotdaclient.listProducts(request);
        List<ProductSummary> products = response.getProducts();
        System.out.println( products);
    }
}
