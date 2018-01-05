package com.roof.fpa.withdraw.service.impl;

import com.alibaba.fastjson.JSON;
import com.roof.fpa.withdraw.entity.Withdraw;
import com.roof.fpa.withdraw.entity.WithdrawVo;
import com.roof.fpa.withdraw.service.api.IWithdrawService;
import com.roof.fpa.withdraw.service.api.IWithdrawWorkflowService;
import com.sun.xml.internal.rngom.parse.host.Base;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricProcessInstanceQuery;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.roof.roof.dataaccess.api.Page;
import org.roof.spring.Result;
import org.roof.web.role.entity.BaseRole;
import org.roof.web.user.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
public class WithdrawWorkflowService implements IWithdrawWorkflowService {

    private static Logger logger = LoggerFactory.getLogger(WithdrawWorkflowService.class);

    @Autowired
    private IWithdrawService withdrawService;

    private RuntimeService runtimeService;

    protected TaskService taskService;

    protected HistoryService historyService;

    protected RepositoryService repositoryService;

    @Autowired
    private IdentityService identityService;

    /**
     * 启动流程
     *
     * @param entity
     */
    public ProcessInstance startWorkflow(Withdraw entity, Map<String, Object> variables) {
        withdrawService.save(entity);
        logger.info("save entity: {}", entity);
        String businessKey = entity.getId().toString();

        ProcessInstance processInstance = null;
        try {
            // 用来设置启动流程的人员ID，引擎会自动把用户ID保存到activiti:initiator中
            identityService.setAuthenticatedUserId(entity.getProcessUserId());

            processInstance = runtimeService.startProcessInstanceByKey("withdraw", businessKey, variables);
            String processInstanceId = processInstance.getId();
            entity.setProcessInstanceId(processInstanceId);
            logger.info("start process of {key={}, bkey={}, pid={}, variables={}}",
                    new Object[]{"Withdraw", businessKey, processInstanceId, variables});
            withdrawService.updateIgnoreNull(entity);
        } finally {

        }
        identityService.setAuthenticatedUserId(null);
        return processInstance;
    }

    public void completeWorkflow(String taskId, Map<String, Object> variables) throws Exception {
        taskService.complete(taskId, variables);
    }


    /**
     * 查询待办任务
     *
     * @param user
     * @return
     */
    @Transactional(readOnly = true)
    public List<WithdrawVo> findTodoTasks(User user, Page page, int[] pageParams) {
        List<WithdrawVo> results = new ArrayList<WithdrawVo>();
        List<String> roles = new ArrayList<String>();
        for (BaseRole role :
                user.getRoles()) {
            roles.add(role.getId().toString());
        }

        // 根据当前人的ID查询
//        TaskQuery taskQuery = taskService.createTaskQuery().taskCandidateOrAssigned(userId);
        TaskQuery taskQuery = taskService.createTaskQuery().taskCandidateGroupIn(roles);
        List<Task> tasks = taskQuery.list();

        // 根据流程的业务ID查询实体并关联
        for (Task task : tasks) {
            String processInstanceId = task.getProcessInstanceId();
            ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
                    .processInstanceId(processInstanceId).active().singleResult();
            if (processInstance == null) {
                continue;
            }
            String businessKey = processInstance.getBusinessKey();
            if (businessKey == null) {
                continue;
            }
            WithdrawVo withdrawVo = withdrawService.load(new Withdraw(new Long(businessKey)));
            withdrawVo.setTaskAssignee(task.getAssignee());
            withdrawVo.setTaskCreateTime(task.getCreateTime());
            withdrawVo.setTaskDefinitionKey(task.getTaskDefinitionKey());
            withdrawVo.setTaskId(task.getId());
            withdrawVo.setTaskName(task.getName());
            withdrawVo.setProcessDefinitionId(processInstance.getProcessDefinitionId());
            withdrawVo.setProcessDefinitionName(processInstance.getProcessDefinitionName());
            withdrawVo.setProcessDefinitionVersion(getProcessDefinition(processInstance.getProcessDefinitionId()).getVersion());
            //withdrawVo.setTask(task);
//            withdrawVo.setProcessInstance(processInstance);
//            withdrawVo.setProcessDefinition(getProcessDefinition(processInstance.getProcessDefinitionId()));
            results.add(withdrawVo);
        }

        page.setTotal(taskQuery.count());
        page.setDataList(results);
        return results;
    }


