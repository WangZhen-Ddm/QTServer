package com.qt.demo.system.service;

import com.qt.demo.system.entity.User;
import com.qt.demo.system.entity.UserRole;

import java.util.List;

/**
 * @author Wang Zhen
 * @date 2020/9/10 10:29 上午
 */
public interface UserService  {

    User findUserByUsername(String username);

    void save(User user, boolean isAdmin);

    List<UserRole> getUserRolesByUsername(String username);
}
