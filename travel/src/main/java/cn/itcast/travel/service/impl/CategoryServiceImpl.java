package cn.itcast.travel.service.impl;


import cn.itcast.travel.dao.ICategoryDao;
import cn.itcast.travel.domain.Category;

import cn.itcast.travel.service.ICategoryService;
import cn.itcast.travel.util.JedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Service
public class CategoryServiceImpl implements ICategoryService {
    @Autowired
    private ICategoryDao categoryDao;
    @Override
    public List<Category> findAll() {
        Jedis jedis = JedisUtil.getJedis();//withscores
        Set<Tuple> category = jedis.zrangeWithScores("category",0,-1);
        if(category==null||category.size()==0){
            //jedis中没有需要在数据库中查
            List<Category> categorys = categoryDao.findAll();
            System.out.println("数据库中查询");
            //存储到jedis中
            for (Category category1 : categorys) {
                jedis.zadd("category",category1.getCid(),category1.getCname());
            }
            return categorys;
        }

        List<Category> categorys = new LinkedList<Category>();
        for (Tuple tuple : category) {
            double score = tuple.getScore();
            String element = tuple.getElement();
            categorys.add(new Category((int)score,element));
        }
        System.out.println("redis中查询");
        return categorys;
    }
}
