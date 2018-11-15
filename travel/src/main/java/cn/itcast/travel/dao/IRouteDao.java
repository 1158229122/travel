package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Route;

import java.util.List;

public interface IRouteDao {
    /**
     * 查询所有的线路
     */
     List<Route> findAll(int cid);

    /**
     * 查询详情
     * @param rid
     * @return
     */
    Route findDetail(int rid);
}
