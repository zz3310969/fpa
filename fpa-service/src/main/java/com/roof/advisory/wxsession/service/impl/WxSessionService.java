package com.roof.advisory.wxsession.service.impl;

import com.google.common.collect.Maps;
import com.roof.advisory.wxsession.service.api.IWxSessionService;
import com.roof.advisory.wxsession.store.api.WxSessionStore;
import com.roof.fpa.customer.entity.Customer;
import org.roof.web.user.service.api.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidClientException;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.common.exceptions.InvalidRequestException;
import org.springframework.security.oauth2.common.exceptions.UnsupportedGrantTypeException;
import org.springframework.security.oauth2.common.util.OAuth2Utils;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.Map;

@Service
public class WxSessionService implements IWxSessionService {
    @Autowired
    private WxSessionStore wxSessionStore;

    @Autowired
    private TokenGranter tokenGranter;
    @Autowired
    private ClientDetailsService clientDetailsService;
    @Autowired
    private OAuth2RequestFactory oAuth2RequestFactory;
    @Autowired
    private OAuth2RequestFactory defaultOAuth2RequestFactory;
    @Autowired
    private ClientRegistrationService clientRegistrationService;

    @Autowired
    private IUserService userService;

    @Autowired
    private AuthorizationServerTokenServices tokenServices;

    @Autowired
    private OAuth2RequestFactory requestFactory;




    @Override
    public void createSession(String session_key, Customer customer) {
        wxSessionStore.storeSessionKey(session_key,customer);
    }

    @Override
    public Customer getSession(String session_key) {
        return wxSessionStore.readSessionKey(session_key);
    }

    @Override
    public String createToken(String clientId) {
        Map<String,String> parameters = Maps.newHashMap();
        //parameters.put(OAuth2Utils.SCOPE ,this.scope);
        parameters.put(OAuth2Utils.GRANT_TYPE ,"password");
        parameters.put(OAuth2Utils.CLIENT_ID ,clientId);

        parameters.put("username" ,clientId);
        //parameters.put("password" ,this.password);
        //parameters.put("client_secret",this.client_secret);


        //userService.login()

        ClientDetails authenticatedClient = new BaseClientDetails(clientId, null, "", "password", null);;
        /*try{
            authenticatedClient = getClientDetailsService().loadClientByClientId(clientId);
        }catch (NoSuchClientException e){
            ClientDetails clientDetails = new BaseClientDetails(clientId, null, "", "password", null);
            clientRegistrationService.addClientDetails(clientDetails);
            authenticatedClient = getClientDetailsService().loadClientByClientId(clientId);
        }*/

        TokenRequest tokenRequest = getOAuth2RequestFactory().createTokenRequest(parameters, authenticatedClient);
/*
        if (clientId != null && !clientId.equals("")) {
            // Only validate the client details if a client authenticated during this
            // request.
            if (!clientId.equals(tokenRequest.getClientId())) {
                // double check to make sure that the client ID in the token request is the same as that in the
                // authenticated client
                throw new InvalidClientException("Given client ID does not match authenticated client");
            }
        }


        OAuth2AccessToken token = getTokenGranter().grant(tokenRequest.getGrantType(), tokenRequest);
        if (token == null) {
            throw new UnsupportedGrantTypeException("Unsupported grant type: " + tokenRequest.getGrantType());
        }*/

        Authentication userAuth = new UsernamePasswordAuthenticationToken(clientId, "");

        BaseClientDetails client = new BaseClientDetails();
        client.setClientId(clientId);
                OAuth2Request storedOAuth2Request = getRequestFactory().createOAuth2Request(client, tokenRequest);
        OAuth2Authentication authentication = new OAuth2Authentication(storedOAuth2Request, userAuth);

        OAuth2AccessToken token = tokenServices.createAccessToken(authentication);

        return token.getValue();

    }


    protected TokenGranter getTokenGranter() {
        return tokenGranter;
    }


    protected OAuth2RequestFactory getOAuth2RequestFactory() {
        return oAuth2RequestFactory;
    }

    protected OAuth2RequestFactory getDefaultOAuth2RequestFactory() {
        return defaultOAuth2RequestFactory;
    }

    protected ClientDetailsService getClientDetailsService() {
        return clientDetailsService;
    }

    public OAuth2RequestFactory getRequestFactory() {
        return requestFactory;
    }
}
