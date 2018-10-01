package org.roof.account.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * 账户流水
 */
public class AccountFlow {

    protected Long id;
    protected String serialNumber; //流水号
    protected Long userId;//用户ID
    protected Long accountId;//账户ID
    protected int amount;//金额
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    protected Date createTime;//生成时间
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    protected Date lastTime;//完成时间
    protected String type;//类型
    protected String remark;//备注
    protected int lastBalance;


    protected String tag1;
    protected String tag2;
    protected String tag3;
    protected String tag4;
    protected String tag5;

    public AccountFlow(){

    }
    public AccountFlow(Long userId,Long accountId, int amount){
        this.createTime = new Date();
        this.userId = userId;
        this.accountId = accountId;
        this.amount = amount;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }


    public int getLastBalance() {
        return lastBalance;
    }

    public void setLastBalance(int lastBalance) {
        this.lastBalance = lastBalance;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }

    public String getTag1() {
        return tag1;
    }

    public void setTag1(String tag1) {
        this.tag1 = tag1;
    }

    public String getTag2() {
        return tag2;
    }

    public void setTag2(String tag2) {
        this.tag2 = tag2;
    }

    public String getTag3() {
        return tag3;
    }

    public void setTag3(String tag3) {
        this.tag3 = tag3;
    }

    public String getTag4() {
        return tag4;
    }

    public void setTag4(String tag4) {
        this.tag4 = tag4;
    }

    public String getTag5() {
        return tag5;
    }

    public void setTag5(String tag5) {
        this.tag5 = tag5;
    }
}
