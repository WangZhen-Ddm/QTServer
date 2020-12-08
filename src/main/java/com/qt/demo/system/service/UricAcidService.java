package com.qt.demo.system.service;

import com.qt.demo.system.constant.response.ResultModel;
import com.qt.demo.system.response.Report;
import com.qt.demo.system.entity.UricAcid;
import com.qt.demo.system.response.TipResponse;

import java.util.List;

/**
 * @author Wang Zhen
 * @date 2020/7/3 9:36 上午
 */
public interface UricAcidService {

    ResultModel<String> createUricAcidRecords(List<UricAcid> uricAcidList);

    ResultModel<List<UricAcid>> getUricAcidRecordsByTimeGap(String patientID, String startTime, String endTime);

    ResultModel<UricAcid> getLatestUa(String patientID);

    ResultModel<Integer> createUricAcidRecord(UricAcid uricAcid);

    ResultModel<Report> getWeekUricAcidReport(String patientID);

    ResultModel<Report> getMonthUricAcidReport(String patientID);

    ResultModel<TipResponse> getTip(String patientID, double ua);
}
