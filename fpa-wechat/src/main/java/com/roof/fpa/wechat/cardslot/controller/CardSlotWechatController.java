package com.roof.fpa.wechat.cardslot.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.roof.roof.dataaccess.api.Page;
import org.roof.roof.dataaccess.api.PageUtils;
import org.roof.spring.Result;
import com.roof.fpa.cardslot.entity.CardSlot;
import com.roof.fpa.cardslot.entity.CardSlotVo;
import com.roof.fpa.cardslot.service.api.ICardSlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("fpa/wechat")
public class CardSlotWechatController {
	private ICardSlotService cardSlotService;


    @RequestMapping(value = "cardslot", method = {RequestMethod.GET})
    public @ResponseBody Result<List<CardSlotVo>> list(CardSlot cardSlot, HttpServletRequest request) {
    	if(cardSlot.getSceneId() == null){
			return new Result(Result.FAIL,"场景不能为空");
		}
		List<CardSlotVo> cardSlotVos = cardSlotService.selectForList(cardSlot);
	    return new Result(Result.SUCCESS, cardSlotVos);
	}
	



	@Autowired(required = true)
	public void setCardSlotService(
			@Qualifier("cardSlotService") ICardSlotService cardSlotService) {
		this.cardSlotService = cardSlotService;
	}


}
