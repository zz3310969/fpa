package com.roof.advisory.wechat.area.controller;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.roof.advisory.area.entity.AreaVo;
import com.roof.advisory.commentrecord.entity.CommentRecordVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.roof.roof.dataaccess.api.Page;
import org.roof.roof.dataaccess.api.PageUtils;
import org.roof.spring.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Api(value = "area", description = "地区管理")
@Controller
@RequestMapping("/fpa/wechat/advisory")
public class AreaController {

    @ApiOperation(value = "获得评价记录表分页列表")
    @RequestMapping(value = "area", method = {RequestMethod.GET})
    public @ResponseBody
    Result<List<Map<String,Object>>> list(AreaVo areaVo, HttpServletRequest request) {
        List<Map<String,Object>> list = Lists.newArrayList();
        Map<String,Object> shanghai = Maps.newHashMap();
        shanghai.put("code","Shanghai");
        shanghai.put("value","上海");
        shanghai.put("key",1);
        Map<String,Object> zhejiang = Maps.newHashMap();
        zhejiang.put("code","Zhejiang");
        zhejiang.put("value","浙江");
        zhejiang.put("key",2);
        list.add(shanghai);
        list.add(zhejiang);

        return new Result(Result.SUCCESS, list);
    }
}
