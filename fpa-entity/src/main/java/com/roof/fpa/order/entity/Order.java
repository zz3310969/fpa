package com.roof.fpa.order.entity;

import javax.persistence.Id;
import java.util.Date;
import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author 模版生成 <br/>
 *         表名： c_order <br/>
 *         描述：订单 <br/>
 */
@ApiModel(value = "c_order", description = "订单")
public class Order implements Serializable {
	// 需要手动添加非默认的serialVersionUID
	@ApiModelProperty(value = "主键")
	protected Long id;// 主键
	@ApiModelProperty(value = "客户ID")
	protected Long customerId;// 客户ID
	@ApiModelProperty(value = "咨询师ID")
	protected Long counselorId;// 咨询师ID
	@ApiModelProperty(value = "服务记录ID")
	protected Long serviceRecordId;// 服务记录ID
	@ApiModelProperty(value = "价格")
	protected Integer price;// 价格
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "创建时间")
	protected Date createTime;// 创建时间
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "支付时间")
	protected Date payTime;// 支付时间
	@ApiModelProperty(value = "订单状态")
	protected Integer state;// 订单状态
	@ApiModelProperty(value = "账户金额变更详情ID")
	protected Long accountDetailId;// 账户金额变更详情ID

	public Order() {
		super();
	}

	public Order(Long id) {
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
	
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	public Long getCounselorId() {
		return counselorId;
	}
	public void setCounselorId(Long counselorId) {
		this.counselorId = counselorId;
	}
	
	public Long getServiceRecordId() {
		return serviceRecordId;
	}
	public void setServiceRecordId(Long serviceRecordId) {
		this.serviceRecordId = serviceRecordId;
	}
	
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public Date getPayTime() {
		return payTime;
	}
	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}
	
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	
	public Long getAccountDetailId() {
		return accountDetailId;
	}
	public void setAccountDetailId(Long accountDetailId) {
		this.accountDetailId = accountDetailId;
	}
}
