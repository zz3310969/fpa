package com.roof.fpa.withdraw.service.impl;

import java.io.Serializable;
import java.util.List;
import org.roof.roof.dataaccess.api.Page;
import com.roof.fpa.withdraw.dao.api.IWithdrawDao;
import com.roof.fpa.withdraw.entity.Withdraw;
import com.roof.fpa.withdraw.entity.WithdrawVo;
import com.roof.fpa.withdraw.service.api.IWithdrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class WithdrawService implements IWithdrawService {
	private IWithdrawDao withdrawDao;

	public Serializable save(Withdraw withdraw){
		return withdrawDao.save(withdraw);
	}

	public void delete(Withdraw withdraw){
		withdrawDao.delete(withdraw);
	}
	
	public void deleteByExample(Withdraw withdraw){
		withdrawDao.deleteByExample(withdraw);
	}

	public void update(Withdraw withdraw){
		withdrawDao.update(withdraw);
	}
	
	public void updateIgnoreNull(Withdraw withdraw){
		withdrawDao.updateIgnoreNull(withdraw);
	}
		
	public void updateByExample(Withdraw withdraw){
		withdrawDao.update("updateByExampleWithdraw", withdraw);
	}

	public WithdrawVo load(Withdraw withdraw){
		return (WithdrawVo)withdrawDao.reload(withdraw);
	}
	
	public WithdrawVo selectForObject(Withdraw withdraw){
		return (WithdrawVo)withdrawDao.selectForObject("selectWithdraw",withdraw);
	}
	
	public List<WithdrawVo> selectForList(Withdraw withdraw){
		return (List<WithdrawVo>)withdrawDao.selectForList("selectWithdraw",withdraw);
	}
	
	public Page page(Page page, Withdraw withdraw) {
		return withdrawDao.page(page, withdraw);
	}

	@Autowired
	public void setIWithdrawDao(
			@Qualifier("withdrawDao") IWithdrawDao  withdrawDao) {
		this.withdrawDao = withdrawDao;
	}
	

}
