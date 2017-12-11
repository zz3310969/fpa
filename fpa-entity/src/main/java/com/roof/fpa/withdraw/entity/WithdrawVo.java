package com.roof.fpa.withdraw.entity;

import java.util.List;

/**
 * @author 模版生成 <br/>
 *         表名： c_withdraw <br/>
 *         描述：提现记录 <br/>
 */
public class WithdrawVo extends Withdraw {

	private List<WithdrawVo> withdrawList;

	public WithdrawVo() {
		super();
	}

	public WithdrawVo(Long id) {
		super();
		this.id = id;
	}

	public List<WithdrawVo> getWithdrawList() {
		return withdrawList;
	}

	public void setWithdrawList(List<WithdrawVo> withdrawList) {
		this.withdrawList = withdrawList;
	}

}
