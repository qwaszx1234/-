package com.kangfu.system.service;

import com.kangfu.system.domain.SysHospital;

/**
 * 医院简介 业务层
 * @author kangfu
 */
public interface ISysHospitalService {

    /**
     * 获取医院详细信息
     * @return
     */
    SysHospital getHospitalInfo();

    /**
     * 更新医院
     * @param sysHospital
     * @return
     */
    int updateHospital(SysHospital sysHospital);
}
