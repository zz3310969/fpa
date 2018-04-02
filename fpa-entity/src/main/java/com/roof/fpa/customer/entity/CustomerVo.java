package com.roof.fpa.customer.entity;

import com.roof.fpa.GenderEnum;
import com.roof.fpa.cardtestresult.entity.CardTestResult;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * @author 模版生成 <br/>
 * 表名： c_customer <br/>
 * 描述：客户 <br/>
 */
public class CustomerVo extends Customer {

    private List<CustomerVo> customerList;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date followTimeStart;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date followTimeEnd;
    private GenderEnum genderEnum;

    private String code;

    private Long partnerId;
    private Long friendId;

    private CardTestResult testResult;

    private String[] types;


    public CustomerVo() {
        super();
    }

    public CustomerVo(Long id) {
        super();
        this.id = id;
    }

    public List<CustomerVo> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<CustomerVo> customerList) {
        this.customerList = customerList;
    }

    public Date getFollowTimeStart() {
        return followTimeStart;
    }

    public void setFollowTimeStart(Date followTimeStart) {
        this.followTimeStart = followTimeStart;
    }

    public Date getFollowTimeEnd() {
        return followTimeEnd;
    }

    public void setFollowTimeEnd(Date followTimeEnd) {
        this.followTimeEnd = followTimeEnd;
    }

    public GenderEnum getGenderEnum() {
        return GenderEnum.getEnumByCode(super.gender);
    }

    public void setGenderEnum(GenderEnum genderEnum) {
        this.genderEnum = genderEnum;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(Long partnerId) {
        this.partnerId = partnerId;
    }

    public Long getFriendId() {
        return friendId;
    }

    public void setFriendId(Long friendId) {
        this.friendId = friendId;
    }

    public CardTestResult getTestResult() {
        return testResult;
    }

    public void setTestResult(CardTestResult testResult) {
        this.testResult = testResult;
    }

    public String[] getTypes() {
        return types;
    }

    public void setTypes(String[] types) {
        this.types = types;
    }
}
