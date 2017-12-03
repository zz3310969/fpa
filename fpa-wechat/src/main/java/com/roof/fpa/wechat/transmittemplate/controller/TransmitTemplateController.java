package com.roof.fpa.wechat.transmittemplate.controller;

import com.roof.fpa.DefaultUseableEnum;
import com.roof.fpa.transmittemplate.entity.TransmitTemplate;
import com.roof.fpa.transmittemplate.entity.TransmitTemplateVo;
import com.roof.fpa.transmittemplate.service.api.ITransmitTemplateService;
import io.swagger.annotations.Api;
import org.roof.roof.dataaccess.api.Page;
import org.roof.roof.dataaccess.api.PageUtils;
import org.roof.spring.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Api(value = "transmittemplate", description = "场景管理")
@Controller
@RequestMapping("fpa/wechat")
public class TransmitTemplateController {
    private ITransmitTemplateService transmitTemplateService;

    @RequestMapping(value = "transmittemplate/{id}", method = {RequestMethod.GET})
    public @ResponseBody
    Result<TransmitTemplateVo> load(@PathVariable Long id) {
        TransmitTemplateVo transmitTemplateVo = transmitTemplateService.load(new TransmitTemplate(id));
        return new Result(Result.SUCCESS, transmitTemplateVo);
    }


    @Autowired(required = true)
    public void setTransmitTemplateService(
            @Qualifier("transmitTemplateService") ITransmitTemplateService transmitTemplateService) {
        this.transmitTemplateService = transmitTemplateService;
    }


}
