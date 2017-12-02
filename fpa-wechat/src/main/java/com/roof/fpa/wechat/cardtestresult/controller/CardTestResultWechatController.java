package com.roof.fpa.wechat.cardtestresult.controller;

import com.google.common.collect.Maps;
import com.roof.fpa.DefaultStateEnum;
import com.roof.fpa.cardtestresult.entity.CardTestResult;
import com.roof.fpa.cardtestresult.entity.CardTestResultVo;
import com.roof.fpa.cardtestresult.service.api.ICardTestResultService;
import com.roof.fpa.scene.entity.Scene;
import com.roof.fpa.scene.entity.SceneVo;
import com.roof.fpa.scene.service.api.ISceneService;
import org.roof.roof.dataaccess.api.Page;
import org.roof.roof.dataaccess.api.PageUtils;
import org.roof.spring.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("fpa/wechat")
public class CardTestResultWechatController {
	private ICardTestResultService cardTestResultService;





	@RequestMapping(value = "cardtestresult", method = {RequestMethod.POST})
	public @ResponseBody Result create(@RequestBody CardTestResultVo cardTestResultVo) {
		if (cardTestResultVo != null) {
			CardTestResult cardTestResult = new CardTestResult();
			BeanUtils.copyProperties(cardTestResultVo,cardTestResult);
			Long id = (Long) cardTestResultService.save(cardTestResult);
			return new Result("保存成功!",id);
		} else {
			return new Result(Result.FAIL,"数据传输失败!");
		}
	}


	@Autowired(required = true)
	public void setCardTestResultService(
			@Qualifier("cardTestResultService") ICardTestResultService cardTestResultService) {
		this.cardTestResultService = cardTestResultService;
	}


}
