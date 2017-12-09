package com.roof.fpa.counselorrank.entity;

import java.util.List;

/**
 * @author 模版生成 <br/>
 *         表名： c_counselor_rank <br/>
 *         描述：咨询师等级 <br/>
 */
public class CounselorRankVo extends CounselorRank {

	private List<CounselorRankVo> counselorRankList;

	public CounselorRankVo() {
		super();
	}

	public CounselorRankVo(Long id) {
		super();
		this.id = id;
	}

	public List<CounselorRankVo> getCounselorRankList() {
		return counselorRankList;
	}

	public void setCounselorRankList(List<CounselorRankVo> counselorRankList) {
		this.counselorRankList = counselorRankList;
	}

}
