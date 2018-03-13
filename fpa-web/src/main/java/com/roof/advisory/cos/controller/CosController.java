package com.roof.advisory.cos.controller;

import com.roof.advisory.cos.service.api.ICosService;
import org.roof.spring.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
@RequestMapping("/advisory")
public class CosController {


    @Autowired
    private ICosService cosService;

    private static final Logger logger = LoggerFactory.getLogger(CosController.class);


    @RequestMapping(value = "cos/sign", method = {RequestMethod.GET})
    public @ResponseBody
    Result getSign(@RequestParam Map<String,String> map, HttpServletResponse response, HttpServletRequest request) {
        return new Result(Result.SUCCESS, cosService.getSign(map));

    }

    @RequestMapping(value = "cos/config", method = {RequestMethod.GET})
    public @ResponseBody Result getConfiguration(HttpServletResponse response, HttpServletRequest request) {
        return new Result(Result.SUCCESS, cosService.getConfiguration());
    }




}
