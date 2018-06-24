package com.roof.advisory.advisoryorderrecord.entity;

import javax.persistence.Id;
import java.util.Date;
import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author 模版生成 <br/>
 *         表名： z_advisory_order_record <br/>
 *         描述：订单变更记录表 <br/>
 */
@ApiModel(value = "z_advisory_order_record", description = "订单变更记录表")
public class AdvisoryOrderRecord implements Serializable {
	// 需要手动添加非默认的serialVersionUID
	@ApiModelProperty(value = "id")
	protected Long id;// id
	@ApiModelProperty(value = "订单id")
	protected Long orderId;// 订单id
	@ApiModelProperty(value = "事件类型")
	protected String eventType;// 事件类型
	@ApiModelProperty(value = "事件")
	protected String event;// 事件
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "事件发生时间")
	protected Date eventTime;// 事件发生时间
	@ApiModelProperty(value = " 变更内容")
	protected String eventNote;//  变更内容

	public AdvisoryOrderRecord() {
		super();
	}

	public AdvisoryOrderRecord(Long id) {
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
	
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	
	public String getEventType() {
		return eventType;
	}
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	
	public Date getEventTime() {
		return eventTime;
	}
	public void setEventTime(Date eventTime) {
		this.eventTime = eventTime;
	}
	
	public String getEventNote() {
		return eventNote;
	}
	public void setEventNote(String eventNote) {
		this.eventNote = eventNote;
	}
}
