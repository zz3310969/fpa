package com.roof.fpa.cardtestresult.service.impl;

import com.roof.chain.support.NodeResult;
import com.roof.fpa.cache.api.ICacheHander;
import com.roof.fpa.cardtestresult.entity.GeneralCardTestCustomerResult;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.roof.web.dictionary.entity.Dictionary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;

import static com.roof.fpa.cardtestresult.service.api.Colors.*;


public class ScoreNumberCalculator {
    private static final Logger LOGGER = LoggerFactory.getLogger(ScoreNumberCalculator.class);
    private static final int SCORE = 10;
    private static final String[] PROPERTY_NAMES = new String[]{"redScore", "yellowScore", "blueScore", "greenScore"};
    //互斥：红蓝互斥，黄绿互斥
    private static final String[][] mutex = new String[][]{new String[]{RED, BLUE}, new String[]{YELLOW, GREEN}};

    private ICacheHander cacheHander;


    public String doNode(GeneralCardTestCustomerResult generalCardTestCustomerResult) {
        int ltScoreNumber = getLtScoreNumber(generalCardTestCustomerResult);//得分>=10的颜色数量
        if (ltScoreNumber == 0) {

        }
        if (ltScoreNumber == 1) { //取得分最高的颜色作为测试结果
            getMaxScoreColor(generalCardTestCustomerResult);
        }
        if (ltScoreNumber > 1) {

        }


        return toSuccess();
    }

    /**
     * 获取得分最高的颜色
     */
    private String getMaxScoreColor(GeneralCardTestCustomerResult generalCardTestCustomerResult) {
        String color = null;
        int maxColor = -1;
        for (String propertyName : PROPERTY_NAMES) {
            int s = 0;
            try {
                s = (Integer) PropertyUtils.getProperty(generalCardTestCustomerResult, propertyName);
            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                LOGGER.error(e.getMessage(), e);
            }
            if (s > maxColor) {
                maxColor = s;
                generalCardTestCustomerResult.setScoreMax(String.valueOf(maxColor));
                for (String c : colorMap.keySet()) {
                    if (StringUtils.containsIgnoreCase(propertyName, c)) {
                        color = c;
                        generalCardTestCustomerResult.setScoreMaxColorName(color);
                        Dictionary colorDic = cacheHander.loadDictionaryByType("COLOR", color);
                        generalCardTestCustomerResult.setScoreMaxColorId(colorDic.getId());
                    }
                }
            }
        }
        return color;
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

    public void setCacheHander(ICacheHander cacheHander) {
        this.cacheHander = cacheHander;
    }
}
