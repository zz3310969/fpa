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
        Map<String, Integer> adScoreMap = new HashMap<>();
        adScoreMap.put("red", Integer.valueOf(generalCardTestCustomerResult.getRedAdScore()));
        adScoreMap.put("blue", Integer.valueOf(generalCardTestCustomerResult.getBlueAdScore()));
        adScoreMap.put("yellow", Integer.valueOf(generalCardTestCustomerResult.getYellowAdScore()));
        adScoreMap.put("green", Integer.valueOf(generalCardTestCustomerResult.getGreenAdScore()));
        List<Map.Entry<String, Integer>> adSoreList = sortByValue(adScoreMap);
        String adColor = adSoreList.get(adSoreList.size() - 1).getKey();

        Map<String, Integer> imScoreMap = new HashMap<>();
        imScoreMap.put("red", Integer.valueOf(generalCardTestCustomerResult.getRedImScore()));
        imScoreMap.put("blue", Integer.valueOf(generalCardTestCustomerResult.getBlueImScore()));
        imScoreMap.put("yellow", Integer.valueOf(generalCardTestCustomerResult.getYellowImScore()));
        imScoreMap.put("green", Integer.valueOf(generalCardTestCustomerResult.getGreenImScore()));

        List<Map.Entry<String, Integer>> imScoreList = sortByValue(imScoreMap);
        String imColor = imScoreList.get(imScoreList.size() - 1).getKey();

        String key = adColor + "_" + imColor;
        String practiceAdvice = String.valueOf((practiceAdviceMap.get(key)));
        generalCardTestCustomerResult.setPracticeAdvice(practiceAdvice);
        return "success";
    }

    private List<Map.Entry<String, Integer>> sortByValue(Map<String, Integer> map) {
        List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
        //升序排序
        list.sort(Comparator.comparing(Map.Entry::getValue));
        return list;
    }

}
