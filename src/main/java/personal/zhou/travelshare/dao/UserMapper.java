package personal.zhou.travelshare.dao;

import personal.zhou.travelshare.domain.vo.FollowVo;
import personal.zhou.travelshare.domain.vo.FollowerVo;
import personal.zhou.travelshare.domain.vo.SearchVo;
import personal.zhou.travelshare.domain.vo.UserVo;

import java.util.List;

public interface UserMapper {

    UserVo checkUser(UserVo vo);

    public void register(UserVo vo);

    public void upload_head(UserVo vo);

    public int blogCount(int id);

    public int followerCount(int id);

    public int fansCount(int id);

    public List<FollowerVo> listFollower(int id);

    public List<FollowerVo> listFans(int id);

    public UserVo getPersonById(int id);

    public int editInfo(UserVo vo);

    public List<UserVo> searchUser(SearchVo vo);

    public int addFollow(FollowVo vo);

    public int deletefollowOrFans(FollowVo vo);

    public int checkUserIsExit(String userAccount);

}
