package com.qt.demo.system.controller;

import com.qt.demo.system.constant.response.ResultModel;
import com.qt.demo.system.entity.PatientInfo;
import com.qt.demo.system.service.PatientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Wang Zhen
 * @date 2020/7/1 2:22 下午
 */
@RestController
@RequestMapping("api/patient")
@Api(tags = {"患者"}, value = "患者相关操作接口")
public class PatientController {

    @Autowired
    PatientService patientService;

    @PostMapping("/create")
    @ApiOperation(httpMethod = "POST", value = "患者创建", notes = "创建新患者")
    public ResultModel<String> findPersonByPatientId(@RequestBody PatientInfo patientInfo) {
        return patientService.createNewPatient(patientInfo);
    }

    @RequestMapping(value="/wx/info", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ApiOperation(httpMethod = "POST", value = "获取患者信息", notes = "获取患者信息")
    public ResultModel<PatientInfo> getPatientInfo(@ApiParam(value="用户名") @RequestParam String patientID){
        return patientService.patientLogin(patientID);
    }
}
