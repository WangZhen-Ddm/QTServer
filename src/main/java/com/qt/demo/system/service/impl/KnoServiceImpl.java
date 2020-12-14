package com.qt.demo.system.service.impl;

import com.qt.demo.system.constant.response.ResultModel;
import com.qt.demo.system.dao.KnoMapper;
import com.qt.demo.system.entity.Kno;
import com.qt.demo.system.service.KnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Wang Zhen
 * @date 2020/12/8 5:14 下午
 */
@Service
public class KnoServiceImpl implements KnoService {

    @Autowired
    KnoMapper knoMapper;

    @Override
    public ResultModel<Kno> getKnoByType(int type) {
        ResultModel<Kno> result = new ResultModel<>();
        try {
            Kno kno = knoMapper.getKnoByType(type);
            return result.sendSuccessResult(kno);
        } catch (Exception e) {
            return result.sendFailedMessage(e);
        }
    }

    @Override
    public ResultModel<Kno> getKnoByID(int id) {
        ResultModel<Kno> result = new ResultModel<>();
        try {
            Kno kno = knoMapper.getKnoByID(id);
            return result.sendSuccessResult(kno);
        } catch (Exception e) {
            return result.sendFailedMessage(e);
        }
    }

}
