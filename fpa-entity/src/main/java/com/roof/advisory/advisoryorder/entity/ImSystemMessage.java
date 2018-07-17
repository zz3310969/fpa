package com.roof.advisory.advisoryorder.entity;

import java.io.Serializable;

/**
 * com.roof.advisory.advisoryorder.entity
 *
 * @author liht
 * @date 2018/7/12
 */
public class ImSystemMessage implements Serializable {

    private String payload;
    private String receiver;
    private String requestType = "systemMessage";
    private String token;
    private String type = "TXT";
    private String seq = "1";
    private String clientType = "server";

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }

    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }


}
