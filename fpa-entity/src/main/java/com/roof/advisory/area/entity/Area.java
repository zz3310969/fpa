package com.roof.advisory.area.entity;

import javax.persistence.Id;
import java.util.Date;
import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author 模版生成 <br/>
 *         表名： z_area <br/>
 *         描述：z_area <br/>
 */
@ApiModel(value = "z_area", description = "z_area")
public class Area implements Serializable {
	// 需要手动添加非默认的serialVersionUID
	@ApiModelProperty(value = "id")
	protected Long id;// id
	@ApiModelProperty(value = "国家（中文）")
	protected String nationCn;// 国家（中文）
	@ApiModelProperty(value = "省（中文）")
	protected String provinceCn;// 省（中文）
	@ApiModelProperty(value = "市（中文）")
	protected String cityCn;// 市（中文）
	@ApiModelProperty(value = "国家")
	protected String nation;// 国家
	@ApiModelProperty(value = "省")
	protected String province;// 省
	@ApiModelProperty(value = "市")
	protected String city;// 市
	@ApiModelProperty(value = "民族")
	protected String nationality;// 民族
	@ApiModelProperty(value = "自治县")
	protected String autonomous;// 自治县
	@ApiModelProperty(value = "县类别")
	protected String country;// 县类别
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "创建时间")
	protected Date createDate;// 创建时间
	@ApiModelProperty(value = "状态")
	protected String state;// 状态

	public Area() {
		super();
	}

	public Area(Long id) {
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
	
	public String getNationCn() {
		return nationCn;
	}
	public void setNationCn(String nationCn) {
		this.nationCn = nationCn;
	}
	
	public String getProvinceCn() {
		return provinceCn;
	}
	public void setProvinceCn(String provinceCn) {
		this.provinceCn = provinceCn;
	}
	
	public String getCityCn() {
		return cityCn;
	}
	public void setCityCn(String cityCn) {
		this.cityCn = cityCn;
	}
	
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
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
	
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	
	public String getAutonomous() {
		return autonomous;
	}
	public void setAutonomous(String autonomous) {
		this.autonomous = autonomous;
	}
	
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}


}
