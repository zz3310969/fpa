package com.roof.advisory.advisoryorder.entity;

import javax.persistence.Id;
import java.util.Date;
import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author 模版生成 <br/>
 *         表名： z_advisory_order <br/>
 *         描述：订单 <br/>
 */
@ApiModel(value = "z_advisory_order", description = "订单")
public class AdvisoryOrder implements Serializable {
	// 需要手动添加非默认的serialVersionUID
	@ApiModelProperty(value = "主键")
	protected Long id;// 主键
	@ApiModelProperty(value = "订单编号")
	protected String orderNum;// 订单编号
	@ApiModelProperty(value = "客户编号")
	protected Long customId;// 客户编号
	@ApiModelProperty(value = "服务产品id")
	protected Long productIdId;// 服务产品id
	@ApiModelProperty(value = "客户电话")
	protected String tel;// 客户电话
	@ApiModelProperty(value = "服务时长（分钟）")
	protected Long lenTime;// 服务时长（分钟）
	@ApiModelProperty(value = "订单金额")
	protected Double orderAmount;// 订单金额
	@ApiModelProperty(value = "支付金额")
	protected Long payAmount;// 支付金额
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "下单时间")
	protected Date orderTime;// 下单时间
	@ApiModelProperty(value = "订单状态")
	protected Integer orderStatus;// 订单状态
	@ApiModelProperty(value = "逻辑删除状态")
	protected Integer state;// 逻辑删除状态

	public AdvisoryOrder() {
		super();
	}

	public AdvisoryOrder(Long id) {
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
	
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	
	public Long getCustomId() {
		return customId;
	}
	public void setCustomId(Long customId) {
		this.customId = customId;
	}
	
	public Long getProductIdId() {
		return productIdId;
	}
	public void setProductIdId(Long productIdId) {
		this.productIdId = productIdId;
	}
	
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	public Long getLenTime() {
		return lenTime;
	}
	public void setLenTime(Long lenTime) {
		this.lenTime = lenTime;
	}
	
	public Double getOrderAmount() {
		return orderAmount;
	}
	public void setOrderAmount(Double orderAmount) {
		this.orderAmount = orderAmount;
	}
	
	public Long getPayAmount() {
		return payAmount;
	}
	public void setPayAmount(Long payAmount) {
		this.payAmount = payAmount;
	}
	
	public Date getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}
	
	public Integer getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
}