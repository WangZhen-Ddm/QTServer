package com.qt.demo.system.entity;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author Wang Zhen
 * @date 2020/12/3 3:22 下午
 */
@Data
public class Ogtt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String patientID;

    private Double result;

    private String risk;

    private Date createDateTime;
}
