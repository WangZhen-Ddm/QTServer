package com.qt.demo.service.impl;

import com.qt.demo.constant.response.ResultModel;
import com.qt.demo.dao.UricAcidMapper;
import com.qt.demo.entity.UricAcid;
import com.qt.demo.service.UricAcidService;
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
        return result.sendSuccessResult("Creating new uric acid records successfully!");
    }
}
