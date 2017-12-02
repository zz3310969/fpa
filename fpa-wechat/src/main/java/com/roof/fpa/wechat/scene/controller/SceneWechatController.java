package com.roof.fpa.wechat.scene.controller;

import com.google.common.collect.Maps;
import com.roof.fpa.DefaultStateEnum;
import com.roof.fpa.card.service.api.ICardService;
import com.roof.fpa.cardgroup.entity.CardGroup;
import com.roof.fpa.cardgroup.entity.CardGroupVo;
import com.roof.fpa.cardgroup.service.api.ICardGroupService;
import com.roof.fpa.cardslot.entity.CardSlot;
import com.roof.fpa.cardslot.entity.CardSlotVo;
import com.roof.fpa.cardslot.service.api.ICardSlotService;
import com.roof.fpa.scene.entity.Scene;
import com.roof.fpa.scene.entity.SceneStateEnum;
import com.roof.fpa.scene.entity.SceneVo;
import com.roof.fpa.scene.service.api.ISceneService;
import com.roof.fpa.theme.entity.Theme;
import com.roof.fpa.theme.entity.ThemeVo;
import com.roof.fpa.theme.service.api.IThemeService;
import io.swagger.annotations.Api;
import org.roof.roof.dataaccess.api.Page;
import org.roof.roof.dataaccess.api.PageUtils;
import org.roof.spring.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Api(value = "scene", description = "场景管理")
@Controller
@RequestMapping("fpa")
public class SceneWechatController {
	private ISceneService sceneService;

	@Autowired
	private ICardGroupService cardGroupService;

	@Autowired
	private IThemeService themeService;

	@Autowired
	private ICardSlotService cardSlotService;


    @RequestMapping(value = "scene/{id}", method = {RequestMethod.GET})
    public @ResponseBody Result<SceneVo> load(@PathVariable Long id) {
		SceneVo sceneVo = sceneService.load(new Scene(id));
		CardSlot cardSlot = new CardSlot();
		List<CardSlotVo> cardSlotVos = cardSlotService.selectForList(cardSlot);
		sceneVo.setCardSlotList(cardSlotVos);
		CardGroupVo cardGroupVo = cardGroupService.loadCardByCardGroupId(id);
		sceneVo.setCardGroup(cardGroupVo);
		return new Result(Result.SUCCESS,sceneVo);
    }

	@Autowired(required = true)
	public void setSceneService(
			@Qualifier("sceneService") ISceneService sceneService) {
		this.sceneService = sceneService;
	}


}
