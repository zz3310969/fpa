package com.roof.fpa.withdraw.entity;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author 模版生成 <br/>
 *         表名： c_withdraw <br/>
 *         描述：提现记录 <br/>
 */
public class WithdrawVo extends Withdraw {

    private List<WithdrawVo> withdrawList;

    // -- 临时属性 --//
    // 运行中的流程实例
    //processInstance.processDefinitionId
    private String processDefinitionId;
    //processInstance.processDefinitionName
    private String processDefinitionName;
    // 流程定义
    //processDefinition.version
    private Integer processDefinitionVersion;
    // 流程任务
    //task.createTime
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date taskCreateTime;
    //task.id
    private String taskId;
    //task.name
    private String taskName;
    //task.assignee
    private String taskAssignee;
    //task.taskDefinitionKey
    private String taskDefinitionKey;

    private Map<String, Object> variables;

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

    public Map<String, Object> getVariables() {
        return variables;
    }

    public void setVariables(Map<String, Object> variables) {
        this.variables = variables;
    }

    public String getProcessDefinitionId() {
        return processDefinitionId;
    }

    public void setProcessDefinitionId(String processDefinitionId) {
        this.processDefinitionId = processDefinitionId;
    }

    public String getProcessDefinitionName() {
        return processDefinitionName;
    }

    public void setProcessDefinitionName(String processDefinitionName) {
        this.processDefinitionName = processDefinitionName;
    }

    public Integer getProcessDefinitionVersion() {
        return processDefinitionVersion;
    }

    public void setProcessDefinitionVersion(Integer processDefinitionVersion) {
        this.processDefinitionVersion = processDefinitionVersion;
    }

    public Date getTaskCreateTime() {
        return taskCreateTime;
    }

    public void setTaskCreateTime(Date taskCreateTime) {
        this.taskCreateTime = taskCreateTime;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskAssignee() {
        return taskAssignee;
    }

    public void setTaskAssignee(String taskAssignee) {
        this.taskAssignee = taskAssignee;
    }

    public String getTaskDefinitionKey() {
        return taskDefinitionKey;
    }

    public void setTaskDefinitionKey(String taskDefinitionKey) {
        this.taskDefinitionKey = taskDefinitionKey;
    }
}
