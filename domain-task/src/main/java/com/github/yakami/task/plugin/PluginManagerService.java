package com.github.yakami.task.plugin;

import org.apache.commons.collections4.CollectionUtils;
import org.pf4j.PluginManager;

import java.text.MessageFormat;
import java.util.List;

/**
 * Created by alan on 2023/3/28.
 */
public class PluginManagerService {

    private PluginManager pluginManager;

    public PluginManagerService(PluginManager pluginManager) {
        this.pluginManager = pluginManager;
    }

    /**
     * 获取插件实现类
     *
     * @param clazz    插件接口类
     * @param pluginId 插件id
     * @param <E>
     * @return
     */
    public <E> E getExtensions(Class<E> clazz, String pluginId) {
        final List<E> extensions = pluginManager.getExtensions(clazz, pluginId);
        if (CollectionUtils.isEmpty(extensions)) {
            throw new IllegalArgumentException(MessageFormat.format("clazz {0} does not have an extension point", clazz.getName()));
        }
        return extensions.stream().findFirst().get();
    }

}
