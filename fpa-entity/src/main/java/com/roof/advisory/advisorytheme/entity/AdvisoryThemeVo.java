package com.roof.advisory.advisorytheme.entity;

import java.util.List;

/**
 * @author 模版生成 <br/>
 *         表名： z_advisory_theme <br/>
 *         描述：服务主题 <br/>
 */
public class AdvisoryThemeVo extends AdvisoryTheme {

	private List<AdvisoryThemeVo> advisoryThemeList;
	private String appName;

	public AdvisoryThemeVo() {
		super();
	}

	public AdvisoryThemeVo(Long id) {
		super();
		this.id = id;
	}

	public List<AdvisoryThemeVo> getAdvisoryThemeList() {
		return advisoryThemeList;
	}

	public void setAdvisoryThemeList(List<AdvisoryThemeVo> advisoryThemeList) {
		this.advisoryThemeList = advisoryThemeList;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}
}
