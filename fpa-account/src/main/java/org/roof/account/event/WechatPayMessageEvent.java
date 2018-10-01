package org.roof.account.event;

import org.springframework.context.ApplicationEvent;

public class WechatPayMessageEvent extends ApplicationEvent {

    private String sms;

    public WechatPayMessageEvent(Object source,String sms) {
        super(source);
        this.sms = sms;
    }

    public String getSms() {
        return sms;
    }

    public void setSms(String sms) {
        this.sms = sms;
    }
}
