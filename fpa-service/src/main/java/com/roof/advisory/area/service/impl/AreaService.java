package com.roof.advisory.area.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.roof.advisory.area.entity.AreaTreeSelect;
import org.roof.roof.dataaccess.api.Page;
import com.roof.advisory.area.dao.api.IAreaDao;
import com.roof.advisory.area.entity.Area;
import com.roof.advisory.area.entity.AreaVo;
import com.roof.advisory.area.service.api.IAreaService;
import org.roof.web.core.TreeSelectDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import static java.util.stream.Collectors.groupingBy;

@Service
public class AreaService implements IAreaService {
	private IAreaDao areaDao;

	public Serializable save(Area area){
		return areaDao.save(area);
	}

	public void delete(Area area){
		areaDao.delete(area);
	}
	
	public void deleteByExample(Area area){
		areaDao.deleteByExample(area);
	}

	public void update(Area area){
		areaDao.update(area);
	}
	
	public void updateIgnoreNull(Area area){
		areaDao.updateIgnoreNull(area);
	}
		
	public void updateByExample(Area area){
		areaDao.update("updateByExampleArea", area);
	}

	public AreaVo load(Area area){
		return (AreaVo)areaDao.reload(area);
	}
	
	public AreaVo selectForObject(Area area){
		return (AreaVo)areaDao.selectForObject("selectArea",area);
	}
	
	public List<AreaVo> selectForList(Area area){
		return (List<AreaVo>)areaDao.selectForList("selectArea",area);
	}
	
	public Page page(Page page, Area area) {
		return areaDao.page(page, area);
	}

	@Override
	public List<AreaTreeSelect> tree(Area area) {
		//TreeSelectDate date = new TreeSelectDate();
		/*selectForList(area).stream().collect(() -> new ArrayList<TreeSelectDate>(),
		(list, item) -> { TreeSelectDate date = new TreeSelectDate();date.setKey(item.getCityCn());date.setLabel(item.getCity()); },
		(list1, list2) -> list1.addAll(list2));*/ //collect(groupingBy(Area::getCityCn));
		/*Object strings = selectForList(area).stream().collect(groupingBy(AreaVo::getProvinceTree));
		System.out.println(strings);*/

		List<AreaVo> areaVos = selectForList(area);
		Map<String,AreaTreeSelect> map = Maps.newHashMap();
		for (AreaVo areaVo : areaVos){
			if(map.containsKey(areaVo.getProvince())){
				AreaTreeSelect provinceTree =  map.get(areaVo.getProvince());
				provinceTree.addChildren(areaVo.getCityTree());
				map.put(areaVo.getProvince(),provinceTree);
			}else {
				AreaTreeSelect provinceTree = areaVo.getProvinceTree();
				provinceTree.addChildren(areaVo.getCityTree());
				map.put(areaVo.getProvince(),provinceTree);
			}

		}

		List<AreaTreeSelect> list = map.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toList());

		//System.out.println(list);

		return list;


	}

	@Autowired
	public void setIAreaDao(
			@Qualifier("areaDao") IAreaDao  areaDao) {
		this.areaDao = areaDao;
	}
	

}
