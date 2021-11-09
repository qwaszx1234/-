package com.kangfu.test.mapper;

import com.kangfu.test.domain.SysConfig;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConfigMapper {
    SysConfig selectConfig(SysConfig config);

    /*@Select("select config_id, config_name, config_key, config_value, config_type, create_by, create_time, update_by, update_time, remark\n" +
            "        from sys_config")
    @DS("master")*/
    List<SysConfig> selectConfigList(SysConfig config);

    int insertConfig(SysConfig config);

    int updateConfig(SysConfig config);

    void deleteConfigById(Long configId);

    SysConfig checkConfigKeyUnique(String configKey);
}
