package com.kangfu.test.service;

import com.kangfu.test.domain.Dept;
import com.kangfu.test.domain.TreeSelect;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DeptService {
    List<Dept> selectDeptList(Dept dept);

    void checkDeptDataScope(Long deptId);

    Dept selectDeptById(Long deptId);

    List<TreeSelect> buildDeptTreeSelect(List<Dept> depts);

    Object selectDeptListByRoleId(Long roleId);

    String checkDeptNameUnique(Dept dept);

    int insertDept(Dept dept);

    int selectNormalChildrenDeptById(Long deptId);

    int updateDept(Dept dept);

    boolean hasChildByDeptId(Long deptId);

    boolean checkDeptExistUser(Long deptId);

    int deleteDeptById(Long deptId);
}
