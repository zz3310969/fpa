package com.roof.fpa.share.entity;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * @author 模版生成 <br/>
 *         表名： c_share <br/>
 *         描述：c_share <br/>
 */
public class ShareVo extends Share {

	private List<ShareVo> shareList;

	private Long customerName;// 分享人
	private Long sceneName;// 场景ID
	private Long templateName;// 模板id
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date shareTimeStart;// 分享时间
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date shareTimeEnd;// 分享时间

	public ShareVo() {
		super();
	}

	public ShareVo(Long id) {
		super();
		this.id = id;
	}

	public List<ShareVo> getShareList() {
		return shareList;
	}

	public void setShareList(List<ShareVo> shareList) {
		this.shareList = shareList;
	}

	public Long getCustomerName() {
		return customerName;
	}

	public void setCustomerName(Long customerName) {
		this.customerName = customerName;
	}

	public Long getSceneName() {
		return sceneName;
	}

	public void setSceneName(Long sceneName) {
		this.sceneName = sceneName;
	}

	public Long getTemplateName() {
		return templateName;
	}

	public void setTemplateName(Long templateName) {
		this.templateName = templateName;
	}

	public Date getShareTimeStart() {
		return shareTimeStart;
	}

	public void setShareTimeStart(Date shareTimeStart) {
		this.shareTimeStart = shareTimeStart;
	}

	public Date getShareTimeEnd() {
		return shareTimeEnd;
	}

	public void setShareTimeEnd(Date shareTimeEnd) {
		this.shareTimeEnd = shareTimeEnd;
	}
}
