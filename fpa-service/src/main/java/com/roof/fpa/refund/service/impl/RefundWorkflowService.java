package com.roof.fpa.refund.service.impl;

import com.roof.fpa.refund.entity.Refund;
import com.roof.fpa.refund.entity.RefundVo;
import com.roof.fpa.refund.service.api.IRefundService;
import com.roof.fpa.refund.service.api.IRefundWorkflowService;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricProcessInstanceQuery;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.roof.roof.dataaccess.api.Page;
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
public class RefundWorkflowService implements IRefundWorkflowService {

    private static Logger logger = LoggerFactory.getLogger(RefundWorkflowService.class);

    @Autowired
    private IRefundService refundService;

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
    public ProcessInstance startWorkflow(Refund entity, Map<String, Object> variables) {
        refundService.save(entity);
        logger.info("save entity: {}", entity);
        String businessKey = entity.getId().toString();

        ProcessInstance processInstance = null;
        try {
            // 用来设置启动流程的人员ID，引擎会自动把用户ID保存到activiti:initiator中
            identityService.setAuthenticatedUserId(entity.getProcessUserId());

            processInstance = runtimeService.startProcessInstanceByKey("workflow-refund", businessKey, variables);
            String processInstanceId = processInstance.getId();
            entity.setProcessInstanceId(processInstanceId);
            logger.info("start process of {key={}, bkey={}, pid={}, variables={}}",
                    new Object[]{"Refund", businessKey, processInstanceId, variables});
            refundService.updateIgnoreNull(entity);
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
    public List<RefundVo> findTodoTasks(User user, Page page, int[] pageParams) {
        List<RefundVo> results = new ArrayList<RefundVo>();
        List<String> roles = new ArrayList<String>();
        for (BaseRole role :
                user.getRoles()) {
            roles.add(role.getId().toString());
        }

        // 根据当前人的ID查询
//        TaskQuery taskQuery = taskService.createTaskQuery().taskCandidateOrAssigned(userId);
        TaskQuery taskQuery = taskService.createTaskQuery().taskCandidateGroupIn(roles).processDefinitionKey("workflow-refund");
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
            RefundVo refundVo = refundService.load(new Refund(new Long(businessKey)));
            refundVo.setTaskAssignee(task.getAssignee());
            refundVo.setTaskCreateTime(task.getCreateTime());
            refundVo.setTaskDefinitionKey(task.getTaskDefinitionKey());
            refundVo.setTaskId(task.getId());
            refundVo.setTaskName(task.getName());
            refundVo.setProcessDefinitionId(processInstance.getProcessDefinitionId());
            refundVo.setProcessDefinitionName(processInstance.getProcessDefinitionName());
            refundVo.setProcessDefinitionVersion(getProcessDefinition(processInstance.getProcessDefinitionId()).getVersion());
            //RefundVo.setTask(task);
//            RefundVo.setProcessInstance(processInstance);
//            RefundVo.setProcessDefinition(getProcessDefinition(processInstance.getProcessDefinitionId()));
            results.add(refundVo);
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
    public List<RefundVo> findRunningProcessInstaces(Page page, int[] pageParams) {
        List<RefundVo> results = new ArrayList<RefundVo>();
        ProcessInstanceQuery query = runtimeService.createProcessInstanceQuery().processDefinitionKey("workflow-refund")
                .active().orderByProcessInstanceId().desc();
        List<ProcessInstance> list = query.listPage(pageParams[0], pageParams[1]);

        // 关联业务实体
        for (ProcessInstance processInstance : list) {
            String businessKey = processInstance.getBusinessKey();
            if (businessKey == null) {
                continue;
            }
            RefundVo refundVo = refundService.load(new Refund(new Long(businessKey)));

            refundVo.setProcessDefinitionId(processInstance.getProcessDefinitionId());
            refundVo.setProcessDefinitionName(processInstance.getProcessDefinitionName());
            refundVo.setProcessDefinitionVersion(getProcessDefinition(processInstance.getProcessDefinitionId()).getVersion());

//            RefundVo.setProcessInstance(processInstance);
//            RefundVo.setProcessDefinition(getProcessDefinition(processInstance.getProcessDefinitionId()));
            results.add(refundVo);

            // 设置当前任务信息
            List<Task> tasks = taskService.createTaskQuery().processInstanceId(processInstance.getId()).active()
                    .orderByTaskCreateTime().desc().listPage(0, 1);
            refundVo.setTaskAssignee(tasks.get(0).getAssignee());
            refundVo.setTaskCreateTime(tasks.get(0).getCreateTime());
            refundVo.setTaskDefinitionKey(tasks.get(0).getTaskDefinitionKey());
            refundVo.setTaskId(tasks.get(0).getId());
            refundVo.setTaskName(tasks.get(0).getName());
//            RefundVo.setTask(tasks.get(0));
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
    public List<RefundVo> findFinishedProcessInstaces(Page page, int[] pageParams) {
        List<RefundVo> results = new ArrayList<RefundVo>();
        HistoricProcessInstanceQuery query = historyService.createHistoricProcessInstanceQuery()
                .processDefinitionKey("Refund").finished().orderByProcessInstanceEndTime().desc();
        List<HistoricProcessInstance> list = query.listPage(pageParams[0], pageParams[1]);

        // 关联业务实体
        for (HistoricProcessInstance historicProcessInstance : list) {
            String businessKey = historicProcessInstance.getBusinessKey();
            RefundVo refundVo = refundService.load(new Refund(new Long(businessKey)));
            refundVo.setProcessDefinitionVersion(getProcessDefinition(historicProcessInstance.getProcessDefinitionId()).getVersion());

//            RefundVo.setHistoricProcessInstance(historicProcessInstance);
            results.add(refundVo);
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
