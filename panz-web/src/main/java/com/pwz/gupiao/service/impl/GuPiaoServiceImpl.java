package com.pwz.gupiao.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pwz.aop.MethodLog;
import com.pwz.gupiao.dao.GuPiaoMapper;
import com.pwz.gupiao.pojo.GuPiao;
import com.pwz.gupiao.service.GuPiaoService;
import com.pwz.myGenerator.UUID;
import com.pwz.util.PageBean;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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

    @Resource
    private CacheManager cacheManager;


    @Override
    @MethodLog
    public PageBean<GuPiao> findByPage(int currentPage, int pageSize) {
        //设置分页信息，分别是当前页数和每页显示的总记录数【记住：必须在mapper接口中的方法执行之前设置该分页信息】
        Page<GuPiao> page = PageHelper.startPage(currentPage, pageSize);
//        page(currentPage, pageSize);
        Map<String,Object> map = new HashMap<>(16);
        map.put("md","md");
        Cache cache = cacheManager.getCache("lemonCache");
        if( cache.get("findByPage"+currentPage+pageSize) != null){
            System.out.println(cache.get("findByPage"+currentPage+pageSize).get());
            List<GuPiao> allItems = (List<GuPiao>) cache.get("findByPage"+currentPage+pageSize).get();
            PageBean<GuPiao> pageData = new PageBean<>(currentPage, pageSize,  (int)cache.get("findByPageint"+currentPage+pageSize).get());
            pageData.setItems(allItems);
            return pageData;
        }
        List<GuPiao> allItems = guPiaoMapper.findList(map);        //全部商品
        cache.put("findByPage"+currentPage+pageSize,allItems);
        cache.put("findByPageint"+currentPage+pageSize,(int)page.getTotal());


//        List<GuPiao> allItems = guPiaoMapper.findAll();        //全部商品
//        allItems = allItems.subList(0,10);

//        PageInfo<GuPiao> page = new PageInfo<>(allItems);

//        int countNums = guPiaoMapper.count(new HashMap<>());            //总记录数
        PageBean<GuPiao> pageData = new PageBean<>(currentPage, pageSize, (int)page.getTotal());
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
