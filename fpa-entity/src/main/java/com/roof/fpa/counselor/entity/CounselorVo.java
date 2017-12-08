package com.roof.fpa.counselor.entity;

import java.util.List;

/**
 * @author 模版生成 <br/>
 *         表名： c_counselor <br/>
 *         描述：咨询师 <br/>
 */
public class CounselorVo extends Counselor {

	private List<CounselorVo> counselorList;

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

}
