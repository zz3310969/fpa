package com.roof.fpa.charactercolor.service.impl;

import java.io.Serializable;
import java.util.List;
import org.roof.roof.dataaccess.api.Page;
import com.roof.fpa.charactercolor.dao.api.ICharacterColorDao;
import com.roof.fpa.charactercolor.entity.CharacterColor;
import com.roof.fpa.charactercolor.entity.CharacterColorVo;
import com.roof.fpa.charactercolor.service.api.ICharacterColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class CharacterColorService implements ICharacterColorService {
	private ICharacterColorDao characterColorDao;

	public Serializable save(CharacterColor characterColor){
		return characterColorDao.save(characterColor);
	}

	public void delete(CharacterColor characterColor){
		characterColorDao.delete(characterColor);
	}
	
	public void deleteByExample(CharacterColor characterColor){
		characterColorDao.deleteByExample(characterColor);
	}

	public void update(CharacterColor characterColor){
		characterColorDao.update(characterColor);
	}
	
	public void updateIgnoreNull(CharacterColor characterColor){
		characterColorDao.updateIgnoreNull(characterColor);
	}
		
	public void updateByExample(CharacterColor characterColor){
		characterColorDao.update("updateByExampleCharacterColor", characterColor);
	}

	public CharacterColorVo load(CharacterColor characterColor){
		return (CharacterColorVo)characterColorDao.reload(characterColor);
	}
	
	public CharacterColorVo selectForObject(CharacterColor characterColor){
		return (CharacterColorVo)characterColorDao.selectForObject("selectCharacterColor",characterColor);
	}
	
	public List<CharacterColorVo> selectForList(CharacterColor characterColor){
		return (List<CharacterColorVo>)characterColorDao.selectForList("selectCharacterColor",characterColor);
	}
	
	public Page page(Page page, CharacterColor characterColor) {
		return characterColorDao.page(page, characterColor);
	}

	@Autowired
	public void setICharacterColorDao(
			@Qualifier("characterColorDao") ICharacterColorDao  characterColorDao) {
		this.characterColorDao = characterColorDao;
	}
	

}
