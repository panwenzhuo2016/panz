package test.vo;

import com.pwz.tools.poi.Execl;

import java.util.ArrayList;
import java.util.List;

/**
 * 类说明：
 *
 * @Author panwenzhuo
 * @Date created in 2018/8/11 12:57
 **/
public class T {
    public static void main(String[] args) {
        Execl execl = new Execl();
        execl.createSheet("ddd");
        execl.setTitle("wewewe",4);
        execl.setSubTitle("sdfafds");
        execl.setColumnName("a", "b", "c", "b", "c");
        List<User> users = new ArrayList<>();
        users.add(new User("34","sdf","sdf","eeee"));
        users.add(new User("34","sdf","sdf","eeee"));
        users.add(new User("34","sdf","sdf","eeee"));
        users.add(new User("34","sdf","sdf","eeee"));
        users.add(new User("34","sdf","sdf","eeee"));
        int i=3;
        for (User user : users) {
            execl.setColumn(i,user.getId(),user.getName(),user.getPhone(),user.getSex());
            i++;
        }
        execl.write("e:\\学生.xls");


    }
}
