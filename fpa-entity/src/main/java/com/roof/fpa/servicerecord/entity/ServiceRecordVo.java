package com.roof.fpa.servicerecord.entity;

import java.util.List;

/**
 * @author 模版生成 <br/>
 *         表名： c_service_record <br/>
 *         描述：服务记录 <br/>
 */
public class ServiceRecordVo extends ServiceRecord {

	private List<ServiceRecordVo> serviceRecordList;

	public ServiceRecordVo() {
		super();
	}

	public ServiceRecordVo(Long id) {
		super();
		this.id = id;
	}

	public List<ServiceRecordVo> getServiceRecordList() {
		return serviceRecordList;
	}

	public void setServiceRecordList(List<ServiceRecordVo> serviceRecordList) {
		this.serviceRecordList = serviceRecordList;
	}

}
