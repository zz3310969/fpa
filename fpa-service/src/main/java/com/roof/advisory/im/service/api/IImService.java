package com.roof.advisory.im.service.api;

import com.roof.advisory.im.service.ImRequest;

public interface IImService {
    /**
     * 返回openid
     *
     * @param imRequest
     * @return
     */
    Long openSession(ImRequest imRequest);

    /**
     * @param imRequest
     */
    void closeSession(ImRequest imRequest);

    /**
     * 获取所有在线用户从IM系统
     */
    public void getAllUsers();

}
