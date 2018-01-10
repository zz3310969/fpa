package com.roof.fpa.customer.entity;

import com.roof.fpa.GenderEnum;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * @author 模版生成 <br/>
 *         表名： c_customer <br/>
 *         描述：客户 <br/>
 */
public class CustomerVo extends Customer {

	private List<CustomerVo> customerList;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date followTimeStart;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date followTimeEnd;
	private GenderEnum genderEnum;

	private String code;

	private String partnerUnionid;


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

	public String getPartnerUnionid() {
		return partnerUnionid;
	}

	public void setPartnerUnionid(String partnerUnionid) {
		this.partnerUnionid = partnerUnionid;
	}
}
