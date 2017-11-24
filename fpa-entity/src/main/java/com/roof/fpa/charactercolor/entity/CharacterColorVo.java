package com.roof.fpa.charactercolor.entity;

import java.util.List;

/**
 * @author 模版生成 <br/>
 *         表名： c_character_color <br/>
 *         描述：性格色彩 <br/>
 */
public class CharacterColorVo extends CharacterColor {

	private List<CharacterColorVo> characterColorList;

	public CharacterColorVo() {
		super();
	}

	public CharacterColorVo(Long id) {
		super();
		this.id = id;
	}

	public List<CharacterColorVo> getCharacterColorList() {
		return characterColorList;
	}

	public void setCharacterColorList(List<CharacterColorVo> characterColorList) {
		this.characterColorList = characterColorList;
	}

}
