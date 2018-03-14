package com.roof.advisory.wxsession.store.impl;

import com.roof.advisory.wxsession.store.api.WxSessionStore;
import com.roof.fpa.customer.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisWxSessionStore implements WxSessionStore {

    @Autowired
    private RedisTemplate<String,Customer> redisTemplate;

    private String keyOffset = "wx_session_key:";

    @Override
    public Customer readSessionKey(String session_key) {
        Customer customer =  redisTemplate.boundValueOps(session_key).get();
        return customer;
    }

    @Override
    public void storeSessionKey(String session_key, Customer customer) {
        redisTemplate.boundValueOps(session_key).set(customer);

    }
}
