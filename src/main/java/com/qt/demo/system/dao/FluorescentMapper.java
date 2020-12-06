package com.qt.demo.system.dao;

import com.qt.demo.system.entity.Fluorescent;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Wang Zhen
 * @date 2020/11/10 2:54 下午
 */
@Mapper
@Repository
public interface FluorescentMapper {

    @Insert("insert into db_patient_flu(patientID, fluorescent, up, measureDateTime) values (#{patientID}, #{fluorescent}, #{up}, #{measureDateTime})")
    void createNewFluorescentRecord(Fluorescent fluorescent);

    @Select("SELECT * from db_patient_flu where patientID = #{patientID} and measureDateTime BETWEEN #{startTime} and #{endTime}")
    List<Fluorescent> getFluorescentRecordsByTimeGap(String patientID, String startTime, String endTime);

}