package com.roof.fpa.charactercolor.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.roof.roof.dataaccess.api.Page;
import org.roof.roof.dataaccess.api.PageUtils;
import org.roof.spring.Result;
import com.roof.fpa.charactercolor.entity.CharacterColor;
import com.roof.fpa.charactercolor.entity.CharacterColorVo;
import com.roof.fpa.charactercolor.service.api.ICharacterColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("fpa")
public class CharacterColorController {
	private ICharacterColorService characterColorService;




	@RequestMapping(value = "charactercolor", method = {RequestMethod.GET})
	public @ResponseBody Result<Page> list(CharacterColor characterColor, HttpServletRequest request, Model model) {
		Page page = PageUtils.createPage(request);
		page = characterColorService.page(page, characterColor);
		return new Result(Result.SUCCESS, page);
	}



	@RequestMapping(value = "charactercolor", method = {RequestMethod.POST})
	public @ResponseBody Result create(@RequestBody CharacterColor characterColor) {
		if (characterColor != null) {
			characterColorService.save(characterColor);
			return new Result("保存成功!");
		} else {
			return new Result(Result.FAIL,"数据传输失败!");
		}
	}

	@RequestMapping(value = "charactercolor/{id}", method = {RequestMethod.GET})
	public @ResponseBody Result<CharacterColorVo> load(@PathVariable Long id) {
		CharacterColorVo characterColorVo = characterColorService.load(new CharacterColor(id));
		return new Result(Result.SUCCESS,characterColorVo);
	}

	@RequestMapping(value = "charactercolor/{id}", method = {RequestMethod.PUT})
	public @ResponseBody Result update(@PathVariable Long id ,@RequestBody CharacterColor characterColor) {
		if (id != null && characterColor != null) {
			characterColor.setId(id);
			characterColorService.updateIgnoreNull(characterColor);
			return new Result("保存成功!");
		} else {
			return new Result(Result.FAIL,"数据传输失败!");
		}
	}

	@RequestMapping(value = "charactercolor/{id}", method = {RequestMethod.DELETE})
	public @ResponseBody Result delete(@PathVariable Long id ) {
		// TODO 有些关键数据是不能物理删除的，需要改为逻辑删除
		characterColorService.delete(new CharacterColor(id));
		return new Result("删除成功!");
	}

	@Autowired(required = true)
	public void setCharacterColorService(
			@Qualifier("characterColorService") ICharacterColorService characterColorService) {
		this.characterColorService = characterColorService;
	}


}
