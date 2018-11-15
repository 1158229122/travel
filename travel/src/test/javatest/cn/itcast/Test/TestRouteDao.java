package cn.itcast.Test;

import cn.itcast.travel.dao.IRouteDao;
import cn.itcast.travel.dao.IUserDao;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContest.xml")
public class TestRouteDao {
    @Autowired
    private IUserDao userDao;
    @Autowired
    private IRouteDao routeDao;
    @Test
    public void testaaa(){
        Route detail = routeDao.findDetail(1);
        detail.getSeller();
        detail.getRouteImgList();
        detail.getCategory();
        System.out.println(detail);


        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValueAsString(detail);
        } catch (JsonProcessingException e) {
           e.printStackTrace();
        }
    }
}
