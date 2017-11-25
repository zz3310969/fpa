package com.roof.fpa.character.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.roof.roof.dataaccess.api.Page;
import org.roof.roof.dataaccess.api.PageUtils;
import org.roof.spring.Result;
import com.roof.fpa.character.entity.Character;
import com.roof.fpa.character.entity.CharacterVo;
import com.roof.fpa.character.service.api.ICharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("fpa")
public class CharacterController {
	private ICharacterService characterService;




    @RequestMapping(value = "character", method = {RequestMethod.GET})
    public @ResponseBody Result<Page> list(Character character, HttpServletRequest request, Model model) {
	    Page page = PageUtils.createPage(request);
	    page = characterService.page(page, character);
	    return new Result(Result.SUCCESS, page);
	}
	


	@RequestMapping(value = "character", method = {RequestMethod.POST})
	public @ResponseBody Result create(@RequestBody Character character) {
		if (character != null) {
			characterService.save(character);
			return new Result("保存成功!");
		} else {
			return new Result(Result.FAIL,"数据传输失败!");
		}
	}

    @RequestMapping(value = "character/{id}", method = {RequestMethod.GET})
    public @ResponseBody Result<CharacterVo> load(@PathVariable Long id) {
		CharacterVo characterVo = characterService.load(new Character(id));
        return new Result(Result.SUCCESS,characterVo);
    }
	
	@RequestMapping(value = "character/{id}", method = {RequestMethod.PUT})
	public @ResponseBody Result update(@PathVariable Long id ,@RequestBody Character character) {
		if (id != null && character != null) {
			character.setId(id);
			characterService.updateIgnoreNull(character);
			return new Result("保存成功!");
		} else {
			return new Result(Result.FAIL,"数据传输失败!");
		}
	}
	
	@RequestMapping(value = "character/{id}", method = {RequestMethod.DELETE})
	public @ResponseBody Result delete(@PathVariable Long id ) {
		// TODO 有些关键数据是不能物理删除的，需要改为逻辑删除
		characterService.delete(new Character(id));
		return new Result("删除成功!");
	}

	@Autowired(required = true)
	public void setCharacterService(
			@Qualifier("characterService") ICharacterService characterService) {
		this.characterService = characterService;
	}


}
