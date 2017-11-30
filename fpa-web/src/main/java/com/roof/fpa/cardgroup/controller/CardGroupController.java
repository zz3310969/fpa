package com.roof.fpa.cardgroup.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.google.common.collect.Maps;
import com.roof.fpa.DefaultStateEnum;
import com.roof.fpa.GenderEnum;
import com.roof.fpa.cardunit.entity.CardUnit;
import com.roof.fpa.cardunit.entity.CardUnitVo;
import com.roof.fpa.cardunit.service.api.ICardUnitService;
import com.roof.fpa.charactercolor.entity.CharacterColor;
import com.roof.fpa.charactercolor.entity.CharacterColorVo;
import com.roof.fpa.charactercolor.service.api.ICharacterColorService;
import com.roof.fpa.theme.entity.Theme;
import com.roof.fpa.theme.entity.ThemeVo;
import com.roof.fpa.theme.service.api.IThemeService;
import org.roof.roof.dataaccess.api.Page;
import org.roof.roof.dataaccess.api.PageUtils;
import org.roof.spring.Result;
import com.roof.fpa.cardgroup.entity.CardGroup;
import com.roof.fpa.cardgroup.entity.CardGroupVo;
import com.roof.fpa.cardgroup.service.api.ICardGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("fpa")
public class CardGroupController {
	private ICardGroupService cardGroupService;



	@Autowired
	private ICharacterColorService characterColorService;
	@Autowired
	private IThemeService themeService;
	@Autowired
	private ICardUnitService cardUnitService;

	@RequestMapping(value = "cardgroup/base", method = {RequestMethod.GET})
	public @ResponseBody Result<Map<String,Object>> base(HttpServletRequest request) {
		Map<String,Object> map = Maps.newHashMap();
		Theme theme =  new Theme();
		theme.setState(DefaultStateEnum.usable.getCode());
		List<ThemeVo> themeVos = themeService.selectForList(theme);
		map.put("themes",themeVos);
		CharacterColor characterColor = new CharacterColor();
		characterColor.setState(DefaultStateEnum.usable.getCode());
		List<CharacterColorVo> characterColorVos = characterColorService.selectForList(characterColor);
		map.put("colors",characterColorVos);
		CardUnit cardUnit = new CardUnit();
		List<CardUnitVo> cardUnitVos = cardUnitService.selectForList(cardUnit);
		map.put("cardUnits",cardUnitVos);
		DefaultStateEnum[] stateEnums = DefaultStateEnum.values();
		map.put("states", stateEnums);
		GenderEnum[] genderEnums = GenderEnum.values();
		map.put("genders",genderEnums);
		return new Result(Result.SUCCESS, map);
	}


    @RequestMapping(value = "cardgroup", method = {RequestMethod.GET})
    public @ResponseBody Result<Page> list(CardGroup cardGroup, HttpServletRequest request) {
	    Page page = PageUtils.createPage(request);
	    page = cardGroupService.page(page, cardGroup);
	    return new Result(Result.SUCCESS, page);
	}
	


	@RequestMapping(value = "cardgroup", method = {RequestMethod.POST})
	public @ResponseBody Result create(@RequestBody CardGroup cardGroup) {
		if (cardGroup != null) {
			cardGroupService.save(cardGroup);
			return new Result("保存成功!");
		} else {
			return new Result(Result.FAIL,"数据传输失败!");
		}
	}

    @RequestMapping(value = "cardgroup/{id}", method = {RequestMethod.GET})
    public @ResponseBody Result<CardGroupVo> load(@PathVariable Long id) {
		CardGroupVo cardGroupVo = cardGroupService.load(new CardGroup(id));
        return new Result(Result.SUCCESS,cardGroupVo);
    }
	
	@RequestMapping(value = "cardgroup/{id}", method = {RequestMethod.PUT})
	public @ResponseBody Result update(@PathVariable Long id ,@RequestBody CardGroup cardGroup) {
		if (id != null && cardGroup != null) {
			cardGroup.setId(id);
			cardGroupService.updateIgnoreNull(cardGroup);
			return new Result("保存成功!");
		} else {
			return new Result(Result.FAIL,"数据传输失败!");
		}
	}
	
	@RequestMapping(value = "cardgroup/{id}", method = {RequestMethod.DELETE})
	public @ResponseBody Result delete(@PathVariable Long id ) {
		// TODO 有些关键数据是不能物理删除的，需要改为逻辑删除
		cardGroupService.delete(new CardGroup(id));
		return new Result("删除成功!");
	}

	@Autowired(required = true)
	public void setCardGroupService(
			@Qualifier("cardGroupService") ICardGroupService cardGroupService) {
		this.cardGroupService = cardGroupService;
	}


}
