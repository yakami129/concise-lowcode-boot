package com.github.yakami.task.plugin;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by alan on 2023/3/28.
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "plugin")
public class PluginProperties {

    /**
     * 插件地址
     */
    private String pluginPath;

}
