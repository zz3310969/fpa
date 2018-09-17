package com.roof.fpa.wechat.cardtestresult.controller;

import com.alibaba.fastjson.JSON;
import com.roof.fpa.cardtestresult.entity.CardTestResult;
import com.roof.fpa.cardtestresult.entity.CardTestResultVo;
import com.roof.fpa.cardtestresult.entity.GeneralCardTestCustomerResult;
import com.roof.fpa.cardtestresult.entity.Score;
import com.roof.fpa.cardtestresult.service.api.ICardTestResultService;
import com.roof.fpa.cardtestresultdetail.entity.CardTestResultDetail;
import com.roof.fpa.cardtestresultdetail.service.api.ICardTestResultDetailService;
import com.roof.fpa.template.entity.Template;
import com.roof.fpa.template.entity.TemplateVo;
import com.roof.fpa.template.service.api.ITemplateService;
import freemarker.template.TemplateException;
import org.roof.roof.dataaccess.api.Page;
import org.roof.roof.dataaccess.api.PageUtils;
import org.roof.spring.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

@Controller
@RequestMapping("fpa/wechat")
public class CardTestResultWechatController {
    private ICardTestResultService cardTestResultService;

    @Autowired
    private ICardTestResultDetailService cardTestResultDetailService;
    @Autowired
    private ITemplateService templateService;
    private static final Logger LOGGER = LoggerFactory.getLogger(CardTestResultWechatController.class);


    @RequestMapping(value = "cardtestresult", method = {RequestMethod.GET})
    public @ResponseBody
    Result<Page> list(CardTestResultVo cardTestResult, HttpServletRequest request) {
        Page page = PageUtils.createPage(request);
        page = cardTestResultService.page(page, cardTestResult);
        return new Result(Result.SUCCESS, page);
    }

    @RequestMapping(value = "cardtestresult/{id}", method = {RequestMethod.GET})
    public @ResponseBody
    Result<CardTestResultVo> load(@PathVariable Long id) {
        CardTestResultVo cardTestResultVo = cardTestResultService.load(new CardTestResult(id));
        TemplateVo templateVo = templateService.loadByCache(cardTestResultVo.getTemplateId());
        try {
            cardTestResultVo.setResult(templateService.mergeTemplate(templateVo.getContent(), JSON.parseObject(cardTestResultVo.getResult())));
        } catch (TemplateException | IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
        CardTestResultDetail detail = new CardTestResultDetail();
        detail.setResultId(id);
        cardTestResultVo.setCardTestResultDetailVoList(cardTestResultDetailService.selectForList(detail));

        return new Result(Result.SUCCESS, cardTestResultVo);
    }


    @RequestMapping(value = "v2/cardtestresult/{id}", method = {RequestMethod.GET})
    public @ResponseBody
    Result<CardTestResultVo> load_v2(@PathVariable Long id) {
        CardTestResultVo cardTestResultVo = cardTestResultService.load(new CardTestResult(id));

        GeneralCardTestCustomerResult cardTestCustomerResult = JSON.parseObject(cardTestResultVo.getResult(),GeneralCardTestCustomerResult.class);
        cardTestResultVo.setAdvantage(Score.toAd(cardTestCustomerResult));
        cardTestResultVo.setWeakness(Score.toIm(cardTestCustomerResult));

        TemplateVo templateVo = templateService.loadByCache(cardTestResultVo.getTemplateId());
        try {
            cardTestResultVo.setResult(templateService.mergeTemplate(templateVo.getContent(), JSON.parseObject(cardTestResultVo.getResult())));
        } catch (TemplateException | IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
        CardTestResultDetail detail = new CardTestResultDetail();
        detail.setResultId(id);
        cardTestResultVo.setCardTestResultDetailVoList(cardTestResultDetailService.selectForList_v2(detail));

        return new Result(Result.SUCCESS, cardTestResultVo);
    }


    @RequestMapping(value = "cardtestresult", method = {RequestMethod.POST})
    public @ResponseBody
    Result create(@RequestBody CardTestResultVo cardTestResultVo) {
        if (cardTestResultVo != null) {
            CardTestResult cardTestResult = new CardTestResult();
            BeanUtils.copyProperties(cardTestResultVo, cardTestResult);
            GeneralCardTestCustomerResult generalCardTestCustomerResult = cardTestResultService.calculate(cardTestResultVo);
            cardTestResult.setBlueScore(generalCardTestCustomerResult.getBlueScore());
            cardTestResult.setGreenScore(generalCardTestCustomerResult.getGreenScore());
            cardTestResult.setRedScore(generalCardTestCustomerResult.getRedScore());
            cardTestResult.setYellowScore(generalCardTestCustomerResult.getYellowScore());
            cardTestResult.setCharacterColor(generalCardTestCustomerResult.getCharacterColorDefn());
            cardTestResult.setResult(JSON.toJSONString(generalCardTestCustomerResult));
            cardTestResult.setTemplateId(Long.valueOf(generalCardTestCustomerResult.getTemplateId()));
            Long id = (Long) cardTestResultService.save(cardTestResult);
            cardTestResultDetailService.saveList(cardTestResultVo.getResultDtoList(), id);

            return new Result(Result.SUCCESS, id);

        } else {
            return new Result(Result.FAIL, "数据传输失败!");
        }
    }


    @Autowired(required = true)
    public void setCardTestResultService(
            @Qualifier("cardTestResultService") ICardTestResultService cardTestResultService) {
        this.cardTestResultService = cardTestResultService;
    }


}
