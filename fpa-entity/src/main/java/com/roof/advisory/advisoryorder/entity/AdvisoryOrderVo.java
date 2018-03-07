package com.roof.advisory.advisoryorder.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * @author 模版生成 <br/>
 * 表名： z_advisory_order <br/>
 * 描述：订单 <br/>
 */
public class AdvisoryOrderVo extends AdvisoryOrder {

    private List<AdvisoryOrderVo> advisoryOrderList;

    private String customName;
    private String productName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date orderTimeStart;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date orderTimeEnd;

    public AdvisoryOrderVo() {
        super();
    }

    public AdvisoryOrderVo(Long id) {
        super();
        this.id = id;
    }

    public List<AdvisoryOrderVo> getAdvisoryOrderList() {
        return advisoryOrderList;
    }

    public void setAdvisoryOrderList(List<AdvisoryOrderVo> advisoryOrderList) {
        this.advisoryOrderList = advisoryOrderList;
    }

    public String getCustomName() {
        return customName;
    }

    public void setCustomName(String customName) {
        this.customName = customName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Date getOrderTimeStart() {
        return orderTimeStart;
    }

    public void setOrderTimeStart(Date orderTimeStart) {
        this.orderTimeStart = orderTimeStart;
    }

    public Date getOrderTimeEnd() {
        return orderTimeEnd;
    }

    public void setOrderTimeEnd(Date orderTimeEnd) {
        this.orderTimeEnd = orderTimeEnd;
    }
}
