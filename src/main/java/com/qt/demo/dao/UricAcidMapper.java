package com.qt.demo.dao;

import com.qt.demo.entity.UricAcid;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author Wang Zhen
 * @date 2020/7/3 9:37 上午
 */
@Mapper
@Repository
public interface UricAcidMapper {

    @Insert("insert into db_patient_ua(patientID, uricAcid, measureDateTime) values (#{patientID}, #{uricAcid}, #{measureDateTime})")
    void createNewUricAcidRecord(UricAcid uricAcidRecord);
}
