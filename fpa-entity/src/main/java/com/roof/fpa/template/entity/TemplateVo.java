package com.roof.fpa.template.entity;

import java.util.List;

/**
 * @author 模版生成 <br/>
 *         表名： c_template <br/>
 *         描述：模板 <br/>
 */
public class TemplateVo extends Template {

	private List<TemplateVo> templateList;

	public TemplateVo() {
		super();
	}

	public TemplateVo(Long id) {
		super();
		this.id = id;
	}

	public List<TemplateVo> getTemplateList() {
		return templateList;
	}

	public void setTemplateList(List<TemplateVo> templateList) {
		this.templateList = templateList;
	}

}
