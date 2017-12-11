package com.roof.fpa.accountdetail.entity;

import javax.persistence.Id;
import java.util.Date;
import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author 模版生成 <br/>
 *         表名： c_account_detail <br/>
 *         描述：账户金额变更详情 <br/>
 */
@ApiModel(value = "c_account_detail", description = "账户金额变更详情")
public class AccountDetail implements Serializable {
	// 需要手动添加非默认的serialVersionUID
	@ApiModelProperty(value = "主键")
	protected Long id;// 主键
	@ApiModelProperty(value = "账户id")
	protected Long accountId;// 账户id
	@ApiModelProperty(value = "类型(提现,结算,退款)")
	protected String type;// 类型(提现,结算,退款)
	@ApiModelProperty(value = "变更金额")
	protected Integer amount;// 变更金额
	@ApiModelProperty(value = "状态")
	protected Integer state;// 状态
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "创建时间")
	protected Date createTime;// 创建时间
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "完成时间")
	protected Date completeTime;// 完成时间
	@ApiModelProperty(value = "变更之前金额")
	protected Integer beforeAmount;// 变更之前金额
	@ApiModelProperty(value = "变更之后金额")
	protected Integer afterAmount;// 变更之后金额

	public AccountDetail() {
		super();
	}

	public AccountDetail(Long id) {
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
	
	public Long getAccountId() {
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
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
	
	public Integer getBeforeAmount() {
		return beforeAmount;
	}
	public void setBeforeAmount(Integer beforeAmount) {
		this.beforeAmount = beforeAmount;
	}
	
	public Integer getAfterAmount() {
		return afterAmount;
	}
	public void setAfterAmount(Integer afterAmount) {
		this.afterAmount = afterAmount;
	}
}
