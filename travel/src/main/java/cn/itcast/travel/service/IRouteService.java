package cn.itcast.travel.service;

import cn.itcast.travel.domain.Route;

import java.util.List;

public interface IRouteService {
    /**
     * 分页查询所有的线路
     *
     * @param
     * @param currentPage
     * @param cid
     * @return
     */
    List<Route> findAllByLimit(int cid, int currentPage, int pageSize);

    /**
     * 查询详情
     * @param rid
     * @return
     */
    Route findDetail(int rid);
}
