package com.pwz.gupiao.service;

import com.pwz.gupiao.pojo.GuPiao;
import com.pwz.util.PageBean;

/**
 * 类说明：
 *
 * @Author panwenzhuo
 * @Date created in 2018/8/19 22:53
 **/
public interface GuPiaoService {
    PageBean<GuPiao> findByPage(int currentPage, int pageSize);
}
