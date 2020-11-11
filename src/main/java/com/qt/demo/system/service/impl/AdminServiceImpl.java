package com.qt.demo.system.service.impl;

import com.qt.demo.system.constant.response.ResultModel;
import com.qt.demo.system.dao.PatientInfoMapper;
import com.qt.demo.system.entity.PatientInfo;
import com.qt.demo.system.service.AdminService;
import com.qt.demo.system.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Wang Zhen
 * @date 2020/11/11 4:06 下午
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    PatientInfoMapper patientInfoMapper;

    @Override
    public ResultModel<List<PatientInfo>> getPatientInfoList() {
        ResultModel<List<PatientInfo>> result = new ResultModel<>();
        try {
            return result.sendSuccessResult(patientInfoMapper.getPatientInfoList());
        } catch (Exception e) {
            return result.sendFailedMessage(e.getMessage());
        }
    }
}
