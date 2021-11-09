package com.kangfu.test.mapper;

import com.kangfu.test.domain.Dept;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeptMapper {
    List<Dept> selectDeptList(Dept dept);

    Dept selectDeptById(Long deptId);

    List<Integer> selectDeptListByRoleId(Long roleId, boolean deptCheckStrictly);

    Dept checkDeptNameUnique(String deptName, Long parentId);

    int insertDept(Dept dept);

    int selectNormalChildrenDeptById(Long deptId);

    List<Dept> selectChildrenDeptById(Long deptId);

    void updateDeptChildren(List<Dept> children);

    int updateDept(Dept dept);

    void updateDeptStatusNormal(Long[] deptIds);

    int hasChildByDeptId(Long deptId);

    int checkDeptExistUser(Long deptId);

    int deleteDeptById(Long deptId);
}
