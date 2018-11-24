package cn.itcast.travel.service.impl;


import cn.itcast.travel.dao.IFavoriteDao;
import cn.itcast.travel.domain.Favorite;


import cn.itcast.travel.service.IFavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class FavoriteServiceImpl implements IFavoriteService {
    @Autowired
    private IFavoriteDao favoriteDao;
    @Override
    public Favorite isFavorite(int rid, int uid) {
        Favorite favorite = favoriteDao.findByRidAndUid(rid, uid);

        return favorite;
    }

    @Override
    public void addFavorite(int rid, Date date, int uid) {
        favoriteDao.add(rid,date,uid);
    }

    @Override
    public List<Favorite> findByUid(int uid) {
        List<Favorite> list = favoriteDao.findByUid(uid);
        for (int i = 0; i < list.size(); i++) {
            Favorite favorite = list.get(i);

        }


        return null;
    }
}
