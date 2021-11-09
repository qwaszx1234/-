package com.kangfu.test.service;
import com.kangfu.test.domain.User;
import java.util.List;

public interface UserService {

    List<User> selectUserList(User user);

    void checkUserDataScope(Long userId);

    User selectUserById(Long userId);

    String checkUserNameUnique(String userName);

    String checkPhoneUnique(User user);

    String checkEmailUnique(User user);

    int insertUser(User user);

    void checkUserAllowed(User user);

    int updateUser(User user);

    int deleteUserByIds(Long[] userIds);

    void insertUserAuth(Long userId, Long[] roleIds);

    int resetPwd(User user);

    int updateUserStatus(User user);
}
