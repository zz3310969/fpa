package com.roof.fpa.scene.service.impl;

import java.io.Serializable;
import java.util.List;
import org.roof.roof.dataaccess.api.Page;
import com.roof.fpa.scene.dao.api.ISceneDao;
import com.roof.fpa.scene.entity.Scene;
import com.roof.fpa.scene.entity.SceneVo;
import com.roof.fpa.scene.service.api.ISceneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class SceneService implements ISceneService {
	private ISceneDao sceneDao;

	public Serializable save(Scene scene){
		return sceneDao.save(scene);
	}

	public void delete(Scene scene){
		sceneDao.delete(scene);
	}
	
	public void deleteByExample(Scene scene){
		sceneDao.deleteByExample(scene);
	}

	public void update(Scene scene){
		sceneDao.update(scene);
	}
	
	public void updateIgnoreNull(Scene scene){
		sceneDao.updateIgnoreNull(scene);
	}
		
	public void updateByExample(Scene scene){
		sceneDao.update("updateByExampleScene", scene);
	}

	public SceneVo load(Scene scene){
		return (SceneVo)sceneDao.reload(scene);
	}
	
	public SceneVo selectForObject(Scene scene){
		return (SceneVo)sceneDao.selectForObject("selectScene",scene);
	}
	
	public List<SceneVo> selectForList(Scene scene){
		return (List<SceneVo>)sceneDao.selectForList("selectScene",scene);
	}
	
	public Page page(Page page, Scene scene) {
		return sceneDao.page(page, scene);
	}

	@Autowired
	public void setISceneDao(
			@Qualifier("sceneDao") ISceneDao  sceneDao) {
		this.sceneDao = sceneDao;
	}
	

}
