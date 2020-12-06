package com.qt.demo.system.service.impl;

import com.qt.demo.system.constant.response.ResultModel;
import com.qt.demo.system.dao.MessageMapper;
import com.qt.demo.system.entity.Message;
import com.qt.demo.system.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Wang Zhen
 * @date 2020/11/26 4:28 下午
 */
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    MessageMapper messageMapper;

    @Override
    public ResultModel<Integer> createMessage(Message message) {
        ResultModel<Integer> result = new ResultModel<>();
        try {
            messageMapper.createMessage(message);
            return result.sendSuccessResult(message.getId());
        } catch (Exception e) {
            return result.sendFailedMessage(e.getMessage());
        }
    }

    @Override
    public ResultModel<Message> getLatestMessage(String patientID) {
        ResultModel<Message> result = new ResultModel<>();
        try {
            Message message = messageMapper.getPatientLatestMessage(patientID);
            return result.sendSuccessResult(message);
        } catch (Exception e) {
            return result.sendFailedMessage(e.getMessage());
        }
    }
}
