package com.zzyl.nursing.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.zzyl.common.core.domain.R;
import com.zzyl.nursing.dto.DeviceDto;
import com.zzyl.nursing.vo.DeviceDetailVo;
import com.zzyl.nursing.vo.ProductVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.zzyl.common.annotation.Log;
import com.zzyl.common.core.controller.BaseController;
import com.zzyl.common.core.domain.AjaxResult;
import com.zzyl.common.enums.BusinessType;
import com.zzyl.nursing.domain.Device;
import com.zzyl.nursing.service.IDeviceService;
import com.zzyl.common.utils.poi.ExcelUtil;
import com.zzyl.common.core.page.TableDataInfo;

/**
 * 设备Controller
 * 
 * @author AngelaEzioHe
 * @date 2025-11-18
 */
@Api("设备管理")
@RestController
@RequestMapping("/nursing/device")
public class DeviceController extends BaseController
{
    @Autowired
    private IDeviceService deviceService;

    /**
     * 查询设备列表
     */
    @ApiOperation("查询设备列表")
    @PreAuthorize("@ss.hasPermi('nursing:device:list')")
    @GetMapping("/list")
    public TableDataInfo<List<Device>> list(@ApiParam("查询条件对象") Device device)
    {
        startPage();
        List<Device> list = deviceService.selectDeviceList(device);
        return getDataTable(list);
    }

    /**
     * 同步产品列表
     */
    @PostMapping("/syncProductList")
    @ApiOperation(value = "从物联网平台同步产品列表")
    public AjaxResult syncProductList() {
        deviceService.syncProductList();
        return success();
    }

    /**
     * 查询所有产品列表
     */
    @GetMapping("/allProduct")
    @ApiOperation(value = "查询所有产品列表")
    public R<List<ProductVo>> allProduct() {
        List<ProductVo> list = deviceService.allProduct();
        return R.ok(list);
    }

    @PostMapping("/register")
    @ApiOperation(value = "注册设备")
    public AjaxResult registerDevice(@RequestBody DeviceDto deviceDto) {
        deviceService.register(deviceDto);
        return success();
    }

    @ApiOperation("获取设备详细信息")
    @GetMapping("/{iotid}")
    public R<DeviceDetailVo> queryDeviceDetail(@PathVariable String iotid) {
        DeviceDetailVo deviceDetailVo = deviceService.queryDeviceDetail(iotid);
        return R.ok(deviceDetailVo);
    }

    @ApiOperation("查看设备上报的数据")
    @GetMapping("/queryServiceProperties/{iotId}")
    public AjaxResult queryServiceProperties(@PathVariable String iotId) {
        return deviceService.queryServiceProperties(iotId);
    }
}
