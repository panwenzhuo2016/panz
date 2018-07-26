package com.back.controller;

import com.back.po.Myf;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by hasee on 2018/1/7.
 */
@Controller
@RequestMapping("myf")
public class MyfController {

    @RequestMapping(value = "findData", method = RequestMethod.GET)
    @ResponseBody
    public List findData(Model map) {
        map.addAttribute("name", "dsf");
        List<Myf> lst = new ArrayList<Myf>();
        lst.add(new Myf("日历","https://www.baidu.com/s?ie=UTF-8&wd=%E6%97%A5%E5%8E%86"));
        lst.add(new Myf("百度","https://www.baidu.com"));
//        lst.add(new Myf("ad","https://www.baidu2.com"));
//        lst.add(new Myf("sdf","https://www.baidu1.com"));
        return lst;
    }

    @RequestMapping(value = "uuid", method = RequestMethod.GET)
    @ResponseBody
    public List uuid(Model map) {
        List<String> lst = new ArrayList<String>();
        String uuid = UUID.randomUUID().toString();
        lst.add(uuid);
        lst.add(uuid.replaceAll("-",""));
        return lst;
    }
}
