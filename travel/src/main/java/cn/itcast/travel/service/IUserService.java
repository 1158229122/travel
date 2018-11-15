package cn.itcast.travel.service;

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
}
