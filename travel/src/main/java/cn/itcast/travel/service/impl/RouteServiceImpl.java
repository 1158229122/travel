package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.IRouteDao;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.service.IRouteService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 线路的业务逻辑层
 */
@Service
public class RouteServiceImpl implements IRouteService {
    @Autowired
    private IRouteDao routeDao;
    @Override
    public List<Route> findAllByLimit(int cid, int currentPage, int pageSize) {

        PageHelper.startPage(currentPage,pageSize);
        return routeDao.findAll(cid);
    }

    /**
     * 查询详情,查询到三张表
     * @param rid
     * @return
     */
    @Override
    public Route findDetail(int rid) {
        Route route = routeDao.findDetail(rid);
        return route;
    }

    @Override
    public void updateByRid(int rid) {
        routeDao.updateByRid(rid);
    }
}
