package com.qt.demo.system.service;

import com.qt.demo.system.constant.response.ResultModel;
import com.qt.demo.system.entity.Dm;
import com.qt.demo.system.entity.Ogtt;
import com.qt.demo.system.response.TipResponse;
import com.qt.demo.system.response.Report;

import java.util.List;

/**
 * @author Wang Zhen
 * @date 2020/11/28 7:11 下午
 */
public interface DmService {

    ResultModel<Integer> createDm(Dm dm);

    ResultModel<Dm> getLatestDm(String patientID);

    ResultModel<List<Dm>> getWeekDm(String patientID);

    ResultModel<Report> getWeekDmReport(String patientID);

    ResultModel<Report> getMonthDmReport(String patientID);

    ResultModel<Ogtt> getOgttTestResult(Ogtt ogtt);

    ResultModel<List<Dm>> getDmByTimePoint(String patientID, int timePoint);

    ResultModel<TipResponse> getTip(String patientID, int timePoint, double dm);
}
