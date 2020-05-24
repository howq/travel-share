package personal.zhou.travelshare.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

public class RegisterController {

    @RequestMapping(value = "/trip/user/register")
    public ModelAndView toRegisterPage() {
        System.out.println("jjjj");
        return new ModelAndView("forward:/register.jsp");
    }

}
