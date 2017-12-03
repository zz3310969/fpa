package com.roof.fpa.cardtestresult.service.api;

import com.roof.fpa.cardtestresult.entity.CardTestResultDto;

import java.util.List;

/**
 * 用户卡牌测试结果计算器
 *
 * @param <T> 计算完成后的结果对象
 */
public interface ICardTestCustomerResultCalculator<T> {
    /**
     * 计算结果
     *
     * @param cardTestResultDtos 用户提交的测试结果
     * @return 计算完成后给用户展现的结果对象
     */
    T calculator(List<CardTestResultDto> cardTestResultDtos);
}
