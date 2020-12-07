package com.qt.demo.system.dao;

import com.qt.demo.system.entity.Dm;
import com.qt.demo.system.entity.Message;
import com.qt.demo.system.entity.Ogtt;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author Wang Zhen
 * @date 2020/11/28 7:09 下午
 */
@Mapper
@Repository
public interface DmMapper {

    @Insert("insert into db_patient_dm(patientID, dm, timePoint, measureDateTime) values (#{patientID}, #{dm}, #{timePoint}, #{measureDateTime})")
    void createDm(Dm dm);

    @Select("select * from db_patient_dm where patientID = #{patientID} order by id desc limit 1")
    Dm getPatientLatestDm(String patientID);

    @Select("select * from db_patient_dm where patientID = #{patientID} and measureDateTime between #{date} and #{now}")
    List<Dm> getDmFromDate(String patientID, String date, String now);

    @Insert("insert into db_patient_ogtt(patientID, result, risk) values (#{patientID}, #{result}, #{risk})")
    void createOgtt(Ogtt ogtt);

    @Select("select * from db_patient_dm where patientID = #{patientID} and timePoint = #{timePoint}")
    List<Dm> getDmByTimePoint(String patientID, int timePoint);
}
