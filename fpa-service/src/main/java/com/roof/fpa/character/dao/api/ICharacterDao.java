package com.roof.fpa.character.dao.api;

import org.roof.roof.dataaccess.api.IDaoSupport;
import org.roof.roof.dataaccess.api.Page;

import com.roof.fpa.character.entity.Character;

public interface ICharacterDao extends IDaoSupport {
	Page page(Page page, Character character);
}