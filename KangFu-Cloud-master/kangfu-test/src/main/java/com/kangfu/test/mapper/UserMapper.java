package com.kangfu.test.mapper;



import com.kangfu.test.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

    List<User> selectUserList(User user);

    User selectUserById(Long userId);

    int checkUserNameUnique(String userName);

    User checkPhoneUnique(String phonenumber);

    User checkEmailUnique(String email);

    int insertUser(User user);

    int updateUser(User user);

    int deleteUserByIds(Long[] userIds);
}
