package com.roof.fpa.character.entity;

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

}
