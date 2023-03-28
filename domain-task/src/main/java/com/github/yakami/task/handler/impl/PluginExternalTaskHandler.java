package com.github.yakami.task.handler.impl;

import com.alibaba.fastjson2.JSON;
import com.github.yakami.library.infrastructure.common.utils.text.StringUtils;
import com.github.yakami.task.handler.AbstractExternalTaskHandler;
import com.github.yakami.task.interceptor.core.ExternalTaskResponse;
import com.github.yakami.task.plugin.PluginManagerService;
import io.github.yakami129.lowcode.plugin.api.ExternalTaskHandlerPlugin;
import io.github.yakami129.lowcode.plugin.api.dto.PluginRequestDTO;
import io.github.yakami129.lowcode.plugin.api.dto.PluginResponseDTO;
import io.github.yakami129.lowcode.plugin.api.enums.PluginResponseCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.Map;

/**
 * Created by alan on 2023/3/27.
 * 插件任务执行
 */
@Slf4j
@Component
@ExternalTaskSubscription(topicName = "plugin-topic", includeExtensionProperties = true)
public class PluginExternalTaskHandler extends AbstractExternalTaskHandler {

    @Autowired
    private PluginManagerService pluginManagerService;

    @Override
    public ExternalTaskResponse run(ExternalTask externalTask,
                                    ExternalTaskService externalTaskService,
                                    Map<String, Object> allVariables,
                                    Map<String, String> extensionProperties) {

        // 获取插件ID
        final String pluginId = extensionProperties.get("pluginId");
        if (StringUtils.isBlank(pluginId)) {
            throw new IllegalArgumentException("pluginId is null");
        }

        final ExternalTaskHandlerPlugin extensions = pluginManagerService.getExtensions(ExternalTaskHandlerPlugin.class, pluginId);
        PluginRequestDTO pluginRequestDTO = new PluginRequestDTO();
        pluginRequestDTO.setAllVariables(allVariables);
        pluginRequestDTO.setExtensionProperties(extensionProperties);
        final PluginResponseDTO pluginResponseDTO = extensions.invoke(pluginRequestDTO);
        if (log.isDebugEnabled()) {
            log.debug("[BIZ] # plugin invoke # pluginResponseDTO:{}", JSON.toJSONString(pluginResponseDTO));
        }

        if (!PluginResponseCodeEnum.success.getCode().equals(pluginResponseDTO.getCode())) {
            throw new IllegalArgumentException(MessageFormat.format("plugin[{0}] invoke error", pluginId));
        }

        return ExternalTaskResponse
                .builder()
                .outPutVariables(pluginResponseDTO.getData())
                .build();
    }

}
