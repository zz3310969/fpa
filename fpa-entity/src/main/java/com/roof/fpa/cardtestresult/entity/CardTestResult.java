package com.roof.fpa.cardtestresult.entity;

import javax.persistence.Id;
import java.util.Date;
import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author 模版生成 <br/>
 *         表名： c_card_test_result <br/>
 *         描述：卡牌测试结果 <br/>
 */
@ApiModel(value = "c_card_test_result", description = "卡牌测试结果")
public class CardTestResult implements Serializable {
	// 需要手动添加非默认的serialVersionUID
	@ApiModelProperty(value = "主键")
	protected Long id;// 主键
	@ApiModelProperty(value = "测试场景id")
	protected Long sceneId;// 测试场景id
	@ApiModelProperty(value = "分享人ID")
	protected Long shareCustomerId;// 分享人ID
	@ApiModelProperty(value = "测试人ID")
	protected Long customerId;// 测试人ID
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "测试时间")
	protected Date testDate;// 测试时间
	@ApiModelProperty(value = "测试结果")
	protected String result;// 测试结果
	@ApiModelProperty(value = "结果模板ID")
	protected Long templateId;// 结果模板ID
	@ApiModelProperty(value = "性格")
	protected String characterColor;// 性格
	@ApiModelProperty(value = "红色得分")
	protected String redScore;// 红色得分
	@ApiModelProperty(value = "蓝色得分")
	protected String blueScore;// 蓝色得分
	@ApiModelProperty(value = "黄色得分")
	protected String yellowScore;// 黄色得分
	@ApiModelProperty(value = "绿色得分")
	protected String greenScore;// 绿色得分
	@ApiModelProperty(value = "执行人")
	protected Long operatorId;//执行人

	public CardTestResult() {
		super();
	}

	public CardTestResult(Long id) {
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
	
	public Long getSceneId() {
		return sceneId;
	}
	public void setSceneId(Long sceneId) {
		this.sceneId = sceneId;
	}
	
	public Long getShareCustomerId() {
		return shareCustomerId;
	}
	public void setShareCustomerId(Long shareCustomerId) {
		this.shareCustomerId = shareCustomerId;
	}
	
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	public Date getTestDate() {
		return testDate;
	}
	public void setTestDate(Date testDate) {
		this.testDate = testDate;
	}
	
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	
	public Long getTemplateId() {
		return templateId;
	}
	public void setTemplateId(Long templateId) {
		this.templateId = templateId;
	}
	
	public String getCharacterColor() {
		return characterColor;
	}
	public void setCharacterColor(String characterColor) {
		this.characterColor = characterColor;
	}
	
	public String getRedScore() {
		return redScore;
	}
	public void setRedScore(String redScore) {
		this.redScore = redScore;
	}
	
	public String getBlueScore() {
		return blueScore;
	}
	public void setBlueScore(String blueScore) {
		this.blueScore = blueScore;
	}
	
	public String getYellowScore() {
		return yellowScore;
	}
	public void setYellowScore(String yellowScore) {
		this.yellowScore = yellowScore;
	}
	
	public String getGreenScore() {
		return greenScore;
	}
	public void setGreenScore(String greenScore) {
		this.greenScore = greenScore;
	}

	public Long getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(Long operatorId) {
		this.operatorId = operatorId;
	}
}
