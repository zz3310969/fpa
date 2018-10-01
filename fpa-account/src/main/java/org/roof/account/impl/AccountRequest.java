package org.roof.account.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class AccountRequest {
    private Long userId;
    private String type;

    private Map<String, String> requestParameters = Collections
            .unmodifiableMap(new HashMap<String, String>());


    public AccountRequest(Long userId,String type,Map<String, String> requestParameters){
        this.userId = userId;
        this.type = type;
        setRequestParameters(requestParameters);
    }



    protected void setRequestParameters(Map<String, String> requestParameters) {
        if (requestParameters != null) {
            this.requestParameters = Collections
                    .unmodifiableMap(new HashMap<String, String>(requestParameters));
        }
    }

    public String getRequestParameters(String key) {
        return requestParameters.get(key);
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
