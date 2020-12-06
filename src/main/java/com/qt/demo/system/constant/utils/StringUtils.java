package com.qt.demo.system.constant.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

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

    /**
     * 获得指定日期的前一天
     * @param specifiedDay
     * @return
     * @throws Exception
     */
    public static String getSpecifiedDayBefore(String specifiedDay){
        Calendar c = Calendar.getInstance();
        Date date=null;
        try {
            date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(date);
        int day=c.get(Calendar.DATE);
        c.set(Calendar.DATE,day-1);

        String dayBefore=new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
        return dayBefore;
    }
    /**
     * 获得指定日期的后一天
     * @param specifiedDay
     * @return
     */
    public static String getSpecifiedDayAfter(String specifiedDay){
        Calendar c = Calendar.getInstance();
        Date date=null;
        try {
            date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(date);
        int day=c.get(Calendar.DATE);
        c.set(Calendar.DATE,day+1);

        String dayAfter=new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
        return dayAfter;
    }


    /**
     * 获取过去或者未来 任意天内的日期数组
     * @param intervals      intervals天内
     * @return              日期数组
     */
    public static ArrayList<String> test(int intervals ) {
        ArrayList<String> pastDaysList = new ArrayList<>();
        ArrayList<String> fetureDaysList = new ArrayList<>();
        for (int i = 0; i <intervals; i++) {
            pastDaysList.add(getPastDate(i));
            fetureDaysList.add(getFetureDate(i));
        }
        return pastDaysList;
    }

    /**
     * 获取过去第几天的日期
     *
     * @param past
     * @return
     */
    public static String getPastDate(int past) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - past);
        Date today = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String result = format.format(today);
        return result;
    }

    /**
     * 获取未来 第 past 天的日期
     * @param past
     * @return
     */
    public static String getFetureDate(int past) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + past);
        Date today = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String result = format.format(today);
        return result;
    }

    /**
     * 获取今天
     * @return
     */
    public static String getToday() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR));
        Date today = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String result = format.format(today);
        return result;
    }
}
