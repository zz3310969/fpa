package com.roof.fpa.wechat;

import com.roof.fpa.account.service.api.IAccountService;
import org.roof.spring.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by zhenglt on 2017/11/29.
 */
@Controller
@RequestMapping("fpa/wechat")
public class HomeController {
    @Autowired
    private IAccountService accountService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public @ResponseBody
    String home(Model model, HttpServletRequest request) {
        try {
            String addr = InetAddress.getLocalHost().getHostAddress();
            String port = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
            return "Hello Wold ip=" + addr + ",prot:" + request.getLocalPort();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return "Hello WeChat";
    }

    @RequestMapping(value = "usercount", method = {RequestMethod.GET})
    public @ResponseBody
    Result<Long> userCount() {
        Long l = accountService.countAllUser();
        return new Result(Result.SUCCESS, l);
    }
}
