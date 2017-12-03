package com.roof.fpa.cardtestresult.service.impl;

import com.roof.chain.support.NodeResult;
import com.roof.fpa.cardslot.entity.CardSlot;
import com.roof.fpa.cardslot.service.api.ICardSlotService;
import com.roof.fpa.cardtestresult.entity.CardTestResultDto;
import com.roof.fpa.cardtestresult.entity.CardTestResultVo;
import com.roof.fpa.cardtestresult.entity.GeneralCardTestCustomerResult;
import com.roof.fpa.cardunit.entity.CardUnit;
import com.roof.fpa.cardunit.service.api.ICardUnitService;
import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

public class DescriptionCalculator {
    private static final Logger LOGGER = LoggerFactory.getLogger(DescriptionCalculator.class);
    private BigDecimal yVal; //y偏移系数
    private Integer cardScore; //卡牌得分
    private String propertyName;//属性名称

    private ICardUnitService cardUnitService;
    private ICardSlotService cardSlotService;

    public String doNode(CardTestResultVo cardTestResultVo, GeneralCardTestCustomerResult generalCardTestCustomerResult) {
        if (cardTestResultVo == null || CollectionUtils.isEmpty(cardTestResultVo.getResultDtoList()) || generalCardTestCustomerResult == null) {
            return NodeResult.SUCCESS;
        }
        for (CardTestResultDto cardTestResultDto : cardTestResultVo.getResultDtoList()) {
            CardUnit cardUnit = cardUnitService.load(cardTestResultDto.getCardUnitId());
            CardSlot cardSlot = cardSlotService.selectBySceneIdAndNumb(cardTestResultVo.getSceneId(), cardTestResultDto.getNumb());
            if (cardUnit.getScore().equals(cardScore) && BigDecimal.valueOf(cardSlot.getYVal()).equals(yVal)) {
                try {
                    PropertyUtils.setProperty(generalCardTestCustomerResult, propertyName, cardUnit.getDescription());
                } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                    LOGGER.error(e.getMessage(), e);
                }
            }
        }
        return NodeResult.SUCCESS;
    }

    public void setCardUnitService(ICardUnitService cardUnitService) {
        this.cardUnitService = cardUnitService;
    }

    public void setCardSlotService(ICardSlotService cardSlotService) {
        this.cardSlotService = cardSlotService;
    }

    public void setyVal(BigDecimal yVal) {
        this.yVal = yVal;
    }

    public void setCardScore(Integer cardScore) {
        this.cardScore = cardScore;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }
}
