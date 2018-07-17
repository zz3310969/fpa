package com.roof.fpa.banner.service.impl;

import java.io.Serializable;
import java.util.List;
import org.roof.roof.dataaccess.api.Page;
import com.roof.fpa.banner.dao.api.IBannerDao;
import com.roof.fpa.banner.entity.Banner;
import com.roof.fpa.banner.entity.BannerVo;
import com.roof.fpa.banner.service.api.IBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class BannerService implements IBannerService {
	private IBannerDao bannerDao;

	public Serializable save(Banner banner){
		return bannerDao.save(banner);
	}

	public void delete(Banner banner){
		bannerDao.delete(banner);
	}
	
	public void deleteByExample(Banner banner){
		bannerDao.deleteByExample(banner);
	}

	public void update(Banner banner){
		bannerDao.update(banner);
	}
	
	public void updateIgnoreNull(Banner banner){
		bannerDao.updateIgnoreNull(banner);
	}
		
	public void updateByExample(Banner banner){
		bannerDao.update("updateByExampleBanner", banner);
	}

	public BannerVo load(Banner banner){
		return (BannerVo)bannerDao.reload(banner);
	}
	
	public BannerVo selectForObject(Banner banner){
		return (BannerVo)bannerDao.selectForObject("selectBanner",banner);
	}
	
	public List<BannerVo> selectForList(Banner banner){
		return (List<BannerVo>)bannerDao.selectForList("selectBanner",banner);
	}
	
	public Page page(Page page, Banner banner) {
		return bannerDao.page(page, banner);
	}

	@Autowired
	public void setIBannerDao(
			@Qualifier("bannerDao") IBannerDao  bannerDao) {
		this.bannerDao = bannerDao;
	}
	

}
