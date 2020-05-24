package personal.zhou.travelshare.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import personal.zhou.travelshare.dao.ProvinceMapper;
import personal.zhou.travelshare.service.inter.ProvinceService;
import personal.zhou.travelshare.util.Result;

@Service
public class ProvinceServiceImpl implements ProvinceService {
    @Autowired
    ProvinceMapper dao;

    @Override
    public Result listAllProvince() {
        Result rs = new Result();
        rs.setSuccess(true);
        rs.setObject(dao.listAllProvince());
        return rs;
    }

}
