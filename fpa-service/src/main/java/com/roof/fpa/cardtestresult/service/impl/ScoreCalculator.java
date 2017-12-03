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
import org.apache.commons.lang.StringUtils;
import org.roof.web.dictionary.entity.Dictionary;
import org.roof.web.dictionary.service.api.IDictionaryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

/**
 * 得分计算
 */
public class ScoreCalculator {
    private static final Logger LOGGER = LoggerFactory.getLogger(ScoreCalculator.class);
    private String color; //颜色
    private String valPropertyName; //结果属性名称
    private ICardUnitService cardUnitService;
    private ICardSlotService cardSlotService;
    private IDictionaryService dictionaryService;

    public String doNode(CardTestResultVo cardTestResultVo, GeneralCardTestCustomerResult generalCardTestCustomerResult) {
        int score = 0;
        if (cardTestResultVo == null || CollectionUtils.isEmpty(cardTestResultVo.getResultDtoList()) || generalCardTestCustomerResult == null) {
            return toSuccess(score, generalCardTestCustomerResult);
        }
        for (CardTestResultDto cardTestResultDto : cardTestResultVo.getResultDtoList()) {
            CardUnit cardUnit = cardUnitService.load(cardTestResultDto.getCardUnitId());
            Dictionary colorDic = dictionaryService.load(new Dictionary(cardUnit.getColorId()));
            if (StringUtils.equalsIgnoreCase(colorDic.getVal(), color)) {
                CardSlot cardSlot = cardSlotService.load(cardTestResultDto.getCardSlotId());
                //TODO 使用场景配置的操作符
                score += cardUnit.getScore() + cardSlot.getWeight();
                LOGGER.info("color:{}, cardUnitScore:{}, cardSlotWeight:{}, score:{}", color, cardUnit.getScore(), cardSlot.getWeight(), score);
            }
        }
        return toSuccess(score, generalCardTestCustomerResult);
    }

    private String toSuccess(int score, GeneralCardTestCustomerResult generalCardTestCustomerResult) {
        try {
            PropertyUtils.setProperty(generalCardTestCustomerResult, valPropertyName, String.valueOf(score));
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return NodeResult.SUCCESS;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getValPropertyName() {
        return valPropertyName;
    }

    public void setValPropertyName(String valPropertyName) {
        this.valPropertyName = valPropertyName;
    }

    public ICardUnitService getCardUnitService() {
        return cardUnitService;
    }

    public void setCardUnitService(ICardUnitService cardUnitService) {
        this.cardUnitService = cardUnitService;
    }

    public ICardSlotService getCardSlotService() {
        return cardSlotService;
    }

    public void setCardSlotService(ICardSlotService cardSlotService) {
        this.cardSlotService = cardSlotService;
    }

    public IDictionaryService getDictionaryService() {
        return dictionaryService;
    }

    public void setDictionaryService(IDictionaryService dictionaryService) {
        this.dictionaryService = dictionaryService;
    }
}
