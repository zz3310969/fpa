package com.roof.advisory.application.service.impl;

import java.io.Serializable;
import java.util.List;

import com.roof.fpa.DefaultStateEnum;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.text.RandomStringGenerator;
import org.roof.roof.dataaccess.api.Page;
import com.roof.advisory.application.dao.api.IApplicationDao;
import com.roof.advisory.application.entity.Application;
import com.roof.advisory.application.entity.ApplicationVo;
import com.roof.advisory.application.service.api.IApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import static org.apache.commons.text.CharacterPredicates.DIGITS;
import static org.apache.commons.text.CharacterPredicates.LETTERS;

@Service
public class ApplicationService implements IApplicationService {
	private IApplicationDao applicationDao;
	//使用字母0-9,a-z，生成20个code point(维基百科称之为'码位')的随机字符串
	RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('0', 'z').filteredBy(LETTERS, DIGITS).build();

	public Serializable save(Application application){
		String randomLetters = generator.generate(15);
		application.setAppCode("zx"+randomLetters);
		application.setAppSecret(DigestUtils.md5Hex(generator.generate(10)).toUpperCase());
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