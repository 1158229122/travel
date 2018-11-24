package cn.itcast.travel.service;

import cn.itcast.travel.domain.Favorite;


import java.util.Date;
import java.util.List;

public interface IFavoriteService {
    /**
     * 判断是否收藏
     * @param rid
     * @param uid
     * @return
     */
    public Favorite isFavorite(int rid, int uid);

    /**
     * 添加收藏
     * @param rid
     * @param uid
     *
     */
    void addFavorite(int rid, Date date, int uid);

    /**
     * 根据uid查询favorite
     *
     * @param
     * @param uid
     * @return
     */
    List<Favorite> findByUid(int uid);
}
