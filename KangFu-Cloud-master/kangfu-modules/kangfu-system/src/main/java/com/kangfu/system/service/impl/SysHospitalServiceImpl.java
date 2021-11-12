package com.kangfu.system.service.impl;

import com.kangfu.system.domain.SysHospital;
import com.kangfu.system.mapper.SysHospitalMapper;
import com.kangfu.system.service.ISysHospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 医院简介 业务层处理
 *
 * @author kangfu
 */
@Service
public class SysHospitalServiceImpl implements ISysHospitalService {

    @Autowired
    public SysHospitalMapper sysHospitalMapper;

    /**
     * 获取医院详细信息
     * @return
     */
    @Override
    public SysHospital getHospitalInfo() {
        return sysHospitalMapper.getHospitalInfo();
    }

    /**
     * 更新医院
     * @param sysHospital
     * @return
     */
    @Override
    public int updateHospital(SysHospital sysHospital) {
        return sysHospitalMapper.updateHospital(sysHospital);
    }
}