    /**
     * 读取运行中的流程
     *
     * @return
     */
    @Transactional(readOnly = true)
    public List<WithdrawVo> findRunningProcessInstaces(Page page, int[] pageParams) {
        List<WithdrawVo> results = new ArrayList<WithdrawVo>();
        ProcessInstanceQuery query = runtimeService.createProcessInstanceQuery().processDefinitionKey("withdraw")
                .active().orderByProcessInstanceId().desc();
        List<ProcessInstance> list = query.listPage(pageParams[0], pageParams[1]);

        // 关联业务实体
        for (ProcessInstance processInstance : list) {
            String businessKey = processInstance.getBusinessKey();
            if (businessKey == null) {
                continue;
            }
            WithdrawVo withdrawVo = withdrawService.load(new Withdraw(new Long(businessKey)));

            withdrawVo.setProcessDefinitionId(processInstance.getProcessDefinitionId());
            withdrawVo.setProcessDefinitionName(processInstance.getProcessDefinitionName());
            withdrawVo.setProcessDefinitionVersion(getProcessDefinition(processInstance.getProcessDefinitionId()).getVersion());

//            withdrawVo.setProcessInstance(processInstance);
//            withdrawVo.setProcessDefinition(getProcessDefinition(processInstance.getProcessDefinitionId()));
            results.add(withdrawVo);

            // 设置当前任务信息
            List<Task> tasks = taskService.createTaskQuery().processInstanceId(processInstance.getId()).active()
                    .orderByTaskCreateTime().desc().listPage(0, 1);
            withdrawVo.setTaskAssignee(tasks.get(0).getAssignee());
            withdrawVo.setTaskCreateTime(tasks.get(0).getCreateTime());
            withdrawVo.setTaskDefinitionKey(tasks.get(0).getTaskDefinitionKey());
            withdrawVo.setTaskId(tasks.get(0).getId());
            withdrawVo.setTaskName(tasks.get(0).getName());
//            withdrawVo.setTask(tasks.get(0));
        }

        page.setTotal(query.count());
        page.setDataList(results);
        return results;
    }

    /**
     * 读取已结束中的流程
     *
     * @return
     */
    @Transactional(readOnly = true)
    public List<WithdrawVo> findFinishedProcessInstaces(Page page, int[] pageParams) {
        List<WithdrawVo> results = new ArrayList<WithdrawVo>();
        HistoricProcessInstanceQuery query = historyService.createHistoricProcessInstanceQuery()
                .processDefinitionKey("withdraw").finished().orderByProcessInstanceEndTime().desc();
        List<HistoricProcessInstance> list = query.listPage(pageParams[0], pageParams[1]);

        // 关联业务实体
        for (HistoricProcessInstance historicProcessInstance : list) {
            String businessKey = historicProcessInstance.getBusinessKey();
            WithdrawVo withdrawVo = withdrawService.load(new Withdraw(new Long(businessKey)));
            withdrawVo.setProcessDefinitionVersion(getProcessDefinition(historicProcessInstance.getProcessDefinitionId()).getVersion());

//            withdrawVo.setHistoricProcessInstance(historicProcessInstance);
            results.add(withdrawVo);
        }
        page.setTotal(query.count());
        page.setDataList(results);
        return results;
    }

    /**
     * 查询流程定义对象
     *
     * @param processDefinitionId 流程定义ID
     * @return
     */
    public ProcessDefinition getProcessDefinition(String processDefinitionId) {
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .processDefinitionId(processDefinitionId).singleResult();
        return processDefinition;
    }


    @Autowired
    public void setRuntimeService(RuntimeService runtimeService) {
        this.runtimeService = runtimeService;
    }

    @Autowired
    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }

    @Autowired
    public void setHistoryService(HistoryService historyService) {
        this.historyService = historyService;
    }

    @Autowired
    public void setRepositoryService(RepositoryService repositoryService) {
        this.repositoryService = repositoryService;
    }

}
