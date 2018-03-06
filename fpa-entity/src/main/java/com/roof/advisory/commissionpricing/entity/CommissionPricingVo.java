package com.roof.advisory.commissionpricing.entity;

import java.util.List;

/**
 * @author 模版生成 <br/>
 *         表名： z_commission_pricing <br/>
 *         描述：咨询佣金定价 <br/>
 */
public class CommissionPricingVo extends CommissionPricing {

	private List<CommissionPricingVo> commissionPricingList;
	private String appName;
	private String consultantName;// 咨询师姓名
	private String modeName;


	public CommissionPricingVo() {
		super();
	}

	public CommissionPricingVo(Long id) {
		super();
		this.id = id;
	}

	public List<CommissionPricingVo> getCommissionPricingList() {
		return commissionPricingList;
	}

	public void setCommissionPricingList(List<CommissionPricingVo> commissionPricingList) {
		this.commissionPricingList = commissionPricingList;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getConsultantName() {
		return consultantName;
	}

	public void setConsultantName(String consultantName) {
		this.consultantName = consultantName;
	}

	public String getModeName() {
		return modeName;
	}

	public void setModeName(String modeName) {
		this.modeName = modeName;
	}
}