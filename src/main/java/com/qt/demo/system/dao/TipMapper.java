package com.qt.demo.system.dao;

import com.qt.demo.system.entity.Dm;
import com.qt.demo.system.entity.Tip;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author Wang Zhen
 * @date 2020/12/2 8:22 下午
 */
@Mapper
@Repository
public interface TipMapper {

    @Select("select * from db_tip where tag = #{tag} and type = #{type}")
    Tip getTipByTagAndType(int tag, int type);
}
