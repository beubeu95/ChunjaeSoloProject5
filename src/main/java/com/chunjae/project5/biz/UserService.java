package com.chunjae.project5.biz;

import com.chunjae.project5.domain.UserPrincipal;
import com.chunjae.project5.entity.Role;
import com.chunjae.project5.entity.User;
import com.chunjae.project5.entity.UserRole;
import com.chunjae.project5.entity.UserVO;
import com.chunjae.project5.persis.RoleMapper;
import com.chunjae.project5.persis.UserMapper;
import com.chunjae.project5.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    public User getUserByLoginId(String loginId) {
        return userMapper.getUserByLoginId(loginId);
    }

    public int saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        return userMapper.userInsert(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserVO userVO = userMapper.findUserListByLoginId(username);

        if(userVO == null) {
            throw new UsernameNotFoundException("null");
        }

        return new UserPrincipal(userVO);
    }

    public List<User> userList(Page page) { return userMapper.userList(page); }
    public int getCount(Page page) { return userMapper.getCount(page); }

    //회원 정보 수정
    public void userEdit(User user) {
        userMapper.userEdit(user);
    }

    //패스워드 변경
    public void pwEdit(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userMapper.pwEdit(user);
    }


    public User findId(String email, String tel){
        return userMapper.findId(email, tel);
    }

    public int cntDeal(String loginId) {
        return userMapper.cntDeal(loginId);
    }

    public void addPt(String loginId) {
        userMapper.addPt(loginId);
    }

}