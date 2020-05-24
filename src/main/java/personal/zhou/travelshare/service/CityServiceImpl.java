package personal.zhou.travelshare.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import personal.zhou.travelshare.dao.CityMapper;
import personal.zhou.travelshare.domain.vo.CitySearchVo;
import personal.zhou.travelshare.service.inter.CityService;
import personal.zhou.travelshare.util.Result;
import personal.zhou.travelshare.util.ResultCode;

@Service
public class CityServiceImpl extends BaseService implements CityService {

    @Autowired
    private CityMapper dao;

    @Override
    public Result listAllCity(CitySearchVo vo) {
        return getResult(ResultCode.SUCCESS, dao.listAllCity(vo), "success");


    }

}
