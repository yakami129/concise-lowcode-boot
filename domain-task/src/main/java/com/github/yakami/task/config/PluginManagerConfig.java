package com.github.yakami.task.config;

import com.github.yakami.task.plugin.PluginManagerScanner;
import com.github.yakami.task.plugin.PluginManagerService;
import com.github.yakami.task.plugin.PluginProperties;
import lombok.extern.slf4j.Slf4j;
import org.pf4j.PluginManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by alan on 2023/3/28.
 */
@Slf4j
@Configuration
public class PluginManagerConfig {

    @Bean
    public PluginManager initPluginManager(PluginProperties pluginProperties) {
        PluginManagerScanner pluginManagerScanner = new PluginManagerScanner(pluginProperties);
        return pluginManagerScanner.getPluginManager();
    }

    @Bean
    public PluginManagerService initPluginManagerService(PluginManager pluginManager) {
        return new PluginManagerService(pluginManager);
    }

}
