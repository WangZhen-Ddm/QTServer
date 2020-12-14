package com.qt.demo.system.controller;

import com.qt.demo.system.constant.response.ResultModel;
import com.qt.demo.system.entity.PatientInfo;
import com.qt.demo.system.service.AdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Wang Zhen
 * @date 2020/11/11 3:54 下午
 */
@RestController
@RequestMapping("api/admin")
@Api(tags = {"管理员"}, value = "管理员相关操作接口")
public class AdminController {

    @Autowired
    AdminService adminService;

    @RequestMapping(value="/patient/list", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ApiOperation(httpMethod = "POST", value = "获取患者列表", notes = "管理员登录获取患者列表")
    public ResultModel<List<PatientInfo>> getPatientList(){
        return adminService.getPatientInfoList();
    }
}
