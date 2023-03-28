package com.github.yakami.task.plugin;

import com.github.yakami.library.infrastructure.common.utils.lambda.LambdaUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.pf4j.*;

import java.util.List;
import java.util.Properties;

/**
 * Created by alan on 2023/3/28.
 */
@Slf4j
public class PluginManagerScanner {

    private PluginProperties pluginProperties;
    private PluginManager pluginManager;

    public PluginManagerScanner(PluginProperties pluginProperties) {
        this.pluginProperties = pluginProperties;
        initPluginManager();
    }

    private void initPluginManager() {

        Properties systemProperties = System.getProperties();
        systemProperties.put("pf4j.pluginsDir", pluginProperties.getPluginPath());

        // create the plugin manager
        pluginManager = new DefaultPluginManager() {
            @Override
            protected CompoundPluginDescriptorFinder createPluginDescriptorFinder() {
                return new CompoundPluginDescriptorFinder()
                        // Demo is using the Manifest file
                        // PropertiesPluginDescriptorFinder is commented out just to avoid error log
                        //.add(new PropertiesPluginDescriptorFinder())
                        .add(new ManifestPluginDescriptorFinder());
            }
        };

        // load the plugins
        pluginManager.loadPlugins();
        // enable a disabled plugin
//        pluginManager.enablePlugin("welcome-plugin");
        // start (active/resolved) the plugins
        pluginManager.startPlugins();

        printLogo(pluginManager);
    }

    public PluginManager getPluginManager() {
        return pluginManager;
    }

    private void printLogo(PluginManager pluginManager) {
        final String pluginDirectory = System.getProperty("pf4j.pluginsDir", "plugins");
        final List<PluginWrapper> plugins = pluginManager.getPlugins();
        log.info(StringUtils.repeat("#", 60));
        log.info(StringUtils.center("Concise Lowcode Plugins", 60));
        log.info("pluginDirectory:{}", pluginDirectory);
        log.info("plugins:{}", LambdaUtil.list2list(plugins, PluginWrapper::getPluginId));
        log.info(StringUtils.repeat("#", 60));
    }

}
