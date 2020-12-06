package com.qt.demo.system.dao;

import com.qt.demo.system.entity.Message;
import com.qt.demo.system.entity.PatientInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author Wang Zhen
 * @date 2020/11/26 4:29 下午
 */
@Mapper
@Repository
public interface MessageMapper {

    @Insert("insert into db_message(patientID, detail, doctorName, doctorID) values (#{patientID}, #{detail}, #{doctorName}, #{doctorID})")
    void createMessage(Message message);

    @Select("select * from db_message where patientID = #{patientID} order by id desc limit 1")
    Message getPatientLatestMessage(String patientID);
}
