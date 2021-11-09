package com.kangfu.test.service;

import com.kangfu.test.domain.SysConfig;
import java.util.List;

/**
 * @author kangfu
 */
public interface ConfigService {
    /**
     *
     * @param config
     * @return
     */
    List<SysConfig> selectConfigList(SysConfig config);

    /**
     *
     * @param configId
     * @return
     */
    SysConfig selectConfigById(Long configId);

    /**
     *
     * @param configKey
     * @return
     */
    String selectConfigByKey(String configKey);

    /**
     *
     * @param config
     * @return
     */
    String checkConfigKeyUnique(SysConfig config);

    /**
     *
     * @param config
     * @return
     */
    int insertConfig(SysConfig config);

    /**
     *
     * @param config
     * @return
     */
    int updateConfig(SysConfig config);

    /**
     *
     * @param configIds
     */
    void deleteConfigByIds(Long[] configIds);

    /**
     *
     */
    void resetConfigCache();
}
