package com.roof.fpa.wechat.cardtestresult.controller;

import com.roof.fpa.cardtestresult.entity.CardTestResult;
import com.roof.fpa.cardtestresult.entity.CardTestResultVo;
import com.roof.fpa.cardtestresult.entity.GeneralCardTestCustomerResult;
import com.roof.fpa.cardtestresult.service.api.ICardTestResultService;
import org.roof.spring.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
			GeneralCardTestCustomerResult generalCardTestCustomerResult = cardTestResultService.calculate(cardTestResultVo);
			return new Result(Result.SUCCESS, generalCardTestCustomerResult);
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
