package com.roof.fpa.character.service.impl;

import java.io.Serializable;
import java.util.List;
import org.roof.roof.dataaccess.api.Page;
import com.roof.fpa.character.dao.api.ICharacterDao;
import com.roof.fpa.character.entity.Character;
import com.roof.fpa.character.entity.CharacterVo;
import com.roof.fpa.character.service.api.ICharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class CharacterService implements ICharacterService {
	private ICharacterDao characterDao;

	public Serializable save(Character character){
		return characterDao.save(character);
	}

	public void delete(Character character){
		characterDao.delete(character);
	}
	
	public void deleteByExample(Character character){
		characterDao.deleteByExample(character);
	}

	public void update(Character character){
		characterDao.update(character);
	}
	
	public void updateIgnoreNull(Character character){
		characterDao.updateIgnoreNull(character);
	}
		
	public void updateByExample(Character character){
		characterDao.update("updateByExampleCharacter", character);
	}

	public CharacterVo load(Character character){
		return (CharacterVo)characterDao.reload(character);
	}
	
	public CharacterVo selectForObject(Character character){
		return (CharacterVo)characterDao.selectForObject("selectCharacter",character);
	}
	
	public List<CharacterVo> selectForList(Character character){
		return (List<CharacterVo>)characterDao.selectForList("selectCharacter",character);
	}
	
	public Page page(Page page, Character character) {
		return characterDao.page(page, character);
	}

	@Autowired
	public void setICharacterDao(
			@Qualifier("characterDao") ICharacterDao  characterDao) {
		this.characterDao = characterDao;
	}
	

}
