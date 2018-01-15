package com.back.service.impl;


import com.google.common.collect.Maps;
import com.back.dao.IClassifyDao;
import com.back.po.Classify;
import com.back.po.Note;
import com.back.service.IClassifyService;
import com.back.service.INoteService;
import com.util.DateUtils;
import com.util.UuidUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by 潘文卓 on Mon Nov 13 17:00:38 CST 2017.
 */
@Service
public class ClassifyService implements IClassifyService {

    @Autowired
    IClassifyDao classifyDao;
    @Autowired
    INoteService noteService;

    @Override
    public void add(Classify classify) {
        classify.setId(UuidUtil.getUuid());
        classify.setDeleteState('0');
        classifyDao.add(classify);
    }

    @Override
    public void update(Classify classify) {
        classify.setModifyTime(DateUtils.CURR_DATE_STR);
        classify.setCreateTime(null);
        classify.setDeleteState('0');
        classifyDao.update(classify);
    }

    @Override
    public void delete(String objId) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("classifyId", objId);
        List<Note> noteList = noteService.findDataGrid(params);
        for(Note note : noteList){
            note.setClassifyId("12612c15bc6946a083c80eb9aaef8557");
            noteService.update(note);
        }
        classifyDao.delete(objId);
    }

    @Override
    public Classify get(String objId) {
       return classifyDao.getEntityById(objId);
    }

    @Override
    public void save(Classify classify) {
        if(StringUtils.isEmpty(classify.getId())){
            add(classify);
        }else {
            update(classify);
        }
    }

    @Override
    public List<Classify> findDataGrid(Map<String, Object> params) {
        return classifyDao.findDataGrid(params);
    }


}
