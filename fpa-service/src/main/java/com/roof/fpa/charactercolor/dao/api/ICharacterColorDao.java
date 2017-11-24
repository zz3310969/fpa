package com.roof.fpa.charactercolor.dao.api;

import org.roof.roof.dataaccess.api.IDaoSupport;
import org.roof.roof.dataaccess.api.Page;

import com.roof.fpa.charactercolor.entity.CharacterColor;

public interface ICharacterColorDao extends IDaoSupport {
	Page page(Page page, CharacterColor characterColor);
}