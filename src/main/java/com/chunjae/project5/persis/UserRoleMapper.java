package com.chunjae.project5.persis;

import com.chunjae.project5.entity.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface UserRoleMapper {
    void setUserRoleInfo(@Param("param") UserRole param);
}
