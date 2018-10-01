package org.roof.account.api;

import org.roof.account.entity.Account;
import org.roof.account.impl.AccountRequest;

import java.util.Map;

public interface IAccountService {

    Account load(Long id);

    Account create(AccountRequest accountRequest);

    void freeze(Account account);

    void recharge(Account account, int money, Map<String, String> map);

    Map deduction(Account account, int money, Map<String, String> map);

    void thaw(Account account);

    IAccountService get(AccountType type);

    IAccountService get(String type);
}
