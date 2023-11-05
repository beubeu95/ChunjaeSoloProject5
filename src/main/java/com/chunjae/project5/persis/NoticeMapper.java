package com.chunjae.project5.persis;

import com.chunjae.project5.entity.Notice;
import com.chunjae.project5.entity.Role;
import com.chunjae.project5.entity.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface NoticeMapper {

    List<Notice> getList();
    Notice getNotice(@Param("no") int no);
    void noticeInsert(@Param("param") Notice param);
    void noticeUpdate(@Param("param") Notice param);
    void noticeDelete(int no);
}
