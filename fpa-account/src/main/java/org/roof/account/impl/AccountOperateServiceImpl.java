package org.roof.account.impl;

import com.google.common.collect.Lists;
import org.roof.account.api.AccountType;
import org.roof.account.api.IAccountServiceContainer;
import org.roof.account.api.IAccountOperateService;
import org.roof.account.api.IAccountService;
import org.roof.account.entity.Account;
import org.roof.account.entity.AccountFlow;
import org.roof.account.jdbc.mapper.AccountFlowMapper;
import org.roof.account.jdbc.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccountOperateServiceImpl implements IAccountOperateService, IAccountServiceContainer {

    private List<IAccountService> list = Lists.newArrayList();

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private AccountFlowMapper accountFlowMapper;
    

    IAccountService getAccountService(AccountType type) {
        for (IAccountService accountService : list) {
            IAccountService accountService_ = accountService.get(type);
            if (accountService_ != null) {
                return accountService_;
            }
        }
        throw new UnsupportedOperationException("Unsupported grant type: " + type.name());
    }

    IAccountService getAccountService(String type) {
        for (IAccountService accountService : list) {
            IAccountService accountService_ = accountService.get(type);
            if (accountService_ != null) {
                return accountService_;
            }
        }
        throw new UnsupportedOperationException("Unsupported grant type: " + type);
    }

    @Override
    public Account create(Long userId, AccountType type, Map<String, String> map) {
        AccountRequest accountRequest = new AccountRequest(userId, type.name(), map);
        return getAccountService(type).create(accountRequest);
    }

    @Override
    public void freeze(Long accountId) {
        Account account = accountMapper.selectByPrimaryKey(accountId);
        Assert.notNull(account, "account must not null");
        getAccountService(account.getAccountType()).freeze(account);
    }

    @Override
    public void thaw(Long accountId) {
        Account account = accountMapper.selectByPrimaryKey(accountId);
        Assert.notNull(account, "account must not null");
        getAccountService(account.getAccountType()).thaw(account);
    }


    @Override
    public String recharge(Long accountId, int money, Map<String, String> map) {
        Assert.state(money > 0, "money must > 0");
        Account account = accountMapper.selectByPrimaryKey(accountId);
        Assert.notNull(account, "account must not null");
        Assert.state(account.isAccountNonLocked(), "account must not lock");
        getAccountService(account.getAccountType()).recharge(account, money, map);
        return null;
    }

    @Override
    public Map deduction(Long accountId, int money, Map<String, String> map) {
        Assert.state(money > 0, "money must > 0");
        Account account = accountMapper.selectByPrimaryKey(accountId);
        Assert.notNull(account, "account must not null");
        Map rsmap = getAccountService(account.getAccountType()).deduction(account, money, map);
        return rsmap;
    }

    @Override
    public String withdraw(Long originalAccountId, Long targetAccountId, int money, Map<String, String> map) {
        Assert.state(money > 0, "money must > 0");
        Account originalAccount = accountMapper.selectByPrimaryKey(originalAccountId);
        Assert.notNull(originalAccount, "account must not null");
        Assert.state(originalAccount.isAccountNonLocked(), "account must not lock");
        Account targetAccount = accountMapper.selectByPrimaryKey(targetAccountId);
        Assert.notNull(targetAccount, "account must not null");
        Assert.state(targetAccount.isAccountNonLocked(), "account must not lock");
        getAccountService(originalAccount.getAccountType()).deduction(originalAccount, money, map);
        getAccountService(targetAccount.getAccountType()).recharge(targetAccount, money, map);
        return null;
    }


    @Override
    public Account queryByAccountId(Long accountId) {
        Account account = accountMapper.selectByPrimaryKey(accountId);
        return account;
    }

    @Override
    public Account queryByUserType(Long userId, AccountType type) {
        Account account = new Account();
        account.setUserId(userId);
        account.setAccountType(type.name());
        return accountMapper.selectByExampleObject(account);
    }

    @Override
    public List<Account> queryByUser(Long userId) {
        return accountMapper.selectByUserId(userId);
    }

    @Override
    public List<AccountFlow> queryFlowByUser(Long userId, Long offset, Long limit) {
        if (offset == null) {
            offset = 0L;
        }
        if (limit == null) {
            limit = 10L;
        }
        return accountFlowMapper.selectByUserId(userId, offset, limit);
    }

    @Override
    public void refund(String accountId, int money) {

    }

    @Override
    public Long queryByUserTypeTotalIncome(Long userId, AccountType local) {
        Map<String,Object> map = new HashMap<>();
        map.put("userId",userId);
        return accountFlowMapper.queryByUserTypeTotalIncome(map);
    }

    @Override
    public Long queryByUserTypeTotalIncomeTime(Long userId, Date nowDayStart, Date nowDayEnd) {
        Map<String,Object> map = new HashMap<>();
        map.put("userId",userId);
        map.put("startTime",nowDayStart);
        map.put("endTime",nowDayEnd);
        Long l = accountFlowMapper.queryByUserTypeTotalIncome(map);
        return l == null ? 0L:l;
    }


    @Override
    public void addAccountService(IAccountService accountService) {
        list.add(accountService);
    }
}
