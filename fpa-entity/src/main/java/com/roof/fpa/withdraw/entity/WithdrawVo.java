package com.roof.fpa.withdraw.entity;

import java.util.List;
import java.util.Map;


import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

/**
 * @author 模版生成 <br/>
 *         表名： c_withdraw <br/>
 *         描述：提现记录 <br/>
 */
public class WithdrawVo extends Withdraw {

    private List<WithdrawVo> withdrawList;

    // -- 临时属性 --//
    // 流程任务
    private Task task;

    private Map<String, Object> variables;

    // 运行中的流程实例
    private ProcessInstance processInstance;

    // 历史的流程实例
    private HistoricProcessInstance historicProcessInstance;

    // 流程定义
    private ProcessDefinition processDefinition;

    public WithdrawVo() {
        super();
    }

    public WithdrawVo(Long id) {
        super();
        this.id = id;
    }

    public List<WithdrawVo> getWithdrawList() {
        return withdrawList;
    }

    public void setWithdrawList(List<WithdrawVo> withdrawList) {
        this.withdrawList = withdrawList;
    }


    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Map<String, Object> getVariables() {
        return variables;
    }

    public void setVariables(Map<String, Object> variables) {
        this.variables = variables;
    }

    public ProcessInstance getProcessInstance() {
        return processInstance;
    }

    public void setProcessInstance(ProcessInstance processInstance) {
        this.processInstance = processInstance;
    }

    public HistoricProcessInstance getHistoricProcessInstance() {
        return historicProcessInstance;
    }

    public void setHistoricProcessInstance(HistoricProcessInstance historicProcessInstance) {
        this.historicProcessInstance = historicProcessInstance;
    }

    public ProcessDefinition getProcessDefinition() {
        return processDefinition;
    }

    public void setProcessDefinition(ProcessDefinition processDefinition) {
        this.processDefinition = processDefinition;
    }
}
