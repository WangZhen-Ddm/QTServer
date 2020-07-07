package com.qt.demo.entity;

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
    private int id;

    private int patientID;

    private String name;

    private int age;

    private int height;

    private int weight;

    private String sex;

    private String birthPlace;

    private String illnessHistory;

    private String allergen;

    private String bloodType;

    private String smokeHistory;

    private String drinkHistory;

}
