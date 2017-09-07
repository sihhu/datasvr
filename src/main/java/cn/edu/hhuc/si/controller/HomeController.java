package cn.edu.hhuc.si.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description: 这只是一个关于跳转到首页的控制器
 */
@Controller
@RequestMapping("/home")
public class HomeController {
    private static Logger log = LoggerFactory.getLogger(HomeController.class);

    /**
     * @return
     * @Description: 跳转到首页的控制器，处理来自客户端的根目录请求及home请求，请求方式为gei方式
     */
    @RequestMapping({"/index"})
    public String home() {
        log.debug("Function Info: {}", "HomeController.index");
        return "/home";
    }

}
