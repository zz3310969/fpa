package com.roof.fpa.wechat.cardtestresult.controller;

import com.alibaba.fastjson.JSON;
import com.roof.fpa.cardtestresult.entity.CardTestResult;
import com.roof.fpa.cardtestresult.entity.CardTestResultVo;
import com.roof.fpa.cardtestresult.entity.GeneralCardTestCustomerResult;
import com.roof.fpa.cardtestresult.service.api.ICardTestResultService;
import com.roof.fpa.cardtestresultdetail.service.api.ICardTestResultDetailService;
import org.roof.roof.dataaccess.api.Page;
import org.roof.roof.dataaccess.api.PageUtils;
import org.roof.spring.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("fpa/wechat")
public class CardTestResultWechatController {
	private ICardTestResultService cardTestResultService;

	@Autowired
	private ICardTestResultDetailService cardTestResultDetailService;


	@RequestMapping(value = "cardtestresult", method = {RequestMethod.GET})
	public @ResponseBody Result<Page> list(CardTestResultVo cardTestResult, HttpServletRequest request) {
		Page page = PageUtils.createPage(request);
		page = cardTestResultService.page(page, cardTestResult);
		return new Result(Result.SUCCESS, page);
	}


	@RequestMapping(value = "cardtestresult/{id}", method = {RequestMethod.GET})
	public @ResponseBody Result<CardTestResultVo> load(@PathVariable Long id) {
		CardTestResultVo cardTestResultVo = cardTestResultService.load(new CardTestResult(id));
		return new Result(Result.SUCCESS,cardTestResultVo);
	}


	@RequestMapping(value = "cardtestresult", method = {RequestMethod.POST})
	public @ResponseBody Result create(@RequestBody CardTestResultVo cardTestResultVo) {
		if (cardTestResultVo != null) {
			CardTestResult cardTestResult = new CardTestResult();
			BeanUtils.copyProperties(cardTestResultVo,cardTestResult);
			GeneralCardTestCustomerResult generalCardTestCustomerResult = cardTestResultService.calculate(cardTestResultVo);
			cardTestResult.setBlueScore(generalCardTestCustomerResult.getBlueScore());
			cardTestResult.setGreenScore(generalCardTestCustomerResult.getGreenScore());
			cardTestResult.setRedScore(generalCardTestCustomerResult.getRedScore());
			cardTestResult.setYellowScore(generalCardTestCustomerResult.getYellowScore());
			cardTestResult.setCharacterColor(generalCardTestCustomerResult.getCharacterColorDefn());
			cardTestResult.setResult(JSON.toJSONString(generalCardTestCustomerResult));
			cardTestResult.setTemplateId(Long.valueOf(generalCardTestCustomerResult.getTemplateId()));
			Long id = (Long) cardTestResultService.save(cardTestResult);
			cardTestResultDetailService.saveList(cardTestResultVo.getResultDtoList(),id);

			return new Result(Result.SUCCESS, id);

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
