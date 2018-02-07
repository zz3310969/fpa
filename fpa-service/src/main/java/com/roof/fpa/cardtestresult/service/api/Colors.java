package com.roof.fpa.cardtestresult.service.api;

import java.util.HashMap;
import java.util.Map;

public class Colors {
    public static final String RED = "RED";
    public static final String YELLOW = "YELLOW";
    public static final String BLUE = "BLUE";
    public static final String GREEN = "GREEN";

    public static final Map<String, String> colorMap = new HashMap<>();

    public static final String[] colors = new String[]{RED, YELLOW, BLUE, GREEN};

    static {
        colorMap.put(RED, "红色");
        colorMap.put(YELLOW, "黄色");
        colorMap.put(BLUE, "蓝色");
        colorMap.put(GREEN, "绿色");
    }
}
