package com.qt.demo.controller;

import com.qt.demo.constant.response.ResultModel;
import com.qt.demo.entity.PatientInfo;
import com.qt.demo.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Wang Zhen
 * @date 2020/7/1 2:22 下午
 */
@RestController
@RequestMapping("/api")
public class PatientController {

    @Autowired
    PatientService patientService;

    @PostMapping("/patient/create")
    public ResultModel<String> findPersonByPatientId(@RequestBody PatientInfo patientInfo) {
        return patientService.createNewPatient(patientInfo);
    }
}
