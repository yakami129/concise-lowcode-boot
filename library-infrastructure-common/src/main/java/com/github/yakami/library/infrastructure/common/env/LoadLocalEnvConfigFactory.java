package com.github.yakami.library.infrastructure.common.env;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by alan on 2022/11/7.
 */
@Slf4j
public class LoadLocalEnvConfigFactory {

    private static final String ENV_LOCAL = "local";

    public static void loadLocalEnv() {
        Properties systemProperties = System.getProperties();
        final String active = systemProperties.getProperty("spring.profiles.active");
        if (ENV_LOCAL.equals(active))
            try {
                Properties localEvn = new Properties();
                localEvn.load(IoUtil.toStream(FileUtil.file("config/env.properties")));
                localEvn.forEach((k, v) -> {
                    systemProperties.setProperty(k.toString(), v.toString());
                });
                log.info("[CONFIG] 加载本地配置成功");
            } catch (IOException e) {
                log.error("[ERROR] 加载配置失败");
            }
    }

}
