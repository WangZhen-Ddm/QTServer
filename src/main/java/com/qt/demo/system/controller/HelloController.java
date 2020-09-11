package com.qt.demo.system.controller;

import com.qt.demo.system.constant.response.ResultModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Wang Zhen
 * @date 2020/7/2 4:09 下午
 */
@RestController
public class HelloController {

    @GetMapping("test/hello")
    public ResultModel<String> helloWorld() {
        ResultModel<String> result = new ResultModel<>();
        return result.sendSuccessResult("Hello world!");
    }
}
