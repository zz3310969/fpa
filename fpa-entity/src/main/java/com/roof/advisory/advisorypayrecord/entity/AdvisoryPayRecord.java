package com.roof.advisory.advisorypayrecord.entity;

import javax.persistence.Id;
import java.util.Date;
import java.io.Serializable;

import org.springframework.format.annotation.DateTimeFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author 模版生成 <br/>
 * 表名： z_advisory_pay_record <br/>
 * 描述：付款记录 <br/>
 */
@ApiModel(value = "z_advisory_pay_record", description = "付款记录")
public class AdvisoryPayRecord implements Serializable {
    // 需要手动添加非默认的serialVersionUID
    @ApiModelProperty(value = "id")
    protected Long id;// id
    @ApiModelProperty(value = "订单编号")
    protected Long orderId;// 订单编号
    @ApiModelProperty(value = "金额")
    protected Integer fee;// 金额
    @ApiModelProperty(value = "付款平台")
    protected String platform;// 付款平台
    @ApiModelProperty(value = "消息类型")
    protected String recordType;// 消息类型
    @ApiModelProperty(value = "预支付编码")
    protected String prepayId;// 预支付编码
    @ApiModelProperty(value = "请求报文")
    protected String requestData;// 请求报文
    @ApiModelProperty(value = "返回报文")
    protected String responseData;// 返回报文

    public AdvisoryPayRecord() {
        super();
    }

    public AdvisoryPayRecord(Long id) {
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

    public Integer getFee() {
        return fee;
    }

    public void setFee(Integer fee) {
        this.fee = fee;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getRecordType() {
        return recordType;
    }

    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }

    public String getPrepayId() {
        return prepayId;
    }

    public void setPrepayId(String prepayId) {
        this.prepayId = prepayId;
    }

    public String getRequestData() {
        return requestData;
    }

    public void setRequestData(String requestData) {
        this.requestData = requestData;
    }

    public String getResponseData() {
        return responseData;
    }

    public void setResponseData(String responseData) {
        this.responseData = responseData;
    }
}
