package com.qt.demo.security.dto;

import lombok.Data;

/**
 * @author Wang Zhen
 * @date 2020-05-22 10:51
 */
@Data
public class LoginRequest {
    private String username;
    private String password;
}
