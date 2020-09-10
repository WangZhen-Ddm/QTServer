package com.qt.demo.system.service;

import com.qt.demo.system.constant.response.ResultModel;
import com.qt.demo.system.entity.UricAcid;

import java.util.List;

/**
 * @author Wang Zhen
 * @date 2020/7/3 9:36 上午
 */
public interface UricAcidService {

    ResultModel<String> createUricAcidRecords(List<UricAcid> uricAcidList);

    ResultModel<List<UricAcid>> getUricAcidRecordsByTimeGap(String patientID, String startTime, String endTime);
}
