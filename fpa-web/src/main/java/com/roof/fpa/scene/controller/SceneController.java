package com.roof.fpa.scene.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.google.common.collect.Maps;
import com.roof.fpa.DefaultStateEnum;
import com.roof.fpa.cardgroup.entity.CardGroup;
import com.roof.fpa.cardgroup.entity.CardGroupVo;
import com.roof.fpa.cardgroup.service.api.ICardGroupService;
import com.roof.fpa.scene.entity.SceneStateEnum;
import com.roof.fpa.theme.entity.Theme;
import com.roof.fpa.theme.entity.ThemeVo;
import com.roof.fpa.theme.service.api.IThemeService;
import io.swagger.annotations.Api;
import org.roof.roof.dataaccess.api.Page;
import org.roof.roof.dataaccess.api.PageUtils;
import org.roof.spring.Result;
import com.roof.fpa.scene.entity.Scene;
import com.roof.fpa.scene.entity.SceneVo;
import com.roof.fpa.scene.service.api.ISceneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Api(value = "scene", description = "场景管理")
@Controller
@RequestMapping("fpa")
public class SceneController {
	private ISceneService sceneService;

	@Autowired
	private ICardGroupService cardGroupService;

	@Autowired
	private IThemeService themeService;


	@RequestMapping(value = "scene/base", method = {RequestMethod.GET})
	public @ResponseBody Result<Map<String,Object>> base(HttpServletRequest request) {
		Map<String,Object> map = Maps.newHashMap();
		CardGroup cardGroup =  new CardGroup();
		cardGroup.setUsable(DefaultStateEnum.usable.getCode());
		List<CardGroupVo> cardGroupVos = cardGroupService.selectForList(cardGroup);
		map.put("cardGroups",cardGroupVos);
		Theme theme = new Theme();
		theme.setState(DefaultStateEnum.usable.getCode());
		List<ThemeVo> themeVos = themeService.selectForList(theme);
		map.put("themes",themeVos);
		SceneStateEnum[] states = SceneStateEnum.values();
		map.put("states",states);
		return new Result(Result.SUCCESS, map);
	}


    @RequestMapping(value = "scene", method = {RequestMethod.GET})
    public @ResponseBody Result<Page> list(Scene scene, HttpServletRequest request, Model model) {
	    Page page = PageUtils.createPage(request);
	    page = sceneService.page(page, scene);
	    return new Result(Result.SUCCESS, page);
	}
	


	@RequestMapping(value = "scene", method = {RequestMethod.POST})
	public @ResponseBody Result create(@RequestBody Scene scene) {
		if (scene != null) {
			sceneService.save(scene);
			return new Result("保存成功!");
		} else {
			return new Result(Result.FAIL,"数据传输失败!");
		}
	}

    @RequestMapping(value = "scene/{id}", method = {RequestMethod.GET})
    public @ResponseBody Result<SceneVo> load(@PathVariable Long id) {
		SceneVo sceneVo = sceneService.load(new Scene(id));
        return new Result(Result.SUCCESS,sceneVo);
    }
	
	@RequestMapping(value = "scene/{id}", method = {RequestMethod.PUT})
	public @ResponseBody Result update(@PathVariable Long id ,@RequestBody Scene scene) {
		if (id != null && scene != null) {
			scene.setId(id);
			sceneService.updateIgnoreNull(scene);
			return new Result("保存成功!");
		} else {
			return new Result(Result.FAIL,"数据传输失败!");
		}
	}
	
	@RequestMapping(value = "scene/{id}", method = {RequestMethod.DELETE})
	public @ResponseBody Result delete(@PathVariable Long id ) {
		// TODO 有些关键数据是不能物理删除的，需要改为逻辑删除
		sceneService.delete(new Scene(id));
		return new Result("删除成功!");
	}

	@Autowired(required = true)
	public void setSceneService(
			@Qualifier("sceneService") ISceneService sceneService) {
		this.sceneService = sceneService;
	}


}
