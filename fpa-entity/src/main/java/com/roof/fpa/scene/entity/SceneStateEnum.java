package com.roof.fpa.scene.entity;

/**
 * Created by zhenglt on 2017/11/25.
 */
public enum SceneStateEnum {

    usable(1, "已上线","success"),
    unusable(0, "未上线","error");



    private Integer code;
    private String display;
    private  String value;

    private SceneStateEnum(Integer code,String display,String value){
        this.code = code;
        this.display = display;
        this.value = value;
    }


}
