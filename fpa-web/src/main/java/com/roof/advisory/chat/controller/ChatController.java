package com.roof.advisory.chat.controller;

import com.roof.advisory.area.entity.Area;
import com.roof.advisory.area.entity.AreaTreeSelect;
import com.roof.advisory.im.service.ImRequest;
import com.roof.advisory.im.service.api.IImService;
import io.swagger.annotations.ApiOperation;
import org.roof.spring.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ChatController {

    @Autowired
    private IImService imService;



    @ApiOperation(value = "")
    @RequestMapping(value = "chat/close", method = {RequestMethod.POST})
    public @ResponseBody
    Result close( HttpServletRequest request) {

        //订单关闭



        imService.closeSession(new ImRequest());

        return new Result(Result.SUCCESS);
    }}
