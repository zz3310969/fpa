package com.roof.fpa.scene.entity;

import com.roof.fpa.cardgroup.entity.CardGroupVo;
import com.roof.fpa.cardslot.entity.CardSlotVo;

import java.util.List;

/**
 * @author 模版生成 <br/>
 *         表名： c_scene <br/>
 *         描述：场景 <br/>
 */
public class SceneVo extends Scene {

	private List<SceneVo> sceneList;

	private String cardGroupName;// 套牌id
	private String themeName;// 主题ID


	private List<CardSlotVo> cardSlotList;
	private CardGroupVo cardGroup;


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

	public String getCardGroupName() {
		return cardGroupName;
	}

	public void setCardGroupName(String cardGroupName) {
		this.cardGroupName = cardGroupName;
	}

	public String getThemeName() {
		return themeName;
	}

	public void setThemeName(String themeName) {
		this.themeName = themeName;
	}

	public List<CardSlotVo> getCardSlotList() {
		return cardSlotList;
	}

	public void setCardSlotList(List<CardSlotVo> cardSlotList) {
		this.cardSlotList = cardSlotList;
	}

	public CardGroupVo getCardGroup() {
		return cardGroup;
	}

	public void setCardGroup(CardGroupVo cardGroup) {
		this.cardGroup = cardGroup;
	}
}
