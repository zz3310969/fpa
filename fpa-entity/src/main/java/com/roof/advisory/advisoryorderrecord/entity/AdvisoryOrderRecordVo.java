package com.roof.advisory.advisoryorderrecord.entity;

import java.util.List;

/**
 * @author 模版生成 <br/>
 *         表名： z_advisory_order_record <br/>
 *         描述：订单变更记录表 <br/>
 */
public class AdvisoryOrderRecordVo extends AdvisoryOrderRecord {

	private List<AdvisoryOrderRecordVo> advisoryOrderRecordList;

	public AdvisoryOrderRecordVo() {
		super();
	}

	public AdvisoryOrderRecordVo(Long id) {
		super();
		this.id = id;
	}

	public List<AdvisoryOrderRecordVo> getAdvisoryOrderRecordList() {
		return advisoryOrderRecordList;
	}

	public void setAdvisoryOrderRecordList(List<AdvisoryOrderRecordVo> advisoryOrderRecordList) {
		this.advisoryOrderRecordList = advisoryOrderRecordList;
	}

}
