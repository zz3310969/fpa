package com.roof.advisory.companypay;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * com.roof.advisory.companypay
 *
 * @author liht
 * @date 2018/10/8
 */
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath*:spring.xml"})
public class CompanyPayServiceTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private ICompanyPayService companyPayService;

    @Test
    public void transfers() {
        String rs = companyPayService.transfers("om2wi0S4Fkxzm9rLzinOrfgG8NeE", "");
        System.out.println(rs);
    }

    @Test
    public void transferInfo() {
        String rs = companyPayService.transferInfo("1538989814418");
        System.out.println(rs);
    }
}
