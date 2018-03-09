package com.roof.fpa.wechat.customerwords.controller;

import com.roof.fpa.customerwords.entity.CustomerWords;
import com.roof.fpa.customerwords.entity.CustomerWordsVo;
import com.roof.fpa.customerwords.service.api.ICustomerWordsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.roof.spring.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Api(value = "customerwords", description = "客户留言管理")
@Controller
@RequestMapping("fpa/wechat")
public class CustomerWordsWechatController {
	private ICustomerWordsService customerWordsService;



    @ApiOperation(value = "新增客户留言")
	@RequestMapping(value = "customerwords", method = {RequestMethod.POST})
	public @ResponseBody Result create(@RequestBody CustomerWords customerWords) {
		if (customerWords != null) {
			customerWordsService.save(customerWords);
			return new Result("保存成功!");
		} else {
			return new Result(Result.FAIL,"数据传输失败!");
		}
	}

    @ApiOperation(value = "根据ID加载客户留言")
    @RequestMapping(value = "customerwords/{id}", method = {RequestMethod.GET})
    public @ResponseBody Result<CustomerWordsVo> load(@PathVariable Long id) {
		CustomerWordsVo customerWordsVo = customerWordsService.load(new CustomerWords(id));
        return new Result(Result.SUCCESS,customerWordsVo);
    }

	@ApiOperation(value = "根据ID更新客户留言")
	@RequestMapping(value = "customerwords/{id}", method = {RequestMethod.PUT})
	public @ResponseBody Result update(@PathVariable Long id ,@RequestBody CustomerWords customerWords) {
		if (id != null && customerWords != null) {
			customerWords.setId(id);
			customerWordsService.updateIgnoreNull(customerWords);
			return new Result("保存成功!");
		} else {
			return new Result(Result.FAIL,"数据传输失败!");
		}
	}


	@Autowired(required = true)
	public void setCustomerWordsService(
			@Qualifier("customerWordsService") ICustomerWordsService customerWordsService) {
		this.customerWordsService = customerWordsService;
	}


}
