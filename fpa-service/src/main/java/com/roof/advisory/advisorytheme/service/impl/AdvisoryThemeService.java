package com.roof.advisory.advisorytheme.service.impl;

import java.io.Serializable;
import java.util.List;

import com.roof.fpa.DefaultStateEnum;
import org.roof.roof.dataaccess.api.Page;
import com.roof.advisory.advisorytheme.dao.api.IAdvisoryThemeDao;
import com.roof.advisory.advisorytheme.entity.AdvisoryTheme;
import com.roof.advisory.advisorytheme.entity.AdvisoryThemeVo;
import com.roof.advisory.advisorytheme.service.api.IAdvisoryThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class AdvisoryThemeService implements IAdvisoryThemeService {
	private IAdvisoryThemeDao advisoryThemeDao;

	public Serializable save(AdvisoryTheme advisoryTheme){
		advisoryTheme.setState(DefaultStateEnum.usable.getCode());
		return advisoryThemeDao.save(advisoryTheme);
	}

	public void delete(AdvisoryTheme advisoryTheme){
		advisoryThemeDao.delete(advisoryTheme);
	}
	
	public void deleteByExample(AdvisoryTheme advisoryTheme){
		advisoryThemeDao.deleteByExample(advisoryTheme);
	}

	public void update(AdvisoryTheme advisoryTheme){
		advisoryThemeDao.update(advisoryTheme);
	}
	
	public void updateIgnoreNull(AdvisoryTheme advisoryTheme){
		advisoryThemeDao.updateIgnoreNull(advisoryTheme);
	}
		
	public void updateByExample(AdvisoryTheme advisoryTheme){
		advisoryThemeDao.update("updateByExampleAdvisoryTheme", advisoryTheme);
	}

	public AdvisoryThemeVo load(AdvisoryTheme advisoryTheme){
		return (AdvisoryThemeVo)advisoryThemeDao.reload(advisoryTheme);
	}
	
	public AdvisoryThemeVo selectForObject(AdvisoryTheme advisoryTheme){
		return (AdvisoryThemeVo)advisoryThemeDao.selectForObject("selectAdvisoryTheme",advisoryTheme);
	}
	
	public List<AdvisoryThemeVo> selectForList(AdvisoryTheme advisoryTheme){
		return (List<AdvisoryThemeVo>)advisoryThemeDao.selectForList("selectAdvisoryTheme",advisoryTheme);
	}
	
	public Page page(Page page, AdvisoryTheme advisoryTheme) {
		return advisoryThemeDao.page(page, advisoryTheme);
	}

	@Autowired
	public void setIAdvisoryThemeDao(
			@Qualifier("advisoryThemeDao") IAdvisoryThemeDao  advisoryThemeDao) {
		this.advisoryThemeDao = advisoryThemeDao;
	}
	

}
