package com.roof.fpa.scene.dao.api;

import org.roof.roof.dataaccess.api.IDaoSupport;
import org.roof.roof.dataaccess.api.Page;

import com.roof.fpa.scene.entity.Scene;

public interface ISceneDao extends IDaoSupport {
	Page page(Page page, Scene scene);
}