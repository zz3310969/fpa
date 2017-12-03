package com.roof.fpa.transmittemplate.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.roof.fpa.DefaultUseableEnum;
import io.swagger.annotations.Api;
import org.roof.roof.dataaccess.api.Page;
import org.roof.roof.dataaccess.api.PageUtils;
import org.roof.spring.Result;
import com.roof.fpa.transmittemplate.entity.TransmitTemplate;
import com.roof.fpa.transmittemplate.entity.TransmitTemplateVo;
import com.roof.fpa.transmittemplate.service.api.ITransmitTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Api(value = "transmittemplate", description = "分享模板管理")
@Controller
@RequestMapping("fpa")
public class TransmitTemplateController {
    private ITransmitTemplateService transmitTemplateService;


    @RequestMapping(value = "transmittemplate", method = {RequestMethod.GET})
    public @ResponseBody
    Result<Page> list(TransmitTemplate transmitTemplate, HttpServletRequest request) {
        Page page = PageUtils.createPage(request);
        page = transmitTemplateService.page(page, transmitTemplate);
        return new Result(Result.SUCCESS, page);
    }


    @RequestMapping(value = "transmittemplate", method = {RequestMethod.POST})
    public @ResponseBody
    Result create(@RequestBody TransmitTemplate transmitTemplate) {
        if (transmitTemplate != null) {
            transmitTemplateService.save(transmitTemplate);
            return new Result("保存成功!");
        } else {
            return new Result(Result.FAIL, "数据传输失败!");
        }
    }

    @RequestMapping(value = "transmittemplate/{id}", method = {RequestMethod.GET})
    public @ResponseBody
    Result<TransmitTemplateVo> load(@PathVariable Long id) {
        TransmitTemplateVo transmitTemplateVo = transmitTemplateService.load(new TransmitTemplate(id));
        return new Result(Result.SUCCESS, transmitTemplateVo);
    }

    @RequestMapping(value = "transmittemplate/{id}", method = {RequestMethod.PUT})
    public @ResponseBody
    Result update(@PathVariable Long id, @RequestBody TransmitTemplate transmitTemplate) {
        if (id != null && transmitTemplate != null) {
            transmitTemplate.setId(id);
            transmitTemplateService.updateIgnoreNull(transmitTemplate);
            return new Result("保存成功!");
        } else {
            return new Result(Result.FAIL, "数据传输失败!");
        }
    }

    @RequestMapping(value = "transmittemplate/{id}", method = {RequestMethod.DELETE})
    public @ResponseBody
    Result delete(@PathVariable Long id) {
        TransmitTemplate transmitTemplate = new TransmitTemplate(id);
        transmitTemplate.setUseable(DefaultUseableEnum.unusable.getCode());
        transmitTemplateService.updateIgnoreNull(transmitTemplate);
        return new Result("删除成功!");
    }

    @Autowired(required = true)
    public void setTransmitTemplateService(
            @Qualifier("transmitTemplateService") ITransmitTemplateService transmitTemplateService) {
        this.transmitTemplateService = transmitTemplateService;
    }


}
