package com.roof.advisory.advisoryproduct.entity;

import java.util.List;

/**
 * @author 模版生成 <br/>
 *         表名： z_advisory_product <br/>
 *         描述：服务产品 <br/>
 */
public class AdvisoryProductVo extends AdvisoryProduct {

	private List<AdvisoryProductVo> advisoryProductList;

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

}
