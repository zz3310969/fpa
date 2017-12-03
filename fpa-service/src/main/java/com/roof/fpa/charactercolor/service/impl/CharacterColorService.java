package com.roof.fpa.charactercolor.service.impl;

import java.io.Serializable;
import java.util.List;

import com.roof.fpa.DefaultUseableEnum;
import org.roof.roof.dataaccess.api.Page;
import com.roof.fpa.charactercolor.dao.api.ICharacterColorDao;
import com.roof.fpa.charactercolor.entity.CharacterColor;
import com.roof.fpa.charactercolor.entity.CharacterColorVo;
import com.roof.fpa.charactercolor.service.api.ICharacterColorService;
import org.roof.spring.ApplicationException;
import org.roof.web.dictionary.entity.Dictionary;
import org.roof.web.dictionary.service.api.IDictionaryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class CharacterColorService implements ICharacterColorService {
    private ICharacterColorDao characterColorDao;

    @Autowired
    private IDictionaryService dictionaryService;

    public Serializable saveVo(CharacterColorVo characterColorVo) throws ApplicationException {
        if (characterColorVo.getColorIds() == null) {
            throw ApplicationException.newInstance(ApplicationException.DEFAULT_EXCEPTION_CODE);
        }
        CharacterColor characterColor = new CharacterColor();
        copyProperties(characterColorVo, characterColor);
        return this.save(characterColor);
    }

    private void copyProperties(CharacterColorVo characterColorVo, CharacterColor characterColor) {
        BeanUtils.copyProperties(characterColorVo, characterColor);
        characterColor.setUseable(DefaultUseableEnum.usable.getCode());
        if (characterColorVo.getColorIds().size() == 1) {
            characterColor.setColorId(characterColorVo.getColorIds().get(0));
            characterColor.setColorCode(dictionaryService.load(new Dictionary(characterColorVo.getColorIds().get(0))).getDescription());
            characterColor.setColor2Id(null);
            characterColor.setColor2Code(null);
        } else if (characterColorVo.getColorIds().size() == 2) {
            characterColor.setColorId(characterColorVo.getColorIds().get(0));
            characterColor.setColorCode(dictionaryService.load(new Dictionary(characterColorVo.getColorIds().get(0))).getDescription());
            characterColor.setColor2Id(characterColorVo.getColorIds().get(1));
            characterColor.setColor2Code(dictionaryService.load(new Dictionary(characterColorVo.getColorIds().get(1))).getDescription());
        }
    }

    public Serializable save(CharacterColor characterColor) {
        characterColor.setUseable(DefaultUseableEnum.usable.getCode());
        return characterColorDao.save(characterColor);
    }

    public void delete(CharacterColor characterColor) {
        characterColorDao.delete(characterColor);
    }

    public void deleteByExample(CharacterColor characterColor) {
        characterColorDao.deleteByExample(characterColor);
    }

    public void update(CharacterColor characterColor) {
        characterColorDao.update(characterColor);
    }

    public void updateIgnoreNullVo(CharacterColorVo characterColorVo) throws ApplicationException {
        if (characterColorVo.getColorIds() == null) {
            throw ApplicationException.newInstance(ApplicationException.DEFAULT_EXCEPTION_CODE);
        }
        CharacterColor characterColor = new CharacterColor();
        characterColor.setId(characterColorVo.getId());
        copyProperties(characterColorVo, characterColor);
        characterColorDao.update(characterColor);
    }


    public void updateIgnoreNull(CharacterColor characterColor) {
        characterColorDao.updateIgnoreNull(characterColor);
    }

    public void updateByExample(CharacterColor characterColor) {
        characterColorDao.update("updateByExampleCharacterColor", characterColor);
    }

    public CharacterColorVo load(CharacterColor characterColor) {
        return (CharacterColorVo) characterColorDao.reload(characterColor);
    }

    public CharacterColorVo selectForObject(CharacterColor characterColor) {
        return (CharacterColorVo) characterColorDao.selectForObject("selectCharacterColor", characterColor);
    }

    public List<CharacterColorVo> selectForList(CharacterColor characterColor) {
        return (List<CharacterColorVo>) characterColorDao.selectForList("selectCharacterColor", characterColor);
    }

    public Page page(Page page, CharacterColor characterColor) {
        return characterColorDao.page(page, characterColor);
    }

    @Autowired
    public void setICharacterColorDao(
            @Qualifier("characterColorDao") ICharacterColorDao characterColorDao) {
        this.characterColorDao = characterColorDao;
    }


}
