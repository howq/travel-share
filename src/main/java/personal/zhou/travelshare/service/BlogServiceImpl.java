package personal.zhou.travelshare.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import personal.zhou.travelshare.dao.BlogMapper;
import personal.zhou.travelshare.domain.vo.*;
import personal.zhou.travelshare.service.inter.BlogService;
import personal.zhou.travelshare.util.Result;
import personal.zhou.travelshare.util.ResultCode;
import personal.zhou.travelshare.util.SysConst;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Service
public class BlogServiceImpl extends BaseService implements BlogService {

    @Autowired
    private BlogMapper blogMapper;
    private HttpSession session;

    @Override
    public Result insert(BlogVo vo, HttpServletRequest request) {
        session = request.getSession();
        UserVo userVo = (UserVo) session.getAttribute(SysConst.CurrentUser);
        vo.setUserid(userVo.getId());
        vo.setCreatetime(new Date());
        blogMapper.insert(vo);
        return getResult(ResultCode.SUCCESS, "", "博文发布成功");
    }

    @Override
    public Result delete(int id, HttpServletRequest request) {
        blogMapper.delete(id);
        return getResult(ResultCode.SUCCESS, "", "博文删除成功");

    }

    @Override
    public Result list(HttpServletRequest request, UserVo uservo) {
        session = request.getSession();
        UserVo vo = (UserVo) session.getAttribute(SysConst.CurrentUser);
        uservo.setId(vo.getId());
        List<BlogVo> list = blogMapper.list(uservo);
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setLocaltime(list.get(i).getCreatetime().toLocaleString());
        }
        return getResult(ResultCode.SUCCESS, list, "");
    }

    @Override
    public Result listSelfBlog(UserVo vo) {
        List<BlogVo> list = blogMapper.listSelfBlog(vo);
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setLocaltime(list.get(i).getCreatetime().toLocaleString());
        }
        return getResult(ResultCode.SUCCESS, list, "");
    }

    @Override
    public Result insertComment(CommentVo vo, HttpServletRequest req) {
        session = req.getSession();
        UserVo uservo = (UserVo) session.getAttribute(SysConst.CurrentUser);
        vo.setUserId(uservo.getId());
        vo.setCreateTime(new Date().toLocaleString());
        int i = blogMapper.insertComment(vo);

        if (i > 0) {
            return getResult(ResultCode.SUCCESS, "", "");
        } else {
            return getResult(ResultCode.FAILURE, "", "");
        }
    }

    @Override
    public Result listComment(int blogId) {
        List<CommentVo> list = blogMapper.listComment(blogId);
        return getResult(ResultCode.SUCCESS, list, "");
    }

    @Override
    public Result searchblog(BlogSearchVo vo, HttpServletRequest request) {
        vo.setSize(5);
        session = request.getSession();
        UserVo uservo = (UserVo) session.getAttribute(SysConst.CurrentUser);
        vo.setId(uservo.getId());
        List<BlogVo> list = blogMapper.searchblog(vo);
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setLocaltime(list.get(i).getCreatetime().toLocaleString());
        }
        return getResult(ResultCode.SUCCESS, list, "");
    }

    @Override
    public Result addLikeIt(LikeitVo vo, HttpServletRequest req) {
        session = req.getSession();
        UserVo uservo = (UserVo) session.getAttribute(SysConst.CurrentUser);
        vo.setUserId(uservo.getId());
        int check = blogMapper.checklikeit(vo);
        if (check > 0) {
            blogMapper.deletelikeit(vo);
            return getResult(ResultCode.FAILURE, "", "取消赞成功");
        } else {
            int i = blogMapper.addlikeit(vo);

            if (i > 0) {
                return getResult(ResultCode.SUCCESS, "", "点赞成功");
            } else {
                return getResult(ResultCode.FAILURE, "", "");
            }
        }

    }

}
