package com.github.yakami.process.handle;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.springframework.stereotype.Service;

/**
 * Created by alan on 2023/3/22.
 */
@Slf4j
@Service
public class CreateTaskListener implements TaskListener {

    @Override
    public void notify(DelegateTask delegateTask) {
        log.info("[BIZ] create {}", delegateTask.getName());
    }
}
