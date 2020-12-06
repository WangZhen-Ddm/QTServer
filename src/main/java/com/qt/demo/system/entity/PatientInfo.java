package com.qt.demo.system.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Wang Zhen
 * @date 2020/7/1 9:52 上午
 */
@Data
@Entity
public class PatientInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String patientID;

    private String name;

    private Integer age;

    private Integer height;

    private Integer weight;

    private String sex;

    private String birthPlace;

    private String illnessHistory;

    private String allergen;

    private String bloodType;

    private String smokeHistory;

    private String drinkHistory;

    private String password;

    private String sports;

    private Integer dmType;

    private Integer goutType;

    private String idCard;

    private String phoneNumber;

    private Integer status;

}
