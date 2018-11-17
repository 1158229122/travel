package cn.itcast.travel.web.controller;

import cn.itcast.travel.domain.Favorite;
import cn.itcast.travel.domain.ResultInfo;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.IFavoriteService;
import cn.itcast.travel.service.IRouteService;

import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping(path = "/route")
public class RouteController {
    @Autowired
    private IFavoriteService favoriteService;
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


    @RequestMapping(path = "/findDetail")
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

    //是否被收藏
    @RequestMapping(path = "/isFavorite")
    public  @ResponseBody boolean isFavorite(HttpServletRequest req, @Param("rid") int rid ,@Param("uid") int uid) throws ServletException, IOException {
        //1判断用户是否登录
        System.out.println(rid);
        System.out.println(uid);
        User user = (User) req.getSession().getAttribute("user");
//        int uid = 0;
//        if (user == null) {
//            //用户没有登录
//            uid = 0;
//        } else {
//            //用户登陆了
//            uid = user.getUid();
//        }
        //2到数据中查询用户是否已经进行了收藏
        Favorite favorite = favoriteService.isFavorite(rid, uid);
        System.out.println(favorite);
        return false;
    }

    public void addFavorite(HttpServletRequest req) throws ServletException, IOException {
        //1获取参数行
        User user = (User) req.getSession().getAttribute("user");
        int uid = 0;
        if (user != null) {
            uid = user.getUid();
        }
        String rid = req.getParameter("rid");
        //添加在喜欢列表中保存到数据库
        favoriteService.addFavorite(rid, uid);
    }
}
