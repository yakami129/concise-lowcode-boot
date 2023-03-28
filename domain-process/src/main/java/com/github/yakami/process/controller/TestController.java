package com.github.yakami.process.controller;

import com.github.yakami.library.infrastructure.common.dto.ResultBean;
import com.github.yakami.library.infrastructure.common.utils.lambda.LambdaUtil;
import com.github.yakami.library.infrastructure.rpc.process.ProductInfoGrpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.repository.Deployment;
import org.camunda.bpm.engine.repository.ProcessDefinition;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by alan on 2023/3/21.
 */
@Api(produces = "application/json", tags = "测试类")
@RestController
@RequestMapping("/test")
public class TestController {

    @GrpcClient("domain-task")
    private ProductInfoGrpc.ProductInfoBlockingStub productInfoBlockingStub;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private TaskService taskService;

    @GetMapping("/deployment/list")
    @ApiOperation(value = "查看部署的流程列表")
    public ResultBean getDeployment() {
        final List<Deployment> deployments = repositoryService.createDeploymentQuery().list();
        return ResultBean.succeed(LambdaUtil.list2list(deployments, Deployment::getName));
    }

    @GetMapping("/ProcessDefinition/list")
    @ApiOperation(value = "查看流程定义列表")
    public ResultBean getProcessDefinitions() {
        final List<ProcessDefinition> processDefinitions = repositoryService.createProcessDefinitionQuery().list();
        return ResultBean.succeed(LambdaUtil.list2list(processDefinitions, ProcessDefinition::getKey));
    }


    @GetMapping("/startProcess")
    @ApiOperation(value = "启动流程")
    public ResultBean startProcess(@RequestParam String processDefinitionKey) {
        final ProcessInstance loanRequest = runtimeService.startProcessInstanceByKey(processDefinitionKey);
        return ResultBean.succeed(loanRequest.getId());
    }

    @PostMapping("/startProcessAndVariables")
    @ApiOperation(value = "启动流程")
    public ResultBean startProcess(@RequestBody Map<String, Object> variables) {
        final String processDefinitionKey = variables.get("processDefinitionKey").toString();
        final ProcessInstance loanRequest = runtimeService.startProcessInstanceByKey(processDefinitionKey,variables);
        return ResultBean.succeed(loanRequest.getId());
    }

    @GetMapping("/task/list")
    @ApiOperation(value = "查看任务列表")
    public ResultBean getTaskList(@RequestParam String processInstanceId) {
        final List<Task> tasks = taskService.createTaskQuery().processInstanceId(processInstanceId).list();
        return ResultBean.succeed(LambdaUtil.list2list(tasks, Task::getId));
    }

    @GetMapping("/task/complete")
    @ApiOperation(value = "提交任务")
    public ResultBean completeTask(@RequestParam String taskId) {
        taskService.complete(taskId);
        return ResultBean.succeed();
    }

}
