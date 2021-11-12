package com.kangfu.system.mapper;

import com.kangfu.system.domain.SysHospital;
import org.apache.ibatis.annotations.Mapper;

/**
 * 医院简介 数据层
 *
 * @author kangfu
 */
@Mapper
public interface SysHospitalMapper {

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
