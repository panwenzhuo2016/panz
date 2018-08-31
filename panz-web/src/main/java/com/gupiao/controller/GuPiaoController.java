package com.gupiao.controller;

import com.back.po.Myf;
import com.gupiao.dao.GuPiaoMapper;
import com.gupiao.pojo.GuPiao;
import com.gupiao.service.GuPiaoService;
import com.util.PageBean;
import com.util.TableDate.Table;
import com.util.TableDate.TableTitle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 类说明：
 *
 * @Author panwenzhuo
 * @Date created in 2018/8/19 14:58
 **/
@Controller
@RequestMapping("gupiao")
public class GuPiaoController {
    @Autowired
    GuPiaoService guPiaoService;

    @RequestMapping(value = "findData", method = RequestMethod.GET)
    @ResponseBody
    public Table findData(Model map,String page,String pageSize) {
        Table table = new Table();
        List<TableTitle> columns1 = new ArrayList<>();
        columns1.add(new TableTitle("名字", "mingZi"));
        columns1.add(new TableTitle("代码", "daiMa"));
        columns1.add(new TableTitle("当前价格", "dangQianJiaGe"));
        table.setColumns1(columns1);
        PageBean<GuPiao> datas = guPiaoService.findByPage(Integer.valueOf(page),Integer.valueOf(pageSize));
//        List<GuPiao> datas = new ArrayList<>();
//        GuPiao d = guPiaoMapper.selectByPrimaryKey("aeeada51e48c4542927b73c72b60f7ce");
//        datas.add(d);
        table.setData1(datas.getItems());
        table.setTotal(datas.getTotalNum());

        return table;
    }

}
