package com.roof.fpa.order.entity;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author 模版生成 <br/>
 *         表名： c_order <br/>
 *         描述：订单 <br/>
 */
public class OrderVo extends Order {

	private List<OrderVo> orderList;

	@ApiModelProperty(value = "客户名称")
	protected Long customerName;// 客户名称
	@ApiModelProperty(value = "咨询师名称")
	protected Long counselorName;// 咨询师名称

	public OrderVo() {
		super();
	}

	public OrderVo(Long id) {
		super();
		this.id = id;
	}

	public List<OrderVo> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<OrderVo> orderList) {
		this.orderList = orderList;
	}

	public Long getCustomerName() {
		return customerName;
	}

	public void setCustomerName(Long customerName) {
		this.customerName = customerName;
	}

	public Long getCounselorName() {
		return counselorName;
	}

	public void setCounselorName(Long counselorName) {
		this.counselorName = counselorName;
	}
}
