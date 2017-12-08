package com.roof.fpa.customerwords.entity;

import java.util.List;

/**
 * @author 模版生成 <br/>
 *         表名： c_customer_words <br/>
 *         描述：客户留言 <br/>
 */
public class CustomerWordsVo extends CustomerWords {

	private List<CustomerWordsVo> customerWordsList;

	public CustomerWordsVo() {
		super();
	}

	public CustomerWordsVo(Long id) {
		super();
		this.id = id;
	}

	public List<CustomerWordsVo> getCustomerWordsList() {
		return customerWordsList;
	}

	public void setCustomerWordsList(List<CustomerWordsVo> customerWordsList) {
		this.customerWordsList = customerWordsList;
	}

}
