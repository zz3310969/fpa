package com.roof.advisory.advisoryproduct.entity;

import java.util.List;

/**
 * @author 模版生成 <br/>
 * 表名： z_advisory_product <br/>
 * 描述：服务产品 <br/>
 */
public class AdvisoryProductVo extends AdvisoryProduct {

    private List<AdvisoryProductVo> advisoryProductList;
    /**
     * 服务模式
     */
    private String modelName;
    /**
     * 咨询师名称
     */
    private String consultantName;
    /**
     * app名称
     */
    private String appName;
    /**
     * 咨询定价名称
     */
    private String advisName;


    public AdvisoryProductVo() {
        super();
    }

    public AdvisoryProductVo(Long id) {
        super();
        this.id = id;
    }

    public List<AdvisoryProductVo> getAdvisoryProductList() {
        return advisoryProductList;
    }

    public void setAdvisoryProductList(List<AdvisoryProductVo> advisoryProductList) {
        this.advisoryProductList = advisoryProductList;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getConsultantName() {
        return consultantName;
    }

    public void setConsultantName(String consultantName) {
        this.consultantName = consultantName;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAdvisName() {
        return advisName;
    }

    public void setAdvisName(String advisName) {
        this.advisName = advisName;
    }
}
