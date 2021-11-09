package com.kangfu.test.mapper;

import com.kangfu.test.domain.SysConfig;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author kangfu
 */
public interface ConfigMapper {
    /**
     *
     * @param config
     * @return
     */
    SysConfig selectConfig(SysConfig config);

    /**
     *
     * @param config
     * @return
     */
    /*@Select("select config_id, config_name, config_key, config_value, config_type, create_by, create_time, update_by, update_time, remark\n" +
            "        from sys_config")
    @DS("master")*/
    List<SysConfig> selectConfigList(SysConfig config);

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
     * @param configId
     */
    void deleteConfigById(Long configId);

    /**
     *
     * @param configKey
     * @return
     */
    SysConfig checkConfigKeyUnique(String configKey);
}
