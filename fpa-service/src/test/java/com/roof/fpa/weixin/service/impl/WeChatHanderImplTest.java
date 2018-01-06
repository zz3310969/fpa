package com.roof.fpa.weixin.service.impl;

import com.roof.fpa.weixin.service.api.IWeChatHander;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import static org.junit.Assert.*;

/**
 * Created by liangl on 2018/1/6.
 */
@ContextConfiguration(locations = {"classpath*:spring.xml"})
public class WeChatHanderImplTest extends AbstractJUnit4SpringContextTests {
    private IWeChatHander weChatHander;

    @org.junit.Test
    public void getacode() throws Exception {
        weChatHander.getacode();
    }

}