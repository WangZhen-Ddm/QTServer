package com.qt.demo.system.controller;

import com.qt.demo.system.constant.response.ResultModel;
import com.qt.demo.system.entity.UricAcid;
import com.qt.demo.system.service.UricAcidService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Wang Zhen
 * @date 2020/7/3 9:52 上午
 */
@RestController
@RequestMapping("/api/ua")
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
}
