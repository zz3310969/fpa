package com.roof.fpa.character.entity;

import com.roof.fpa.DefaultStateEnum;
import com.roof.fpa.GenderEnum;

import java.util.List;

/**
 * @author 模版生成 <br/>
 *         表名： c_character <br/>
 *         描述：性格 <br/>
 */
public class CharacterVo extends Character {

	private List<CharacterVo> characterList;

	public CharacterVo() {
		super();
	}

	private String characterColorName;
	private String themeName;
	private String cardUnitName;
	private DefaultStateEnum stateEnum;
	private GenderEnum genderEnum;

	public CharacterVo(Long id) {
		super();
		this.id = id;
	}

	public List<CharacterVo> getCharacterList() {
		return characterList;
	}

	public void setCharacterList(List<CharacterVo> characterList) {
		this.characterList = characterList;
	}

	public String getThemeName() {
		return themeName;
	}

	public void setThemeName(String themeName) {
		this.themeName = themeName;
	}

	public DefaultStateEnum getStateEnum() {
		return DefaultStateEnum.getEnumByCode(super.state);
	}

	public void setStateEnum(DefaultStateEnum stateEnum) {
		this.stateEnum = stateEnum;
	}

	public GenderEnum getGenderEnum() {
		return GenderEnum.getEnumByCode(super.gender);
	}

	public void setGenderEnum(GenderEnum genderEnum) {
		this.genderEnum = genderEnum;
	}

	public String getCharacterColorName() {
		return characterColorName;
	}

	public void setCharacterColorName(String characterColorName) {
		this.characterColorName = characterColorName;
	}

	public String getCardUnitName() {
		return cardUnitName;
	}

	public void setCardUnitName(String cardUnitName) {
		this.cardUnitName = cardUnitName;
	}
}
