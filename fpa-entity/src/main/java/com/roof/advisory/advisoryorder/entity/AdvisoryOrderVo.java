package com.roof.advisory.advisoryorder.entity;

import java.util.List;

/**
 * @author 模版生成 <br/>
 *         表名： z_advisory_order <br/>
 *         描述：订单 <br/>
 */
public class AdvisoryOrderVo extends AdvisoryOrder {

	private List<AdvisoryOrderVo> advisoryOrderList;

	public AdvisoryOrderVo() {
		super();
	}

	public AdvisoryOrderVo(Long id) {
		super();
		this.id = id;
	}

	public List<AdvisoryOrderVo> getAdvisoryOrderList() {
		return advisoryOrderList;
	}

	public void setAdvisoryOrderList(List<AdvisoryOrderVo> advisoryOrderList) {
		this.advisoryOrderList = advisoryOrderList;
	}

}
