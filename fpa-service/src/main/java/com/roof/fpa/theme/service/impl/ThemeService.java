package com.roof.fpa.theme.service.impl;

import java.io.Serializable;
import java.util.List;
import org.roof.roof.dataaccess.api.Page;
import com.roof.fpa.theme.dao.api.IThemeDao;
import com.roof.fpa.theme.entity.Theme;
import com.roof.fpa.theme.entity.ThemeVo;
import com.roof.fpa.theme.service.api.IThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ThemeService implements IThemeService {
	private IThemeDao themeDao;

	public Serializable save(Theme theme){
		return themeDao.save(theme);
	}

	public void delete(Theme theme){
		themeDao.delete(theme);
	}
	
	public void deleteByExample(Theme theme){
		themeDao.deleteByExample(theme);
	}

	public void update(Theme theme){
		themeDao.update(theme);
	}
	
	public void updateIgnoreNull(Theme theme){
		themeDao.updateIgnoreNull(theme);
	}
		
	public void updateByExample(Theme theme){
		themeDao.update("updateByExampleTheme", theme);
	}

	public ThemeVo load(Theme theme){
		return (ThemeVo)themeDao.reload(theme);
	}
	
	public ThemeVo selectForObject(Theme theme){
		return (ThemeVo)themeDao.selectForObject("selectTheme",theme);
	}
	
	public List<ThemeVo> selectForList(Theme theme){
		return (List<ThemeVo>)themeDao.selectForList("selectTheme",theme);
	}
	
	public Page page(Page page, Theme theme) {
		return themeDao.page(page, theme);
	}

	@Autowired
	public void setIThemeDao(
			@Qualifier("themeDao") IThemeDao  themeDao) {
		this.themeDao = themeDao;
	}
	

}
