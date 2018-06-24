package com.roof.advisory;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.JSONSerializable;
import com.alibaba.fastjson.serializer.JSONSerializer;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * @author liht
 * @date 2018/03/07
 */
public enum WechatRecordTypeEnum implements JSONSerializable {

    getprepayid("getprepayid", "微信获取预支付编码", "getprepayid"),
    wechatNoticePayded("wechatNoticePayded", "微信支付成功", "wechatNoticePayded");


    private String code;
    private String display;
    private String value;

    private WechatRecordTypeEnum(String code, String display, String value) {
        this.code = code;
        this.display = display;
        this.value = value;
    }

    public static WechatRecordTypeEnum getEnumByCode(String code) {
        for (WechatRecordTypeEnum stateEnum : values()) {
            if (stateEnum.getCode().equals(code)) {
                return stateEnum;
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
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
        object.put("code", code);
        object.put("display", display);
        serializer.write(object);

    }
}
