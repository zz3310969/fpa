package com.roof.advisory.advisorypayrecord.entity;

import java.util.List;

/**
 * @author 模版生成 <br/>
 *         表名： z_advisory_pay_record <br/>
 *         描述：付款记录 <br/>
 */
public class AdvisoryPayRecordVo extends AdvisoryPayRecord {

	private List<AdvisoryPayRecordVo> advisoryPayRecordList;

	public AdvisoryPayRecordVo() {
		super();
	}

	public AdvisoryPayRecordVo(Long id) {
		super();
		this.id = id;
	}

	public List<AdvisoryPayRecordVo> getAdvisoryPayRecordList() {
		return advisoryPayRecordList;
	}

	public void setAdvisoryPayRecordList(List<AdvisoryPayRecordVo> advisoryPayRecordList) {
		this.advisoryPayRecordList = advisoryPayRecordList;
	}

}
