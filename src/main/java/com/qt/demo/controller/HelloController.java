package com.qt.demo.controller;

import com.qt.demo.constant.response.ResultModel;
import com.qt.demo.entity.PatientInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Wang Zhen
 * @date 2020/7/2 4:09 下午
 */
@RestController
public class HelloController {

    @GetMapping("/test/hello")
    public ResultModel<String> helloWorld() {
        ResultModel<String> result = new ResultModel<>();
        return result.sendSuccessResult("Hello world!test");
    }
}
