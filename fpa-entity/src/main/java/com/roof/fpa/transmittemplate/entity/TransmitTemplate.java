package com.roof.fpa.transmittemplate.entity;

import javax.persistence.Id;
import java.util.Date;
import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author 模版生成 <br/>
 *         表名： c_transmit_template <br/>
 *         描述：分享模板 <br/>
 */
@ApiModel(value = "c_transmit_template", description = "分享模板")
public class TransmitTemplate implements Serializable {
	// 需要手动添加非默认的serialVersionUID
	@ApiModelProperty(value = "id")
	protected Long id;// id
	@ApiModelProperty(value = "模板编号")
	protected String code;// 模板编号
	@ApiModelProperty(value = "模板名称")
	protected String name;// 模板名称
	@ApiModelProperty(value = "转发标题")
	protected String title;// 转发标题
	@ApiModelProperty(value = "转发路径")
	protected String path;// 转发路径
	@ApiModelProperty(value = "自定义图片路径")
	protected String imageurl;// 自定义图片路径
	@ApiModelProperty(value = "状态")
	protected Integer state;// 状态
	@ApiModelProperty(value = "逻辑删除")
	protected Integer useable;// 逻辑删除

	public TransmitTemplate() {
		super();
	}

	public TransmitTemplate(Long id) {
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
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	public String getImageurl() {
		return imageurl;
	}
	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}
	
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getUseable() {
		return useable;
	}

	public void setUseable(Integer useable) {
		this.useable = useable;
	}
}
