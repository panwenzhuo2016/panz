package com.pwz.sys.service;

import com.pwz.sys.pojo.SysUser;

/**
 * 类说明：
 *
 * @Author panwenzhuo
 * @Date created in 2018/9/23 14:36
 **/
public interface UserService {
    SysUser getUserByName(String username);
}
