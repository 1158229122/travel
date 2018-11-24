package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Favorite;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface IFavoriteDao {
    /**
     * 根据rid和uid查询是否喜欢
     * @param rid
     * @param uid
     * @return
     */
    public Favorite findByRidAndUid(@Param("rid") int rid, @Param("uid") int uid);

    /**
     * 添加喜欢
     * @param rid
     * @param uid
     */
    void add(@Param("rid") int rid, @Param("date") Date date , @Param("uid") int uid);

    /**
     * 查询收藏数量
     * @param i
     * @return
     */
    int findCount(int i);

    /**
     * 根据ID查询
     * @param uid
     * @return
     */
     List<Favorite> findByUid(int uid);

    /**
     * 根据uid查询用户喜欢的路线
     * @param uid
     * @return
     */
    List<Favorite> findByUidFavoriteAndRoute(int uid);
}
