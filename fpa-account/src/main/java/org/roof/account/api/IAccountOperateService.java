package org.roof.account.api;

import org.roof.account.entity.Account;
import org.roof.account.entity.AccountFlow;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 对外API
 */
public interface IAccountOperateService {

    /**
     * 创建账户
     *
     * @param userId 用户ID
     * @param type   账户类型
     * @return 账户信息
     */
    Account create(Long userId, AccountType type, Map<String, String> map);


    /**
     * 冻结
     *
     * @param accountId 账户标识
     * @return
     */
    void freeze(Long accountId);

    /**
     * 解冻
     *
     * @param accountId 账户标识
     * @return
     */
    void thaw(Long accountId);


    /**
     * 充值
     *
     * @param accountId 账户标识
     * @param money     金额 必须大于0
     * @return 流水号
     */
    String recharge(Long accountId, int money, Map<String, String> map);

    /**
     * 扣款
     *
     * @param accountId 账户标识
     * @param money     金额 必须大于0
     * @return 流水号
     */
    Map deduction(Long accountId, int money, Map<String, String> map);


    /**
     * 提现
     *
     * @param originalAccountId 原账户
     * @param targetAccountId   目标账户
     * @param money             金额 必须大于0
     * @return 流水号
     */
    String withdraw(Long originalAccountId, Long targetAccountId, int money, Map<String, String> map);


    /**
     * 根据账户标识查询账户信息
     *
     * @param accountId
     * @return
     */
    Account queryByAccountId(Long accountId);

    Account queryByUserType(Long userId, AccountType type);

    /**
     * 根据用户ID查询账户信息
     *
     * @param userId
     * @return
     */
    List<Account> queryByUser(Long userId);


    /**
     * 根据用户标识查询流水
     *
     * @param userId
     * @return
     */
    List<AccountFlow> queryFlowByUser(Long userId, Long offset, Long limit);


    /**
     * @param accountId
     * @param money
     */
    void refund(String accountId, int money);


    Long queryByUserTypeTotalIncome(Long userId, AccountType local);

    Long queryByUserTypeTotalIncomeTime(Long userId, Date nowDayStart, Date nowDayEnd);
}
