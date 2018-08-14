package com.roof.advisory.wechat.theme.controller;


import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.roof.advisory.advisorytheme.entity.AdvisoryThemeVo;
import com.roof.advisory.advisorytheme.service.api.IAdvisoryThemeService;
import com.roof.advisory.commentrecord.entity.CommentRecordVo;
import com.roof.fpa.theme.entity.Theme;
import com.roof.fpa.theme.entity.ThemeVo;
import com.roof.fpa.theme.service.api.IThemeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.roof.roof.dataaccess.api.Page;
import org.roof.roof.dataaccess.api.PageUtils;
import org.roof.spring.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Api(value = "theme", description = "地区管理")
@Controller
@RequestMapping("/fpa/wechat/advisory")
public class ThemeController {


    //@Autowired
    //private IThemeService themeService;
    @Autowired
    private IAdvisoryThemeService advisoryThemeService;

    @RequestMapping(value = "theme", method = {RequestMethod.GET})
    public @ResponseBody
    Result<Page> list(AdvisoryThemeVo theme, HttpServletRequest request, Model model) {
        Page page = PageUtils.createPage(request);
        page = advisoryThemeService.page(page, theme);
        return new Result(Result.SUCCESS, page);
    }
}
