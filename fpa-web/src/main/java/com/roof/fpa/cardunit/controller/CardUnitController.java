package com.roof.fpa.cardunit.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.roof.roof.dataaccess.api.Page;
import org.roof.roof.dataaccess.api.PageUtils;
import org.roof.spring.Result;
import com.roof.fpa.cardunit.entity.CardUnit;
import com.roof.fpa.cardunit.entity.CardUnitVo;
import com.roof.fpa.cardunit.service.api.ICardUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("fpa")
public class CardUnitController {
	private ICardUnitService cardUnitService;




    @RequestMapping(value = "cardunit", method = {RequestMethod.GET})
    public @ResponseBody Result<Page> list(CardUnit cardUnit, HttpServletRequest request, Model model) {
	    Page page = PageUtils.createPage(request);
	    page = cardUnitService.page(page, cardUnit);
	    return new Result(Result.SUCCESS, page);
	}
	


	@RequestMapping(value = "cardunit", method = {RequestMethod.POST})
	public @ResponseBody Result create(@RequestBody CardUnit cardUnit) {
		if (cardUnit != null) {
			cardUnitService.save(cardUnit);
			return new Result("保存成功!");
		} else {
			return new Result(Result.FAIL,"数据传输失败!");
		}
	}

    @RequestMapping(value = "cardunit/{id}", method = {RequestMethod.GET})
    public @ResponseBody Result<CardUnitVo> load(@PathVariable Long id) {
		CardUnitVo cardUnitVo = cardUnitService.load(new CardUnit(id));
        return new Result(Result.SUCCESS,cardUnitVo);
    }
	
	@RequestMapping(value = "cardunit/{id}", method = {RequestMethod.PUT})
	public @ResponseBody Result update(@PathVariable Long id ,@RequestBody CardUnit cardUnit) {
		if (id != null && cardUnit != null) {
			cardUnit.setId(id);
			cardUnitService.updateIgnoreNull(cardUnit);
			return new Result("保存成功!");
		} else {
			return new Result(Result.FAIL,"数据传输失败!");
		}
	}
	
	@RequestMapping(value = "cardunit/{id}", method = {RequestMethod.DELETE})
	public @ResponseBody Result delete(@PathVariable Long id ) {
		// TODO 有些关键数据是不能物理删除的，需要改为逻辑删除
		cardUnitService.delete(new CardUnit(id));
		return new Result("删除成功!");
	}

	@Autowired(required = true)
	public void setCardUnitService(
			@Qualifier("cardUnitService") ICardUnitService cardUnitService) {
		this.cardUnitService = cardUnitService;
	}


}
