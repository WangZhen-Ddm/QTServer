package com.qt.demo.system.dao;

import com.qt.demo.system.entity.PatientInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Wang Zhen
 * @date 2020/7/1 11:27 上午
 */
@Mapper
@Repository
public interface PatientInfoMapper {

    @Insert("insert into db_patient_info(patientID, name, age, height, weight, sex, birthPlace, illnessHistory, allergen, bloodType, smokeHistory, drinkHistory) " +
            "values (#{patientID}, #{name}, #{age}, #{height}, #{weight}, #{sex}, #{birthPlace}, #{illnessHistory}, #{allergen}, #{bloodType}, #{smokeHistory}, #{drinkHistory})")
    void createPerson(PatientInfo patientInfo);

    @Select("select * from db_patient_info where patientID = #{patientID}")
    PatientInfo selectPatientByPatientID(String patientID);

    @Select("select * from db_patient_info where status = 0")
    List<PatientInfo> getPatientInfoList();
}
