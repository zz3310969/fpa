package com.roof.advisory.wxsession.store.api;

import com.roof.fpa.customer.entity.Customer;
import org.springframework.security.core.Authentication;

public interface WxSessionStore {

    /**
     * Read the authentication stored under the specified token value.
     *
     * @param session_key The token value under which the authentication is stored.
     * @return The authentication, or null if none.
     */
    Customer readSessionKey(String session_key);

    /**
     * Store an access token.
     *
     * @param session_key The token to store.
     * @param customer The authentication associated with the token.
     */
    void storeSessionKey(String session_key, Customer customer);


}
