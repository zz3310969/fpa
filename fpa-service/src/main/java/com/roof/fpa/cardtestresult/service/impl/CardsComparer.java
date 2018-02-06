package com.roof.fpa.cardtestresult.service.impl;

import com.roof.fpa.cardunit.entity.CardUnit;

import java.util.Objects;

public class CardsComparer {
    private int lam = 10;
    private float tau = 0.3f;

    /**
     * 相似度
     *
     * @param cardUnits1 第一组卡牌
     * @param cardUnits2 第一组卡牌
     * @return 相似度
     */
    public float degree(CardUnit[] cardUnits1, CardUnit[] cardUnits2) {
        float same = 0;
        for (int i = 0; i < cardUnits1.length; i++) {
            CardUnit cardUnit1 = cardUnits1[i];
            int cardUnit2Index = getCardUnitIndex(cardUnit1, cardUnits2);
            if (cardUnit2Index == -1) { //在第二组卡牌中未找到该卡牌
                same += tau / (1 + tau);
            } else {
                //两张牌所在行差值
                float temp = (lam - Math.abs(i / 4 - cardUnit2Index / 4)) / lam;
                same += (temp + tau) / (1 + tau);
            }
        }
        return same / 12;
    }

    /**
     * 互补度
     *
     * @param cardUnits1 第一组卡牌
     * @param cardUnits2 第一组卡牌
     * @return
     */
    public float comple(CardUnit[] cardUnits1, CardUnit[] cardUnits2) {
        float com = 0;
        for (int i = 0; i < cardUnits1.length; i++) {
            CardUnit cardUnit1 = cardUnits1[i];
            int cardUnit2Index = getCardUnitIndex(cardUnit1, cardUnits2);
            if (cardUnit2Index == -1) {//在第二组卡牌中未找到该卡牌
                cardUnit2Index = getCardIndex(cardUnit1, cardUnits2);
                float temp = (lam - Math.abs(i / 4 - cardUnit2Index / 4)) / lam;
                com += (temp + tau) / (1 + tau);
            } else {
                com += tau / (1 + tau);
            }

        }
        return com / 12;
    }

    /**
     * 在同一面不存在的情况下
     * 查找另一幅牌中同一张卡牌的索引
     */
    private int getCardIndex(CardUnit cardUnit, CardUnit[] cardUnits) {
        for (int i = 0; i < cardUnits.length; i++) {
            CardUnit unit = cardUnits[i];
            if (Objects.equals(unit.getCardId(), cardUnit.getCardId())) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 查找一张卡牌的一面在另外一副卡牌中的索引
     */
    private int getCardUnitIndex(CardUnit cardUnit, CardUnit[] cardUnits) {
        for (int i = 0; i < cardUnits.length; i++) {
            CardUnit unit = cardUnits[i];
            if (Objects.equals(unit.getId(), cardUnit.getId())) {
                return i;
            }
        }
        return -1;
    }
}
