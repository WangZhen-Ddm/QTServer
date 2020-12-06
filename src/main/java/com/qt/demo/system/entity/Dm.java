package com.qt.demo.system.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author Wang Zhen
 * @date 2020/11/28 7:07 下午
 */
@Data
public class Dm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String patientID;

    private float dm;

    private Integer timePoint;

    private Date measureDateTime;

    private Date uploadDateTime;
}
