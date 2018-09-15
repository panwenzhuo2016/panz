package com.pwz.gupiao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pwz.aop.MethodLog;
import com.pwz.gupiao.dao.GuPiaoMapper;
import com.pwz.gupiao.pojo.GuPiao;
import com.pwz.gupiao.service.GuPiaoService;
import com.pwz.myGenerator.UUID;
import com.pwz.util.PageBean;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 类说明：
 *
 * @Author panwenzhuo
 * @Date created in 2018/8/19 22:53
 **/
@Service
public class GuPiaoServiceImpl implements GuPiaoService {

    @Autowired
    GuPiaoMapper guPiaoMapper;

    @Override
    @MethodLog
    public PageBean<GuPiao> findByPage(int currentPage, int pageSize) {
        //设置分页信息，分别是当前页数和每页显示的总记录数【记住：必须在mapper接口中的方法执行之前设置该分页信息】
        PageHelper.startPage(currentPage, pageSize);
//        page(currentPage, pageSize);
        Map<String,Object> map = new HashMap<>(16);
        map.put("md","md");

        List<GuPiao> allItems = guPiaoMapper.findList(map);        //全部商品
//        List<GuPiao> allItems = guPiaoMapper.findAll();        //全部商品

        PageInfo<GuPiao> page = new PageInfo<>(allItems);

        int countNums = guPiaoMapper.count(new HashMap<>());            //总记录数
        PageBean<GuPiao> pageData = new PageBean<>(currentPage, pageSize, countNums);
        pageData.setItems(allItems);
        return pageData;
    }

    @Override
    public GuPiao addorUpdate(GuPiao guPiao) {
        if(StringUtils.isBlank(guPiao.getId()) || guPiaoMapper.selectByPrimaryKey(guPiao.getId())== null){
            guPiao.setId(UUID.get());
            guPiaoMapper.insertSelective(guPiao);
        }else {
            guPiaoMapper.updateByPrimaryKey(guPiao);
        }
        return guPiao;
    }

    private void page(int currentPage, int pageSize) {
        Map<String,Object> map = new HashMap<>();
        map.put("currentPage",currentPage);
        map.put("pageSize",pageSize);
    }
}
