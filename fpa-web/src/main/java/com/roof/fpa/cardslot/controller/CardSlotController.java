package com.roof.fpa.cardslot.controller;

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
@RequestMapping("fpa")
public class CardSlotController {
	private ICardSlotService cardSlotService;




    @RequestMapping(value = "cardslot", method = {RequestMethod.GET})
    public @ResponseBody Result<Page> list(CardSlot cardSlot, HttpServletRequest request) {
	    Page page = PageUtils.createPage(request);
	    page = cardSlotService.page(page, cardSlot);
	    return new Result(Result.SUCCESS, page);
	}
	


	@RequestMapping(value = "cardslot", method = {RequestMethod.POST})
	public @ResponseBody Result create(@RequestBody CardSlot cardSlot) {
		if (cardSlot != null) {
			cardSlotService.save(cardSlot);
			return new Result("保存成功!");
		} else {
			return new Result(Result.FAIL,"数据传输失败!");
		}
	}

    @RequestMapping(value = "cardslot/{id}", method = {RequestMethod.GET})
    public @ResponseBody Result<CardSlotVo> load(@PathVariable Long id) {
		CardSlotVo cardSlotVo = cardSlotService.load(new CardSlot(id));
        return new Result(Result.SUCCESS,cardSlotVo);
    }
	
	@RequestMapping(value = "cardslot/{id}", method = {RequestMethod.PUT})
	public @ResponseBody Result update(@PathVariable Long id ,@RequestBody CardSlot cardSlot) {
		if (id != null && cardSlot != null) {
			cardSlot.setId(id);
			cardSlotService.updateIgnoreNull(cardSlot);
			return new Result("保存成功!");
		} else {
			return new Result(Result.FAIL,"数据传输失败!");
		}
	}
	
	@RequestMapping(value = "cardslot/{id}", method = {RequestMethod.DELETE})
	public @ResponseBody Result delete(@PathVariable Long id ) {
		// TODO 有些关键数据是不能物理删除的，需要改为逻辑删除
		cardSlotService.delete(new CardSlot(id));
		return new Result("删除成功!");
	}

	@Autowired(required = true)
	public void setCardSlotService(
			@Qualifier("cardSlotService") ICardSlotService cardSlotService) {
		this.cardSlotService = cardSlotService;
	}


}
