package com.roof.advisory.applicationuser.entity;

import java.util.List;

/**
 * @author 模版生成 <br/>
 *         表名： z_application_user <br/>
 *         描述：接入系统用户 <br/>
 */
public class ApplicationUserVo extends ApplicationUser {

	private List<ApplicationUserVo> applicationUserList;

	public ApplicationUserVo() {
		super();
	}

	public ApplicationUserVo(Long id) {
		super();
		this.id = id;
	}

	public List<ApplicationUserVo> getApplicationUserList() {
		return applicationUserList;
	}

	public void setApplicationUserList(List<ApplicationUserVo> applicationUserList) {
		this.applicationUserList = applicationUserList;
	}

}
