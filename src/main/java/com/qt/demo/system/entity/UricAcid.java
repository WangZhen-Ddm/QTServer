package com.qt.demo.system.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author Wang Zhen
 * @date 2020/7/1 10:53 上午
 */
@Data
public class UricAcid {

    private int id;

    private String patientID;

    private double uricAcid;

    private Date measureDateTime;
}
