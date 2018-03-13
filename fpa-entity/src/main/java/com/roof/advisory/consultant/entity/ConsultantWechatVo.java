package com.roof.advisory.consultant.entity;

import com.roof.advisory.advisorytheme.entity.AdvisoryTheme;
import com.roof.fpa.GenderEnum;

import java.util.List;


/**
 * @author liht
 */
public class ConsultantWechatVo extends Consultant {

    private List<ConsultantWechatVo> consultantList;
    /**
     * 咨询师等级
     */
    private String levelName;
    /**
     * 是否在线 1在线，0离线
     */
    private Integer isOnline;
    /**
     * 是否可预约
     */
    private Integer canOrder;
    /**
     * 服务主题
     */
    private List<AdvisoryTheme> themes;

    private String themeName;
    /**
     * 评分 星级
     */
    private Double grade;
    /**
     * 咨询次数
     */
    private Long times;
    /**
     * 地区
     */
    private String areaName;


    public ConsultantWechatVo() {
        super();
    }

    public ConsultantWechatVo(Long id) {
        super();
        this.id = id;
    }

    public List<ConsultantWechatVo> getConsultantList() {
        return consultantList;
    }

    public void setConsultantList(List<ConsultantWechatVo> consultantList) {
        this.consultantList = consultantList;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public Integer getIsOnline() {
        return isOnline;
    }

    public void setIsOnline(Integer isOnline) {
        this.isOnline = isOnline;
    }

    public Integer getCanOrder() {
        return canOrder;
    }

    public void setCanOrder(Integer canOrder) {
        this.canOrder = canOrder;
    }

    public List<AdvisoryTheme> getThemes() {
        return themes;
    }

    public void setThemes(List<AdvisoryTheme> themes) {
        this.themes = themes;
    }

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }

    public Long getTimes() {
        return times;
    }

    public void setTimes(Long times) {
        this.times = times;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getThemeName() {
        return themeName;
    }

    public void setThemeName(String themeName) {
        this.themeName = themeName;
    }
}
