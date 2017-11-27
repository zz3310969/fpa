package com.roof.fpa.cardtestresultdetail.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.roof.roof.dataaccess.api.Page;
import org.roof.roof.dataaccess.api.PageUtils;
import org.roof.spring.Result;
import com.roof.fpa.cardtestresultdetail.entity.CardTestResultDetail;
import com.roof.fpa.cardtestresultdetail.entity.CardTestResultDetailVo;
import com.roof.fpa.cardtestresultdetail.service.api.ICardTestResultDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("fpa")
public class CardTestResultDetailController {
	private ICardTestResultDetailService cardTestResultDetailService;




    @RequestMapping(value = "cardtestresultdetail", method = {RequestMethod.GET})
    public @ResponseBody Result<Page> list(CardTestResultDetail cardTestResultDetail, HttpServletRequest request) {
	    Page page = PageUtils.createPage(request);
	    page = cardTestResultDetailService.page(page, cardTestResultDetail);
	    return new Result(Result.SUCCESS, page);
	}
	


	@RequestMapping(value = "cardtestresultdetail", method = {RequestMethod.POST})
	public @ResponseBody Result create(@RequestBody CardTestResultDetail cardTestResultDetail) {
		if (cardTestResultDetail != null) {
			cardTestResultDetailService.save(cardTestResultDetail);
			return new Result("保存成功!");
		} else {
			return new Result(Result.FAIL,"数据传输失败!");
		}
	}

    @RequestMapping(value = "cardtestresultdetail/{id}", method = {RequestMethod.GET})
    public @ResponseBody Result<CardTestResultDetailVo> load(@PathVariable Long id) {
		CardTestResultDetailVo cardTestResultDetailVo = cardTestResultDetailService.load(new CardTestResultDetail(id));
        return new Result(Result.SUCCESS,cardTestResultDetailVo);
    }
	
	@RequestMapping(value = "cardtestresultdetail/{id}", method = {RequestMethod.PUT})
	public @ResponseBody Result update(@PathVariable Long id ,@RequestBody CardTestResultDetail cardTestResultDetail) {
		if (id != null && cardTestResultDetail != null) {
			cardTestResultDetail.setId(id);
			cardTestResultDetailService.updateIgnoreNull(cardTestResultDetail);
			return new Result("保存成功!");
		} else {
			return new Result(Result.FAIL,"数据传输失败!");
		}
	}
	
	@RequestMapping(value = "cardtestresultdetail/{id}", method = {RequestMethod.DELETE})
	public @ResponseBody Result delete(@PathVariable Long id ) {
		// TODO 有些关键数据是不能物理删除的，需要改为逻辑删除
		cardTestResultDetailService.delete(new CardTestResultDetail(id));
		return new Result("删除成功!");
	}

	@Autowired(required = true)
	public void setCardTestResultDetailService(
			@Qualifier("cardTestResultDetailService") ICardTestResultDetailService cardTestResultDetailService) {
		this.cardTestResultDetailService = cardTestResultDetailService;
	}


}
