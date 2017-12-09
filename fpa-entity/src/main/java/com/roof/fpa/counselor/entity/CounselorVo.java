package com.roof.fpa.counselor.entity;

import com.roof.fpa.GenderEnum;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * @author 模版生成 <br/>
 *         表名： c_counselor <br/>
 *         描述：咨询师 <br/>
 */
public class CounselorVo extends Counselor {

	private List<CounselorVo> counselorList;

	private String rankName;
	private GenderEnum genderEnum;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date regTimeStart;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date regTimeEnd;

	public CounselorVo() {
		super();
	}

	public CounselorVo(Long id) {
		super();
		this.id = id;
	}

	public List<CounselorVo> getCounselorList() {
		return counselorList;
	}

	public void setCounselorList(List<CounselorVo> counselorList) {
		this.counselorList = counselorList;
	}

	public Date getRegTimeStart() {
		return regTimeStart;
	}

	public void setRegTimeStart(Date regTimeStart) {
		this.regTimeStart = regTimeStart;
	}

	public Date getRegTimeEnd() {
		return regTimeEnd;
	}

	public void setRegTimeEnd(Date regTimeEnd) {
		this.regTimeEnd = regTimeEnd;
	}

	public String getRankName() {
		return rankName;
	}

	public void setRankName(String rankName) {
		this.rankName = rankName;
	}

	public GenderEnum getGenderEnum() {
		return GenderEnum.getEnumByCode(super.gender);
	}

	public void setGenderEnum(GenderEnum genderEnum) {
		this.genderEnum = genderEnum;
	}

}
