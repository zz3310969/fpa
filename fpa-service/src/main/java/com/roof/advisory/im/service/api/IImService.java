package com.roof.advisory.im.service.api;

import com.roof.advisory.im.service.ImRequest;

public interface IImService {
    /**
     * 返回openid
     * @param imRequest
     * @return
     */
    Long openSession(ImRequest imRequest);
    void closeSession(ImRequest imRequest);

}
