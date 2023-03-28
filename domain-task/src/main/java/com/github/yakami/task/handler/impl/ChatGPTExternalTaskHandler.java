package com.github.yakami.task.handler.impl;

import com.github.yakami.task.handler.AbstractExternalTaskHandler;
import com.github.yakami.task.interceptor.core.ExternalTaskResponse;
import io.github.yakami129.starter.chatgpt.ChatGPT;
import io.github.yakami129.starter.chatgpt.util.Proxys;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.math.NumberUtils;
import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.springframework.stereotype.Component;

import java.net.Proxy;
import java.util.Map;

/**
 * Created by alan on 2023/3/27.
 */
@Slf4j
@Component
@ExternalTaskSubscription(topicName = "chatgpt-topic", includeExtensionProperties = true)
public class ChatGPTExternalTaskHandler extends AbstractExternalTaskHandler {

    @Override
    public ExternalTaskResponse run(ExternalTask externalTask,
                                    ExternalTaskService externalTaskService,
                                    Map<String, Object> allVariables,
                                    Map<String, String> extensionProperties) {

        // 初始化ChatGPT组件
        final String apiKey = extensionProperties.get("apiKey");
        final String apiHost = extensionProperties.get("apiHost");
        final String proxyHost = extensionProperties.get("proxyHost");
        final Integer proxyPort = NumberUtils.toInt(extensionProperties.get("proxyPort"));
        Proxy proxy = Proxys.http(proxyHost, proxyPort);
        final ChatGPT chatGPT = ChatGPT.builder()
                .openAIKey(apiKey)
                .openaiHost(apiHost)
                .proxy(proxy)
                .build()
                .init();


        // 调用ChatGPT生成内容
        final String chatMessage = allVariables.get("chatMessage").toString();
        final String chatResponse = chatGPT.chatRequest().chat(chatMessage);
        if (log.isDebugEnabled()) {
            log.debug("[BIZ] \nQ:{} \nA:{}", chatMessage, chatResponse);
        }

        final ExternalTaskResponse externalTaskResponse = ExternalTaskResponse.builder().build();
        externalTaskResponse.addVariable("chatResponse", chatResponse);
        return externalTaskResponse;
    }

}
