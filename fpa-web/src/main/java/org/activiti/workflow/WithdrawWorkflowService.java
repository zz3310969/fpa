package org.activiti.workflow;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.roof.fpa.withdraw.entity.WithdrawVo;
import com.roof.fpa.withdraw.entity.Withdraw;
import com.roof.fpa.withdraw.service.api.IWithdrawService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricProcessInstanceQuery;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.activiti.util.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
public class WithdrawWorkflowService {

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
        } finally {
            identityService.setAuthenticatedUserId(null);
        }
        return processInstance;
    }

    /**
     * 查询待办任务
     *
     * @param userId 用户ID
     * @return
     */
    @Transactional(readOnly = true)
    public List<WithdrawVo> findTodoTasks(String userId, Page<WithdrawVo> page, int[] pageParams) {
        List<WithdrawVo> results = new ArrayList<WithdrawVo>();

        // 根据当前人的ID查询
        TaskQuery taskQuery = taskService.createTaskQuery().taskCandidateOrAssigned(userId);
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
            withdrawVo.setTask(task);
            withdrawVo.setProcessInstance(processInstance);
            withdrawVo.setProcessDefinition(getProcessDefinition(processInstance.getProcessDefinitionId()));
            results.add(withdrawVo);
        }

        page.setTotalCount(taskQuery.count());
        page.setResult(results);
        return results;
    }

    /**
     * 读取运行中的流程
     *
     * @return
     */
    @Transactional(readOnly = true)
    public List<WithdrawVo> findRunningProcessInstaces(Page<WithdrawVo> page, int[] pageParams) {
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
            withdrawVo.setProcessInstance(processInstance);
            withdrawVo.setProcessDefinition(getProcessDefinition(processInstance.getProcessDefinitionId()));
            results.add(withdrawVo);

            // 设置当前任务信息
            List<Task> tasks = taskService.createTaskQuery().processInstanceId(processInstance.getId()).active()
                    .orderByTaskCreateTime().desc().listPage(0, 1);
            withdrawVo.setTask(tasks.get(0));
        }

        page.setTotalCount(query.count());
        page.setResult(results);
        return results;
    }

    /**
     * 读取已结束中的流程
     *
     * @return
     */
    @Transactional(readOnly = true)
    public List<WithdrawVo> findFinishedProcessInstaces(Page<WithdrawVo> page, int[] pageParams) {
        List<WithdrawVo> results = new ArrayList<WithdrawVo>();
        HistoricProcessInstanceQuery query = historyService.createHistoricProcessInstanceQuery()
                .processDefinitionKey("withdraw").finished().orderByProcessInstanceEndTime().desc();
        List<HistoricProcessInstance> list = query.listPage(pageParams[0], pageParams[1]);

        // 关联业务实体
        for (HistoricProcessInstance historicProcessInstance : list) {
            String businessKey = historicProcessInstance.getBusinessKey();
            WithdrawVo withdrawVo = withdrawService.load(new Withdraw(new Long(businessKey)));
            withdrawVo.setProcessDefinition(getProcessDefinition(historicProcessInstance.getProcessDefinitionId()));
            withdrawVo.setHistoricProcessInstance(historicProcessInstance);
            results.add(withdrawVo);
        }
        page.setTotalCount(query.count());
        page.setResult(results);
        return results;
    }

    /**
     * 查询流程定义对象
     *
     * @param processDefinitionId 流程定义ID
     * @return
     */
    protected ProcessDefinition getProcessDefinition(String processDefinitionId) {
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
