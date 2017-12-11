package com.roof.fpa.account.entity;

import javax.persistence.Id;
import java.util.Date;
import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author 模版生成 <br/>
 *         表名： c_account <br/>
 *         描述：账户 <br/>
 */
@ApiModel(value = "c_account", description = "账户")
public class Account implements Serializable {
	// 需要手动添加非默认的serialVersionUID
	@ApiModelProperty(value = "主键")
	protected Long id;// 主键
	@ApiModelProperty(value = "编号")
	protected String numb;// 编号
	@ApiModelProperty(value = "咨询师ID")
	protected Long counselorId;// 咨询师ID
	@ApiModelProperty(value = "账户可提现金额(分)")
	protected Integer amount;// 账户可提现金额(分)
    @JSONField(format = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "结算日期")
	protected Date balanceDate;// 结算日期
	@ApiModelProperty(value = "状态")
	protected Integer state;// 状态

	public Account() {
		super();
	}

	public Account(Long id) {
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
	
	public Long getCounselorId() {
		return counselorId;
	}
	public void setCounselorId(Long counselorId) {
		this.counselorId = counselorId;
	}
	
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	
	public Date getBalanceDate() {
		return balanceDate;
	}
	public void setBalanceDate(Date balanceDate) {
		this.balanceDate = balanceDate;
	}
	
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
}
