package com.roof.fpa.partner.service.impl;

/**
 * Created by zhenglt on 2018/1/6.
 */
public class XiaoeUserInfo {
    private String wx_union_id;
    private String nick_name;
    private String phone;
    private Integer gender;

    public String getWx_union_id() {
        return wx_union_id;
    }

    public void setWx_union_id(String wx_union_id) {
        this.wx_union_id = wx_union_id;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }
}
