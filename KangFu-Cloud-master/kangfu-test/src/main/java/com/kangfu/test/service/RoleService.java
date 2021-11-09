package com.kangfu.test.service;


import com.kangfu.test.domain.Role;

import java.util.List;

public interface RoleService {
    List<Role> selectRoleAll();

    Object selectRoleListByUserId(Long userId);

    List<Role> selectRoleList(Role role);

    List<Role> selectRolesByUserId(Long userId);
}
