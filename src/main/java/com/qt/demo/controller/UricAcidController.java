package com.qt.demo.controller;

import com.qt.demo.constant.response.ResultModel;
import com.qt.demo.entity.PatientInfo;
import com.qt.demo.entity.UricAcid;
import com.qt.demo.service.UricAcidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Wang Zhen
 * @date 2020/7/3 9:52 上午
 */
@RestController
@RequestMapping("/api")
public class UricAcidController {

    @Autowired
    UricAcidService uricAcidService;

    @PostMapping("/ua/create")
    public ResultModel<String> createUricAcidRecords(@RequestBody List<UricAcid> uricAcidList) {
        return uricAcidService.createUricAcidRecords(uricAcidList);
    }
}
