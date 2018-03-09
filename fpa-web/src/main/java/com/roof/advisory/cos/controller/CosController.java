package com.roof.advisory.cos.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/fpa/cos")
public class CosController {

    private Logger logger = LoggerFactory.getLogger(CosController.class);


    @RequestMapping(value = "/sign", method = {RequestMethod.GET})
    public void getMiddleFile(HttpServletResponse response, HttpServletRequest request) {

    }

    @RequestMapping(value = "/config", method = {RequestMethod.GET})
    public void getConfiguration(HttpServletResponse response, HttpServletRequest request) {

    }




}
