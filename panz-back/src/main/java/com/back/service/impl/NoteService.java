package com.back.service.impl;


import com.back.dao.INoteDao;
import com.back.po.Note;
import com.back.service.INoteService;
import com.util.DateUtils;
import com.util.UuidUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by 潘文卓 on Mon Nov 13 17:11:00 CST 2017.
 */
@Service
public class NoteService implements INoteService {

    @Autowired
    INoteDao noteDao;

    @Override
    public void add(Note note) {
        note.setId(UuidUtil.getUuid());
        note.setDeleteState('0');
        noteDao.add(note);
    }

    @Override
    public void update(Note note) {
        note.setModifyTime(DateUtils.CURR_DATE_STR);
        note.setCreateTime(null);
        note.setDeleteState('0');
        noteDao.update(note);
    }

    @Override
    public void delete(String objId) {
        noteDao.delete(objId);
    }

    @Override
    public Note get(String objId) {
       return noteDao.getEntityById(objId);
    }

    @Override
    public void save(Note note) {
        if(StringUtils.isEmpty(note.getId())){
            add(note);
        }else {
            update(note);
        }
    }

    @Override
    public List<Note> findDataGrid(Map<String, Object> params) {
        return noteDao.findDataGrid(params);
    }


}
