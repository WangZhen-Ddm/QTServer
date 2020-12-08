package com.qt.demo.system.service;

import com.qt.demo.system.constant.response.ResultModel;
import com.qt.demo.system.entity.Kno;

/**
 * @author Wang Zhen
 * @date 2020/12/8 5:14 下午
 */
public interface KnoService {

    ResultModel<Kno> getKnoByType(int type);

    ResultModel<Kno> getKnoByID(int id);
}
