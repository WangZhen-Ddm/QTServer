package com.qt.demo.system.controller;

import com.qt.demo.system.constant.response.ResultModel;
import com.qt.demo.system.entity.Message;
import com.qt.demo.system.service.MessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Wang Zhen
 * @date 2020/11/26 4:35 下午
 */
@RestController
@RequestMapping("api")
@Api(tags = {"消息相关"}, value = "消息相关操作接口")
public class MessageController {

    @Autowired
    MessageService messageService;

    @PostMapping("/admin/message/create")
    @ApiOperation(httpMethod = "POST", value = "消息创建", notes = "新留言")
    public ResultModel<Integer> createNewMessage(@RequestBody Message message) {
        return messageService.createMessage(message);
    }

    @PostMapping("/message/get/latest")
    @ApiOperation(httpMethod = "POST", value = "获取最近一条留言", notes = "新留言")
    public ResultModel<Message> getLatestMessage(@ApiParam(value="用户名") @RequestParam String patientID) {
        return messageService.getLatestMessage(patientID);
    }
}
