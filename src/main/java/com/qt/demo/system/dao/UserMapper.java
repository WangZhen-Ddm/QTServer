package com.qt.demo.system.dao;

import com.qt.demo.system.entity.User;
import com.qt.demo.system.entity.UserRole;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Wang Zhen
 * @date 2020/9/10 10:30 上午
 */
@Mapper
public interface UserMapper {

    @Select("select * from db_user where username = #{username}")
    User findUserByUsername(@Param("username") String username);

    @Insert("insert into db_user (id, username, password, authorities) values (#{id}, #{username}, #{password}, #{authorities})")
    void createUser(User user);

    @Select("select * from db_user_role where username = #{username}")
    List<UserRole> getUserRoleByUserName(String username);
}
