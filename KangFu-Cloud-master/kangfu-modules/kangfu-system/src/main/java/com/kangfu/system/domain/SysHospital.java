package com.kangfu.system.domain;

import com.kangfu.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 医院信息表
 * @author kangfu
 */
public class SysHospital extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 医院ID */
    private Long hospitalId;

    /** 医院logo */
    private String hospitalLogo;

    /** 医院名称 */
    @Size(min = 0, max = 50, message = "医院名称长度不能超过30个字符")
    private String hospitalName;

    /** 医院等级 */
    private int hospitalLevel;

    /** 医院类型 */
    private int hospitalType;

    /** 医院介绍 */
    private String hospitalIntroduce;

    /** 医院图片 */
    private String hospitalImage;

    public SysHospital() {
    }

    public SysHospital(Long hospitalId, String hospitalLogo, String hospitalName,
                       int hospitalLevel, int hospitalType, String hospitalIntroduce,
                       String hospitalImage) {
        this.hospitalId = hospitalId;
        this.hospitalLogo = hospitalLogo;
        this.hospitalName = hospitalName;
        this.hospitalLevel = hospitalLevel;
        this.hospitalType = hospitalType;
        this.hospitalIntroduce = hospitalIntroduce;
        this.hospitalImage = hospitalImage;
    }

    public Long getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(Long hospitalId) {
        this.hospitalId = hospitalId;
    }

    public String getHospitalLogo() {
        return hospitalLogo;
    }

    public void setHospitalLogo(String hospitalLogo) {
        this.hospitalLogo = hospitalLogo;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public int getHospitalLevel() {
        return hospitalLevel;
    }

    public void setHospitalLevel(int hospitalLevel) {
        this.hospitalLevel = hospitalLevel;
    }

    public int getHospitalType() {
        return hospitalType;
    }

    public void setHospitalType(int hospitalType) {
        this.hospitalType = hospitalType;
    }

    public String getHospitalIntroduce() {
        return hospitalIntroduce;
    }

    public void setHospitalIntroduce(String hospitalIntroduce) {
        this.hospitalIntroduce = hospitalIntroduce;
    }

    public String getHospitalImage() {
        return hospitalImage;
    }

    public void setHospitalImage(String hospitalImage) {
        this.hospitalImage = hospitalImage;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("hospitalId", getHospitalId())
                .append("hospitalLogo", getHospitalLogo())
                .append("hospitalName", getHospitalName())
                .append("hospitalLevel", getHospitalLevel())
                .append("hospitalType", getHospitalType())
                .append("hospitalIntroduce", getHospitalIntroduce())
                .append("hospitalImage", getHospitalImage())
                .toString();
    }
}
