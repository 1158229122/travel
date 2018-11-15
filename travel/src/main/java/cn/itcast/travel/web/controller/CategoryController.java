package cn.itcast.travel.web.controller;

import cn.itcast.travel.domain.Category;
import cn.itcast.travel.domain.ResultInfo;
import cn.itcast.travel.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping(path = "/category")
public class CategoryController {
    @Autowired
    private ResultInfo resultInfo;
    @Autowired
    private ICategoryService categoryService;
    @RequestMapping(path = "/findAll")
    public @ResponseBody List<Category> findAll(){
        List<Category> categorys = categoryService.findAll();
        categorys.sort(new Comparator<Category>() {
            @Override
            public int compare(Category o1, Category o2) {
                //按照升序排列
                return o1.getCid()-o2.getCid();
            }
        });

        return categorys;
    }
}
