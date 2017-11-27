package com.roof.fpa.transmittemplate.entity;

import java.util.List;

/**
 * @author 模版生成 <br/>
 *         表名： c_transmit_template <br/>
 *         描述：分享模板 <br/>
 */
public class TransmitTemplateVo extends TransmitTemplate {

	private List<TransmitTemplateVo> transmitTemplateList;

	public TransmitTemplateVo() {
		super();
	}

	public TransmitTemplateVo(Long id) {
		super();
		this.id = id;
	}

	public List<TransmitTemplateVo> getTransmitTemplateList() {
		return transmitTemplateList;
	}

	public void setTransmitTemplateList(List<TransmitTemplateVo> transmitTemplateList) {
		this.transmitTemplateList = transmitTemplateList;
	}

}
