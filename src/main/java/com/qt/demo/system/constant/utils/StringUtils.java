package com.qt.demo.system.constant.utils;

/**
 * @author Wang Zhen
 * @date 2020/9/3 3:31 下午
 */
public class StringUtils {

    public static boolean stringIsBlank(String str) {
        if (str == null || str.trim().isEmpty()) {
            return true;
        }
        return false;
    }
}
