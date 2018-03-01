package com.roof.advisory.applicationuser.service.impl;

import java.io.Serializable;
import java.util.List;

import com.roof.fpa.DefaultStateEnum;
import org.roof.roof.dataaccess.api.Page;
import com.roof.advisory.applicationuser.dao.api.IApplicationUserDao;
import com.roof.advisory.applicationuser.entity.ApplicationUser;
import com.roof.advisory.applicationuser.entity.ApplicationUserVo;
import com.roof.advisory.applicationuser.service.api.IApplicationUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ApplicationUserService implements IApplicationUserService {
	private IApplicationUserDao applicationUserDao;

	public Serializable save(ApplicationUser applicationUser){
		applicationUser.setState(DefaultStateEnum.usable.getCode());
		return applicationUserDao.save(applicationUser);
	}

	public void delete(ApplicationUser applicationUser){
		applicationUserDao.delete(applicationUser);
	}
	
	public void deleteByExample(ApplicationUser applicationUser){
		applicationUserDao.deleteByExample(applicationUser);
	}

	public void update(ApplicationUser applicationUser){
		applicationUserDao.update(applicationUser);
	}
	
	public void updateIgnoreNull(ApplicationUser applicationUser){
		applicationUserDao.updateIgnoreNull(applicationUser);
	}
		
	public void updateByExample(ApplicationUser applicationUser){
		applicationUserDao.update("updateByExampleApplicationUser", applicationUser);
	}

	public ApplicationUserVo load(ApplicationUser applicationUser){
		return (ApplicationUserVo)applicationUserDao.reload(applicationUser);
	}
	
	public ApplicationUserVo selectForObject(ApplicationUser applicationUser){
		return (ApplicationUserVo)applicationUserDao.selectForObject("selectApplicationUser",applicationUser);
	}
	
	public List<ApplicationUserVo> selectForList(ApplicationUser applicationUser){
		return (List<ApplicationUserVo>)applicationUserDao.selectForList("selectApplicationUser",applicationUser);
	}
	
	public Page page(Page page, ApplicationUser applicationUser) {
		return applicationUserDao.page(page, applicationUser);
	}

	@Autowired
	public void setIApplicationUserDao(
			@Qualifier("applicationUserDao") IApplicationUserDao  applicationUserDao) {
		this.applicationUserDao = applicationUserDao;
	}
	

}
