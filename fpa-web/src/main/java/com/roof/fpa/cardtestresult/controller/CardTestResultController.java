package com.roof.fpa.cardtestresult.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.google.common.collect.Maps;
import com.roof.fpa.DefaultStateEnum;
import com.roof.fpa.GenderEnum;
import com.roof.fpa.cardunit.entity.CardUnit;
import com.roof.fpa.cardunit.entity.CardUnitVo;
import com.roof.fpa.charactercolor.entity.CharacterColor;
import com.roof.fpa.charactercolor.entity.CharacterColorVo;
import com.roof.fpa.scene.entity.Scene;
import com.roof.fpa.scene.entity.SceneVo;
import com.roof.fpa.scene.service.api.ISceneService;
import com.roof.fpa.theme.entity.Theme;
import com.roof.fpa.theme.entity.ThemeVo;
import org.roof.roof.dataaccess.api.Page;
import org.roof.roof.dataaccess.api.PageUtils;
import org.roof.spring.Result;
import com.roof.fpa.cardtestresult.entity.CardTestResult;
import com.roof.fpa.cardtestresult.entity.CardTestResultVo;
import com.roof.fpa.cardtestresult.service.api.ICardTestResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("fpa")
public class CardTestResultController {
	private ICardTestResultService cardTestResultService;

	@Autowired
	private ISceneService sceneService;


	@RequestMapping(value = "cardtestresult/base", method = {RequestMethod.GET})
	public @ResponseBody Result<Map<String,Object>> base(HttpServletRequest request) {
		Map<String,Object> map = Maps.newHashMap();
		Scene scene =  new Scene();
		scene.setState(DefaultStateEnum.usable.getCode());
		List<SceneVo> sceneVos = sceneService.selectForList(scene);
		map.put("scenes",sceneVos);
		return new Result(Result.SUCCESS, map);
	}


    @RequestMapping(value = "cardtestresult", method = {RequestMethod.GET})
    public @ResponseBody Result<Page> list(CardTestResultVo cardTestResult, HttpServletRequest request) {
	    Page page = PageUtils.createPage(request);
	    page = cardTestResultService.page(page, cardTestResult);
	    return new Result(Result.SUCCESS, page);
	}
	


	@RequestMapping(value = "cardtestresult", method = {RequestMethod.POST})
	public @ResponseBody Result create(@RequestBody CardTestResult cardTestResult) {
		if (cardTestResult != null) {
			cardTestResultService.save(cardTestResult);
			return new Result("保存成功!");
		} else {
			return new Result(Result.FAIL,"数据传输失败!");
		}
	}

    @RequestMapping(value = "cardtestresult/{id}", method = {RequestMethod.GET})
    public @ResponseBody Result<CardTestResultVo> load(@PathVariable Long id) {
		CardTestResultVo cardTestResultVo = cardTestResultService.load(new CardTestResult(id));
        return new Result(Result.SUCCESS,cardTestResultVo);
    }
	
	@RequestMapping(value = "cardtestresult/{id}", method = {RequestMethod.PUT})
	public @ResponseBody Result update(@PathVariable Long id ,@RequestBody CardTestResult cardTestResult) {
		if (id != null && cardTestResult != null) {
			cardTestResult.setId(id);
			cardTestResultService.updateIgnoreNull(cardTestResult);
			return new Result("保存成功!");
		} else {
			return new Result(Result.FAIL,"数据传输失败!");
		}
	}
	
	@RequestMapping(value = "cardtestresult/{id}", method = {RequestMethod.DELETE})
	public @ResponseBody Result delete(@PathVariable Long id ) {
		// TODO 有些关键数据是不能物理删除的，需要改为逻辑删除
		cardTestResultService.delete(new CardTestResult(id));
		return new Result("删除成功!");
	}

	@Autowired(required = true)
	public void setCardTestResultService(
			@Qualifier("cardTestResultService") ICardTestResultService cardTestResultService) {
		this.cardTestResultService = cardTestResultService;
	}


}
