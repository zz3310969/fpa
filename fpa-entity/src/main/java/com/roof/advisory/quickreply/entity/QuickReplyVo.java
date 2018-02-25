package com.roof.advisory.quickreply.entity;

import java.util.List;

/**
 * @author 模版生成 <br/>
 *         表名： z_quick_reply <br/>
 *         描述：快捷回复 <br/>
 */
public class QuickReplyVo extends QuickReply {

	private List<QuickReplyVo> quickReplyList;

	private String userName;
	private String appName;

	public QuickReplyVo() {
		super();
	}

	public QuickReplyVo(Long id) {
		super();
		this.id = id;
	}

	public List<QuickReplyVo> getQuickReplyList() {
		return quickReplyList;
	}

	public void setQuickReplyList(List<QuickReplyVo> quickReplyList) {
		this.quickReplyList = quickReplyList;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}
}
