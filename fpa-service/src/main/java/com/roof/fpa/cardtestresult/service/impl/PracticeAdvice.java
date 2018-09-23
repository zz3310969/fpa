package com.roof.fpa.cardtestresult.service.impl;

import com.roof.fpa.cardtestresult.entity.GeneralCardTestCustomerResult;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * @author liuxin
 * @since 2018/9/16
 */
public class PracticeAdvice {

    private Map<Object, Object> practiceAdviceMap;
    private static final Logger LOGGER = LoggerFactory.getLogger(PracticeAdvice.class);


    public PracticeAdvice() {
        Properties properties = new Properties();
        InputStream in = null;
        try {
            in = PracticeAdvice.class.getClassLoader().getResourceAsStream("practice-advice.properties");
            properties.load(in);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ignored) {
            }
        }
        practiceAdviceMap = properties;
    }

    public String doNode(GeneralCardTestCustomerResult generalCardTestCustomerResult) {
        Map<String, Double> adScoreMap = new HashMap<>();
        adScoreMap.put("red", Double.valueOf(generalCardTestCustomerResult.getRedAdScore()));
        adScoreMap.put("blue", Double.valueOf(generalCardTestCustomerResult.getBlueAdScore()));
        adScoreMap.put("yellow", Double.valueOf(generalCardTestCustomerResult.getYellowAdScore()));
        adScoreMap.put("green", Double.valueOf(generalCardTestCustomerResult.getGreenAdScore()));
        List<Map.Entry<String, Double>> adSoreList = sortByValue(adScoreMap);
        String adColor = adSoreList.get(adSoreList.size() - 1).getKey();

        Map<String, Double> imScoreMap = new HashMap<>();
        imScoreMap.put("red", Double.valueOf(generalCardTestCustomerResult.getRedImScore()));
        imScoreMap.put("blue", Double.valueOf(generalCardTestCustomerResult.getBlueImScore()));
        imScoreMap.put("yellow", Double.valueOf(generalCardTestCustomerResult.getYellowImScore()));
        imScoreMap.put("green", Double.valueOf(generalCardTestCustomerResult.getGreenImScore()));

        List<Map.Entry<String, Double>> imScoreList = sortByValue(imScoreMap);
        String imColor = imScoreList.get(imScoreList.size() - 1).getKey();

        String key = adColor + "_" + imColor;
        String practiceAdvice = String.valueOf((practiceAdviceMap.get(key)));
        generalCardTestCustomerResult.setPracticeAdvice(practiceAdvice);
        return "success";
    }

    private List<Map.Entry<String, Double>> sortByValue(Map<String, Double> map) {
        List<Map.Entry<String, Double>> list = new ArrayList<>(map.entrySet());
        //升序排序
        list.sort(Comparator.comparing(Map.Entry::getValue));
        return list;
    }

}
