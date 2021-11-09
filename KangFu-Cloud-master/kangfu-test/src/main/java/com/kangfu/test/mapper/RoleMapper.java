package com.kangfu.test.mapper;



import com.kangfu.test.domain.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleMapper {

    List<Role> selectRoleList(Role role);

    List<Long> selectRoleListByUserId(Long userId);

    Role selectRoleById(Long roleId);

    List<Role> selectRolePermissionByUserId(Long userId);
}
