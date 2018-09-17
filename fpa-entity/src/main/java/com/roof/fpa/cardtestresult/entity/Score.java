package com.roof.fpa.cardtestresult.entity;

import io.swagger.annotations.ApiModelProperty;

public class Score {

    @ApiModelProperty(value = "红色得分")
    protected String red;// 红色得分
    @ApiModelProperty(value = "蓝色得分")
    protected String blue;// 蓝色得分
    @ApiModelProperty(value = "黄色得分")
    protected String yellow;// 黄色得分
    @ApiModelProperty(value = "绿色得分")
    protected String green;// 绿色得分

    public String getRed() {
        return red;
    }

    public void setRed(String red) {
        this.red = red;
    }

    public String getBlue() {
        return blue;
    }

    public void setBlue(String blue) {
        this.blue = blue;
    }

    public String getYellow() {
        return yellow;
    }

    public void setYellow(String yellow) {
        this.yellow = yellow;
    }

    public String getGreen() {
        return green;
    }

    public void setGreen(String green) {
        this.green = green;
    }

    public static Score toAd(GeneralCardTestCustomerResult result){
        Score score = new Score();
        score.setRed(result.getRedAdScore());
        score.setYellow(result.getYellowAdScore());
        score.setBlue(result.getBlueAdScore());
        score.setGreen(result.getGreenAdScore());
        return score;
    }

    public static Score toIm(GeneralCardTestCustomerResult result){
        Score score = new Score();
        score.setRed(result.getRedImScore());
        score.setYellow(result.getYellowImScore());
        score.setBlue(result.getBlueImScore());
        score.setGreen(result.getGreenImScore());
        return score;
    }
}
