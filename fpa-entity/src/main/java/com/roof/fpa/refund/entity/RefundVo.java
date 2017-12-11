package com.roof.fpa.refund.entity;

import java.util.List;

/**
 * @author 模版生成 <br/>
 *         表名： c_refund <br/>
 *         描述：退款记录 <br/>
 */
public class RefundVo extends Refund {

	private List<RefundVo> refundList;

	public RefundVo() {
		super();
	}

	public RefundVo(Long id) {
		super();
		this.id = id;
	}

	public List<RefundVo> getRefundList() {
		return refundList;
	}

	public void setRefundList(List<RefundVo> refundList) {
		this.refundList = refundList;
	}

}
