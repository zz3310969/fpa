package com.roof.fpa.charactercolor.entity;

import java.util.List;

/**
 * @author 模版生成 <br/>
 *         表名： c_character_color <br/>
 *         描述：性格色彩 <br/>
 */
public class CharacterColorVo extends CharacterColor {

    private List<CharacterColorVo> characterColorList;

    private List<Long> colorIds;

    public CharacterColorVo() {
        super();
    }

    public CharacterColorVo(Long id) {
        super();
        this.id = id;
    }

    public List<CharacterColorVo> getCharacterColorList() {
        return characterColorList;
    }

    public void setCharacterColorList(List<CharacterColorVo> characterColorList) {
        this.characterColorList = characterColorList;
    }

    public List<Long> getColorIds() {
        return colorIds;
    }

    public void setColorIds(List<Long> colorIds) {
        this.colorIds = colorIds;
    }

}
