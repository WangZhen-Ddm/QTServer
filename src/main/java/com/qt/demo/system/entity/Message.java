package com.qt.demo.system.entity;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author Wang Zhen
 * @date 2020/11/26 4:26 下午
 */
@Data
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String patientID;

    private String detail;

    private String doctorName;

    private String doctorID;

    private Date createDateTime;
}