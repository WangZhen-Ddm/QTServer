package com.qt.demo.system.service.impl;

import com.qt.demo.system.dao.UserMapper;
import com.qt.demo.system.entity.User;
import com.qt.demo.system.entity.UserRole;
import com.qt.demo.system.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Wang Zhen
 * @date 2020/9/10 10:28 上午
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userMapper.createUser(user);
    }

    @Override
    public User findUserByUsername(String username) {
        return userMapper.findUserByUsername(username);
    }

    @Override
    public List<UserRole> getUserRolesByUsername(String username) {
        List<UserRole> userRoles = userMapper.getUserRoleByUserName(username);
        return userRoles;
    }
}