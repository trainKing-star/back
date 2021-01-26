package com.service.uaa.mapper;

import com.service.uaa.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper {



//    @Select("select * from mybatis.user where username=#{username}")
    User getUserByName(String username);

    @Insert("insert into mybatis.user(username,password) values(#{username},${password})")
    void insetUser(@Param("username") String username,@Param("password") String password);

}
