package com.roof.fpa.wechat.cos.controller;

import com.roof.advisory.cos.service.api.ICosService;
import org.roof.spring.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/fpa/wechat")
public class CosWechatController {
    @Autowired
    private ICosService cosService;

    private Logger logger = LoggerFactory.getLogger(CosWechatController.class);


    @RequestMapping(value = "cos/sign", method = {RequestMethod.GET})
    public @ResponseBody
    Result getSign(HttpServletResponse response, HttpServletRequest request) {
        return new Result(Result.SUCCESS, cosService.getSign());

    }

    @RequestMapping(value = "cos/config", method = {RequestMethod.GET})
    public @ResponseBody Result getConfiguration(HttpServletResponse response, HttpServletRequest request) {
        return new Result(Result.SUCCESS, cosService.getConfiguration());
    }

}
