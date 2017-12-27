package com.roof.fpa.counselor.service.impl;

import com.roof.fpa.account.service.api.IAccountService;
import com.roof.fpa.counselor.dao.api.ICounselorDao;
import com.roof.fpa.counselor.entity.Counselor;
import com.roof.fpa.counselor.entity.CounselorVo;
import com.roof.fpa.counselor.service.api.ICounselorService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.roof.commons.PropertiesUtil;
import org.roof.roof.dataaccess.api.Page;
import org.roof.web.role.entity.BaseRole;
import org.roof.web.role.entity.Role;
import org.roof.web.user.entity.User;
import org.roof.web.user.service.api.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CounselorService implements ICounselorService {
	private ICounselorDao counselorDao;
	@Autowired
	private IAccountService accountService;
	@Autowired
	private IUserService userService;

	private String counselorRoles = PropertiesUtil.getPorpertyString("counselorRoles");


	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Serializable save(Counselor counselor){
		User user = counselorConvertUser(counselor);
		userService.save(user);
		counselor.setUserId(user.getId());
		Long id = (Long) counselorDao.save(counselor);
		accountService.createAccount(id);
		return id;
	}

	private User counselorConvertUser(Counselor counselor){
		User user = new User();
		String [] rolesIds = counselorRoles.split(",");
		if (rolesIds != null) {
			Set<BaseRole> roles = new HashSet<BaseRole>();
			for (String roleId : rolesIds) {
				roles.add(new Role(Long.valueOf(roleId), null));
			}
			user.setRoles(roles);
		}
		user.setUsername(counselor.getMobile());
		user.setName(counselor.getName());
		user.setCreate_date(new Date());
		String tel = counselor.getMobile();
		user.setPassword(DigestUtils.md5Hex(tel).toUpperCase());
		return user;
	}

	public void delete(Counselor counselor){
		counselorDao.delete(counselor);
	}
	
	public void deleteByExample(Counselor counselor){
		counselorDao.deleteByExample(counselor);
	}

	public void update(Counselor counselor){
		counselorDao.update(counselor);
	}
	
	public void updateIgnoreNull(Counselor counselor){
		counselorDao.updateIgnoreNull(counselor);
	}
		
	public void updateByExample(Counselor counselor){
		counselorDao.update("updateByExampleCounselor", counselor);
	}

	public CounselorVo load(Counselor counselor){
		return (CounselorVo)counselorDao.reload(counselor);
	}
	
	public CounselorVo selectForObject(Counselor counselor){
		return (CounselorVo)counselorDao.selectForObject("selectCounselor",counselor);
	}
	
	public List<CounselorVo> selectForList(Counselor counselor){
		return (List<CounselorVo>)counselorDao.selectForList("selectCounselor",counselor);
	}
	
	public Page page(Page page, Counselor counselor) {
		return counselorDao.page(page, counselor);
	}

	@Autowired
	public void setICounselorDao(
			@Qualifier("counselorDao") ICounselorDao  counselorDao) {
		this.counselorDao = counselorDao;
	}

	public static void main(String [] s){


	}
	

}
