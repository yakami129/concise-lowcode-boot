package com.github.yakami.process.config;

import lombok.extern.slf4j.Slf4j;
import me.ahoo.cosid.snowflake.SnowflakeId;
import org.camunda.bpm.engine.impl.cfg.IdGenerator;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.camunda.bpm.spring.boot.starter.configuration.CamundaProcessEngineConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * Created by alan on 2023/3/22.
 * 自定义流程引擎配置
 */
@Slf4j
@Configuration
public class CustomizeProcessEngineConfiguration implements CamundaProcessEngineConfiguration {

    @Autowired
    private SnowflakeId snowflakeId;

    @Override
    public void preInit(ProcessEngineConfigurationImpl processEngineConfiguration) {

        final CosIdGenerator cosIdGenerator = new CosIdGenerator(snowflakeId);
        processEngineConfiguration.setIdGenerator(cosIdGenerator);
        log.info("[CONFIG] CustomizeProcessEngineConfiguration setIdGenerator is {}", cosIdGenerator.getClass().getName());

        log.info("[CONFIG] CustomizeProcessEngineConfiguration postInit success");
    }

    public class CosIdGenerator implements IdGenerator {

        private final SnowflakeId snowflakeId;

        public CosIdGenerator(SnowflakeId snowflakeId) {
            this.snowflakeId = snowflakeId;
        }

        @Override
        public String getNextId() {
            return snowflakeId.generate() + "";
        }
    }
}
