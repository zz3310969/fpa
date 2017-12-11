package com.roof.fpa.account.entity;

import java.util.List;

/**
 * @author 模版生成 <br/>
 *         表名： c_account <br/>
 *         描述：账户 <br/>
 */
public class AccountVo extends Account {

	private List<AccountVo> accountList;

	public AccountVo() {
		super();
	}

	public AccountVo(Long id) {
		super();
		this.id = id;
	}

	public List<AccountVo> getAccountList() {
		return accountList;
	}

	public void setAccountList(List<AccountVo> accountList) {
		this.accountList = accountList;
	}

}
