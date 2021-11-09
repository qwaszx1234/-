package com.kangfu.test.mapper;

import com.kangfu.test.domain.Dept;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author kangfu
 */
public interface DeptMapper {
    /**
     * 查询部门列表
     * @param dept
     * @return
     */
    List<Dept> selectDeptList(Dept dept);

    /**
     * 查询部门对象
     * @param deptId
     * @return
     */
    Dept selectDeptById(Long deptId);

    /**
     * 查询部门列表
     * @param roleId
     * @param deptCheckStrictly
     * @return
     */
    List<Integer> selectDeptListByRoleId(Long roleId, boolean deptCheckStrictly);

    /**
     * 检查部门名称是否唯一
     * @param deptName
     * @param parentId
     * @return
     */
    Dept checkDeptNameUnique(String deptName, Long parentId);

    /**
     * 新增部门
     * @param dept
     * @return
     */
    int insertDept(Dept dept);

    /**
     * 检查部门的下级
     * @param deptId
     * @return
     */
    int selectNormalChildrenDeptById(Long deptId);

    /**
     * 查询部门的下级
     * @param deptId
     * @return
     */
    List<Dept> selectChildrenDeptById(Long deptId);

    /**
     * 更新部门的下级
     * @param children
     */
    void updateDeptChildren(List<Dept> children);

    /**
     * 更新部门
     * @param dept
     * @return
     */
    int updateDept(Dept dept);

    /**
     * 更新部门状态
     * @param deptIds
     */
    void updateDeptStatusNormal(Long[] deptIds);

    /**
     * 查询部门的下级
     * @param deptId
     * @return
     */
    int hasChildByDeptId(Long deptId);

    /**
     * 检查部门是否存在
     * @param deptId
     * @return
     */
    int checkDeptExistUser(Long deptId);

    /**
     * 删除部门
     * @param deptId
     * @return
     */
    int deleteDeptById(Long deptId);
}
