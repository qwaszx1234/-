package com.kangfu.test.controller;

import com.kangfu.common.core.constant.UserConstants;
import com.kangfu.common.core.utils.poi.ExcelUtil;
import com.kangfu.common.core.web.controller.BaseController;
import com.kangfu.common.core.web.domain.AjaxResult;
import com.kangfu.common.core.web.page.TableDataInfo;
import com.kangfu.common.log.annotation.Log;
import com.kangfu.common.log.enums.BusinessType;
import com.kangfu.common.security.utils.SecurityUtils;
import com.kangfu.test.service.ConfigService;
import com.kangfu.test.domain.SysConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author kangfu
 */
@RestController
@RequestMapping("/config2")
public class ConfigController extends BaseController{
    @Autowired
    public ConfigService configService;

    /**
     * 获取参数配置列表
     */
    //@RequiresPermissions("system:config2:list")
    @GetMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysConfig config,@RequestHeader(value = "origin",required = false)String origin)
    {
        //System.out.println("获取参数配置列表 origin = " + origin);
        startPage();
        List<SysConfig> list = configService.selectConfigList(config);
        return getDataTable(list);
    }

    @Log(title = "参数管理", businessType = BusinessType.EXPORT)
    //@RequiresPermissions("system:config2:export")
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysConfig config) throws IOException
    {
        List<SysConfig> list = configService.selectConfigList(config);
        ExcelUtil<SysConfig> util = new ExcelUtil<SysConfig>(SysConfig.class);
        util.exportExcel(response, list, "参数数据");
    }

    /**
     * 根据参数编号获取详细信息
     */
    @GetMapping(value = "/{configId}")
    public AjaxResult getInfo(@PathVariable Long configId,@RequestHeader(value = "origin",required = false)String origin)
    {
        //System.out.println("根据参数编号获取详细信息 origin = " + origin);
        return AjaxResult.success(configService.selectConfigById(configId));
    }

    /**
     * 根据参数键名查询参数值
     */
    @GetMapping(value = "/configKey/{configKey}")
    public AjaxResult getConfigKey(@PathVariable String configKey,@RequestHeader(value = "origin",required = false)String origin)
    {
        //System.out.println("根据参数键名查询参数值 origin = " + origin);
        return AjaxResult.success(configService.selectConfigByKey(configKey));
    }

    /**
     * 新增参数配置
     */
    //@RequiresPermissions("system:config2:add")
    @Log(title = "参数管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody SysConfig config,@RequestHeader(value = "origin",required = false)String origin)
    {
        //System.out.println("新增参数配置 origin = " + origin);
        if (UserConstants.NOT_UNIQUE.equals(configService.checkConfigKeyUnique(config)))
        {
            return AjaxResult.error("新增参数'" + config.getConfigName() + "'失败，参数键名已存在");
        }
        config.setCreateBy(SecurityUtils.getUsername());
        return toAjax(configService.insertConfig(config));
    }

    /**
     * 修改参数配置
     */
    //@RequiresPermissions("system:config2:edit")
    @Log(title = "参数管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody SysConfig config,@RequestHeader(value = "origin",required = false)String origin)
    {
        //System.out.println("修改参数配置 origin = " + origin);
        if (UserConstants.NOT_UNIQUE.equals(configService.checkConfigKeyUnique(config)))
        {
            return AjaxResult.error("修改参数'" + config.getConfigName() + "'失败，参数键名已存在");
        }
        config.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(configService.updateConfig(config));
    }

    /**
     * 删除参数配置
     */
    //@RequiresPermissions("system:config2:remove")
    @Log(title = "参数管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{configIds}")
    public AjaxResult remove(@PathVariable Long[] configIds,@RequestHeader(value = "origin",required = false)String origin)
    {
        //System.out.println("删除参数配置 origin = " + origin);
        configService.deleteConfigByIds(configIds);
        return success();
    }

    /**
     * 刷新参数缓存
     */
    //@RequiresPermissions("system:config2:remove")
    @Log(title = "参数管理", businessType = BusinessType.CLEAN)
    @DeleteMapping("/refreshCache")
    public AjaxResult refreshCache(@RequestHeader(value = "origin",required = false)String origin)
    {
        //System.out.println("刷新参数缓存 origin = " + origin);
        configService.resetConfigCache();
        return AjaxResult.success();
    }
}
