package com.roof.advisory.consultant.service.impl;

import com.google.common.collect.Maps;
import com.roof.advisory.area.entity.Area;
import com.roof.advisory.area.entity.AreaVo;
import com.roof.advisory.area.service.api.IAreaService;
import com.roof.advisory.consultant.dao.api.IConsultantDao;
import com.roof.advisory.consultant.entity.Consultant;
import com.roof.advisory.consultant.entity.ConsultantVo;
import com.roof.advisory.consultant.entity.ConsultantWechatVo;
import com.roof.advisory.consultant.service.api.IConsultantService;
import com.roof.advisory.im.service.api.IImService;
import com.roof.advisory.im.service.impl.ImService;
import com.roof.advisory.wxsession.service.api.IWxSessionService;
import com.roof.fpa.DefaultStateEnum;
import org.apache.commons.codec.digest.DigestUtils;
import org.roof.account.api.AccountType;
import org.roof.account.api.IAccountOperateService;
import org.roof.commons.PropertiesUtil;
import org.roof.roof.dataaccess.api.Page;
import org.roof.web.role.entity.BaseRole;
import org.roof.web.role.entity.Role;
import org.roof.web.user.entity.User;
import org.roof.web.user.service.api.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ConsultantService implements IConsultantService {

    private static final Logger logger = LoggerFactory.getLogger(ConsultantService.class);
    private IConsultantDao consultantDao;

    private String counselorRoles = PropertiesUtil.getPorpertyString("counselorRoles");

    @Autowired
    private IImService imService;
    @Autowired
    private IUserService userService;

    @Autowired
    private IAreaService areaService;

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private IAccountOperateService accountOperateService;


    public Serializable save(Consultant consultant) {
        User user = counselorConsultant(consultant);
        userService.save(user);
        consultant.setUserId(user.getId());
        consultant.setState(DefaultStateEnum.usable.getCode());

        Long id = (Long) consultantDao.save(consultant);
        accountOperateService.create(id, AccountType.local, Maps.newHashMap());
        return id;
    }

    private User counselorConsultant(Consultant consultant) {
        User user = new User();
        String[] rolesIds = counselorRoles.split(",");
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

    public void delete(Consultant consultant) {
        consultantDao.delete(consultant);
    }

    public void deleteByExample(Consultant consultant) {
        consultantDao.deleteByExample(consultant);
    }

    public void update(Consultant consultant) {
        consultantDao.update(consultant);
    }

    public void updateIgnoreNull(Consultant consultant) {
        consultantDao.updateIgnoreNull(consultant);
    }

    public void updateByExample(Consultant consultant) {
        consultantDao.update("updateByExampleConsultant", consultant);
    }

    public ConsultantVo load(Consultant consultant) {
        return (ConsultantVo) consultantDao.reload(consultant);
    }

    public ConsultantVo selectForObject(Consultant consultant) {
        return (ConsultantVo) consultantDao.selectForObject("selectConsultant", consultant);
    }

    @Override
    public List<ConsultantVo> selectForList(Consultant consultant) {
        return (List<ConsultantVo>) consultantDao.selectForList("selectConsultant", consultant);
    }

    @Override
    public Page page(Page page, Consultant consultant) {
        return consultantDao.page(page, consultant);
    }

    @Override
    public Page pageWechat(Page page, ConsultantWechatVo consultantWechatVo) {
        //发送异步同步在线用户请求
        imService.getAllUsers();
        //获取在线咨询师
        BoundValueOperations<String, List<String>> operations = redisTemplate.boundValueOps(ImService.ONLINE_USERS);
        List<String> list = operations.get();
        //微信咨询师列表返回
        Page pageWechat = consultantDao.pageWechat(page, consultantWechatVo);
        List<ConsultantWechatVo> consultantWechatVos = (List<ConsultantWechatVo>) pageWechat.getDataList();
        for (ConsultantWechatVo consultant : consultantWechatVos) {
            Area area = new Area();
            area.setCity(consultant.getCity());
            area.setProvince(consultant.getProvince());
            AreaVo vo = areaService.loadByCache(area);
            consultant.setAreaName(vo.getProvinceCn() + "-" + vo.getCityCn());
            //更新在线状态
            for (String s : list
                    ) {
                if (consultant.getUsername().equals(s)) {
                    consultant.setIsOnline(1);
                }
            }
        }
        pageWechat.setDataList(consultantWechatVos);
        return pageWechat;
    }

    @Override
    public ConsultantWechatVo loadForWechat(ConsultantWechatVo consultant) {
        //发送异步同步在线用户请求
        imService.getAllUsers();
        //获取在线咨询师
        BoundValueOperations<String, List<String>> operations = redisTemplate.boundValueOps(ImService.ONLINE_USERS);
        List<String> list = operations.get();

        ConsultantWechatVo consultantWechatVo = (ConsultantWechatVo) consultantDao.reload(consultant);
        Area area = new Area();
        area.setCity(consultantWechatVo.getCity());
        area.setProvince(consultantWechatVo.getProvince());
        AreaVo vo = areaService.loadByCache(area);
        consultantWechatVo.setAreaName(vo.getProvinceCn() + "-" + vo.getCityCn());
        //更新在线状态
        for (String s : list
                ) {
            if (consultantWechatVo.getUsername().equals(s)) {
                consultantWechatVo.setIsOnline(1);
            }
        }
        return consultantWechatVo;
    }


    @Override
    public List<Long> selectForListByUsernames(String[] usernames) {
        List<Long> ids = (List<Long>) this.consultantDao.selectForList("selectForListByUsernames", usernames);
        return ids;
    }

    @Override
    public List<String> selectConsultantUsernames() {
        return (List<String>) this.consultantDao.selectForList("selectConsultantUsernames");
    }


    @Autowired
    public void setIConsultantDao(
            @Qualifier("consultantDao") IConsultantDao consultantDao
    ) {
        this.consultantDao = consultantDao;
    }


}
