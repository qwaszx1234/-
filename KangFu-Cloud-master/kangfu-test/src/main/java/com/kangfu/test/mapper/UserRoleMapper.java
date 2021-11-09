package com.kangfu.test.mapper;

import com.kangfu.test.domain.UserRole;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleMapper {
    void batchUserRole(List<UserRole> list);

    void deleteUserRoleByUserId(Long userId);

    void deleteUserRole(Long[] userIds);
}
