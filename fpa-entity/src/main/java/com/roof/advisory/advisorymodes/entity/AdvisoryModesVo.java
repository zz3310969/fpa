package com.roof.advisory.advisorymodes.entity;

import java.util.List;

/**
 * @author 模版生成 <br/>
 *         表名： z_advisory_modes <br/>
 *         描述：咨询模式 <br/>
 */
public class AdvisoryModesVo extends AdvisoryModes {

	private List<AdvisoryModesVo> advisoryModesList;
	private String appName;

	public AdvisoryModesVo() {
		super();
	}

	public AdvisoryModesVo(Long id) {
		super();
		this.id = id;
	}

	public List<AdvisoryModesVo> getAdvisoryModesList() {
		return advisoryModesList;
	}

	public void setAdvisoryModesList(List<AdvisoryModesVo> advisoryModesList) {
		this.advisoryModesList = advisoryModesList;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}
}
