package com.qt.demo.system.service.impl;

import com.qt.demo.system.constant.response.ResultModel;
import com.qt.demo.system.constant.utils.StringUtils;
import com.qt.demo.system.dao.TipMapper;
import com.qt.demo.system.dao.UricAcidMapper;
import com.qt.demo.system.entity.*;
import com.qt.demo.system.response.Report;
import com.qt.demo.system.response.TipResponse;
import com.qt.demo.system.service.PatientService;
import com.qt.demo.system.service.UricAcidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Wang Zhen
 * @date 2020/7/3 9:36 上午
 */
@Service
public class UricAcidServiceImpl implements UricAcidService {

    @Autowired
    UricAcidMapper uricAcidMapper;

    @Autowired
    TipMapper tipMapper;

    @Autowired
    PatientService patientService;

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

    @Override
    public ResultModel<UricAcid> getLatestUa(String patientID) {
        ResultModel<UricAcid> result = new ResultModel<>();
        try {
            return result.sendSuccessResult(uricAcidMapper.getPatientLatestUa(patientID));
        } catch (Exception e) {
            return result.sendFailedMessage(e.getMessage());
        }
    }

    @Override
    public ResultModel<Integer> createUricAcidRecord(UricAcid uricAcid) {
        ResultModel<Integer> result = new ResultModel<>();
        try {
            uricAcidMapper.createUricAcid(uricAcid);
            return result.sendSuccessResult(uricAcid.getId());
        } catch (Exception e) {
            return result.sendFailedMessage(e);
        }
    }

    @Override
    public ResultModel<Report> getWeekUricAcidReport(String patientID) {
        ResultModel<Report> result = new ResultModel<>();
        try {
            String date = StringUtils.getPastDate(7);
            String now = StringUtils.getToday();
            List<UricAcid> uas = uricAcidMapper.getUaFromDate(patientID, date, now);
            Report report = getReport(uas, patientID);
            report.setType("尿酸周报");
            return result.sendSuccessResult(report);
        } catch (Exception e) {
            return result.sendFailedMessage(e);
        }
    }

    @Override
    public ResultModel<Report> getMonthUricAcidReport(String patientID) {
        ResultModel<Report> result = new ResultModel<>();
        try {
            String date = StringUtils.getPastDate(30);
            String now = StringUtils.getToday();
            List<UricAcid> uas = uricAcidMapper.getUaFromDate(patientID, date, now);
            Report report = getReport(uas, patientID);
            getTips(report, patientID);
            report.setType("尿酸月报");
            return result.sendSuccessResult(report);
        } catch (Exception e) {
            return result.sendFailedMessage(e);
        }
    }

    private Report getReport(List<UricAcid> uas, String patientID) {
        PatientInfo patientInfo = patientService.getPatientInfo(patientID);
        int lowTarget = 149, highTarget = 416;
        if(patientInfo.getSex().equals("女")) {
            lowTarget = 89;
            highTarget = 357;
        }
        double sum = 0, average = 0, max = 0, min = 10000;
        int high = 0, normal = 0, low = 0;
        for(UricAcid ua: uas) {
            Double value = ua.getUricAcid();
            sum += value;
            max = Math.max(max, value);
            min = Math.min(min, value);
            if(value<lowTarget) {
                low++;
            } else if(value>highTarget) {
                high++;
            } else {
                normal++;
            }
        }
        average = (double)(Math.round(sum/uas.size()*10))/10;
        Report report = new Report( average, uas.size(), high, normal, low, max, min);
        return report;
    }

    //判断尿酸，0-正常，1-偏低，2-正常
    private int judgeUricAcid(double ua, PatientInfo patientInfo) {
        int lowTarget = 149, highTarget = 416;
        if(patientInfo.getSex().equals("女")) {
            lowTarget = 89;
            highTarget = 357;
        }
        if(ua<lowTarget) return 1;
        else if(ua>highTarget) return 2;
        else return 0;
    }

    public void getTips(Report report, String patientID) {
        PatientInfo patientInfo = patientService.getPatientInfo(patientID);
        List<Tip> tips = new ArrayList<>();
        boolean test = false;
        if(report.getHigh()>0) {
            if(patientInfo.getGoutType()==1) {
                tips.add(tipMapper.getTipByTagAndType(2, 1));
            }
        }
        getCommonTips(tips, patientInfo);
        report.setTest(test);
    }

    private double getBmi(int height, int weight) {
        double res = 0;
        if(height==0 || weight==0) return res;
        res = (weight * 1.0) / height / height;
        return res;
    }

    @Override
    public ResultModel<TipResponse> getTip(String patientID, double ua) {
        ResultModel<TipResponse> result = new ResultModel<>();
        try {
            TipResponse tipResponse = new TipResponse();
            List<Tip> tips = new ArrayList<>();
            PatientInfo patientInfo = patientService.getPatientInfo(patientID);
            int judge = judgeUricAcid(ua, patientInfo);
            if(judge==2) {
                if(patientInfo.getGoutType()==1) {
                    tips.add(tipMapper.getTipByTagAndType(2, 1));
                }
            }
            getCommonTips(tips, patientInfo);
            tipResponse.setTips(tips);
            return result.sendSuccessResult(tipResponse);
        } catch (Exception e) {
            return result.sendFailedMessage(e);
        }
    }

    private void getCommonTips(List<Tip> tips, PatientInfo patientInfo) {
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
    }
}
