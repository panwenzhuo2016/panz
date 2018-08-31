package com.gupiao.service;

import com.gupiao.pojo.GuPiao;
import com.util.PageBean;

import java.util.List;

/**
 * 类说明：
 *
 * @Author panwenzhuo
 * @Date created in 2018/8/19 22:53
 **/
public interface GuPiaoService {
    PageBean<GuPiao> findByPage(int currentPage, int pageSize);
}
