package com.kangfu.test.service;

import com.kangfu.test.domain.Dept;
import com.kangfu.test.domain.TreeSelect;
import java.util.List;

/**
 * @author kangfu
 */
public interface DeptService {
    /**
     * 查询部门列表
     * @param dept
     * @return
     */
    List<Dept> selectDeptList(Dept dept);

    /**
     * 检查部门数据范围
     * @param deptId
     */
    void checkDeptDataScope(Long deptId);

    /**
     * 查询部门
     * @param deptId
     * @return
     */
    Dept selectDeptById(Long deptId);

    /**
     * 构建部门tree
     * @param depts
     * @return
     */
    List<TreeSelect> buildDeptTreeSelect(List<Dept> depts);

    /**
     * 查询部门列表
     * @param roleId
     * @return
     */
    Object selectDeptListByRoleId(Long roleId);

    /**
     * 检查部门名称是否唯一
     * @param dept
     * @return
     */
    String checkDeptNameUnique(Dept dept);

    /**
     * 新增部门
     * @param dept
     * @return
     */
    int insertDept(Dept dept);

    /**
     * 查询部门的下级
     * @param deptId
     * @return
     */
    int selectNormalChildrenDeptById(Long deptId);

    /**
     * 更新部门
     * @param dept
     * @return
     */
    int updateDept(Dept dept);

    /**
     * 查询部门的下级
     * @param deptId
     * @return
     */
    boolean hasChildByDeptId(Long deptId);

    /**
     * 检查部门是否存在
     * @param deptId
     * @return
     */
    boolean checkDeptExistUser(Long deptId);

    /**
     * 删除部门
     * @param deptId
     * @return
     */
    int deleteDeptById(Long deptId);
}
