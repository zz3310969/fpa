package com.roof.fpa.theme.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import io.swagger.annotations.Api;
import org.roof.roof.dataaccess.api.Page;
import org.roof.roof.dataaccess.api.PageUtils;
import org.roof.spring.Result;
import com.roof.fpa.theme.entity.Theme;
import com.roof.fpa.theme.entity.ThemeVo;
import com.roof.fpa.theme.service.api.IThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Api(value = "theme", description = "主题管理")
@Controller
@RequestMapping("fpa")
public class ThemeController {
	private IThemeService themeService;




    @RequestMapping(value = "theme", method = {RequestMethod.GET})
    public @ResponseBody Result<Page> list(Theme theme, HttpServletRequest request, Model model) {
	    Page page = PageUtils.createPage(request);
	    page = themeService.page(page, theme);
	    return new Result(Result.SUCCESS, page);
	}
	


	@RequestMapping(value = "theme", method = {RequestMethod.POST})
	public @ResponseBody Result create(@RequestBody Theme theme) {
		if (theme != null) {
			themeService.save(theme);
			return new Result("保存成功!");
		} else {
			return new Result(Result.FAIL,"数据传输失败!");
		}
	}

    @RequestMapping(value = "theme/{id}", method = {RequestMethod.GET})
    public @ResponseBody Result<ThemeVo> load(@PathVariable Long id) {
		ThemeVo themeVo = themeService.load(new Theme(id));
        return new Result(Result.SUCCESS,themeVo);
    }
	
	@RequestMapping(value = "theme/{id}", method = {RequestMethod.PUT})
	public @ResponseBody Result update(@PathVariable Long id ,@RequestBody Theme theme) {
		if (id != null && theme != null) {
			theme.setId(id);
			themeService.updateIgnoreNull(theme);
			return new Result("保存成功!");
		} else {
			return new Result(Result.FAIL,"数据传输失败!");
		}
	}
	
	@RequestMapping(value = "theme/{id}", method = {RequestMethod.DELETE})
	public @ResponseBody Result delete(@PathVariable Long id ) {
		// TODO 有些关键数据是不能物理删除的，需要改为逻辑删除
		themeService.delete(new Theme(id));
		return new Result("删除成功!");
	}

	@Autowired(required = true)
	public void setThemeService(
			@Qualifier("themeService") IThemeService themeService) {
		this.themeService = themeService;
	}


}
