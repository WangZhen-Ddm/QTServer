package com.qt.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author Wang Zhen
 * @date 2020/7/1 10:53 上午
 */
@Data
public class UricAcid {

    private int id;

    private int patientID;

    private double uricAcid;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd hh:mm:ss")
    private Date measureDateTime;
}
