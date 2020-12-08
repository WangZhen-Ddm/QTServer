package com.qt.demo.system.dao;

import com.qt.demo.system.entity.Dm;
import com.qt.demo.system.entity.Kno;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author Wang Zhen
 * @date 2020/12/8 5:13 下午
 */
@Mapper
@Repository
public interface KnoMapper {

    @Select("select * from db_kno where type = #{type} order by rand() limit 1")
    Kno getKnoByType(Integer type);

    @Select("select * from db_kno where id = #{id}")
    Kno getKnoByID(Integer id);

}
