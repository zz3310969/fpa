package com.roof.fpa.cardtestresultdetail.service.api;

import java.util.List;
import java.io.Serializable;

import com.roof.fpa.cardtestresult.entity.CardTestResultDto;
import com.roof.fpa.cardunit.entity.CardUnit;
import org.roof.roof.dataaccess.api.Page;
import com.roof.fpa.cardtestresultdetail.entity.CardTestResultDetail;
import com.roof.fpa.cardtestresultdetail.entity.CardTestResultDetailVo;

public interface ICardTestResultDetailService {

	/**
	 * 将对象保存，返回该条记录的操作数量，保存成功之后，将主键填充到参数对象中
	 */
	public abstract Serializable save(CardTestResultDetail cardTestResultDetail);

	/**
	 * 按对象中的主键进行删除，如果是DRDS，还需要添加拆分键
	 */
	public abstract void delete(CardTestResultDetail cardTestResultDetail);
	
	/**
	 * 按对象中的非空属性作为条件，进行删除
	 */
	public abstract void deleteByExample(CardTestResultDetail cardTestResultDetail);

	/**
	 * 按对象中的主键进行所有属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void update(CardTestResultDetail cardTestResultDetail);
	
	/**
	 * 按对象中的主键进行所有非空属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void updateIgnoreNull(CardTestResultDetail cardTestResultDetail);
	
	/**
	 * 按对象中的非空属性作为条件，进行修改
	 */
	public abstract void updateByExample(CardTestResultDetail cardTestResultDetail);

	/**
	 * 按对象中的主键进行数据加载，如果是DRDS，还需要添加拆分键
	 */
	public abstract CardTestResultDetailVo load(CardTestResultDetail cardTestResultDetail);
	
	/**
	 * 按对象中的非空属性作为条件，进行查询实体
	 */
	public abstract CardTestResultDetailVo selectForObject(CardTestResultDetail cardTestResultDetail);
	
	/**
	 * 按对象中的非空属性作为条件，进行查询列表
	 */
	public abstract List<CardTestResultDetailVo> selectForList(CardTestResultDetail cardTestResultDetail);

	public abstract List<CardTestResultDetailVo> selectForList_v2(CardTestResultDetail cardTestResultDetail);

	/**
	 * 按对象中的非空属性作为条件，进行分页查询
	 */
	public abstract Page page(Page page, CardTestResultDetail cardTestResultDetail);

	public abstract void saveList(List<CardTestResultDto> cardTestResultDtoList,Long resultId);

	public abstract CardUnit[] selectForListByResultId(Long resultId);



}