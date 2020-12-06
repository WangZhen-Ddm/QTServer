package com.qt.demo.system.entity;

import io.swagger.models.auth.In;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author Wang Zhen
 * @date 2020/12/2 4:35 下午
 */
@Data
public class Report {

    //类型，包括"月报"和"周报"
    private String type;

    //平均值
    private Double average;

    //次数
    private Integer times;

    //超标次数
    private Integer high;

    //正常次数
    private Integer normal;

    //过低次数
    private Integer low;

    //最大值
    private Double max;

    //最小值
    private Double min;

    //不同时间点对应的平均值
    private Map<Integer, Double> averageTimePoints;

    private List<Tip> tips;

    private Boolean test;

    public Report() {
    }

    public Report(Double average, Integer times, Integer high, Integer normal, Integer low, Double max, Double min, Map<Integer, Double> averageTimePoints) {
        this.average = average;
        this.times = times;
        this.high = high;
        this.normal = normal;
        this.low = low;
        this.max = max;
        this.min = min;
        this.averageTimePoints = averageTimePoints;
    }

    public Report(Double average, Integer times, Integer high, Integer normal, Integer low, Double max, Double min) {
        this.average = average;
        this.times = times;
        this.high = high;
        this.normal = normal;
        this.low = low;
        this.max = max;
        this.min = min;
    }
}
