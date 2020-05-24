package personal.zhou.travelshare.dao;

import personal.zhou.travelshare.domain.vo.CitySearchVo;

import java.util.List;

public interface CityMapper {

    public List<String> listAllCity(CitySearchVo vo);

}
