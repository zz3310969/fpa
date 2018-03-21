package com.roof.fpa.main.controller;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.roof.advisory.consultant.entity.Consultant;
import com.roof.advisory.consultant.entity.ConsultantVo;
import com.roof.advisory.consultant.service.api.IConsultantService;
import org.apache.commons.lang3.StringUtils;
import org.roof.web.user.entity.User;
import org.roof.web.user.service.api.BaseUserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by zhenglt on 2017/11/25.
 */
@Controller
public class MainController {
    @Autowired
    private IConsultantService consultantService;

    @RequestMapping(value = "currentUser", method = {RequestMethod.GET})
    public @ResponseBody Map currentUser(HttpServletRequest httpServletRequest){
        Map<String,Object> map = Maps.newHashMap();
        User user = (User) BaseUserContext.getCurrentUser(httpServletRequest);
        Consultant consultant = new Consultant();
        consultant.setUserId(user.getId());
        ConsultantVo consultantVo = consultantService.selectForObject(consultant);

        map.put("avatar","https://gw.alipayobjects.com/zos/rmsportal/dRFVcIqZOYPcSNrlJsqQ.png");
        map.put("name",user.getName());
        map.put("userid",user.getId());
        map.put("notifyCount",0);
        map.put("user",user);
        if(consultantVo != null && StringUtils.isNoneEmpty(consultantVo.getHeadImageUrl())){
            map.put("avatar",consultantVo.getHeadImageUrl());
        }

        return  map;
    }

    @RequestMapping(value = "notices", method = {RequestMethod.GET})
    public @ResponseBody List<Map<String,Object>> notices(){
        List list = Lists.newArrayList();
        Map<String,Object> map = Maps.newHashMap();
        map.put("id","000000001");
        map.put("avatar","https://gw.alipayobjects.com/zos/rmsportal/ThXAXghbEsBCCSDihZxY.png");
        map.put("title","你收到了 14 份新周报");
        map.put("datetime","2017-08-09");
        map.put("type","通知");
        list.add(map);
        return  list;
    }


}
