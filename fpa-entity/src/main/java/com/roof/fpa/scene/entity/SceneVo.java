package com.roof.fpa.scene.entity;

import java.util.List;

/**
 * @author 模版生成 <br/>
 *         表名： c_scene <br/>
 *         描述：场景 <br/>
 */
public class SceneVo extends Scene {

	private List<SceneVo> sceneList;

	public SceneVo() {
		super();
	}

	public SceneVo(Long id) {
		super();
		this.id = id;
	}

	public List<SceneVo> getSceneList() {
		return sceneList;
	}

	public void setSceneList(List<SceneVo> sceneList) {
		this.sceneList = sceneList;
	}

}
