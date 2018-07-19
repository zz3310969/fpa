package com.roof.advisory.wxsession.service.api;

import com.roof.fpa.customer.entity.Customer;

public interface IWxSessionService {

    void createSession(String session_key, Customer customer);
    Customer getSession(String session_key);


    String createToken(String client_id);

     String getToken(String clientId);


    }
