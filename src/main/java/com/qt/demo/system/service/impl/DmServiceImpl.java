package com.qt.demo.system.service.impl;

import com.google.common.collect.Lists;
import com.qt.demo.system.constant.response.ResultModel;
import com.qt.demo.system.constant.utils.StringUtils;
import com.qt.demo.system.dao.DmMapper;
import com.qt.demo.system.dao.TipMapper;
import com.qt.demo.system.entity.*;
import com.qt.demo.system.service.DmService;
import com.qt.demo.system.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author Wang Zhen
 * @date 2020/11/28 7:11 下午
 */
@Service
public class DmServiceImpl implements DmService {

    @Autowired
    DmMapper dmMapper;

    @Autowired
    TipMapper tipMapper;

    @Autowired
    PatientService patientService;

    @Override
    public ResultModel<Integer> createDm(Dm dm) {
        ResultModel<Integer> result = new ResultModel<>();
        try {
            dmMapper.createDm(dm);
            return result.sendSuccessResult(dm.getId());
        } catch (Exception e) {
            return result.sendFailedMessage(e);
        }
    }
    @Override
    public ResultModel<Dm> getLatestDm(String patientID) {
        ResultModel<Dm> result = new ResultModel<>();
        try {
            Dm dm = dmMapper.getPatientLatestDm(patientID);
            return result.sendSuccessResult(dm);
        } catch (Exception e) {
            return result.sendFailedMessage(e.getMessage());
        }
    }

    @Override
    public ResultModel<List<Dm>> getWeekDm(String patientID) {
        ResultModel<List<Dm>> result = new ResultModel<>();
        try {
            String date = StringUtils.getPastDate(7);
            String now = StringUtils.getToday();
            List<Dm> dm = dmMapper.getDmFromDate(patientID, date, now);
            Collections.reverse(dm);
            return result.sendSuccessResult(dm);
        } catch (Exception e) {
            return result.sendFailedMessage(e.getMessage());
        }
    }

    @Override
    public ResultModel<List<Dm>> getDmByTimePoint(String patientID, int timePoint) {
        ResultModel<List<Dm>> result = new ResultModel<>();
        try {
            List<Dm> dm = dmMapper.getDmByTimePoint(patientID, timePoint);
            if(dm.size()>5) {
                dm = dm.subList(dm.size()-5, dm.size());
            }
            return result.sendSuccessResult(dm);
        } catch (Exception e) {
            return result.sendFailedMessage(e.getMessage());
        }
    }

    @Override
    public ResultModel<Report> getWeekDmReport(String patientID) {
        ResultModel<Report> result = new ResultModel<>();
        try {
            String date = StringUtils.getPastDate(7);
            String now = StringUtils.getToday();
            List<Dm> dms = dmMapper.getDmFromDate(patientID, date, now);
            Report report = getReport(dms);
            report.setType("血糖周报");
            return result.sendSuccessResult(report);
        } catch (Exception e) {
            return result.sendFailedMessage(e);
        }
    }

    @Override
    public ResultModel<Report> getMonthDmReport(String patientID) {
        ResultModel<Report> result = new ResultModel<>();
        try {
            String date = StringUtils.getPastDate(30);
            String now = StringUtils.getToday();
            List<Dm> dms = dmMapper.getDmFromDate(patientID, date, now);
            Report report = getReport(dms);
            getTipsAndTest(report, patientID);
            report.setType("血糖月报");
            return result.sendSuccessResult(report);
        } catch (Exception e) {
            return result.sendFailedMessage(e);
        }
    }

    private Report getReport(List<Dm> dms) {
        double average = 0, sum = 0, max = 0, min = 100, sum0 = 0, sum1 = 0, sum2 = 0, sum3 = 0;
        int count0 = 0, count1 = 0, count2 = 0, count3 = 0, high = 0, low = 0, normal = 0;
        Map<Integer, Double> averageTimePoints = new HashMap();
        for(Dm dm: dms) {
            double value = dm.getDm();
            sum += value;
            max = Math.max(max, value);
            min = Math.min(min, value);
            if(dm.getTimePoint()==0) {
                sum0 += value;
                count0++;
            } else if(dm.getTimePoint()==1) {
                sum1 += value;
                count1++;
            } else if(dm.getTimePoint()==2) {
                sum2 += value;
                count2++;
            } else if(dm.getTimePoint()==3) {
                sum3 += value;
                count3++;
            }
            int judge = judgeDm(dm.getTimePoint(), value);
            if(judge==0) normal++;
            else if(judge==1) low++;
            else if(judge==2) high++;
        }
        if(count0 != 0) {
            averageTimePoints.put(0, (double)(Math.round(sum0/count0*10))/10);
        }
        if(count1 != 0) {
            averageTimePoints.put(1, (double)(Math.round(sum1/count1*10))/10);
        }
        if(count2 != 0) {
            averageTimePoints.put(2, (double)(Math.round(sum2/count2*10))/10);
        }
        if(count3 != 0) {
            averageTimePoints.put(3, (double)(Math.round(sum3/count3*10))/10);
        }
        average = (double)(Math.round(sum/dms.size()*10))/10;
        Report report = new Report ((double)(Math.round(average*10))/10, dms.size(), high, normal, low, max, min, averageTimePoints);
        return report;
    }

    //判断血糖，0-正常，1-偏低，2-偏高
    private int judgeDm(int timePoint, double dm) {
        if(timePoint==0) {
            if(dm<4.4) return 1;
            else if(dm>8) return 2;
            else return 0;
        } else if(timePoint==1) {
            if(dm<4.4) return 1;
            else if(dm>8) return 2;
            else return 0;
        } else if(timePoint==2) {
            if(dm<4.4) return 1;
            else if(dm>11) return 2;
            else return 0;
        } else {
            if(dm<4.4) return 1;
            else if(dm>11) return 2;
            else return 0;
        }
    }

    @Override
    public ResultModel<Ogtt> getOgttTestResult(Ogtt ogtt) {
        ResultModel<Ogtt> result = new ResultModel<>();
        try {
            if(ogtt.getResult()<7.5) {
                ogtt.setRisk("低风险");//低风险
            } else {
                ogtt.setRisk("高风险");//高风险
            }
            dmMapper.createOgtt(ogtt);
            return result.sendSuccessResult(ogtt);
        } catch (Exception e) {
            return result.sendFailedMessage(e);
        }
    }

    public void getTipsAndTest(Report report, String patientID) {
        PatientInfo patientInfo = patientService.getPatientInfo(patientID);
        List<Tip> tips = new ArrayList<>();
        boolean test = false;
        if(report.getHigh()>0) {
            if(patientInfo.getDmType()==0) {
                tips.add(tipMapper.getTipByTagAndType(1, 1));
            } else {
                tips.add(tipMapper.getTipByTagAndType(1, 2));
            }
            test = needTest(patientInfo);
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
        report.setTips(tips);
        report.setTest(test);
    }

    //判断是否需要进行糖代量测试
    private boolean needTest(PatientInfo patientInfo) {
        if(patientInfo==null) return false;
        if(patientInfo.getDmType()==0) {
            return true;
        } else return false;
    }

    private double getBmi(int height, int weight) {
        double res = 0;
        if(height==0 || weight==0) return res;
        res = (weight * 1.0) / height / height;
        return res;
    }
}
