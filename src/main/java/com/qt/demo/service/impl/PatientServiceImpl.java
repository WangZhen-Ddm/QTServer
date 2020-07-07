package com.qt.demo.service.impl;

import com.qt.demo.constant.response.ResultModel;
import com.qt.demo.dao.PatientInfoMapper;
import com.qt.demo.entity.PatientInfo;
import com.qt.demo.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Wang Zhen
 * @date 2020/7/1 11:02 上午
 */
@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    PatientInfoMapper patientInfoMapper;

    private boolean checkPatientExist(int patientID) {
        PatientInfo patient = patientInfoMapper.selectPatientByPatientID(patientID);
        if(patient==null) return false;
        else return true;
    }

    @Override
    public ResultModel<String> createNewPatient(PatientInfo patientInfo) {
        ResultModel<String> result = new ResultModel<>();
        boolean exist = checkPatientExist(patientInfo.getPatientID());
        if(exist) {
            return result.sendFailedMessage("Patient already exists！");
        } else {
            try {
                patientInfoMapper.createPerson(patientInfo);
                return result.sendSuccessResult("Creating patient successfully");
            } catch (Exception e) {
                return result.sendFailedMessage(e.getMessage());
            }
        }
    }
}
