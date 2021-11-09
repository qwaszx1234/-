package com.kangfu.test.service;

import com.kangfu.test.domain.SysConfig;
import java.util.List;

public interface ConfigService {
    List<SysConfig> selectConfigList(SysConfig config);

    SysConfig selectConfigById(Long configId);

    String selectConfigByKey(String configKey);

    String checkConfigKeyUnique(SysConfig config);

    int insertConfig(SysConfig config);

    int updateConfig(SysConfig config);

    void deleteConfigByIds(Long[] configIds);

    void resetConfigCache();
}
