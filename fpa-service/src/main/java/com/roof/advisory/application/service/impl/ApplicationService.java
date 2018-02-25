package com.roof.advisory.application.service.impl;

import java.io.Serializable;
import java.util.List;

import com.roof.fpa.DefaultStateEnum;
import org.roof.roof.dataaccess.api.Page;
import com.roof.advisory.application.dao.api.IApplicationDao;
import com.roof.advisory.application.entity.Application;
import com.roof.advisory.application.entity.ApplicationVo;
import com.roof.advisory.application.service.api.IApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ApplicationService implements IApplicationService {
	private IApplicationDao applicationDao;

	public Serializable save(Application application){
		application.setState(DefaultStateEnum.usable.getCode());
		return applicationDao.save(application);
	}

	public void delete(Application application){
		applicationDao.delete(application);
	}
	
	public void deleteByExample(Application application){
		applicationDao.deleteByExample(application);
	}

	public void update(Application application){
		applicationDao.update(application);
	}
	
	public void updateIgnoreNull(Application application){
		applicationDao.updateIgnoreNull(application);
	}
		
	public void updateByExample(Application application){
		applicationDao.update("updateByExampleApplication", application);
	}

	public ApplicationVo load(Application application){
		return (ApplicationVo)applicationDao.reload(application);
	}
	
	public ApplicationVo selectForObject(Application application){
		return (ApplicationVo)applicationDao.selectForObject("selectApplication",application);
	}
	
	public List<ApplicationVo> selectForList(Application application){
		return (List<ApplicationVo>)applicationDao.selectForList("selectApplication",application);
	}
	
	public Page page(Page page, Application application) {
		return applicationDao.page(page, application);
	}

	@Autowired
	public void setIApplicationDao(
			@Qualifier("applicationDao") IApplicationDao  applicationDao) {
		this.applicationDao = applicationDao;
	}
	

}
