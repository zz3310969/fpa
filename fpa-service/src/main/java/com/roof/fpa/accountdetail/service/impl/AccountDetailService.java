package com.roof.fpa.accountdetail.service.impl;

import java.io.Serializable;
import java.util.List;
import org.roof.roof.dataaccess.api.Page;
import com.roof.fpa.accountdetail.dao.api.IAccountDetailDao;
import com.roof.fpa.accountdetail.entity.AccountDetail;
import com.roof.fpa.accountdetail.entity.AccountDetailVo;
import com.roof.fpa.accountdetail.service.api.IAccountDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class AccountDetailService implements IAccountDetailService {
	private IAccountDetailDao accountDetailDao;

	public Serializable save(AccountDetail accountDetail){
		return accountDetailDao.save(accountDetail);
	}

	public void delete(AccountDetail accountDetail){
		accountDetailDao.delete(accountDetail);
	}
	
	public void deleteByExample(AccountDetail accountDetail){
		accountDetailDao.deleteByExample(accountDetail);
	}

	public void update(AccountDetail accountDetail){
		accountDetailDao.update(accountDetail);
	}
	
	public void updateIgnoreNull(AccountDetail accountDetail){
		accountDetailDao.updateIgnoreNull(accountDetail);
	}
		
	public void updateByExample(AccountDetail accountDetail){
		accountDetailDao.update("updateByExampleAccountDetail", accountDetail);
	}

	public AccountDetailVo load(AccountDetail accountDetail){
		return (AccountDetailVo)accountDetailDao.reload(accountDetail);
	}
	
	public AccountDetailVo selectForObject(AccountDetail accountDetail){
		return (AccountDetailVo)accountDetailDao.selectForObject("selectAccountDetail",accountDetail);
	}
	
	public List<AccountDetailVo> selectForList(AccountDetail accountDetail){
		return (List<AccountDetailVo>)accountDetailDao.selectForList("selectAccountDetail",accountDetail);
	}
	
	public Page page(Page page, AccountDetail accountDetail) {
		return accountDetailDao.page(page, accountDetail);
	}

	@Autowired
	public void setIAccountDetailDao(
			@Qualifier("accountDetailDao") IAccountDetailDao  accountDetailDao) {
		this.accountDetailDao = accountDetailDao;
	}
	

}
