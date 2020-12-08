package com.qt.demo.system.controller;

import com.qt.demo.system.constant.response.ResultModel;
import com.qt.demo.system.entity.Dm;
import com.qt.demo.system.entity.Ogtt;
import com.qt.demo.system.response.TipResponse;
import com.qt.demo.system.response.Report;
import com.qt.demo.system.service.DmService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Wang Zhen
 * @date 2020/11/28 7:12 下午
 */
@RestController
@RequestMapping("api/dm")
@Api(tags = {"糖尿病相关"}, value = "糖尿病相关操作接口")
public class DmController {

    @Autowired
    DmService dmService;

    @PostMapping("/create")
    @ApiOperation(httpMethod = "POST", value = "糖尿病数据创建", notes = "新数据")
    public ResultModel<Integer> createNewDmRecord(@RequestBody Dm dm) {
        return dmService.createDm(dm);
    }

    @PostMapping("/get/latest")
    @ApiOperation(httpMethod = "POST", value = "获取最近一条血糖数据", notes = "")
    public ResultModel<Dm> getLatestDm(@ApiParam(value="用户名") @RequestParam String patientID) {
        return dmService.getLatestDm(patientID);
    }

    @PostMapping("/get/week")
    @ApiOperation(httpMethod = "POST", value = "获取最近一周的血糖数据", notes = "")
    public ResultModel<List<Dm>> getWeekDm(@ApiParam(value="用户名") @RequestParam String patientID) {
        return dmService.getWeekDm(patientID);
    }

    @PostMapping("/get/week/report")
    @ApiOperation(httpMethod = "POST", value = "获取最近一周的血糖数据报告", notes = "")
    public ResultModel<Report> getWeekDmReport(@ApiParam(value="用户名") @RequestParam String patientID) {
        return dmService.getWeekDmReport(patientID);
    }

    @PostMapping("/get/month/report")
    @ApiOperation(httpMethod = "POST", value = "获取最近一月的血糖数据报告", notes = "")
    public ResultModel<Report> getMonthDmReport(@ApiParam(value="用户名") @RequestParam String patientID) {
        return dmService.getMonthDmReport(patientID);
    }

    @PostMapping("/ogtt/test")
    @ApiOperation(httpMethod = "POST", value = "提交糖尿量测试结果，获取糖尿病风险", notes = "")
    public ResultModel<Ogtt> getOgttTestResult(@ApiParam(value="用户名") @RequestBody Ogtt ogtt) {
        return dmService.getOgttTestResult(ogtt);
    }

    @PostMapping("/get/by/time/point")
    @ApiOperation(httpMethod = "POST", value = "根据录入时间点获取血糖数据，最多5条", notes = "")
    public ResultModel<List<Dm>> getDmByTimePoint(@ApiParam(value="用户名") @RequestParam String patientID,
                                                  @ApiParam(value="时间点") @RequestParam Integer timePoint) {
        return dmService.getDmByTimePoint(patientID, timePoint);
    }

    @PostMapping("/get/tip/by/dm")
    @ApiOperation(httpMethod = "POST", value = "根据血糖获取建议", notes = "")
    public ResultModel<TipResponse> getDmByTimePoint(@ApiParam(value="用户名") @RequestParam String patientID,
                                                     @ApiParam(value="时间点") @RequestParam Integer timePoint,
                                                     @ApiParam(value="血糖值") @RequestParam Double dm) {
        return dmService.getTip(patientID, timePoint, dm);
    }
}
