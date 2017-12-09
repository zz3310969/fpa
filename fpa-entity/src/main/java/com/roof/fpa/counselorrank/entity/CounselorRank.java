package com.roof.fpa.counselorrank.entity;

import javax.persistence.Id;
import java.util.Date;
import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author 模版生成 <br/>
 *         表名： c_counselor_rank <br/>
 *         描述：咨询师等级 <br/>
 */
@ApiModel(value = "c_counselor_rank", description = "咨询师等级")
public class CounselorRank implements Serializable {
	// 需要手动添加非默认的serialVersionUID
	@ApiModelProperty(value = "主键")
	protected Long id;// 主键
	@ApiModelProperty(value = "等级编号")
	protected String numb;// 等级编号
	@ApiModelProperty(value = "等级名称")
	protected String name;// 等级名称
	@ApiModelProperty(value = "备注")
	protected String remark;// 备注
	@ApiModelProperty(value = "状态")
	protected Integer state;// 状态

	public CounselorRank() {
		super();
	}

	public CounselorRank(Long id) {
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
	
	public String getNumb() {
		return numb;
	}
	public void setNumb(String numb) {
		this.numb = numb;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
}
