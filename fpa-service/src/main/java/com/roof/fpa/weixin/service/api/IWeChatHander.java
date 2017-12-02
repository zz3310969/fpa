package com.roof.fpa.weixin.service.api;

import java.io.IOException;

/**
 * Created by zhenglt on 2017/12/2.
 */
public interface IWeChatHander {
    public String getOpenid(String code) throws IOException;

}
