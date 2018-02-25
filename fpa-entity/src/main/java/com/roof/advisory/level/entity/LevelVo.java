package com.roof.advisory.level.entity;

import java.util.List;

/**
 * @author 模版生成 <br/>
 *         表名： z_level <br/>
 *         描述：咨询师等级 <br/>
 */
public class LevelVo extends Level {

	private List<LevelVo> levelList;
	private String appName;

	public LevelVo() {
		super();
	}

	public LevelVo(Long id) {
		super();
		this.id = id;
	}

	public List<LevelVo> getLevelList() {
		return levelList;
	}

	public void setLevelList(List<LevelVo> levelList) {
		this.levelList = levelList;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}
}
