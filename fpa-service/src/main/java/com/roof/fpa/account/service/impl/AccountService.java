package com.roof.fpa.account.service.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.roof.fpa.DefaultStateEnum;
import com.roof.fpa.DefaultUseableEnum;
import com.roof.fpa.accountdetail.entity.AccountDetail;
import com.roof.fpa.accountdetail.service.api.IAccountDetailService;
import org.roof.roof.dataaccess.api.Page;
import com.roof.fpa.account.dao.api.IAccountDao;
import com.roof.fpa.account.entity.Account;
import com.roof.fpa.account.entity.AccountVo;
import com.roof.fpa.account.service.api.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
public class AccountService implements IAccountService {
    private IAccountDao accountDao;

    @Autowired
    private IAccountDetailService accountDetailService;

    public Serializable save(Account account) {
        return accountDao.save(account);
    }

    public void delete(Account account) {
        accountDao.delete(account);
    }

    public void deleteByExample(Account account) {
        accountDao.deleteByExample(account);
    }

    public void update(Account account) {
        accountDao.update(account);
    }

    public void updateIgnoreNull(Account account) {
        accountDao.updateIgnoreNull(account);
    }

    public void updateByExample(Account account) {
        accountDao.update("updateByExampleAccount", account);
    }

    public AccountVo load(Account account) {
        return (AccountVo) accountDao.reload(account);
    }

    public AccountVo selectForObject(Account account) {
        return (AccountVo) accountDao.selectForObject("selectAccount", account);
    }

    public List<AccountVo> selectForList(Account account) {
        return (List<AccountVo>) accountDao.selectForList("selectAccount", account);
    }

    public void update_(Account account) {
        accountDao.update("updateByExampleAccount", account);
    }


    public Page page(Page page, Account account) {
        return accountDao.page(page, account);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Long createAccount(Long counselorId) {
        Account account = new Account();
        account.setCounselorId(counselorId);
        Account old = this.selectForObject(account);
        Assert.isNull(old, counselorId + "已经存在账户");
        account.setAmount(0);
        account.setState(DefaultStateEnum.usable.getCode());
        return (Long) save(account);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void updateAccount(Long counselorId, Integer money, Date completeTime) {
        Account account = new Account();
        account.setCounselorId(counselorId);
        Account account_ = this.selectForObject(account);
        Assert.notNull(account_, counselorId + "不存在账户");
        Integer afterAmount = account_.getAmount() + money;
        accountDao.updateAccount(account_.getId(),afterAmount,money);
        AccountDetail detail = new AccountDetail();
        detail.setAccountId(account_.getId());
        detail.setBeforeAmount(account_.getAmount());
        detail.setAmount(money);
        detail.setAfterAmount(afterAmount);
        detail.setCreateTime(new Date());
        detail.setCompleteTime(completeTime == null ? new Date() : completeTime);
        accountDetailService.save(detail);
    }

    @Autowired
    public void setIAccountDao(
            @Qualifier("accountDao") IAccountDao accountDao) {
        this.accountDao = accountDao;
    }


}
