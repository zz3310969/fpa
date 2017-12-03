package com.roof.fpa.cardtestresult.entity;

import java.util.List;

/**
 * Created by zhenglt on 2017/12/2.
 */
public class CardTestResultDto {
    protected Integer numb;// 卡槽编号
    protected Long cardUnitId;// 卡牌id

    public Integer getNumb() {
        return numb;
    }

    public void setNumb(Integer numb) {
        this.numb = numb;
    }

    public Long getCardUnitId() {
        return cardUnitId;
    }

    public void setCardUnitId(Long cardUnitId) {
        this.cardUnitId = cardUnitId;
    }
}
