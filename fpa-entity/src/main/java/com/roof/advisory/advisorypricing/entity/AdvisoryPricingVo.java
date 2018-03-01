package com.roof.advisory.advisorypricing.entity;

import java.util.List;

/**
 * @author 模版生成 <br/>
 *         表名： z_advisory_pricing <br/>
 *         描述：咨询服务定价 <br/>
 */
public class AdvisoryPricingVo extends AdvisoryPricing {

	private List<AdvisoryPricingVo> advisoryPricingList;
	private String appName;
	private String consultantName;// 咨询师姓名
	private String modeName;

	public AdvisoryPricingVo() {
		super();
	}

	public AdvisoryPricingVo(Long id) {
		super();
		this.id = id;
	}

	public List<AdvisoryPricingVo> getAdvisoryPricingList() {
		return advisoryPricingList;
	}

	public void setAdvisoryPricingList(List<AdvisoryPricingVo> advisoryPricingList) {
		this.advisoryPricingList = advisoryPricingList;
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
