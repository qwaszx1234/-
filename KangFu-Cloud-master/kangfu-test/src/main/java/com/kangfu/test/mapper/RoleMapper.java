package com.kangfu.test.mapper;



import com.kangfu.test.domain.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author kangfu
 */
public interface RoleMapper {
    /**
     * 查询角色
     * @param role
     * @return
     */
    List<Role> selectRoleList(Role role);

    /**
     * 查询角色
     * @param userId
     * @return
     */
    List<Long> selectRoleListByUserId(Long userId);

    /**
     * 查询角色对象
     * @param roleId
     * @return
     */
    Role selectRoleById(Long roleId);

    /**
     * 查询角色权限
     * @param userId
     * @return
     */
    List<Role> selectRolePermissionByUserId(Long userId);
}
