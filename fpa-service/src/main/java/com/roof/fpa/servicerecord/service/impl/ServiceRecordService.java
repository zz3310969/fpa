package com.roof.fpa.servicerecord.service.impl;

import java.io.Serializable;
import java.util.List;
import org.roof.roof.dataaccess.api.Page;
import com.roof.fpa.servicerecord.dao.api.IServiceRecordDao;
import com.roof.fpa.servicerecord.entity.ServiceRecord;
import com.roof.fpa.servicerecord.entity.ServiceRecordVo;
import com.roof.fpa.servicerecord.service.api.IServiceRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ServiceRecordService implements IServiceRecordService {
	private IServiceRecordDao serviceRecordDao;

	public Serializable save(ServiceRecord serviceRecord){
		return serviceRecordDao.save(serviceRecord);
	}

	public void delete(ServiceRecord serviceRecord){
		serviceRecordDao.delete(serviceRecord);
	}
	
	public void deleteByExample(ServiceRecord serviceRecord){
		serviceRecordDao.deleteByExample(serviceRecord);
	}

	public void update(ServiceRecord serviceRecord){
		serviceRecordDao.update(serviceRecord);
	}
	
	public void updateIgnoreNull(ServiceRecord serviceRecord){
		serviceRecordDao.updateIgnoreNull(serviceRecord);
	}
		
	public void updateByExample(ServiceRecord serviceRecord){
		serviceRecordDao.update("updateByExampleServiceRecord", serviceRecord);
	}

	public ServiceRecordVo load(ServiceRecord serviceRecord){
		return (ServiceRecordVo)serviceRecordDao.reload(serviceRecord);
	}
	
	public ServiceRecordVo selectForObject(ServiceRecord serviceRecord){
		return (ServiceRecordVo)serviceRecordDao.selectForObject("selectServiceRecord",serviceRecord);
	}
	
	public List<ServiceRecordVo> selectForList(ServiceRecord serviceRecord){
		return (List<ServiceRecordVo>)serviceRecordDao.selectForList("selectServiceRecord",serviceRecord);
	}
	
	public Page page(Page page, ServiceRecord serviceRecord) {
		return serviceRecordDao.page(page, serviceRecord);
	}

	@Autowired
	public void setIServiceRecordDao(
			@Qualifier("serviceRecordDao") IServiceRecordDao  serviceRecordDao) {
		this.serviceRecordDao = serviceRecordDao;
	}
	

}
