package com.chunjae.project5.persis;

import com.chunjae.project5.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface RoleMapper {
    Role getRoleInfo(@Param("role") String role);
}
