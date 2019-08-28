package com.roof.fpa.account.service.api;

import java.util.Date;
import java.util.List;
import java.io.Serializable;

import org.roof.roof.dataaccess.api.Page;
import com.roof.fpa.account.entity.Account;
import com.roof.fpa.account.entity.AccountVo;

public interface IAccountService {
    Long countAllUser();

    /**
     * 将对象保存，返回该条记录的操作数量，保存成功之后，将主键填充到参数对象中
     */
    public abstract Serializable save(Account account);

    /**
     * 按对象中的主键进行删除，如果是DRDS，还需要添加拆分键
     */
    public abstract void delete(Account account);

    /**
     * 按对象中的非空属性作为条件，进行删除
     */
    public abstract void deleteByExample(Account account);

    /**
     * 按对象中的主键进行所有属性的修改，如果是DRDS，还需要添加拆分键
     */
    public abstract void update(Account account);

    /**
     * 按对象中的主键进行所有非空属性的修改，如果是DRDS，还需要添加拆分键
     */
    public abstract void updateIgnoreNull(Account account);

    /**
     * 按对象中的非空属性作为条件，进行修改
     */
    public abstract void updateByExample(Account account);

    /**
     * 按对象中的主键进行数据加载，如果是DRDS，还需要添加拆分键
     */
    public abstract AccountVo load(Account account);

    /**
     * 按对象中的非空属性作为条件，进行查询实体
     */
    public abstract AccountVo selectForObject(Account account);

    /**
     * 按对象中的非空属性作为条件，进行查询列表
     */
    public abstract List<AccountVo> selectForList(Account account);

    /**
     * 按对象中的非空属性作为条件，进行分页查询
     */
    public abstract Page page(Page page, Account account);


    public abstract Long createAccount(Long counselorId);

    public abstract void updateAccount(Long counselorId, Integer money, Date completeTime);


}