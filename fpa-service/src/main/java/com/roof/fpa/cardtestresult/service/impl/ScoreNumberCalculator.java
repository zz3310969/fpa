package com.roof.fpa.cardtestresult.service.impl;

import com.roof.chain.support.NodeResult;
import com.roof.fpa.cardtestresult.entity.GeneralCardTestCustomerResult;
import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;

public class ScoreNumberCalculator {
    private static final Logger LOGGER = LoggerFactory.getLogger(ScoreNumberCalculator.class);
    private static final int SCORE = 10;
    private static final String[] PROPERTY_NAMES = new String[]{"redScore", "yellowScore", "blueScore", "greenScore"};
    //互斥：红蓝互斥，黄绿互斥
    private static final String[][] mutex = new String[][]{new String[]{"red", "blue"}, new String[]{"yellow", "green"}};

    public String doNode(GeneralCardTestCustomerResult generalCardTestCustomerResult) {
        int ltScoreNumber = getLtScoreNumber(generalCardTestCustomerResult);//得分>=10的颜色数量
        if (ltScoreNumber == 0) {

        }
        if (ltScoreNumber == 1) { //取得分最高的颜色作为测试结果

        }
        if (ltScoreNumber > 1) {

        }


        return toSuccess();
    }

    private int getLtScoreNumber(GeneralCardTestCustomerResult generalCardTestCustomerResult) {
        int result = 0;
        for (String propertyName : PROPERTY_NAMES) {
            int s = 0;
            try {
                s = (Integer) PropertyUtils.getProperty(generalCardTestCustomerResult, propertyName);
            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                LOGGER.error(e.getMessage(), e);
            }
            if (s >= SCORE) {
                result++;
            }
        }
        return result;
    }


    private String toSuccess() {
        return NodeResult.SUCCESS;
    }
}
