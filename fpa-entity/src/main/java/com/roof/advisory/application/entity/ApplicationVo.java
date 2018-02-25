package com.roof.advisory.application.entity;

import java.util.List;

/**
 * @author 模版生成 <br/>
 *         表名： z_application <br/>
 *         描述：接入系统 <br/>
 */
public class ApplicationVo extends Application {

	private List<ApplicationVo> applicationList;

	public ApplicationVo() {
		super();
	}

	public ApplicationVo(Long id) {
		super();
		this.id = id;
	}

	public List<ApplicationVo> getApplicationList() {
		return applicationList;
	}

	public void setApplicationList(List<ApplicationVo> applicationList) {
		this.applicationList = applicationList;
	}

}
