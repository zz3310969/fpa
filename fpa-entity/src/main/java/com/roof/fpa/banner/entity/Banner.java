package com.roof.fpa.banner.entity;

import javax.persistence.Id;
import java.util.Date;
import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author 模版生成 <br/>
 *         表名： c_banner <br/>
 *         描述：首页banner <br/>
 */
@ApiModel(value = "c_banner", description = "首页banner")
public class Banner implements Serializable {
	// 需要手动添加非默认的serialVersionUID
	@ApiModelProperty(value = "id")
	protected Long id;// id
	@ApiModelProperty(value = "跳转路径")
	protected String path;// 跳转路径
	@ApiModelProperty(value = "图片地址")
	protected String imageUrl;// 图片地址
	@ApiModelProperty(value = "类型")
	protected String type;// 类型

	public Banner() {
		super();
	}

	public Banner(Long id) {
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
	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
