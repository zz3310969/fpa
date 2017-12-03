package com.roof.fpa;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.JSONSerializable;
import com.alibaba.fastjson.serializer.JSONSerializer;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * Created by zhenglt on 2017/11/25.
 */
public enum DefaultUseableEnum implements JSONSerializable {

    usable(1, "在用","usable"),
    unusable(0, "删除","unusable");



    private Integer code;
    private String display;
    private  String value;

    private DefaultUseableEnum(Integer code, String display, String value){
        this.code = code;
        this.display = display;
        this.value = value;
    }

    public static DefaultUseableEnum getEnumByCode(Integer code){
        for(DefaultUseableEnum stateEnum:values()){
            if(stateEnum.getCode().equals(code)){
                return stateEnum;
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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public void write(JSONSerializer serializer, Object fieldName, Type fieldType, int features) throws IOException {

        JSONObject object = new JSONObject();
        object.put("code",code);
        object.put("display",display);
        serializer.write(object);

    }
}
