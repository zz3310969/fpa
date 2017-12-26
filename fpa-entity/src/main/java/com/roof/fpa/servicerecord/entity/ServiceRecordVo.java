package com.roof.fpa.servicerecord.entity;

import java.util.List;

/**
 * @author 模版生成 <br/>
 *         表名： c_service_record <br/>
 *         描述：服务记录 <br/>
 */
public class ServiceRecordVo extends ServiceRecord {

	private List<ServiceRecordVo> serviceRecordList;

	private String customerName;
	private String themeName;
	private String counselorName;

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

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getThemeName() {
		return themeName;
	}

	public void setThemeName(String themeName) {
		this.themeName = themeName;
	}

	public String getCounselorName() {
		return counselorName;
	}

	public void setCounselorName(String counselorName) {
		this.counselorName = counselorName;
	}
}
