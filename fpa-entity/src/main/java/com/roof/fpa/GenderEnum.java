package com.roof.fpa;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.JSONSerializable;
import com.alibaba.fastjson.serializer.JSONSerializer;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * Created by zhenglt on 2017/11/25.
 */
public enum GenderEnum implements JSONSerializable {

    MALE(1, "男"),
    FEMALE(2, "女"),
    NONE(0, "所有");



    private Integer code;
    private String display;

    private GenderEnum(Integer code, String display){
        this.code = code;
        this.display = display;
    }

    public static GenderEnum getEnumByCode(Integer code){
        for(GenderEnum genderEnum:values()){
            if(genderEnum.getCode().equals(code)){
                return genderEnum;
            }
        }
        return null;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    @Override
    public void write(JSONSerializer serializer, Object fieldName, Type fieldType, int features) throws IOException {

        JSONObject object = new JSONObject();
        object.put("code",code);
        object.put("display",display);
        serializer.write(object);

    }

    public static GenderEnum[] AllGender(){
        GenderEnum[] genderEnums = new GenderEnum[2];
        genderEnums[0] = MALE;
        genderEnums[1] = FEMALE;
        return genderEnums;
    }

}
