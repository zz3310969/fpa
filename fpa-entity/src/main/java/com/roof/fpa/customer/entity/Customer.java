package com.roof.fpa.customer.entity;

import javax.persistence.Id;
import java.util.Date;
import java.io.Serializable;

import org.springframework.format.annotation.DateTimeFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author 模版生成 <br/>
 * 表名： c_customer <br/>
 * 描述：客户 <br/>
 */
@ApiModel(value = "c_customer", description = "客户")
public class Customer implements Serializable {
    // 需要手动添加非默认的serialVersionUID
    @ApiModelProperty(value = "客户id")
    protected Long id;// 客户id
    @ApiModelProperty(value = "微信openid")
    protected String weixinOpenId;// 微信openid
    @ApiModelProperty(value = "微信头像")
    protected String weixinHeadImage;// 微信头像
    @ApiModelProperty(value = "昵称")
    protected String nickName;// 昵称
    @ApiModelProperty(value = "姓名")
    protected String name;// 姓名
    @ApiModelProperty(value = "联系电话")
    protected String mobile;// 联系电话
    @ApiModelProperty(value = "性别")
    protected Integer gender;// 性别
    @ApiModelProperty(value = "地区")
    protected String area;// 地区
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "关注时间")
    protected Date followTime;// 关注时间
    @ApiModelProperty(value = "国家")
    protected String country;// 国家
    @ApiModelProperty(value = "省")
    protected String province;// 省
    @ApiModelProperty(value = "市")
    protected String city;// 市
    @ApiModelProperty(value = "语言")
    protected String language;// 语言
    @ApiModelProperty(value = "逻辑删除")
    protected Integer useable;// 逻辑删除
    @ApiModelProperty(value = "二进制用户类型")
    protected Long binaryType;// 二进制用户类型

    protected String unionid;

    protected Long userId;

    protected Long consId;

    public Customer() {
        super();
    }

    public Customer(Long id) {
        super();
        this.id = id;
    }

    @Id// 主键
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWeixinOpenId() {
        return weixinOpenId;
    }

    public void setWeixinOpenId(String weixinOpenId) {
        this.weixinOpenId = weixinOpenId;
    }

    public String getWeixinHeadImage() {
        return weixinHeadImage;
    }

    public void setWeixinHeadImage(String weixinHeadImage) {
        this.weixinHeadImage = weixinHeadImage;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Date getFollowTime() {
        return followTime;
    }

    public void setFollowTime(Date followTime) {
        this.followTime = followTime;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setUseable(Integer useable) {
        this.useable = useable;
    }

    public Integer getUseable() {
        return useable;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getBinaryType() {
        return binaryType;
    }

    public void setBinaryType(Long binaryType) {
        this.binaryType = binaryType;
    }

    public Long getConsId() {
        return consId;
    }

    public void setConsId(Long consId) {
        this.consId = consId;
    }
}
