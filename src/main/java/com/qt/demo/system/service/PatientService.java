package com.qt.demo.system.service;

import com.qt.demo.system.constant.response.ResultModel;
import com.qt.demo.system.entity.PatientInfo;

/**
 * @author Wang Zhen
 * @date 2020/7/1 11:03 上午
 */
public interface PatientService {

    ResultModel<String> createNewPatient(PatientInfo patientInfo);

    ResultModel<PatientInfo> patientLogin(String patientID);

    PatientInfo getPatientInfo(String patientID);
}
