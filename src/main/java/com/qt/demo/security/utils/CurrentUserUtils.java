package com.qt.demo.security.utils;

import com.qt.demo.system.entity.User;
import com.qt.demo.system.entity.UserRole;
import com.qt.demo.system.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * @author shuang.kou
 * @description 获取当前请求的用户
 */
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CurrentUserUtils {

    @Autowired
    UserService userService;

    public static CurrentUserUtils currentUserUtils;

    @PostConstruct
    public void init() {
        currentUserUtils = this;
        currentUserUtils.userService = this.userService;
    }

    public User getCurrentUser() {
        return userService.findUserByUsername(getCurrentUserName());
    }

    private static String getCurrentUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() != null) {
            return (String) authentication.getPrincipal();
        }
        return null;
    }

    public static List<SimpleGrantedAuthority> getCurrentUserRoles(String username) {
        List<UserRole> userRoles = currentUserUtils.userService.getUserRolesByUsername(username);
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        userRoles.forEach(userRole -> authorities.add(new SimpleGrantedAuthority("ROLE_" + userRole.getRole())));
        return authorities;
    }
}
