package com.roof.fpa.charactercolor.controller;

import com.google.common.collect.Maps;
import com.roof.fpa.DefaultStateEnum;
import com.roof.fpa.DefaultUseableEnum;
import com.roof.fpa.charactercolor.entity.CharacterColor;
import com.roof.fpa.charactercolor.entity.CharacterColorVo;
import com.roof.fpa.charactercolor.entity.ColorDicVo;
import com.roof.fpa.charactercolor.service.api.ICharacterColorService;
import io.swagger.annotations.Api;
import org.roof.roof.dataaccess.api.Page;
import org.roof.roof.dataaccess.api.PageUtils;
import org.roof.spring.ApplicationException;
import org.roof.spring.Result;
import org.roof.web.dictionary.entity.Dictionary;
import org.roof.web.dictionary.service.api.IDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Api(value = "charactercolor", description = "性格色彩管理")
@Controller
@RequestMapping("fpa")
public class CharacterColorController {
    private ICharacterColorService characterColorService;

    @Autowired
    private IDictionaryService dictionaryService;

    @RequestMapping(value = "charactercolor/base", method = {RequestMethod.GET})
    public @ResponseBody
    Result<Map<String, Object>> base(HttpServletRequest request) {
        Map<String, Object> map = Maps.newHashMap();
        DefaultStateEnum[] stateEnums = DefaultStateEnum.values();
        map.put("states", stateEnums);
        //颜色字典
        List<Dictionary> dics = dictionaryService.findByType("COLOR");
        List<ColorDicVo> colors = new ArrayList<ColorDicVo>();
        for (Dictionary d : dics) {
            ColorDicVo vo = new ColorDicVo();
            vo.setId(d.getId());
            vo.setCode(d.getDescription());
            vo.setDisabled(false);
            vo.setDisplay(d.getText());
            colors.add(vo);
        }
        map.put("colors", colors);
        return new Result(Result.SUCCESS, map);
    }


    @RequestMapping(value = "charactercolor", method = {RequestMethod.GET})
    public @ResponseBody
    Result<Page> list(CharacterColor characterColor, HttpServletRequest request, Model model) {
        Page page = PageUtils.createPage(request);
        page = characterColorService.page(page, characterColor);
        return new Result(Result.SUCCESS, page);
    }


    @RequestMapping(value = "charactercolor", method = {RequestMethod.POST})
    public @ResponseBody
    Result create(@RequestBody CharacterColorVo characterColorVo) throws ApplicationException {
        if (characterColorVo != null) {
//			characterColorService.save(characterColor);
            characterColorService.saveVo(characterColorVo);
            return new Result("保存成功!");
        } else {
            return new Result(Result.FAIL, "数据传输失败!");
        }
    }

    @RequestMapping(value = "charactercolor/{id}", method = {RequestMethod.GET})
    public @ResponseBody
    Result<CharacterColorVo> load(@PathVariable Long id) {
        CharacterColorVo characterColorVo = characterColorService.load(new CharacterColor(id));
        return new Result(Result.SUCCESS, characterColorVo);
    }

    @RequestMapping(value = "charactercolor/{id}", method = {RequestMethod.PUT})
    public @ResponseBody
    Result update(@PathVariable Long id, @RequestBody CharacterColorVo characterColorVo) throws ApplicationException {
        if (id != null && characterColorVo != null) {
            characterColorVo.setId(id);
            characterColorService.updateIgnoreNullVo(characterColorVo);
            return new Result("保存成功!");
        } else {
            return new Result(Result.FAIL, "数据传输失败!");
        }
    }

    @RequestMapping(value = "charactercolor/{id}", method = {RequestMethod.DELETE})
    public @ResponseBody
    Result delete(@PathVariable Long id) {
        CharacterColor c = new CharacterColor(id);
        c.setUseable(DefaultUseableEnum.unusable.getCode());//标记为已删
        characterColorService.updateIgnoreNull(c);
        return new Result("删除成功!");
    }

    @Autowired(required = true)
    public void setCharacterColorService(
            @Qualifier("characterColorService") ICharacterColorService characterColorService) {
        this.characterColorService = characterColorService;
    }


}
