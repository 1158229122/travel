package cn.itcast.travel.web.controller;

import cn.itcast.travel.domain.ResultInfo;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping(path = "/user")
public class UserController {
    @Autowired
    private IUserService userService;
    //用于封装结果集
    @Autowired
    private ResultInfo resultInfo;
    @RequestMapping(path = "/findAll")
    public void findAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("控制器方法执行了");
    //return "redirect:/login.html";
        List<User> users = userService.findAll();
        for (User user : users) {
            System.out.println(user);
        }
        response.sendRedirect(request.getContextPath()+"/login.html");
    }

    @RequestMapping(path = "/register")
    public @ResponseBody ResultInfo register(User user){
        try {
            userService.register(user);
        } catch (Exception e) {
            System.out.println("遇到了一个错误!预测是用户名被占用");
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("用户名被占用");
            return resultInfo;
        }
        resultInfo.setFlag(true);
        return resultInfo;
    }
    @RequestMapping("/login")
    public @ResponseBody ResultInfo login(User user,HttpServletRequest request){


        User loginUser = userService.findUserByUsernameAndPassword(user);

        if(loginUser==null){

            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("用户名或密码错误");
            return resultInfo;
        }else if("N".equalsIgnoreCase(loginUser.getStatus())){
            //登录失败
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("您还未激活,请先激活");
            return resultInfo;
        }else {
            //登录失败
            //登陆成功存入session欲
            request.getSession().setAttribute("user",loginUser);
            resultInfo.setFlag(true);
            return resultInfo;
        }

    }
    @RequestMapping("/findOne")
    public @ResponseBody User findOne(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");

            return user;


    }
}
