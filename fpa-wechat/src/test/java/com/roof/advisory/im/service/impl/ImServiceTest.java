package com.roof.advisory.im.service.impl;

import com.roof.advisory.im.service.api.IImService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import static org.junit.Assert.*;

/**
 * com.roof.advisory.im.service.impl
 *
 * @author liht
 * @date 2018/3/14
 */
@ContextConfiguration(locations = {"classpath*:spring.xml"})
public class ImServiceTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private IImService imService;

    @Test
    public void getAllUsers() {
        imService.getAllUsers();
    }
}
