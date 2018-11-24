package cn.itcast.Test;

import cn.itcast.travel.dao.IFavoriteDao;
import cn.itcast.travel.dao.IRouteDao;
import cn.itcast.travel.dao.IUserDao;
import cn.itcast.travel.domain.Favorite;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.annotations.Param;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContest.xml")
public class TestRouteDao {
    @Autowired
    private IUserDao userDao;
    @Autowired
    private IRouteDao routeDao;
    @Autowired
    private IFavoriteDao favoriteDao;
    @Test
    public void testaaa(){
        Route detail = routeDao.findDetail(1);
        detail.getSeller();
        detail.getRouteImgList();
        detail.getCategory();
        System.out.println(detail);
//
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        try {
//            objectMapper.writeValueAsString(detail);
//        } catch (JsonProcessingException e) {
//           e.printStackTrace();
//        }
        //Favorite favorite = favoriteDao.findByRidAndUid( 1,  1);

        //System.out.println(favorite);
    }
    @Test
    public void testbb(){
        List<Favorite> favorites = favoriteDao.findByUidFavoriteAndRoute(2);
        //获取用户和图片
        for (Favorite favorite : favorites) {
            favorite.getUser();
            favorite.getRoute();
        }
        System.out.println(favorites);
    }
}
