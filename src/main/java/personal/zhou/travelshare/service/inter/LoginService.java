package personal.zhou.travelshare.service.inter;


import personal.zhou.travelshare.domain.vo.UserVo;
import personal.zhou.travelshare.util.Result;

import javax.servlet.http.HttpServletRequest;

public interface LoginService {

    public Result checkUser(UserVo vo, HttpServletRequest request);

}
