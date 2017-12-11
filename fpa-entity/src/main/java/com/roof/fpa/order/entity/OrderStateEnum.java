package com.roof.fpa.order.entity;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.JSONSerializable;
import com.alibaba.fastjson.serializer.JSONSerializer;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * Created by zhenglt on 2017/12/11.
 */
public enum OrderStateEnum implements JSONSerializable {
    WAITPAY(0, "未支付","waitpay"),
    PAID(1, "已支付","paid"),
    COMPLETE(2, "已完成","complete"),
    cancel(3, "已取消","cancel");

    private  String value;
    private Integer code;
    private String display;

    private OrderStateEnum(Integer code, String display, String value){
        this.code = code;
        this.display = display;
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
