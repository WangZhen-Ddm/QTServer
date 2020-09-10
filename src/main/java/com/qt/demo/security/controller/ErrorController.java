package com.qt.demo.security.controller;

import com.qt.demo.system.constant.response.ResultModel;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author Wang Zhen
 * @date 2020/5/27 10:22 上午
 * @description 用于捕获filter层异常
 */
@RestController
public class ErrorController extends BasicErrorController {

    public ErrorController(ServerProperties serverProperties) {
        super(new DefaultErrorAttributes(), serverProperties.getError());
    }

    @Override
    @RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity error(HttpServletRequest request) {
        Map<String, Object> body = getErrorAttributes(request, isIncludeStackTrace(request, MediaType.ALL));
        HttpStatus status = getStatus(request);

        ResultModel<String> result = new ResultModel();
        result.setMessage(body.get("message").toString());
        if(result.getMessage().equals("Bad credentials")) {
            result.setMessage("密码错误！");
        }
        result.setStatus(status.value());
        return new ResponseEntity<>(result,status);
    }
}
