package com.roof.fpa.wechat.cardgroup.controller;

import com.roof.fpa.cardgroup.entity.CardGroup;
import com.roof.fpa.cardgroup.entity.CardGroupVo;
import com.roof.fpa.cardgroup.service.api.ICardGroupService;
import com.roof.fpa.cardslot.entity.CardSlot;
import com.roof.fpa.cardslot.entity.CardSlotVo;
import com.roof.fpa.cardslot.service.api.ICardSlotService;
import org.roof.roof.dataaccess.api.Page;
import org.roof.roof.dataaccess.api.PageUtils;
import org.roof.spring.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("fpa/wechat")
public class CardGroupWechatController {
	private ICardSlotService cardSlotService;

	@Autowired
	private ICardGroupService cardGroupService;



	@RequestMapping(value = "cardgroup/{id}", method = {RequestMethod.GET})
	public @ResponseBody Result<CardGroupVo> load(@PathVariable Long id) {
		CardGroupVo cardGroupVo = cardGroupService.loadCardByCardGroupId(id);
		return new Result(Result.SUCCESS,cardGroupVo);
	}


	@Autowired(required = true)
	public void setCardSlotService(
			@Qualifier("cardSlotService") ICardSlotService cardSlotService) {
		this.cardSlotService = cardSlotService;
	}


}
