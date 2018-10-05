package org.roof.account.local.service;

import org.roof.account.api.AccountType;
import org.roof.account.api.IAccountService;
import org.roof.account.api.IAccountServiceContainer;
import org.roof.account.entity.Account;
import org.roof.account.entity.AccountFlow;
import org.roof.account.impl.AccountRequest;
import org.roof.account.jdbc.mapper.AccountFlowMapper;
import org.roof.account.jdbc.mapper.AccountMapper;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Map;

public class LoaclAccountService implements IAccountService, InitializingBean {

    private AccountType accountType = AccountType.local;

    @Autowired
    private IAccountServiceContainer accountServiceContainer;

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private AccountFlowMapper accountFlowMapper;

    @Override
    public Account load(Long id) {
        return accountMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Account create(AccountRequest accountRequest) {
        Account account = new Account(0, 0);
        account.setUserId(accountRequest.getUserId());
        account.setAccountType(accountType.name());
        account.setCreateTime(new Date());
        account.setId(accountMapper.insert(account));
        return account;
    }

    public String createAccountNum() {
        return null;
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void freeze(Account account) {
        account.setAccountNonLocked(false);
        accountMapper.updateByPrimaryKeySelective(account);
    }

    @Override
    public IAccountService get(AccountType type) {
        if (accountType.equals(type)) {
            return this;
        } else {
            return null;
        }
    }

    @Override
    public IAccountService get(String type) {
        if (accountType.name().equals(type)) {
            return this;
        } else {
            return null;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void recharge(Account account, int money, Map<String, String> map) {
        int j = 0;
        while (j < 3) {
            int i = accountMapper.updateBalance(account.getId(), account.getBalance() + money, account.getBalance());
            if (i == 1) {
                AccountFlow accountFlow = new AccountFlow(account.getUserId(), account.getId(), money);
                accountFlow.setLastTime(new Date());
                accountFlow.setLastBalance(account.getBalance() + money);
                accountFlow.setRemark(map.get("remark"));
                accountFlow.setTag1(map.get("tag1"));
                accountFlow.setTag2(map.get("tag2"));
                accountFlow.setTag3(map.get("tag3"));
                accountFlow.setTag4(map.get("tag4"));
                accountFlow.setTag5(map.get("tag5"));
                accountFlowMapper.insert(accountFlow);
                return;
            }
            account = load(account.getId());
            j++;
        }
        throw new RuntimeException("更新账户失败");
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Map deduction(Account account, int money, Map<String, String> map) {
        int j = 0;
        while (j < 3) {
            int i = accountMapper.updateBalance(account.getId(), account.getBalance() - money, account.getBalance());
            if (i == 1) {
                AccountFlow accountFlow = new AccountFlow(account.getUserId(), account.getId(), -money);
                accountFlow.setLastTime(new Date());
                accountFlow.setLastBalance(account.getBalance() - money);
                accountFlowMapper.insert(accountFlow);
                //TODO zlt改
                return null;
            }
            account = load(account.getId());
            j++;
        }
        throw new RuntimeException("更新账户失败");
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void thaw(Account account) {
        account.setAccountNonLocked(true);
        accountMapper.updateByPrimaryKeySelective(account);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        accountServiceContainer.addAccountService(this);
    }
}
