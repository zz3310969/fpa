package com.roof.fpa.theme.entity;

import java.util.List;

/**
 * @author 模版生成 <br/>
 *         表名： c_theme <br/>
 *         描述：主题 <br/>
 */
public class ThemeVo extends Theme {

	private List<ThemeVo> themeList;

	public ThemeVo() {
		super();
	}

	public ThemeVo(Long id) {
		super();
		this.id = id;
	}

	public List<ThemeVo> getThemeList() {
		return themeList;
	}

	public void setThemeList(List<ThemeVo> themeList) {
		this.themeList = themeList;
	}

}
