package cn.itcast.travel.web.controller;

import cn.itcast.travel.domain.ResultInfo;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.service.IRouteService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(path = "/route")
public class RouteController {
    @Autowired
    private ResultInfo resultInfo;
    @Autowired
    private IRouteService routeService;
    @RequestMapping(path = "/findAll")
    public @ResponseBody PageInfo findAll(@RequestParam int cid,@RequestParam(name = "currentPage",required = true,defaultValue = "1") int currentPage,@RequestParam(name ="pageSize" ,required = true,defaultValue = "5") int pageSize){
        List<Route> routes =  routeService.findAllByLimit(cid,currentPage,pageSize);
        PageInfo<Route> pageInfo = new PageInfo<Route>(routes);
        System.out.println(pageSize);
        resultInfo.setData(routes);
        return pageInfo;
    }


    @RequestMapping(path = "findDetail")
    public @ResponseBody Route findDetail(int rid){
        Route route = routeService.findDetail(rid);
        //查询分类表
        route.getCategory();
        //查询图片表
        route.getRouteImgList();
        //需要查询商家表
        route.getSeller();



        return route;
    }
}
