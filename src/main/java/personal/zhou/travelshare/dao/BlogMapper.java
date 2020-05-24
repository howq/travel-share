package personal.zhou.travelshare.dao;

import personal.zhou.travelshare.domain.vo.*;

import java.util.List;

public interface BlogMapper {

    void insert(BlogVo vo);

    void delete(int id);

    List<BlogVo> list(UserVo uservo);

    List<BlogVo> listSelfBlog(UserVo uservo);

    int insertComment(CommentVo vo);

    List<CommentVo> listComment(int blogId);

    List<BlogVo> searchblog(BlogSearchVo vo);

    int addlikeit(LikeitVo vo);

    int deletelikeit(LikeitVo vo);

    int checklikeit(LikeitVo vo);

}
