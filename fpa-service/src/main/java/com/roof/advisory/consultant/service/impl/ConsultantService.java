package com.roof.advisory.consultant.service.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.roof.advisory.consultant.entity.ConsultantWechatVo;
import com.roof.fpa.DefaultStateEnum;
import com.roof.fpa.account.service.api.IAccountService;
import com.roof.fpa.counselor.entity.Counselor;
import org.apache.commons.codec.digest.DigestUtils;
import org.roof.commons.PropertiesUtil;
import org.roof.roof.dataaccess.api.Page;
import com.roof.advisory.consultant.dao.api.IConsultantDao;
import com.roof.advisory.consultant.entity.Consultant;
import com.roof.advisory.consultant.entity.ConsultantVo;
import com.roof.advisory.consultant.service.api.IConsultantService;
import org.roof.web.role.entity.BaseRole;
import org.roof.web.role.entity.Role;
import org.roof.web.user.entity.User;
import org.roof.web.user.service.api.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ConsultantService implements IConsultantService {
	private IConsultantDao consultantDao;

	private String counselorRoles = PropertiesUtil.getPorpertyString("counselorRoles");


	@Autowired
	private IUserService userService;

	public Serializable save(Consultant consultant){
		User user = counselorConsultant(consultant);
		userService.save(user);
		consultant.setUserId(user.getId());
		consultant.setState(DefaultStateEnum.usable.getCode());
		return consultantDao.save(consultant);
	}
	private User counselorConsultant(Consultant consultant){
		User user = new User();
		String [] rolesIds = counselorRoles.split(",");
		if (rolesIds != null) {
			Set<BaseRole> roles = new HashSet<BaseRole>();
			for (String roleId : rolesIds) {
				roles.add(new Role(Long.valueOf(roleId), null));
			}
			user.setRoles(roles);
		}
		user.setUsername(consultant.getUsername());
		user.setName(consultant.getName());
		user.setCreate_date(new Date());
		String tel = consultant.getMobile();
		user.setPassword(DigestUtils.md5Hex(tel).toUpperCase());
		return user;
	}

	public void delete(Consultant consultant){
		consultantDao.delete(consultant);
	}
	
	public void deleteByExample(Consultant consultant){
		consultantDao.deleteByExample(consultant);
	}

	public void update(Consultant consultant){
		consultantDao.update(consultant);
	}
	
	public void updateIgnoreNull(Consultant consultant){
		consultantDao.updateIgnoreNull(consultant);
	}
		
	public void updateByExample(Consultant consultant){
		consultantDao.update("updateByExampleConsultant", consultant);
	}

	public ConsultantVo load(Consultant consultant){
		return (ConsultantVo)consultantDao.reload(consultant);
	}
	
	public ConsultantVo selectForObject(Consultant consultant){
		return (ConsultantVo)consultantDao.selectForObject("selectConsultant",consultant);
	}
	
	public List<ConsultantVo> selectForList(Consultant consultant){
		return (List<ConsultantVo>)consultantDao.selectForList("selectConsultant",consultant);
	}
	
	public Page page(Page page, Consultant consultant) {
		return consultantDao.page(page, consultant);
	}

	public Page pageWechat(Page page, ConsultantWechatVo consultantWechatVo) {
		return consultantDao.pageWechat(page, consultantWechatVo);
	}

	@Autowired
	public void setIConsultantDao(
			@Qualifier("consultantDao") IConsultantDao  consultantDao) {
		this.consultantDao = consultantDao;
	}
	

}
