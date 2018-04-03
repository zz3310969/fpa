package com.roof.fpa.weixin.service.impl;

/**
 * Created by zhenglt on 2018/1/6.
 */
public class WeChatDto {
    private String openid;
    private String session_key;
    private String unionid;
    private String session_token;
    private String errcode;
    private String errmsg;

    private Long userId;
    private String[] userTags;

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getSession_key() {
        return session_key;
    }

    public void setSession_key(String session_key) {
        this.session_key = session_key;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getSession_token() {
        return session_token;
    }

    public void setSession_token(String session_token) {
        this.session_token = session_token;
    }

    public String[] getUserTags() {
        return userTags;
    }

    public void setUserTags(String[] userTags) {
        this.userTags = userTags;
    }
}
