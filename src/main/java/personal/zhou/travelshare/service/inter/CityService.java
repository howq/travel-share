package personal.zhou.travelshare.service.inter;


import personal.zhou.travelshare.domain.vo.CitySearchVo;
import personal.zhou.travelshare.util.Result;

public interface CityService {

    public Result listAllCity(CitySearchVo vo);

}
