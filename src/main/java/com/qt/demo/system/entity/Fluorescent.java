package com.qt.demo.system.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author Wang Zhen
 * @date 2020/11/10 2:53 下午
 */
@Data
public class Fluorescent {

    private int id;

    private String patientID;

    private String fluorescent;

    private Double up;

    private Date measureDateTime;
}
