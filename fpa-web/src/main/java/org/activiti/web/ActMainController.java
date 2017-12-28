package org.activiti.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 首页控制器
 *
 * @author HenryYan
 */
@Controller
@RequestMapping("/actmain")
public class ActMainController {

    @RequestMapping(value = "/index")
    public String index() {
        return "/main/index";
    }

    @RequestMapping(value = "/welcome")
    public String welcome() {
        return "/main/welcome";
    }

}
