package com.kangfu.test.service;


import com.kangfu.test.domain.Role;

import java.util.List;

/**
 * @author kangfu
 */
public interface RoleService {
    /**
     * 查询角色列表
     * @return
     */
    List<Role> selectRoleAll();

    /**
     * 删除角色
     * @param userId
     * @return
     */
    Object selectRoleListByUserId(Long userId);

    /**
     * 查询角色列表
     * @param role
     * @return
     */
    List<Role> selectRoleList(Role role);

    /**
     * 查询角色对象
     * @param userId
     * @return
     */
    List<Role> selectRolesByUserId(Long userId);
}
