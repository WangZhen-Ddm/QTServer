package com.qt.demo.service;

import com.qt.demo.constant.response.ResultModel;
import com.qt.demo.entity.UricAcid;

import java.util.Date;
import java.util.List;

/**
 * @author Wang Zhen
 * @date 2020/7/3 9:36 上午
 */
public interface UricAcidService {

    ResultModel<String> createUricAcidRecords(List<UricAcid> uricAcidList);

    ResultModel<List<UricAcid>> getUricAcidRecordsByTimeGap(int patientID, String startTime, String endTime);
}
