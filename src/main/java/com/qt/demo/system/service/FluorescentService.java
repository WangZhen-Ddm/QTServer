package com.qt.demo.system.service;

import com.qt.demo.system.constant.response.ResultModel;
import com.qt.demo.system.entity.Fluorescent;
import com.qt.demo.system.entity.Tip;

import java.util.List;

/**
 * @author Wang Zhen
 * @date 2020/11/10 3:23 下午
 */
public interface FluorescentService {

    ResultModel<String> createFluorescentRecords(List<Fluorescent> fluorescentList);

    ResultModel<List<Fluorescent>> getFluorescentRecordsByTimeGap(String patientID, String startTime, String endTime);

    ResultModel<List<Tip>> getTips(double up, String patientID);
}
