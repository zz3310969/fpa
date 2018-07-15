package com.roof.advisory.im.service;

public class ImRequest {

    public final static String IM_OPENSESSION = "openSession";
    public final static String IM_CLOSENSESSION = "closeSession";

    /**
     * openSession
     */
    private String requestType;
    /**
     * 用户token
     */
    private String token;
    private String seq;

    private String sender;
    private String receiver;
    /**
     * 开始时间
     */
    private Long startTime;
    private Long endTime;
    /**
     * 关闭时必填
     */
    private Long sessionId;

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }
}
