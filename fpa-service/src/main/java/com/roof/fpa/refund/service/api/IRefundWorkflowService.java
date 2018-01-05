package com.roof.fpa.refund.service.api;

import com.roof.fpa.refund.entity.Refund;
import com.roof.fpa.refund.entity.RefundVo;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.roof.roof.dataaccess.api.Page;
import org.roof.web.user.entity.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

public interface IRefundWorkflowService {

    /**
     * 启动流程
     *
     * @param entity
     */
    public ProcessInstance startWorkflow(Refund entity, Map<String, Object> variables);

    /**
     * 完成任务
     *
     * @param
     */
    public void completeWorkflow(String taskId, Map<String, Object> variables) throws Exception;

    /**
     * 查询待办任务
     *
     * @param user 用户ID
     * @return
     */
    @Transactional(readOnly = true)
    public List<RefundVo> findTodoTasks(User user, Page page, int[] pageParams);

    /**
     * 读取运行中的流程
     *
     * @return
     */
    @Transactional(readOnly = true)
    public List<RefundVo> findRunningProcessInstaces(Page page, int[] pageParams);

    /**
     * 读取已结束中的流程
     *
     * @return
     */
    @Transactional(readOnly = true)
    public List<RefundVo> findFinishedProcessInstaces(Page page, int[] pageParams);

    /**
     * 查询流程定义对象
     *
     * @param processDefinitionId 流程定义ID
     * @return
     */
    public ProcessDefinition getProcessDefinition(String processDefinitionId);
}