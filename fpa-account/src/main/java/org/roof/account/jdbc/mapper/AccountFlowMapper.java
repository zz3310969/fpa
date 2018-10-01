package org.roof.account.jdbc.mapper;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;
import org.roof.account.entity.AccountFlow;

import java.util.List;
import java.util.Map;

@MapperScan
public interface AccountFlowMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AccountFlow record);

    int insertSelective(AccountFlow record);

    AccountFlow selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AccountFlow record);

    int updateByPrimaryKey(AccountFlow record);

    List<AccountFlow> selectByUserId(@Param("userId") Long userId, @Param("offset") Long offset,@Param("limit") Long limit);

    //Long queryByUserTypeTotalIncome(Long userId);

    Long queryByUserTypeTotalIncome(Map<String, Object> map);
}