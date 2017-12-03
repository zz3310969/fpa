package com.roof.fpa.cardtestresult.service.impl;

import com.roof.fpa.cardtestresult.entity.CardTestCustomerResult;
import com.roof.fpa.cardtestresult.entity.CardTestResultDto;
import com.roof.fpa.cardtestresult.entity.GeneralCardTestCustomerResult;
import com.roof.fpa.cardtestresult.service.api.ICardTestCustomerResultCalculator;

import java.util.List;

public class CardTestCustomerResultCalculator implements ICardTestCustomerResultCalculator<GeneralCardTestCustomerResult> {
    @Override
    public GeneralCardTestCustomerResult calculator(List<CardTestResultDto> cardTestResultDtos) {
        return null;
    }
}
