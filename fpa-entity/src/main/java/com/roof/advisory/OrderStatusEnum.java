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
public enum OrderStatusEnum implements JSONSerializable {

    submitted("submitted", "提交订单", "submitted"),
    payed("payed", "已付款", "payed"), completed("completed", "已完成", "completed"),
    canceled("canceled", "已取消", "canceled");


    private String code;
    private String display;
    private String value;

    private OrderStatusEnum(String code, String display, String value) {
        this.code = code;
        this.display = display;
        this.value = value;
    }

    public static OrderStatusEnum getEnumByCode(String code) {
        for (OrderStatusEnum stateEnum : values()) {
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
