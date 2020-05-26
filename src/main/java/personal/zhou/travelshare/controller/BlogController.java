package personal.zhou.travelshare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import personal.zhou.travelshare.domain.vo.*;
import personal.zhou.travelshare.service.inter.BlogService;
import personal.zhou.travelshare.util.MethodResourceDesc;
import personal.zhou.travelshare.util.Result;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/trip/user")
public class BlogController {
    @Autowired
    private BlogService blogService;

    @MethodResourceDesc(name = "发布博客")
    @RequestMapping(value = "/addBlog.do",method = RequestMethod.POST)
    @ResponseBody
    public Result insert(BlogVo vo, HttpServletRequest request) {
        return blogService.insert(vo, request);
    }

    @MethodResourceDesc(name = "添加一级评论")
    @RequestMapping(value = "/commitComment.do")
    @ResponseBody
    public Result insertComment(CommentVo vo, HttpServletRequest req) {

        return blogService.insertComment(vo, req);

    }

    @MethodResourceDesc(name = "删除博客")
    @RequestMapping(value = "/deleteBlog.do")
    @ResponseBody
    public Result delete(int id, HttpServletRequest request) {
        return blogService.delete(id, request);
    }

    @MethodResourceDesc(name = "搜索博客")
    @RequestMapping(value = "/getblog.do")
    @ResponseBody
    public Result list(HttpServletRequest request, UserVo vo) {
        return blogService.list(request, vo);

    }

    @MethodResourceDesc(name = "搜索博客by content")
    @RequestMapping(value = "/searchblog.do")
    @ResponseBody
    public Result searchblog(BlogSearchVo vo, HttpServletRequest request) {

        return blogService.searchblog(vo, request);

    }

    @MethodResourceDesc(name = "搜索一级评论")
    @RequestMapping(value = "/getcomment.do")
    @ResponseBody
    public Result listComment(int blogId) {
        //int blogid = Integer.parseInt(blogId);
        return blogService.listComment(blogId);

    }

    @MethodResourceDesc(name = "删除博客")
    @RequestMapping(value = "/addlikeit.do")
    @ResponseBody
    public Result addLikeIt(LikeitVo vo, HttpServletRequest request) {
        return blogService.addLikeIt(vo, request);
    }

}
