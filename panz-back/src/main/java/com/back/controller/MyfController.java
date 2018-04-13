package com.back.controller;

import com.back.po.Myf;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hasee on 2018/1/7.
 */
@Controller
@RequestMapping("myf")
public class MyfController {

    @RequestMapping(value = "findData", method = RequestMethod.GET)
    @ResponseBody
    public List findData(Model map) {
//        map.addAttribute("name", "dsf");
        List<Myf> lst = new ArrayList<>();
        lst.add(new Myf("百度","https://www.baidu.com"));
        lst.add(new Myf("dsf","https://www.baidu3.com"));
        return lst;
    }
    @RequestMapping(value = "findData2", method = RequestMethod.GET)
    @ResponseBody
    public List findData2(Model map) {
//        map.addAttribute("name", "dsf");
        List<Myf> lst = new ArrayList<>();
        lst.add(new Myf("百度2","https://www.baidu.com"));
        lst.add(new Myf("dsf2","https://www.baidu3.com"));
        lst.add(new Myf("d1sf2","https://www.baidu3.com"));
        return lst;
    }
}
