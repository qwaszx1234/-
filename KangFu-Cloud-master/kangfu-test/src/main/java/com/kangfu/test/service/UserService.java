package com.kangfu.test.service;

import com.kangfu.test.domain.User;

import java.util.List;

/**
 * @author kangfu
 */
public interface UserService {
    /**
     * 用户列表
     * @param user
     * @return
     */
    List<User> selectUserList(User user);

    /**
     * 检查用户数据范围
     * @param userId
     */
    void checkUserDataScope(Long userId);

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
    String checkUserNameUnique(String userName);

    /**
     * 检查手机号是否唯一
     * @param user
     * @return
     */
    String checkPhoneUnique(User user);

    /**
     * 检查邮箱号码是否唯一
     * @param user
     * @return
     */
    String checkEmailUnique(User user);

    /**
     * 新增用户
     * @param user
     * @return
     */
    int insertUser(User user);

    /**
     * 检查用户
     * @param user
     */
    void checkUserAllowed(User user);

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

    /**
     * 新增用户授权
     * @param userId
     * @param roleIds
     */
    void insertUserAuth(Long userId, Long[] roleIds);

    /**
     * 重置密码
     * @param user
     * @return
     */
    int resetPwd(User user);

    /**
     * 更新用户状态
     * @param user
     * @return
     */
    int updateUserStatus(User user);
}
