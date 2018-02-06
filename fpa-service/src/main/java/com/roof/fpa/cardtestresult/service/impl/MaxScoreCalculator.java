package com.roof.fpa.cardtestresult.service.impl;

import com.roof.chain.support.NodeResult;
import com.roof.fpa.cache.api.ICacheHander;
import com.roof.fpa.cardtestresult.entity.CardTestResultVo;
import com.roof.fpa.cardtestresult.entity.GeneralCardTestCustomerResult;
import com.roof.fpa.charactercolor.entity.CharacterColor;
import com.roof.fpa.charactercolor.service.api.ICharacterColorService;
import org.apache.commons.lang3.math.NumberUtils;
import org.roof.web.dictionary.entity.Dictionary;
import org.roof.web.dictionary.service.api.IDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**
 * 最大分值计算和性格色彩
 */
public class MaxScoreCalculator {

    private static final String RED = "RED";
    private static final String YELLOW = "YELLOW";
    private static final String BLUE = "BLUE";
    private static final String GREEN = "GREEN";
    private static final Map<String, String> colorMap = new HashMap<>();
    private IDictionaryService dictionaryService;
    private ICharacterColorService characterColorService;

    private ICacheHander cacheHander;


    static {
        colorMap.put(RED, "红色");
        colorMap.put(YELLOW, "黄色");
        colorMap.put(BLUE, "蓝色");
        colorMap.put(GREEN, "绿色");
    }

    public String doNode(CardTestResultVo cardTestResultVo, GeneralCardTestCustomerResult generalCardTestCustomerResult) {
        int scoreMax = 0;
        String color = RED;
        if (NumberUtils.createInteger(generalCardTestCustomerResult.getRedScore()) > scoreMax) {
            scoreMax = NumberUtils.createInteger(generalCardTestCustomerResult.getRedScore());
            color = RED;
        }
        if (NumberUtils.createInteger(generalCardTestCustomerResult.getYellowScore()) > scoreMax) {
            scoreMax = NumberUtils.createInteger(generalCardTestCustomerResult.getYellowScore());
            color = YELLOW;
        }
        if (NumberUtils.createInteger(generalCardTestCustomerResult.getBlueScore()) > scoreMax) {
            scoreMax = NumberUtils.createInteger(generalCardTestCustomerResult.getBlueScore());
            color = BLUE;
        }
        if (NumberUtils.createInteger(generalCardTestCustomerResult.getGreenScore()) > scoreMax) {
            scoreMax = NumberUtils.createInteger(generalCardTestCustomerResult.getGreenScore());
            color = GREEN;
        }
        Dictionary colorDic = cacheHander.loadDictionaryByType("COLOR", color);
        generalCardTestCustomerResult.setScoreMax(String.valueOf(scoreMax));
        generalCardTestCustomerResult.setScoreMaxColorId(colorDic.getId());
        generalCardTestCustomerResult.setScoreMaxColorName(colorMap.get(color));
        CharacterColor characterColor = characterColorService.selectByColorIdByCache(colorDic.getId());
        generalCardTestCustomerResult.setCharacterColorDefn(characterColor.getDescription());
        generalCardTestCustomerResult.setScoreMaxColorCode(color);
        String colorMin = RED;
        int scoreMin = Integer.MAX_VALUE;
        if (NumberUtils.createInteger(generalCardTestCustomerResult.getRedScore()) < scoreMin) {
            scoreMin = NumberUtils.createInteger(generalCardTestCustomerResult.getRedScore());
            colorMin = RED;
        }
        if (NumberUtils.createInteger(generalCardTestCustomerResult.getYellowScore()) < scoreMin) {
            scoreMin = NumberUtils.createInteger(generalCardTestCustomerResult.getYellowScore());
            colorMin = YELLOW;
        }
        if (NumberUtils.createInteger(generalCardTestCustomerResult.getBlueScore()) < scoreMin) {
            scoreMin = NumberUtils.createInteger(generalCardTestCustomerResult.getBlueScore());
            colorMin = BLUE;
        }
        if (NumberUtils.createInteger(generalCardTestCustomerResult.getGreenScore()) < scoreMin) {
            scoreMin = NumberUtils.createInteger(generalCardTestCustomerResult.getGreenScore());
            colorMin = GREEN;
        }

        Dictionary colorDicMin = cacheHander.loadDictionaryByType("COLOR", colorMin);
        CharacterColor characterColorMin = characterColorService.selectByColorIdByCache(colorDicMin.getId());
        if (characterColorMin.getDescriptionLack() != null) {
            generalCardTestCustomerResult.setCharacterColorLows(characterColorMin.getDescriptionLack());
        }
        return toSuccess();
    }

    private String toSuccess() {
        return NodeResult.SUCCESS;
    }

    public void setDictionaryService(IDictionaryService dictionaryService) {
        this.dictionaryService = dictionaryService;
    }

    public void setCharacterColorService(ICharacterColorService characterColorService) {
        this.characterColorService = characterColorService;
    }

    public void setCacheHander(ICacheHander cacheHander) {
        this.cacheHander = cacheHander;
    }
}
