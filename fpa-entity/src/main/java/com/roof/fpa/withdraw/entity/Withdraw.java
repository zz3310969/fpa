package com.roof.fpa.withdraw.entity;

import javax.persistence.Id;
import java.util.Date;
import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author 模版生成 <br/>
 *         表名： c_withdraw <br/>
 *         描述：提现记录 <br/>
 */
@ApiModel(value = "c_withdraw", description = "提现记录")
public class Withdraw implements Serializable {
	// 需要手动添加非默认的serialVersionUID
	@ApiModelProperty(value = "主键")
	protected Long id;// 主键
	@ApiModelProperty(value = "申请编号")
	protected String numb;// 申请编号
	@ApiModelProperty(value = "提现账户")
	protected Long accountId;// 提现账户
	@ApiModelProperty(value = "提现金额")
	protected Integer amount;// 提现金额
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "申请时间")
	protected Date createTime;// 申请时间
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "结算日期")
	protected Date completeTime;// 结算日期
	@ApiModelProperty(value = "状态")
	protected Integer state;// 状态
	@ApiModelProperty(value = "流程实例id")
	private String processInstanceId;
	@ApiModelProperty(value = "流程创建者id")
	private String processUserId;

	public Withdraw() {
		super();
	}

	public Withdraw(Long id) {
		super();
		this.id = id;
	}
	
	@Id// 主键
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNumb() {
		return numb;
	}
	public void setNumb(String numb) {
		this.numb = numb;
	}
	
	public Long getAccountId() {
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public Date getCompleteTime() {
		return completeTime;
	}
	public void setCompleteTime(Date completeTime) {
		this.completeTime = completeTime;
	}
	
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public String getProcessUserId() {
		return processUserId;
	}

	public void setProcessUserId(String processUserId) {
		this.processUserId = processUserId;
	}
}
