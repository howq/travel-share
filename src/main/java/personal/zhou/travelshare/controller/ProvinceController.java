package personal.zhou.travelshare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import personal.zhou.travelshare.service.inter.ProvinceService;
import personal.zhou.travelshare.util.Result;

@Controller
@Scope("prototype")
public class ProvinceController {
    @Autowired
    ProvinceService service;

    @RequestMapping(value = "/trip/listAllProvice.do")
    @ResponseBody
    public Result listAllProvince() {
        return service.listAllProvince();

    }
}
