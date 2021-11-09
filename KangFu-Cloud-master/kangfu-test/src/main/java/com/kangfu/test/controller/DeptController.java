package com.kangfu.test.controller;

import com.kangfu.common.core.constant.UserConstants;
import com.kangfu.common.core.utils.StringUtils;
import com.kangfu.common.core.web.controller.BaseController;
import com.kangfu.common.core.web.domain.AjaxResult;
import com.kangfu.common.log.annotation.Log;
import com.kangfu.common.log.enums.BusinessType;
import com.kangfu.common.security.annotation.RequiresPermissions;
import com.kangfu.common.security.utils.SecurityUtils;
import com.kangfu.test.domain.Dept;
import com.kangfu.test.service.DeptService;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;

/**
 * 部门信息
 *
 * @author kangfu
 */
@RestController
@RequestMapping("/dept")
public class DeptController extends BaseController {


    @Autowired
    private DeptService deptService;

    /**
     * 获取部门列表
     */
    @RequiresPermissions("test:dept:list")
    @GetMapping("/list")
    public AjaxResult list(Dept dept,@RequestHeader(value = "origin",required = false)String origin)
    {
        //System.out.println("获取部门列表 origin = " + origin);
        List<Dept> depts = deptService.selectDeptList(dept);
        return AjaxResult.success(depts);
    }

    /**
     * 查询部门列表（排除节点）
     */
    @RequiresPermissions("test:dept:list")
    @GetMapping("/list/exclude/{deptId}")
    public AjaxResult excludeChild(@PathVariable(value = "deptId", required = false) Long deptId
            ,@RequestHeader(value = "origin",required = false)String origin)
    {
        //System.out.println("查询部门列表（排除节点） origin = " + origin);
        List<Dept> depts = deptService.selectDeptList(new Dept());
        Iterator<Dept> it = depts.iterator();
        while (it.hasNext())
        {
            Dept d = (Dept) it.next();
            if (d.getDeptId().intValue() == deptId
                    || ArrayUtils.contains(StringUtils.split(d.getAncestors(), ","), deptId + ""))
            {
                it.remove();
            }
        }
        return AjaxResult.success(depts);
    }

    /**
     * 根据部门编号获取详细信息
     */
    @RequiresPermissions("test:dept:query")
    @GetMapping(value = "/{deptId}")
    public AjaxResult getInfo(@PathVariable Long deptId
            ,@RequestHeader(value = "origin",required = false)String origin)
    {
        //System.out.println("根据部门编号获取详细信息 origin = " + origin);
        deptService.checkDeptDataScope(deptId);
        return AjaxResult.success(deptService.selectDeptById(deptId));
    }

    /**
     * 获取部门下拉树列表
     */
    @GetMapping("/treeselect")
    public AjaxResult treeselect(Dept dept,@RequestHeader(value = "origin",required = false)String origin)
    {
        //System.out.println("获取部门下拉树列表 origin = " + origin);
        List<Dept> depts = deptService.selectDeptList(dept);
        return AjaxResult.success(deptService.buildDeptTreeSelect(depts));
    }

    /**
     * 加载对应角色部门列表树
     */
    @GetMapping(value = "/roleDeptTreeselect/{roleId}")
    public AjaxResult roleDeptTreeselect(@PathVariable("roleId") Long roleId
            ,@RequestHeader(value = "origin",required = false)String origin)
    {
        //System.out.println("加载对应角色部门列表树 origin = " + origin);
        List<Dept> depts = deptService.selectDeptList(new Dept());
        AjaxResult ajax = AjaxResult.success();
        ajax.put("checkedKeys", deptService.selectDeptListByRoleId(roleId));
        ajax.put("depts", deptService.buildDeptTreeSelect(depts));
        return ajax;
    }

    /**
     * 新增部门
     */
    @RequiresPermissions("test:dept:add")
    @Log(title = "部门管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody Dept dept
            ,@RequestHeader(value = "origin",required = false)String origin)
    {
        //System.out.println("新增部门 origin = " + origin);
        if (UserConstants.NOT_UNIQUE.equals(deptService.checkDeptNameUnique(dept)))
        {
            return AjaxResult.error("新增部门'" + dept.getDeptName() + "'失败，部门名称已存在");
        }
        dept.setCreateBy(SecurityUtils.getUsername());
        return toAjax(deptService.insertDept(dept));
    }

    /**
     * 修改部门
     */
    @RequiresPermissions("test:dept:edit")
    @Log(title = "部门管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody Dept dept
            ,@RequestHeader(value = "origin",required = false)String origin)
    {
        //System.out.println("修改部门 origin = " + origin);
        if (UserConstants.NOT_UNIQUE.equals(deptService.checkDeptNameUnique(dept)))
        {
            return AjaxResult.error("修改部门'" + dept.getDeptName() + "'失败，部门名称已存在");
        }
        else if (dept.getParentId().equals(dept.getDeptId()))
        {
            return AjaxResult.error("修改部门'" + dept.getDeptName() + "'失败，上级部门不能是自己");
        }
        else if (StringUtils.equals(UserConstants.DEPT_DISABLE, dept.getStatus())
                && deptService.selectNormalChildrenDeptById(dept.getDeptId()) > 0)
        {
            return AjaxResult.error("该部门包含未停用的子部门！");
        }
        dept.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(deptService.updateDept(dept));
    }

    /**
     * 删除部门
     */
    @RequiresPermissions("test:dept:remove")
    @Log(title = "部门管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{deptId}")
    public AjaxResult remove(@PathVariable Long deptId,@RequestHeader(value = "origin",required = false)String origin)
    {
        //System.out.println("删除部门 origin = " + origin);
        if (deptService.hasChildByDeptId(deptId))
        {
            return AjaxResult.error("存在下级部门,不允许删除");
        }
        if (deptService.checkDeptExistUser(deptId))
        {
            return AjaxResult.error("部门存在用户,不允许删除");
        }
        return toAjax(deptService.deleteDeptById(deptId));
    }

}
