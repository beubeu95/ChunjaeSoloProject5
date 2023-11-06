package com.chunjae.project5.persis;

import com.chunjae.project5.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface UserMapper {
    User findUserByLoginId(@Param("loginId") String loginId);
    void setUserInfo(@Param("param") User param);
    User getLastInsertUser();
}

