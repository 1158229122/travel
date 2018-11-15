package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Category;

import java.util.List;

public interface ICategoryDao {
    /**
     * 查询所有分类信息
     * @return
     */
    List<Category> findAll();

}
