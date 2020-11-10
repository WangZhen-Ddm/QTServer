package com.qt.demo.system.service.impl;

import com.qt.demo.system.constant.response.ResultModel;
import com.qt.demo.system.dao.FluorescentMapper;
import com.qt.demo.system.entity.Fluorescent;
import com.qt.demo.system.service.FluorescentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Wang Zhen
 * @date 2020/11/10 3:23 下午
 */
@Service
public class FluorescentServiceImpl implements FluorescentService {

    @Autowired
    FluorescentMapper fluorescentMapper;

    @Override
    public ResultModel<String> createFluorescentRecords(List<Fluorescent> fluorescentList) {
        ResultModel<String> result = new ResultModel<>();
        for(Fluorescent fluorescent : fluorescentList) {
            fluorescentMapper.createNewFluorescentRecord(fluorescent);
        }
        return result.sendSuccessResult("Creating new fluorescent records successfully");
    }

    @Override
    public ResultModel<List<Fluorescent>> getFluorescentRecordsByTimeGap(String patientID, String startTime, String endTime) {
        ResultModel<List<Fluorescent>> result = new ResultModel<>();
        try {
            return result.sendSuccessResult(fluorescentMapper.getFluorescentRecordsByTimeGap(patientID, startTime, endTime));
        } catch (Exception e) {
            return result.sendFailedMessage(e.getMessage());
        }
    }
}
