package com.qt.demo.system.service;

import com.qt.demo.system.constant.response.ResultModel;
import com.qt.demo.system.entity.PatientInfo;

import java.util.List;

/**
 * @author Wang Zhen
 * @date 2020/11/11 4:06 下午
 */
public interface AdminService {

    ResultModel<List<PatientInfo>> getPatientInfoList();
}
