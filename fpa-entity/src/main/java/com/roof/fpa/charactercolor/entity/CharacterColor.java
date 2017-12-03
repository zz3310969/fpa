package com.roof.fpa.charactercolor.entity;

import javax.persistence.Id;
import java.util.Date;
import java.io.Serializable;

import org.springframework.format.annotation.DateTimeFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author 模版生成 <br/>
 *         表名： c_character_color <br/>
 *         描述：性格色彩 <br/>
 */
@ApiModel(value = "c_character_color", description = "性格色彩")
public class CharacterColor implements Serializable {
    // 需要手动添加非默认的serialVersionUID
    @ApiModelProperty(value = "主键")
    protected Long id;// 主键
    @ApiModelProperty(value = "编号")
    protected String numb;// 编号
    @ApiModelProperty(value = "名称")
    protected String name;// 名称
    @ApiModelProperty(value = "代表颜色")
    protected Long colorId;// 代表颜色

    @ApiModelProperty(value = "代表颜色编码")
    protected String colorCode;// 代表颜色编码
    @ApiModelProperty(value = "描述")
    protected String description;// 描述
    @ApiModelProperty(value = "性格色彩2")
    protected Long color2Id;// 性格色彩2
    @ApiModelProperty(value = "性格色彩2编码")
    protected String color2Code;// 性格色彩2编码
    @ApiModelProperty(value = "状态")
    protected Integer state;// 状态
    @ApiModelProperty(value = "是否逻辑删除")
    protected Integer useable;// 状态


    @ApiModelProperty(value = "在你的性格中比较缺少的")
    protected String description_lack;

    public CharacterColor() {
        super();
    }

    public CharacterColor(Long id) {
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

    public Long getColorId() {
        return colorId;
    }

    public void setColorId(Long colorId) {
        this.colorId = colorId;
    }

    public String getColorCode() {
        return colorCode;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getColor2Id() {
        return color2Id;
    }

    public void setColor2Id(Long color2Id) {
        this.color2Id = color2Id;
    }

    public String getColor2Code() {
        return color2Code;
    }

    public void setColor2Code(String color2Code) {
        this.color2Code = color2Code;
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

    public String getDescription_lack() {
        return description_lack;
    }

    public void setDescription_lack(String description_lack) {
        this.description_lack = description_lack;
    }
}
