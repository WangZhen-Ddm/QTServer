package com.qt.demo.system.controller;

import com.qt.demo.system.constant.response.ResultModel;
import com.qt.demo.system.entity.Fluorescent;
import com.qt.demo.system.entity.Tip;
import com.qt.demo.system.service.FluorescentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Wang Zhen
 * @date 2020/11/10 2:56 下午
 */
@RestController
@RequestMapping("api/fluorescent")
@Api(tags = {"免疫荧光"}, value = "免疫荧光相关操作接口")
public class FluorescentController {

    @Autowired
    FluorescentService fluorescentService;

    @PostMapping("/create")
    @ApiOperation(httpMethod = "POST", value = "新建免疫荧光记录", notes = "QT端上传免疫荧光记录")
    public ResultModel<String> createFluorescentRecords(@RequestBody List<Fluorescent> fluorescentList) {
        return fluorescentService.createFluorescentRecords(fluorescentList);
    }

    @PostMapping("/get/by/time")
    @ApiOperation(httpMethod = "POST", value = "获取免疫荧光记录", notes = "获取一段时间内的免疫荧光记录")
    public ResultModel<List<Fluorescent>> getFluorescentRecordsByTimeGap(@RequestParam String patientID,
                                                                        @RequestParam String startTime,
                                                                        @RequestParam String endTime) {
        return fluorescentService.getFluorescentRecordsByTimeGap(patientID, startTime, endTime);
    }

    @PostMapping("/get/tips")
    @ApiOperation(httpMethod = "POST", value = "获取最近建议", notes = "")
    public ResultModel<List<Tip>> getTips(@ApiParam(value="用户名") @RequestParam String patientID,
                                          @ApiParam(value="尿蛋白") @RequestParam Double up) {
        return fluorescentService.getTips(up, patientID);
    }
}
