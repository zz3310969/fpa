package com.roof.advisory.area.entity;

import java.util.List;

/**
 * @author 模版生成 <br/>
 *         表名： z_area <br/>
 *         描述：z_area <br/>
 */
public class AreaVo extends Area {

	private List<AreaVo> areaList;
	//private AreaTreeSelect provinceTree;

	public AreaVo() {
		super();
	}

	public AreaVo(Long id) {
		super();
		this.id = id;
	}

	public List<AreaVo> getAreaList() {
		return areaList;
	}

	public void setAreaList(List<AreaVo> areaList) {
		this.areaList = areaList;
	}

	public AreaTreeSelect getProvinceTree() {
		AreaTreeSelect provinceTree = new AreaTreeSelect();
		provinceTree.setValue(super.province);
		provinceTree.setLabel(super.provinceCn);
		return provinceTree;
	}
	public AreaTreeSelect getCityTree() {
		AreaTreeSelect provinceTree = new AreaTreeSelect();
		provinceTree.setValue(super.city);
		provinceTree.setLabel(super.cityCn);
		return provinceTree;
	}
}
