package com.roof.fpa.scene.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
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

@Controller
@RequestMapping("fpa")
public class SceneController {
	private ISceneService sceneService;




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
