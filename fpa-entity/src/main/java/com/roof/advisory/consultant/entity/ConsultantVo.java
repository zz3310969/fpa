package com.roof.advisory.consultant.entity;

import com.roof.fpa.GenderEnum;

import java.util.List;

/**
 * @author 模版生成 <br/>
 *         表名： z_consultant <br/>
 *         描述：咨询师 <br/>
 */
public class ConsultantVo extends Consultant {

	private List<ConsultantVo> consultantList;
	private String appName;
	private String levelName;
	private String themeName;
	private GenderEnum genderEnum;
	private String[] areas;

	public ConsultantVo() {
		super();
	}

	public ConsultantVo(Long id) {
		super();
		this.id = id;
	}

	public List<ConsultantVo> getConsultantList() {
		return consultantList;
	}

	public void setConsultantList(List<ConsultantVo> consultantList) {
		this.consultantList = consultantList;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public String getThemeName() {
		return themeName;
	}

	public void setThemeName(String themeName) {
		this.themeName = themeName;
	}
	public GenderEnum getGenderEnum() {
		return GenderEnum.getEnumByCode(super.gender);
	}

	public void setGenderEnum(GenderEnum genderEnum) {
		this.genderEnum = genderEnum;
	}

	public String[] getAreas() {
		return areas;
	}

	public void setAreas(String[] areas) {
		this.areas = areas;
	}
}
