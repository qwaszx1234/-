package com.kangfu.test.controller;

import com.alibaba.csp.sentinel.context.ContextUtil;
import com.kangfu.common.core.constant.HttpStatus;
import com.kangfu.common.core.constant.UserConstants;
import com.kangfu.common.core.utils.StringUtils;
import com.kangfu.common.core.web.controller.BaseController;
import com.kangfu.common.core.web.domain.AjaxResult;
import com.kangfu.common.core.web.page.TableDataInfo;
import com.kangfu.common.log.annotation.Log;
import com.kangfu.common.log.enums.BusinessType;
import com.kangfu.common.security.annotation.Logical;
import com.kangfu.common.security.annotation.RequiresPermissions;
import com.kangfu.common.security.utils.SecurityUtils;
import com.kangfu.test.domain.Role;
import com.kangfu.test.domain.User;
import com.kangfu.test.service.PostService;
import com.kangfu.test.service.RoleService;
import com.kangfu.test.service.UserService;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户信息
 *
 * @author kangfu
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PostService postService;



    /**
     * 查询用户列表
     * @param user
     * @return
     */
    //@RequiresPermissions(value = {"test:user:list"},logical = Logical.AND)
    @GetMapping(value = "/list")
    public TableDataInfo getUserList(User user
            ,@RequestHeader(value = "origin",required = false)String origin){
        //ContextUtil.enter("userlist", origin);
        //System.out.println("查询用户列表 origin = " + origin);
        logger.info("查询用户列表");
        startPage();
        List<User> list = userService.selectUserList(user);
        if (list.size()>0){
            return getDataTable(list);
        }else{
            TableDataInfo rspData = new TableDataInfo();
            rspData.setCode(HttpStatus.NO_CONTENT);
            rspData.setRows(Collections.emptyList());
            rspData.setMsg("操作成功，但是没有数据返回！");
            rspData.setTotal(0);
            return rspData;
        }
    }


    /**
     * 查询用户详细
     * 根据用户编号获取详细信息
     */
    //@RequiresPermissions("test:user:query")
    @GetMapping(value =  "/query/{userId}" )
    public AjaxResult getInfo(@PathVariable(value = "userId", required = false) Long userId
            ,@RequestHeader(value = "origin",required = false)String origin) {
        //System.out.println("查询用户详细 origin = " + origin);
        logger.info("查询用户详细");
        userService.checkUserDataScope(userId);
        AjaxResult ajax = AjaxResult.success();
        List<Role> roles = roleService.selectRoleAll();
        ajax.put("roles", User.isAdmin(userId) ? roles : roles.stream().filter(r -> !r.isAdmin()).collect(Collectors.toList()));
        ajax.put("posts", postService.selectPostAll());
        if (StringUtils.isNotNull(userId)) {
            ajax.put(AjaxResult.DATA_TAG, userService.selectUserById(userId));
            ajax.put("postIds", postService.selectPostListByUserId(userId));
            ajax.put("roleIds", roleService.selectRoleListByUserId(userId));
        }
        return ajax;
    }

    /**
     * 新增用户
     */
    @RequiresPermissions("test:user:add")
    @Log(title = "用户测试", businessType = BusinessType.INSERT)
    @PostMapping("/insert")
    public AjaxResult add(@Validated @RequestBody User user
            ,@RequestHeader(value = "origin",required = false)String origin) {
        //System.out.println("新增用户 origin = " + origin);
        logger.info("新增用户");
        if (UserConstants.NOT_UNIQUE.equals(userService.checkUserNameUnique(user.getUserName()))) {
            return AjaxResult.error("新增用户'" + user.getUserName() + "'失败，登录账号已存在");
        }
        else if (StringUtils.isNotEmpty(user.getPhonenumber())
                && UserConstants.NOT_UNIQUE.equals(userService.checkPhoneUnique(user))) {
            return AjaxResult.error("新增用户'" + user.getUserName() + "'失败，手机号码已存在");
        }
        else if (StringUtils.isNotEmpty(user.getEmail())
                && UserConstants.NOT_UNIQUE.equals(userService.checkEmailUnique(user))) {
            return AjaxResult.error("新增用户'" + user.getUserName() + "'失败，邮箱账号已存在");
        }
        user.setCreateBy(SecurityUtils.getUsername());
        user.setPassword(SecurityUtils.encryptPassword(user.getPassword()));
        return toAjax(userService.insertUser(user));
    }

    /**
     * 修改用户
     */
    @RequiresPermissions("test:user:edit")
    @Log(title = "用户测试", businessType = BusinessType.UPDATE)
    @PutMapping("/update")
    public AjaxResult edit(@Validated @RequestBody User user
            ,@RequestHeader(value = "origin",required = false)String origin) {
        //System.out.println("修改用户 origin = " + origin);
        logger.info("修改用户信息");
        userService.checkUserAllowed(user);
        if (StringUtils.isNotEmpty(user.getPhonenumber())
                && UserConstants.NOT_UNIQUE.equals(userService.checkPhoneUnique(user))) {
            return AjaxResult.error("修改用户'" + user.getUserName() + "'失败，手机号码已存在");
        }
        else if (StringUtils.isNotEmpty(user.getEmail())
                && UserConstants.NOT_UNIQUE.equals(userService.checkEmailUnique(user))) {
            return AjaxResult.error("修改用户'" + user.getUserName() + "'失败，邮箱账号已存在");
        }
        user.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(userService.updateUser(user));
    }

    /**
     * 删除用户
     */
    @RequiresPermissions("test:user:remove")
    @Log(title = "用户测试", businessType = BusinessType.DELETE)
    @DeleteMapping("/delete/{userIds}")
    public AjaxResult remove(@PathVariable Long[] userIds
            ,@RequestHeader(value = "origin",required = false)String origin) {
        //System.out.println("删除用户 origin = " + origin);
        logger.info("根据id删除用户");
        if (ArrayUtils.contains(userIds, SecurityUtils.getUserId())) {
            return AjaxResult.error("当前用户不能删除");
        }
        return toAjax(userService.deleteUserByIds(userIds));
    }

    /**
     * 根据用户编号获取授权角色
     */
    @RequiresPermissions("test:user:query")
    @GetMapping("/authRole/{userId}")
    public AjaxResult authRole(@PathVariable("userId") Long userId
            ,@RequestHeader(value = "origin",required = false)String origin) {
        //System.out.println("根据用户编号获取授权角色 origin = " + origin);
        logger.info("根据用户编号获取授权角色");
        AjaxResult ajax = AjaxResult.success();
        User user = userService.selectUserById(userId);
        List<Role> roles = roleService.selectRolesByUserId(userId);
        ajax.put("user", user);
        ajax.put("roles", User.isAdmin(userId) ? roles : roles.stream().filter(r -> !r.isAdmin()).collect(Collectors.toList()));
        return ajax;
    }

    /**
     * 用户授权角色
     */
    @RequiresPermissions("test:user:edit")
    @Log(title = "用户测试", businessType = BusinessType.GRANT)
    @PutMapping("/authRole")
    public AjaxResult insertAuthRole(Long userId, Long[] roleIds
            ,@RequestHeader(value = "origin",required = false)String origin) {
        //System.out.println("用户授权角色 origin = " + origin);
        logger.info("用户授权");
        userService.insertUserAuth(userId, roleIds);
        return success();
    }

    /**
     * 重置密码
     */
    @RequiresPermissions("test:user:edit")
    @Log(title = "用户测试", businessType = BusinessType.UPDATE)
    @PutMapping("/resetPwd")
    public AjaxResult resetPwd(@RequestBody User user
            ,@RequestHeader(value = "origin",required = false)String origin)
    {
        //System.out.println("重置密码 origin = " + origin);
        userService.checkUserAllowed(user);
        user.setPassword(SecurityUtils.encryptPassword(user.getPassword()));
        user.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(userService.resetPwd(user));
    }

    /**
     * 状态修改
     */
    @RequiresPermissions("test:user:edit")
    @Log(title = "用户测试", businessType = BusinessType.UPDATE)
    @PutMapping("/changeStatus")
    public AjaxResult changeStatus(@RequestBody User user
            ,@RequestHeader(value = "origin",required = false)String origin)
    {
        //System.out.println("状态修改 origin = " + origin);
        userService.checkUserAllowed(user);
        user.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(userService.updateUserStatus(user));
    }




}
