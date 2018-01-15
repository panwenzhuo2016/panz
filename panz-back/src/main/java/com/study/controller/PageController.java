package com.study.controller;

import com.google.common.collect.Maps;
import com.study.po.Classify;
import com.study.po.Note;
import com.study.service.IClassifyService;
import com.study.service.INoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Created by pwz on 2017/11/13.
 */
@Controller
@RequestMapping("/")
public class PageController {

    @Autowired
    IClassifyService classifyService;
    @Autowired
    INoteService noteService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model map) {
        map.addAttribute("name", "dsf");
        return "page/index";
    }

    @RequestMapping(params = "get")
    @ResponseBody
    public Classify get(String id) {
        Classify classify = classifyService.get(id);
        return classify;
    }

    @RequestMapping(params = "getAllClassify")
    @ResponseBody
    public List<Classify> getAllClassify() {
        Map<String, Object> params = Maps.newHashMap();
        List<Classify> classifyList = classifyService.findDataGrid(params);
        for (Classify c : classifyList) {
            params.put("classifyId", c.getId());
            List<Note> noteList = noteService.findDataGrid(params);
            c.setNum(noteList.size());
        }
        return classifyList;
    }

    @RequestMapping(params = "getNoteByClassify")
    @ResponseBody
    public List<Note> getNoteByClassify(String classifyId) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("classifyId", classifyId);
        List<Note> noteList = noteService.findDataGrid(params);
        return noteList;
    }

    @RequestMapping(params = "search")
    @ResponseBody
    public List<Note> search(String searchName) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("title", searchName);
        List<Note> noteList = noteService.findDataGrid(params);
        return noteList;
    }


    @RequestMapping(params = "getNoteById")
    @ResponseBody
    public Note getNoteById(String noteId) {
        Note note = noteService.get(noteId);
        return note;
    }

    @RequestMapping(params = "addClassify")
    @ResponseBody
    public Classify addClassify(Classify classify) {
        classifyService.add(classify);
        return classify;
    }

    @RequestMapping(params = "addNote")
    @ResponseBody
    public Note addNote(Note note) {
        noteService.save(note);
        return note;
    }
    @RequestMapping(params = "deleteClassfy")
    @ResponseBody
    public Classify deleteClassfy(String classifyId) {
        Classify c = classifyService.get(classifyId);
        classifyService.delete(classifyId);
        return c;
    }
    @RequestMapping(params = "deleteNote")
    @ResponseBody
    public Note deleteNote(String noteId) {
        Note n = noteService.get(noteId);
        noteService.delete(noteId);
        return n;
    }
}
