package com.roof.advisory.user.controller;

import com.roof.fpa.account.service.api.IAccountService;
import com.roof.fpa.customer.entity.CustomerVo;
import org.roof.roof.dataaccess.api.DaoException;
import org.roof.spring.Result;
import org.roof.web.user.entity.User;
import org.roof.web.user.service.api.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("advisory")
public class UserController {

    @Autowired
    private IUserService userService;
    @Autowired
    private IAccountService accountService;

    @RequestMapping(value = "user/{username}", method = {RequestMethod.GET})
    public @ResponseBody
    Result<User> loadByUsername(@PathVariable String username) throws DaoException {
        User u = new User();
        u.setUsername(username);
        User user = userService.login(u);
        return new Result(Result.SUCCESS, user);
    }


    @RequestMapping(value = "usercount", method = {RequestMethod.GET})
    public @ResponseBody
    Result<Long> userCount() {
        Long l = accountService.countAllUser();
        return new Result(Result.SUCCESS, l);
    }
}
