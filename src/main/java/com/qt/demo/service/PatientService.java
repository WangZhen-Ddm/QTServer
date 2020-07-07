package com.qt.demo.service;

import com.qt.demo.constant.response.ResultModel;
import com.qt.demo.entity.PatientInfo;

/**
 * @author Wang Zhen
 * @date 2020/7/1 11:03 上午
 */
public interface PatientService {

    ResultModel<String> createNewPatient(PatientInfo patientInfo);
}
