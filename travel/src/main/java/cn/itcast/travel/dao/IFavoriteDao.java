package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Favorite;

import java.util.List;

public interface IFavoriteDao {
    /**
     * 根据rid和uid查询是否喜欢
     * @param rid
     * @param uid
     * @return
     */
    public Favorite findByRidAndUid(int rid, int uid);

    /**
     * 添加喜欢
     * @param i
     * @param uid
     */
    void add(int i, int uid);

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
}
