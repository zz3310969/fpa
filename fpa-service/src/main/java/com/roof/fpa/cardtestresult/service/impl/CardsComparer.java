package com.roof.fpa.cardtestresult.service.impl;

import com.roof.fpa.cardunit.entity.CardUnit;

import java.util.Objects;

public class CardsComparer {
    private int lam = 10;
    private float tau = 0.3f;

    /**
     * 相似度
     *
     * <p>
     * 相似度算法
     *cards_1和cards_2是两副牌面，记录每个位置牌的编号和正反面，lam和tau是两个参数，lam
     *用于控制牌面相同时，在不同行的相似度权重，lam越大，则只要牌面相同，不论行数是否一样，相似度
     *都越高；tau用于提升最小值，默认为0 时，两人相似度最低为0，设置为1时，两人相似度最低为1
     *函数判断每个位置两个人是否有相同牌面，再用参数调节分布。
     * </p>
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
     * <p>
     *     互补度算法
     *思路类似相似度算法，只不过和相似度算法的思路刚好相反，当两次测试，同一张牌牌面相同时，互补度低；
     *同一张牌，牌面不同时，互补度高。
     * </p>
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
