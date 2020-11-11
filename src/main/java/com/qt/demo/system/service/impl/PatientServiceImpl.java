package com.qt.demo.system.service.impl;

import com.qt.demo.system.constant.response.ResultModel;
import com.qt.demo.system.constant.utils.StringUtils;
import com.qt.demo.system.dao.PatientInfoMapper;
import com.qt.demo.system.entity.PatientInfo;
import com.qt.demo.system.entity.User;
import com.qt.demo.system.service.PatientService;
import com.qt.demo.system.service.UserService;
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

    @Autowired
    UserService userService;

    private boolean checkPatientExist(String patientID) {
        PatientInfo patient = this.getPatientInfo(patientID);
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
                registerPatientUser(patientInfo.getPatientID());

                return result.sendSuccessResult("Creating patient successfully");
            } catch (Exception e) {
                return result.sendFailedMessage(e.getMessage());
            }
        }
    }

    private void registerPatientUser(String patientID) {
        User user = new User();
        user.setUsername(patientID);
        user.setPassword("123456");
        userService.save(user, false);
    }

    @Override
    public ResultModel<PatientInfo> patientLogin(String patientID) {
        ResultModel<PatientInfo> result = new ResultModel<>();
        try {
            PatientInfo patient = this.getPatientInfo(patientID);
            return result.sendSuccessResult(patient);
        } catch (Exception e) {
            return result.sendFailedMessage(e.getMessage());
        }
    }

    @Override
    public PatientInfo getPatientInfo(String patientID) {
        return patientInfoMapper.selectPatientByPatientID(patientID);
    }
}
