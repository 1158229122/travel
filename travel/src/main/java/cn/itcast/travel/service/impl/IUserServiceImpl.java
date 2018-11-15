package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.IUserDao;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.IUserService;
import cn.itcast.travel.util.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class IUserServiceImpl implements IUserService {
    @Autowired
    private IUserDao userDao;
    @Override
    public List<User> findAll() {
        List<User> users = userDao.findAll();
        System.out.println("service方法执行");
        return users;
    }

    @Override
    public void register(User user) {
        String uuid = UuidUtil.getUuid();
        user.setCode(uuid);
        user.setStatus("N");
        userDao.register(user);
    }

    @Override
    public User findUserByUsernameAndPassword(User user) {
        User loginUser = null;
        try {
           loginUser = userDao.findUserByUsernameAndPassword(user);
        } catch (Exception e) {
            System.out.println("登录出错");

        }
        return loginUser;
    }
}
