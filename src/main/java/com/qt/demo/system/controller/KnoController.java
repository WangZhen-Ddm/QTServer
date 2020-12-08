package com.qt.demo.system.controller;

import com.qt.demo.system.constant.response.ResultModel;
import com.qt.demo.system.entity.Dm;
import com.qt.demo.system.entity.Kno;
import com.qt.demo.system.service.KnoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Wang Zhen
 * @date 2020/12/8 5:13 下午
 */
@RestController
@RequestMapping("api/kno")
@Api(tags = {"知识相关"}, value = "知识相关操作接口")
public class KnoController {

    @Autowired
    KnoService knoService;

    @PostMapping("/get/by/type")
    @ApiOperation(httpMethod = "POST", value = "根据类型获取知识", notes = "")
    public ResultModel<Kno> getRandKnoByType(@ApiParam(value="知识类型") @RequestParam Integer type) {
        return knoService.getKnoByType(type);
    }

    @PostMapping("/get/by/id")
    @ApiOperation(httpMethod = "POST", value = "根据类型获取知识", notes = "")
    public ResultModel<Kno> getknoByID(@ApiParam(value="知识id") @RequestParam Integer id) {
        return knoService.getKnoByID(id);
    }
}
