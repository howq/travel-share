package personal.zhou.travelshare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import personal.zhou.travelshare.domain.vo.CitySearchVo;
import personal.zhou.travelshare.service.inter.CityService;
import personal.zhou.travelshare.util.Result;

@Controller
public class CityController {
    @Autowired
    private CityService service;

    @RequestMapping(value = "/trip/listAllCity.do")
    @ResponseBody
    public Result listAllCtiy(CitySearchVo vo) {
        return service.listAllCity(vo);


    }

}
