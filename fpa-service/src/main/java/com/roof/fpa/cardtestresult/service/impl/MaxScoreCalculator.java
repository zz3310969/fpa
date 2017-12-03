package com.roof.fpa.cardtestresult.service.impl;

import com.roof.chain.support.NodeResult;
import com.roof.fpa.cardtestresult.entity.CardTestResultVo;
import com.roof.fpa.cardtestresult.entity.GeneralCardTestCustomerResult;
import com.roof.fpa.charactercolor.entity.CharacterColor;
import com.roof.fpa.charactercolor.service.api.ICharacterColorService;
import org.apache.commons.lang3.math.NumberUtils;
import org.roof.web.dictionary.entity.Dictionary;
import org.roof.web.dictionary.service.api.IDictionaryService;

/**
 * 最大分值计算和性格色彩
 */
public class MaxScoreCalculator {

    private static final String RED = "RED";
    private static final String YELLOW = "YELLOW";
    private static final String BLUE = "BLUE";
    private static final String GREEN = "GREEN";
    private IDictionaryService dictionaryService;
    private ICharacterColorService characterColorService;

    public String doNode(CardTestResultVo cardTestResultVo, GeneralCardTestCustomerResult generalCardTestCustomerResult) {
        int scoreMax = 0;
        String color = RED;
        if (NumberUtils.createInteger(generalCardTestCustomerResult.getRedScore()) > scoreMax) {
            scoreMax = NumberUtils.createInteger(generalCardTestCustomerResult.getRedScore());
            color = RED;
        }
        if (NumberUtils.createInteger(generalCardTestCustomerResult.getYellowScore()) > scoreMax) {
            scoreMax = NumberUtils.createInteger(generalCardTestCustomerResult.getRedScore());
            color = YELLOW;
        }
        if (NumberUtils.createInteger(generalCardTestCustomerResult.getBlueScore()) > scoreMax) {
            scoreMax = NumberUtils.createInteger(generalCardTestCustomerResult.getRedScore());
            color = BLUE;
        }
        if (NumberUtils.createInteger(generalCardTestCustomerResult.getGreenScore()) > scoreMax) {
            scoreMax = NumberUtils.createInteger(generalCardTestCustomerResult.getRedScore());
            color = GREEN;
        }
        Dictionary colorDic = dictionaryService.load("COLOR", color);
        generalCardTestCustomerResult.setScoreMax(String.valueOf(scoreMax));
        generalCardTestCustomerResult.setCharacterCare(color);
        generalCardTestCustomerResult.setScoreMaxColorId(colorDic.getId());

        CharacterColor characterColor = characterColorService.selectByColorId(colorDic.getId());
        generalCardTestCustomerResult.setCharacterColorDefn(characterColor.getDescription());
        if (characterColor.getDescriptionLack()  != null) {
            generalCardTestCustomerResult.setCharacterColorLows(characterColor.getDescriptionLack());
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

}
