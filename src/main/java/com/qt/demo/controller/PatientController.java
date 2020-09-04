package com.qt.demo.controller;

import com.qt.demo.constant.response.ResultModel;
import com.qt.demo.entity.PatientInfo;
import com.qt.demo.service.PatientService;
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
@RequestMapping("/api/patient")
@Api(tags = {"患者"}, value = "患者相关操作接口")
public class PatientController {

    @Autowired
    PatientService patientService;

    @PostMapping("/create")
    @ApiOperation(httpMethod = "POST", value = "患者创建", notes = "创建新患者")
    public ResultModel<String> findPersonByPatientId(@RequestBody PatientInfo patientInfo) {
        return patientService.createNewPatient(patientInfo);
    }

    @RequestMapping(value="/login", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ApiOperation(httpMethod = "POST", value = "患者登录", notes = "验证患者用户名密码以供患者登录")
    public ResultModel<PatientInfo> patientLogin(@ApiParam(value="用户名") @RequestParam int patientID,
                                                 @ApiParam(value="密码") @RequestParam String password){
        return patientService.patientLogin(patientID, password);
    }
}
