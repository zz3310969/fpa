package com.roof.fpa.share.service.impl;

import java.io.Serializable;
import java.util.List;
import org.roof.roof.dataaccess.api.Page;
import com.roof.fpa.share.dao.api.IShareDao;
import com.roof.fpa.share.entity.Share;
import com.roof.fpa.share.entity.ShareVo;
import com.roof.fpa.share.service.api.IShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ShareService implements IShareService {
	private IShareDao shareDao;

	public Serializable save(Share share){
		return shareDao.save(share);
	}

	public void delete(Share share){
		shareDao.delete(share);
	}
	
	public void deleteByExample(Share share){
		shareDao.deleteByExample(share);
	}

	public void update(Share share){
		shareDao.update(share);
	}
	
	public void updateIgnoreNull(Share share){
		shareDao.updateIgnoreNull(share);
	}
		
	public void updateByExample(Share share){
		shareDao.update("updateByExampleShare", share);
	}

	public ShareVo load(Share share){
		return (ShareVo)shareDao.reload(share);
	}
	
	public ShareVo selectForObject(Share share){
		return (ShareVo)shareDao.selectForObject("selectShare",share);
	}
	
	public List<ShareVo> selectForList(Share share){
		return (List<ShareVo>)shareDao.selectForList("selectShare",share);
	}
	
	public Page page(Page page, Share share) {
		return shareDao.page(page, share);
	}

	public Page pageByVo(Page page, ShareVo sharevo) {
		return shareDao.pageVo(page, sharevo);
	}

	@Autowired
	public void setIShareDao(
			@Qualifier("shareDao") IShareDao  shareDao) {
		this.shareDao = shareDao;
	}
	

}
