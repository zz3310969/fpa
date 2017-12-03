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

}
