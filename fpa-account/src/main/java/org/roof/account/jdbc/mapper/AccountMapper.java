package org.roof.account.jdbc.mapper;


import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;
import org.roof.account.entity.Account;

import java.util.List;

@MapperScan
public interface AccountMapper {
    int deleteByPrimaryKey(Long id);

    Long insert(Account record);

    Long insertSelective(Account record);

    Account selectByPrimaryKey(Long id);

    List<Account> selectByUserId(Long userId);

    Account selectByExampleObject(Account record);

    int updateByPrimaryKeySelective(Account record);

    int updateByPrimaryKey(Account record);

    int updateBalance(@Param("id") Long id,@Param("balance") int balance,@Param("oldBalance") int oldBalance);
}