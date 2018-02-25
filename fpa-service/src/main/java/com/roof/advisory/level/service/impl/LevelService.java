package com.roof.advisory.level.service.impl;

import java.io.Serializable;
import java.util.List;

import com.roof.fpa.DefaultStateEnum;
import org.roof.roof.dataaccess.api.Page;
import com.roof.advisory.level.dao.api.ILevelDao;
import com.roof.advisory.level.entity.Level;
import com.roof.advisory.level.entity.LevelVo;
import com.roof.advisory.level.service.api.ILevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class LevelService implements ILevelService {
	private ILevelDao levelDao;

	public Serializable save(Level level){
		level.setState(DefaultStateEnum.usable.getCode());
		return levelDao.save(level);
	}

	public void delete(Level level){
		levelDao.delete(level);
	}
	
	public void deleteByExample(Level level){
		levelDao.deleteByExample(level);
	}

	public void update(Level level){
		levelDao.update(level);
	}
	
	public void updateIgnoreNull(Level level){
		levelDao.updateIgnoreNull(level);
	}
		
	public void updateByExample(Level level){
		levelDao.update("updateByExampleLevel", level);
	}

	public LevelVo load(Level level){
		return (LevelVo)levelDao.reload(level);
	}
	
	public LevelVo selectForObject(Level level){
		return (LevelVo)levelDao.selectForObject("selectLevel",level);
	}
	
	public List<LevelVo> selectForList(Level level){
		return (List<LevelVo>)levelDao.selectForList("selectLevel",level);
	}
	
	public Page page(Page page, Level level) {
		return levelDao.page(page, level);
	}

	@Autowired
	public void setILevelDao(
			@Qualifier("levelDao") ILevelDao  levelDao) {
		this.levelDao = levelDao;
	}
	

}
