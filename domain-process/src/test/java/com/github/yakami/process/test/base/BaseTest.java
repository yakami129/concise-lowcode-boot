package com.github.yakami.process.test.base;

import com.github.yakami.library.infrastructure.common.env.LoadLocalEnvConfigFactory;
import com.github.yakami.process.ProcessApplication;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Created by alan on 2022/5/12.
 */
@SpringBootTest(classes = {
        ProcessApplication.class,
},
        properties = {"spring.profiles.active=local"})
public class BaseTest {

    @BeforeEach
    public void beforeEach(){
        LoadLocalEnvConfigFactory.loadLocalEnv();
    }

}
