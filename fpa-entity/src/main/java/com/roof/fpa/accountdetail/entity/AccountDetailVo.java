package com.roof.fpa.accountdetail.entity;

import java.util.List;

/**
 * @author 模版生成 <br/>
 *         表名： c_account_detail <br/>
 *         描述：账户金额变更详情 <br/>
 */
public class AccountDetailVo extends AccountDetail {

	private List<AccountDetailVo> accountDetailList;

	public AccountDetailVo() {
		super();
	}

	public AccountDetailVo(Long id) {
		super();
		this.id = id;
	}

	public List<AccountDetailVo> getAccountDetailList() {
		return accountDetailList;
	}

	public void setAccountDetailList(List<AccountDetailVo> accountDetailList) {
		this.accountDetailList = accountDetailList;
	}

}
