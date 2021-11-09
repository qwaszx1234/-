package com.kangfu.test.mapper;

import com.kangfu.test.domain.UserRole;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @author kangfu
 */
public interface UserRoleMapper {
    /**
     * 批处理
     * @param list
     */
    void batchUserRole(List<UserRole> list);

    /**
     * 删除用户角色
     * 根据用户id
     * @param userId
     */
    void deleteUserRoleByUserId(Long userId);

    /**
     * 删除用户角色
     * @param userIds
     */
    void deleteUserRole(Long[] userIds);
}
