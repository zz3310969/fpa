package com.roof.fpa.cardtestresult.entity;

import java.util.List;

/**
 * Created by zhenglt on 2017/12/2.
 */
public class CardTestResultDto {

    protected Long cardSlotId;// 编号
    protected Long cardUnitId;// 编号


    public Long getCardSlotId() {
        return cardSlotId;
    }

    public void setCardSlotId(Long cardSlotId) {
        this.cardSlotId = cardSlotId;
    }

    public Long getCardUnitId() {
        return cardUnitId;
    }

    public void setCardUnitId(Long cardUnitId) {
        this.cardUnitId = cardUnitId;
    }
}
