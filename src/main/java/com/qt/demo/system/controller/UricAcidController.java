package com.qt.demo.system.controller;

import com.qt.demo.system.constant.response.ResultModel;
import com.qt.demo.system.response.Report;
import com.qt.demo.system.entity.UricAcid;
import com.qt.demo.system.response.TipResponse;
import com.qt.demo.system.service.UricAcidService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Wang Zhen
 * @date 2020/7/3 9:52 上午
 */
@RestController
@RequestMapping("api/ua")
@Api(tags = {"尿酸"}, value = "尿酸相关操作接口")
public class UricAcidController {

    @Autowired
    UricAcidService uricAcidService;

    @PostMapping("/create")
    @ApiOperation(httpMethod = "POST", value = "新建尿酸记录", notes = "QT端上传尿酸记录")
    public ResultModel<String> createUricAcidRecords(@RequestBody List<UricAcid> uricAcidList) {
        return uricAcidService.createUricAcidRecords(uricAcidList);
    }

    @PostMapping("/get/by/time")
    @ApiOperation(httpMethod = "POST", value = "获取尿酸记录", notes = "获取一段时间内的尿酸记录")
    public ResultModel<List<UricAcid>> getUricAcidRecordsByTimeGap(@RequestParam String patientID,
                                                                   @RequestParam String startTime,
                                                                   @RequestParam String endTime) {
        return uricAcidService.getUricAcidRecordsByTimeGap(patientID, startTime, endTime);
    }

    @PostMapping("/get/latest")
    @ApiOperation(httpMethod = "POST", value = "获取最近一条尿酸数据", notes = "")
    public ResultModel<UricAcid> getLatestUa(@ApiParam(value="用户名") @RequestParam String patientID) {
        return uricAcidService.getLatestUa(patientID);
    }

    @PostMapping("/create/single")
    @ApiOperation(httpMethod = "POST", value = "糖尿病数据创建", notes = "新数据")
    public ResultModel<Integer> createNewUricAcidRecord(@RequestBody UricAcid uricAcid) {
        return uricAcidService.createUricAcidRecord(uricAcid);
    }

    @PostMapping("/get/week/report")
    @ApiOperation(httpMethod = "POST", value = "获取最近一周的尿酸数据报告", notes = "")
    public ResultModel<Report> getWeekUricAcidReport(@ApiParam(value="用户名") @RequestParam String patientID) {
        return uricAcidService.getWeekUricAcidReport(patientID);
    }

    @PostMapping("/get/month/report")
    @ApiOperation(httpMethod = "POST", value = "获取最近一月的尿酸数据报告", notes = "")
    public ResultModel<Report> getMonthUricAcidReport(@ApiParam(value="用户名") @RequestParam String patientID) {
        return uricAcidService.getMonthUricAcidReport(patientID);
    }

    @PostMapping("/get/tip/by/ua")
    @ApiOperation(httpMethod = "POST", value = "根据血糖获取建议", notes = "")
    public ResultModel<TipResponse> getUaTip(@ApiParam(value="用户名") @RequestParam String patientID,
                                             @ApiParam(value="尿酸值") @RequestParam Double ua) {
        return uricAcidService.getTip(patientID, ua);
    }
}
