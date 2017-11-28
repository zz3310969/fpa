package com.roof.fpa.cardgroup.entity;

import javax.persistence.Id;
import java.util.Date;
import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author 模版生成 <br/>
 *         表名： c_card_group <br/>
 *         描述：套牌 <br/>
 */
@ApiModel(value = "c_card_group", description = "套牌")
public class CardGroup implements Serializable {
	// 需要手动添加非默认的serialVersionUID
	@ApiModelProperty(value = "主键")
	protected Long id;// 主键
	@ApiModelProperty(value = "名称")
	protected String name;// 名称
	@ApiModelProperty(value = "张数")
	protected Integer amount;// 张数
	@ApiModelProperty(value = "是否可用")
	protected Integer usable;// 是否可用

	public CardGroup() {
		super();
	}

	public CardGroup(Long id) {
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
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	
	public Integer getUsable() {
		return usable;
	}
	public void setUsable(Integer usable) {
		this.usable = usable;
	}
}
