package com.roof.fpa;

/**
 * Created by zhenglt on 2017/11/25.
 */
public enum DefaultStateEnum {

    usable(1, "可用","success"),
    unusable(0, "不可用","error");



    private Integer code;
    private String display;
    private  String value;

    private DefaultStateEnum(Integer code, String display, String value){
        this.code = code;
        this.display = display;
        this.value = value;
    }


}
