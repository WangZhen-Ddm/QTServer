package com.qt.demo.system.service;

import com.qt.demo.system.constant.response.ResultModel;
import com.qt.demo.system.entity.Message;

/**
 * @author Wang Zhen
 * @date 2020/11/26 4:28 下午
 */
public interface MessageService {
    ResultModel<Integer> createMessage(Message message);
    ResultModel<Message> getLatestMessage(String patientID);
}
