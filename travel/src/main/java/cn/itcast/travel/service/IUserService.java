package cn.itcast.travel.service;

import cn.itcast.travel.domain.Favorite;
import cn.itcast.travel.domain.User;


import java.util.List;

public interface IUserService {
    /**
     * 查询所有
     * @return
     */

    List<User> findAll();

    /**
     * 注册方法
     * @param user
     */
    void register(User user);

    /**
     * 登录方法
     * @param user
     * @return
     */
    User findUserByUsernameAndPassword(User user);

    /**
     * 根据用户uid查询用户喜欢的路线
     * @param uid
     * @return
     */
    List<Favorite> findByUidFavoriteAndRoute(int uid);
}
