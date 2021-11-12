package com.kangfu.system.controller;

import com.kangfu.common.core.domain.R;
import com.kangfu.common.core.utils.StringUtils;
import com.kangfu.common.core.web.controller.BaseController;
import com.kangfu.common.core.web.domain.AjaxResult;
import com.kangfu.common.log.annotation.Log;
import com.kangfu.common.log.enums.BusinessType;
import com.kangfu.common.security.utils.SecurityUtils;
import com.kangfu.system.api.RemoteFileService;
import com.kangfu.system.api.domain.SysFile;
import com.kangfu.system.api.model.LoginUser;
import com.kangfu.system.domain.SysHospital;
import com.kangfu.system.service.ISysHospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;

/**
 * 基础信息之医院信息
 * @author kangfu
 */
@RestController
@RequestMapping("/data/hospital")
public class SysHospitalController extends BaseController {

    @Autowired
    public ISysHospitalService iSysHospitalService;

    @Autowired
    private RemoteFileService remoteFileService;

    /**
     * 获取医院详细信息
     * @return
     */
    @GetMapping("/getHospitalInfo")
    public AjaxResult getHospitalInfo(){
        SysHospital sysHospital=iSysHospitalService.getHospitalInfo();
        AjaxResult ajaxResult = AjaxResult.success();
        ajaxResult.put("hospital",sysHospital);
        String images=sysHospital.getHospitalImage();
        String[] split = images.split(";");
        Arrays.stream(split).filter(item -> item != null && !"".equals(item));
        ajaxResult.put("hospitalImageArray",split);
        return ajaxResult;
    }
    /**
     * 更新医院基本信息
     * @return
     */
    @PostMapping("/updateHospital")
    public AjaxResult updateHospital(@RequestBody SysHospital sysHospital){
        Long hospitalId = 1L;
        sysHospital.setHospitalId(hospitalId);
        int row=iSysHospitalService.updateHospital(sysHospital);
        return AjaxResult.success(sysHospital);
    }
    /**
     * 更新医院介绍图片数组
     * @return
     */
    @PostMapping("/updateHospitalImage")
    public AjaxResult updateHospitalImage(@RequestParam("files")MultipartFile[] files){
        R<SysFile> fileResult=null;
        StringBuilder images=new StringBuilder();
        for (int i = 0; i < files.length; i++) {
            fileResult = remoteFileService.upload(files[i]);
            String url = fileResult.getData().getUrl();
            images.append(url+";");
        }
        Long hospitalId = 1L;
        SysHospital hospital=new SysHospital();
        hospital.setHospitalId(hospitalId);
        hospital.setHospitalImage(images.toString());
        int row=iSysHospitalService.updateHospital(hospital);
        return AjaxResult.success(row);
    }

    /**
     * 医院logo上传
     */
    @Log(title = "医院logo", businessType = BusinessType.UPDATE)
    @PostMapping("/avatar")
    public AjaxResult avatar(@RequestParam("avatarfile") MultipartFile file) throws IOException
    {
        if (!file.isEmpty())
        {
            //LoginUser loginUser = SecurityUtils.getLoginUser();
            R<SysFile> fileResult = remoteFileService.upload(file);
            if (StringUtils.isNull(fileResult) || StringUtils.isNull(fileResult.getData()))
            {
                return AjaxResult.error("文件服务异常，请联系管理员");
            }
            String url = fileResult.getData().getUrl();
            Long hospitalId = 1L;
            SysHospital hospital=new SysHospital();
            hospital.setHospitalId(hospitalId);
            hospital.setHospitalLogo(url);
            int rows = iSysHospitalService.updateHospital(hospital);
            if (rows>0)
            {
                AjaxResult ajax = AjaxResult.success();
                ajax.put("imgUrl", url);
                return ajax;
            }
        }
        return AjaxResult.error("上传图片异常，请联系管理员");
    }
}
