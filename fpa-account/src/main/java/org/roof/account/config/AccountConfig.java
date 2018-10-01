package org.roof.account.config;

import org.mybatis.spring.annotation.MapperScan;
import org.roof.account.api.IAccountService;
import org.roof.account.impl.AccountOperateServiceImpl;
import org.roof.account.local.service.LoaclAccountService;
import org.roof.account.wechat.service.WechatAccountService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("org.roof.account.jdbc.mapper")
public class AccountConfig {


    @Bean
    AccountOperateServiceImpl getAccountOperateServiceImpl(){
        AccountOperateServiceImpl accountOperateService = new AccountOperateServiceImpl();
        return accountOperateService;
    }

    @Bean
    LoaclAccountService getLoaclAccountService(){
        LoaclAccountService loaclAccountService = new LoaclAccountService();
        return  loaclAccountService;
    }

    @Bean
    WechatAccountService getWechatAccountService(){
        WechatAccountService wechatAccountService = new WechatAccountService();
        return  wechatAccountService;
    }


}
