package com.roof.fpa.cardtestresult.entity;

public class GeneralCardTestCustomerResult extends CardTestCustomerResult {
    private Long scoreMaxColorId; //得分最高的colorId
    private String scoreMax =""; //分数最高
    private String characterColorLows=""; //缺少
    private String characterAdvantage=""; //突出的优势
    private String characterAdvantageOther="";
    private String characterInsufficient="";
    private String characterInsufficientOther="";
    private String characterCare="";
    private String characterCareOther="";
    private String templateId="";
    private String scoreMaxColorName = "";
    private String scoreMaxColorCode = "";

    private String redAdScore = ""; //红色优势百分比
    private String redImScore = "";//红色过当百分比

    private String blueAdScore = ""; //蓝色优势百分比
    private String blueImScore = "";//蓝色过当百分比

    private String yellowAdScore = "";//黄色优势百分比
    private String yellowImScore = "";//黄色过当百分比

    private String greenAdScore = "";//绿色优势百分比
    private String greenImScore = "";//绿色过当百分比

    private String practiceAdvice = ""; //修炼建议

    public Long getScoreMaxColorId() {
        return scoreMaxColorId;
    }

    public void setScoreMaxColorId(Long scoreMaxColorId) {
        this.scoreMaxColorId = scoreMaxColorId;
    }

    public String getScoreMax() {
        return scoreMax;
    }

    public void setScoreMax(String scoreMax) {
        this.scoreMax = scoreMax;
    }

    public String getCharacterColorLows() {
        return characterColorLows;
    }

    public void setCharacterColorLows(String characterColorLows) {
        this.characterColorLows = characterColorLows;
    }

    public String getCharacterAdvantage() {
        return characterAdvantage;
    }

    public void setCharacterAdvantage(String characterAdvantage) {
        this.characterAdvantage = characterAdvantage;
    }

    public String getCharacterAdvantageOther() {
        return characterAdvantageOther;
    }

    public void setCharacterAdvantageOther(String characterAdvantageOther) {
        this.characterAdvantageOther = characterAdvantageOther;
    }

    public String getCharacterInsufficient() {
        return characterInsufficient;
    }

    public void setCharacterInsufficient(String characterInsufficient) {
        this.characterInsufficient = characterInsufficient;
    }

    public String getCharacterInsufficientOther() {
        return characterInsufficientOther;
    }

    public void setCharacterInsufficientOther(String characterInsufficientOther) {
        this.characterInsufficientOther = characterInsufficientOther;
    }

    public String getCharacterCare() {
        return characterCare;
    }

    public void setCharacterCare(String characterCare) {
        this.characterCare = characterCare;
    }

    public String getCharacterCareOther() {
        return characterCareOther;
    }

    public void setCharacterCareOther(String characterCareOther) {
        this.characterCareOther = characterCareOther;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getScoreMaxColorName() {
        return scoreMaxColorName;
    }

    public void setScoreMaxColorName(String scoreMaxColorName) {
        this.scoreMaxColorName = scoreMaxColorName;
    }

    public String getScoreMaxColorCode() {
        return scoreMaxColorCode;
    }

    public void setScoreMaxColorCode(String scoreMaxColorCode) {
        this.scoreMaxColorCode = scoreMaxColorCode;
    }

    public String getRedAdScore() {
        return redAdScore;
    }

    public void setRedAdScore(String redAdScore) {
        this.redAdScore = redAdScore;
    }

    public String getRedImScore() {
        return redImScore;
    }

    public void setRedImScore(String redImScore) {
        this.redImScore = redImScore;
    }

    public String getBlueAdScore() {
        return blueAdScore;
    }

    public void setBlueAdScore(String blueAdScore) {
        this.blueAdScore = blueAdScore;
    }

    public String getBlueImScore() {
        return blueImScore;
    }

    public void setBlueImScore(String blueImScore) {
        this.blueImScore = blueImScore;
    }

    public String getYellowAdScore() {
        return yellowAdScore;
    }

    public void setYellowAdScore(String yellowAdScore) {
        this.yellowAdScore = yellowAdScore;
    }

    public String getYellowImScore() {
        return yellowImScore;
    }

    public void setYellowImScore(String yellowImScore) {
        this.yellowImScore = yellowImScore;
    }

    public String getGreenAdScore() {
        return greenAdScore;
    }

    public void setGreenAdScore(String greenAdScore) {
        this.greenAdScore = greenAdScore;
    }

    public String getGreenImScore() {
        return greenImScore;
    }

    public void setGreenImScore(String greenImScore) {
        this.greenImScore = greenImScore;
    }

    public String getPracticeAdvice() {
        return practiceAdvice;
    }

    public void setPracticeAdvice(String practiceAdvice) {
        this.practiceAdvice = practiceAdvice;
    }
}
