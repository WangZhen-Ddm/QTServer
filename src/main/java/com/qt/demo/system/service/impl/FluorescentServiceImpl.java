package com.qt.demo.system.service.impl;

import com.qt.demo.system.constant.response.ResultModel;
import com.qt.demo.system.dao.FluorescentMapper;
import com.qt.demo.system.dao.TipMapper;
import com.qt.demo.system.entity.Fluorescent;
import com.qt.demo.system.entity.PatientInfo;
import com.qt.demo.system.entity.Tip;
import com.qt.demo.system.service.FluorescentService;
import com.qt.demo.system.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Wang Zhen
 * @date 2020/11/10 3:23 下午
 */
@Service
public class FluorescentServiceImpl implements FluorescentService {

    @Autowired
    FluorescentMapper fluorescentMapper;

    @Autowired
    PatientService patientService;

    @Autowired
    TipMapper tipMapper;

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

    @Override
    public ResultModel<List<Tip>> getTips(double up, String patientID) {
        ResultModel<List<Tip>> result = new ResultModel<>();
        try {
            PatientInfo patientInfo = patientService.getPatientInfo(patientID);
            List<Tip> tips = new ArrayList<>();
            if(up>20) {
                tips.add(tipMapper.getTipByTagAndType(6, 1));
            }
            if(patientInfo.getSmokeHistory().equals("是")) {
                tips.add(tipMapper.getTipByTagAndType(4, 1));
            }
            if(patientInfo.getDrinkHistory().equals("是")) {
                tips.add(tipMapper.getTipByTagAndType(5, 1));
            }
            if(getBmi(patientInfo.getHeight(), patientInfo.getWeight())>24) {
                tips.add(tipMapper.getTipByTagAndType(3, 1));
            }
            if(tips.size()==0) tips.add(tipMapper.getTipByTagAndType(0, 0));
            return result.sendSuccessResult(tips);
        } catch (Exception e) {
            return result.sendFailedMessage(e);
        }
    }

    private double getBmi(int height, int weight) {
        double res = 0;
        if(height==0 || weight==0) return res;
        res = (weight * 1.0) / height / height;
        return res;
    }
}
