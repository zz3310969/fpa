package com.roof.fpa.cardtestresult.entity;

/**
 * 客户展示性格色彩
 */
public class CardTestCustomerResult {
    protected String redScore; //红色得分
    protected String yellowScore; //黄色得分
    protected String blueScore; //蓝色得分
    protected String greenScore; //蓝色得分
    protected String CharacterColor; //性格色彩

    public String getRedScore() {
        return redScore;
    }

    public void setRedScore(String redScore) {
        this.redScore = redScore;
    }

    public String getYellowScore() {
        return yellowScore;
    }

    public void setYellowScore(String yellowScore) {
        this.yellowScore = yellowScore;
    }

    public String getBlueScore() {
        return blueScore;
    }

    public void setBlueScore(String blueScore) {
        this.blueScore = blueScore;
    }

    public String getGreenScore() {
        return greenScore;
    }

    public void setGreenScore(String greenScore) {
        this.greenScore = greenScore;
    }

    public String getCharacterColor() {
        return CharacterColor;
    }

    public void setCharacterColor(String characterColor) {
        CharacterColor = characterColor;
    }
}
