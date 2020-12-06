package com.qt.demo.system.dao;

import com.qt.demo.system.entity.Dm;
import com.qt.demo.system.entity.UricAcid;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Wang Zhen
 * @date 2020/7/3 9:37 上午
 */
@Mapper
@Repository
public interface UricAcidMapper {

    @Insert("insert into db_patient_ua(patientID, uricAcid, measureDateTime) values (#{patientID}, #{uricAcid}, #{measureDateTime})")
    void createNewUricAcidRecord(UricAcid uricAcidRecord);

    @Select("SELECT id, patientID, uricAcid, measureDateTime from db_patient_ua where patientID = #{patientID} and measureDateTime BETWEEN #{startTime} and #{endTime}")
    List<UricAcid> getUricAcidRecordsByTimeGap(String patientID, String startTime, String endTime);

    @Select("select * from db_patient_ua where patientID = #{patientID} order by id desc limit 1")
    UricAcid getPatientLatestUa(String patientID);

    @Insert("insert into db_patient_ua(patientID, uricAcid, measureDateTime) values (#{patientID}, #{uricAcid}, #{measureDateTime})")
    void createUricAcid(UricAcid uricAcid);

    @Select("select * from db_patient_ua where patientID = #{patientID} and measureDateTime between #{date} and #{now}")
    List<UricAcid> getUaFromDate(String patientID, String date, String now);
}
