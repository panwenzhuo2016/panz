package com.pwz.sys.service.impl;

import com.google.common.collect.Maps;
import com.pwz.execution.WebExecution;
import com.pwz.sys.dao.SysUserMapper;
import com.pwz.sys.pojo.SysUser;
import com.pwz.sys.service.UserService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 类说明：
 *
 * @Author panwenzhuo
 * @Date created in 2018/9/23 14:37
 **/
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    SysUserMapper sysUserMapper;

    @Override
    public SysUser getUserByName(String username) {
        Map<String,Object> map = Maps.newHashMap();
        map.put("userName",username);
        List<SysUser> list = sysUserMapper.findList(map);
        if(CollectionUtils.isEmpty(list)){
            throw new WebExecution("无人员");
        }
        return list.get(0);
    }
}
