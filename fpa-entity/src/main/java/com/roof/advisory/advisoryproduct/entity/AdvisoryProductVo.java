package com.roof.advisory.advisoryproduct.entity;

import java.util.List;

/**
 * @author 模版生成 <br/>
 *         表名： z_advisory_product <br/>
 *         描述：服务产品 <br/>
 */
public class AdvisoryProductVo extends AdvisoryProduct {

	private List<AdvisoryProductVo> advisoryProductList;
	protected String modelName;// 服务模式

	public AdvisoryProductVo() {
		super();
	}

	public AdvisoryProductVo(Long id) {
		super();
		this.id = id;
	}

	public List<AdvisoryProductVo> getAdvisoryProductList() {
		return advisoryProductList;
	}

	public void setAdvisoryProductList(List<AdvisoryProductVo> advisoryProductList) {
		this.advisoryProductList = advisoryProductList;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
}
