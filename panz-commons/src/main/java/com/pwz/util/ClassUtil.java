package com.pwz.util;

import javax.persistence.Column;
import javax.persistence.Table;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * 类说明：
 *
 * @Author panwenzhuo
 * @Date created in 2018/9/4 18:29
 **/
public class ClassUtil {
    public static void main(String[] args) throws Exception {
        Map<String, String> map = new HashMap<>();
        map.putAll(getMap(HttpUtil.class));
        map.putAll(getMap(MomenyUtil.class));
        map.putAll(getMap(NumUtil.class));
        map.putAll(getMap(ObjectUtil.class));
        map.putAll(getMap(PinYiUtil.class));
        String hql = "select distinct dict from CenterDict dict, SysYeWuFanWeiZhuanYe ywfyZY, SysYeWuFanWei yw, SysUserYeWuFanWei userYW, SysUser user " +
                "where dict.key = ywfyZY.gcLeiXing" +
                "  and ywfyZY.yeWuFanWeiGuid = yw.guid " +
                "  and yw.guid = userYW.yeWuFanWei.guid " +
                "  and userYW.sysUser.userGuid = user.userGuid " +
                "  and dict.isDeleted = 0 " +
                "  and ywfyZY.isDeleted = 0 " +
                "  and yw.isDeleted = 0 " +
                "  and userYW.isDeleted = 0 " +
                "  and user.isDeleted = 0 " +
                "  and dict.typeName = :typeName " +
                "  and user.userGuid = :userGuid" +
                "  order by dict.guid";
        System.out.println(hql);
        String[] split = hql.split(" ");
        for (String s : split) {
            boolean has = false;
            for (Map.Entry<String, String> entry : map.entrySet()) {
                if(s.contains(entry.getKey())){
                    has = true;
                    System.out.print(entry.getValue() + " ");
                    continue;
                }
            }
            if(!has){
                System.out.print(s + " ");
            }

        }


    }

    private static Map<String, String> getMap(Class c) {
        Field[] fields = c.getDeclaredFields();
        Map<String, String> map = new HashMap<>();

        Table table = (Table) c.getAnnotation(Table.class);
        if (table != null) {
            map.put(c.getSimpleName(), table.name());
        }
        for (Field field : fields) {
            Column column = field.getAnnotation(Column.class);
            if (column != null) {
                map.put(field.getName(), column.name());
            }
        }

        return map;
    }
}
