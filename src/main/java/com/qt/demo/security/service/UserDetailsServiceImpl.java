package com.qt.demo.security.service;

import com.qt.demo.security.entity.JwtUser;
import com.qt.demo.system.entity.User;
import com.qt.demo.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Wang Zhen
 * @date 2020-05-22 13:45
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserService userService;

    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findUserByUsername(username);
        if(user==null) {
            throw new UsernameNotFoundException("用户名不存在!");
        }
        return new JwtUser(user);
    }
}
