package com.chunjae.project5.persis;

import com.chunjae.project5.entity.User;
import com.chunjae.project5.entity.UserVO;
import com.chunjae.project5.util.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface UserMapper {
    User getUserByLoginId(@Param("loginId") String loginId);
    UserVO findUserListByLoginId(@Param("loginId") String loginId);
    int userInsert(@Param("param") User param);
    List<User> userList(Page page);
    int getCount(Page page);
    void userEdit(User user);
    void pwEdit(User user);
    User findId(String email, String tel);
    int cntDeal(String loginId);
    void addPt(String loginId);
    void minusPt(String loginId);
}

