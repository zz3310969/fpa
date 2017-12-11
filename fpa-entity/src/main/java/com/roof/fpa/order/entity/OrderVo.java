package com.roof.fpa.order.entity;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * @author 模版生成 <br/>
 *         表名： c_order <br/>
 *         描述：订单 <br/>
 */
public class OrderVo extends Order {

	private List<OrderVo> orderList;

	@ApiModelProperty(value = "客户名称")
	private String customerName;// 客户名称
	@ApiModelProperty(value = "咨询师名称")
	private String counselorName;// 咨询师名称

	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createTimeStart;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createTimeEnd;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date payTimeStart;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date payTimeEnd;

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

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCounselorName() {
		return counselorName;
	}

	public void setCounselorName(String counselorName) {
		this.counselorName = counselorName;
	}

	public Date getCreateTimeStart() {
		return createTimeStart;
	}

	public void setCreateTimeStart(Date createTimeStart) {
		this.createTimeStart = createTimeStart;
	}

	public Date getCreateTimeEnd() {
		return createTimeEnd;
	}

	public void setCreateTimeEnd(Date createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}

	public Date getPayTimeStart() {
		return payTimeStart;
	}

	public void setPayTimeStart(Date payTimeStart) {
		this.payTimeStart = payTimeStart;
	}

	public Date getPayTimeEnd() {
		return payTimeEnd;
	}

	public void setPayTimeEnd(Date payTimeEnd) {
		this.payTimeEnd = payTimeEnd;
	}
}
