package com.kangfu.test.mapper;



import com.kangfu.test.domain.User;

import java.util.List;

/**
 * @author kangfu
 */
public interface UserMapper {
    /**
     * 查询用户列表
     * @param user
     * @return
     */
    List<User> selectUserList(User user);

    /**
     * 查询用户详细信息
     * @param userId
     * @return
     */
    User selectUserById(Long userId);

    /**
     * 检查用户名是否唯一
     * @param userName
     * @return
     */
    int checkUserNameUnique(String userName);

    /**
     * 检查手机号码是否唯一
     * @param phonenumber
     * @return
     */
    User checkPhoneUnique(String phonenumber);

    /**
     * 检查邮箱是否唯一
     * @param email
     * @return
     */
    User checkEmailUnique(String email);

    /**
     * 新增用户
     * @param user
     * @return
     */
    int insertUser(User user);

    /**
     * 更新用户
     * @param user
     * @return
     */
    int updateUser(User user);

    /**
     * 删除用户
     * @param userIds
     * @return
     */
    int deleteUserByIds(Long[] userIds);
}
