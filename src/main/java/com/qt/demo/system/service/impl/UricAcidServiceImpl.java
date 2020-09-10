package com.qt.demo.system.service.impl;

import com.qt.demo.system.constant.response.ResultModel;
import com.qt.demo.system.dao.UricAcidMapper;
import com.qt.demo.system.entity.UricAcid;
import com.qt.demo.system.service.UricAcidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author Wang Zhen
 * @date 2020/7/3 9:36 上午
 */
@Service
public class UricAcidServiceImpl implements UricAcidService {

    @Autowired
    UricAcidMapper uricAcidMapper;

    @Override
    public ResultModel<String> createUricAcidRecords(List<UricAcid> uricAcidList) {
        ResultModel<String> result = new ResultModel<>();
        for(UricAcid uricAcid: uricAcidList) {
            uricAcidMapper.createNewUricAcidRecord(uricAcid);
        }
        return result.sendSuccessResult("Creating new uric acid records successfully");
    }

    @Override
    public ResultModel<List<UricAcid>> getUricAcidRecordsByTimeGap(String patientID, String startTime, String endTime) {
        ResultModel<List<UricAcid>> result = new ResultModel<>();
        try {
            return result.sendSuccessResult(uricAcidMapper.getUricAcidRecordsByTimeGap(patientID, startTime, endTime));
        } catch (Exception e) {
            return result.sendFailedMessage(e.getMessage());
        }
    }
}
