package personal.zhou.travelshare.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import personal.zhou.travelshare.dao.UserMapper;
import personal.zhou.travelshare.domain.vo.UserVo;
import personal.zhou.travelshare.service.inter.LoginService;
import personal.zhou.travelshare.util.Result;
import personal.zhou.travelshare.util.ResultCode;
import personal.zhou.travelshare.util.SysConst;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service
public class LoginServiceImpl extends BaseService implements LoginService {

    @Autowired
    private UserMapper dao;

    @Override
    public Result checkUser(UserVo vo, HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserVo resultvo = dao.checkUser(vo);
        if (resultvo == null) {
            return getResult(ResultCode.FAILURE, "", "failed");
        } else {
            session.setAttribute(SysConst.CurrentUser, resultvo);
            return getResult(ResultCode.SUCCESS, resultvo, "success");
        }

    }

}
