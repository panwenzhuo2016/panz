package com.back.controller;

import com.back.dao.INoteDao;
import com.back.dao.TableNumDao;
import com.back.po.Myf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hasee on 2018/1/7.
 */
@Controller
@RequestMapping("/")
public class TableNumController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @RequestMapping(value = "table", method = RequestMethod.GET)
    public String table() {
        return "page/table";
    }

    @RequestMapping(value = "getTableList", method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String, String>> getTableList(Model map) {
        List<Map<String, String>> r = new ArrayList<>();
        Map<String, String> res;
        List<String> allTables = jdbcTemplate.query("select * from all_tables where owner = 'TRADE_ZS' order by table_name ASC", new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                String table = rs.getString(2);
                return table;
            }
        });
        List<String> zs;
        for (String tableName : allTables) {
            res = new HashMap<>(4);
            try {
                res.put("zl", jdbcTemplate.queryForObject("select count(1) from  " + tableName, Integer.class).toString());
            } catch (Exception e) {

            }
            try {
                res.put("yx", jdbcTemplate.queryForObject("select count(1) from  " + tableName + " where is_deleted = 0", Integer.class).toString());
            } catch (Exception e) {

            }
            try {
                zs = jdbcTemplate.query("select * from all_tab_comments where owner = 'TRADE_ZS' and TABLE_NAME = '" + tableName + "'", new RowMapper<String>() {
                    @Override
                    public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                        String zss = rs.getString(4);
                        return zss;
                    }
                });
                if (zs != null && zs.size() > 0) {
                    res.put("zs", zs.get(0));
                }
            } catch (Exception e) {

            }
            r.add(res);
        }
        return r;
    }
}
