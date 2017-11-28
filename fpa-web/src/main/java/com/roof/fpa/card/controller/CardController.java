package com.roof.fpa.card.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.roof.roof.dataaccess.api.Page;
import org.roof.roof.dataaccess.api.PageUtils;
import org.roof.spring.Result;
import com.roof.fpa.card.entity.Card;
import com.roof.fpa.card.entity.CardVo;
import com.roof.fpa.card.service.api.ICardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Api(value = "card", description = "卡牌管理")
@Controller
@RequestMapping("fpa")
public class CardController {
	private ICardService cardService;



	@ApiOperation(value = "获得卡牌分页列表", notes = "列表信息")
	@RequestMapping(value = "card", method = {RequestMethod.GET})
    public @ResponseBody Result<Page> list(Card card, HttpServletRequest request) {
	    Page page = PageUtils.createPage(request);
	    page = cardService.page(page, card);
	    return new Result(Result.SUCCESS, page);
	}


	@ApiOperation(value = "新增卡牌")
	@RequestMapping(value = "card", method = {RequestMethod.POST})
	public @ResponseBody Result create(@RequestBody Card card) {
		if (card != null) {
			cardService.save(card);
			return new Result("保存成功!");
		} else {
			return new Result(Result.FAIL,"数据传输失败!");
		}
	}

	@ApiOperation(value = "根据ID加载卡牌")
    @RequestMapping(value = "card/{id}", method = {RequestMethod.GET})
    public @ResponseBody Result<CardVo> load(@PathVariable Long id) {
		CardVo cardVo = cardService.load(new Card(id));
        return new Result(Result.SUCCESS,cardVo);
    }

	@ApiOperation(value = "根据ID更新卡牌")
	@RequestMapping(value = "card/{id}", method = {RequestMethod.PUT})
	public @ResponseBody Result update(@PathVariable Long id ,@RequestBody Card card) {
		if (id != null && card != null) {
			card.setId(id);
			cardService.updateIgnoreNull(card);
			return new Result("保存成功!");
		} else {
			return new Result(Result.FAIL,"数据传输失败!");
		}
	}

	@ApiOperation(value = "根据ID删除卡牌")
	@RequestMapping(value = "card/{id}", method = {RequestMethod.DELETE})
	public @ResponseBody Result delete(@PathVariable Long id ) {
		// TODO 有些关键数据是不能物理删除的，需要改为逻辑删除
		cardService.delete(new Card(id));
		return new Result("删除成功!");
	}

	@Autowired(required = true)
	public void setCardService(
			@Qualifier("cardService") ICardService cardService) {
		this.cardService = cardService;
	}


}
