package com.pwz.util;


import com.pwz.anno.Length;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.Column;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.GregorianCalendar;
import java.util.Map;

/**
 * 类说明：
 *
 * @Author panwenzhuo
 * @Date created in 2018-5-23 9:57
 **/
public class ObjectUtil {
    private static final String Dou = "Double";
    private static final String Str = "String";
    private static final String Cal = "XMLGregorianCalendar";
    private static final String Boo = "boolean";
    private static final String Int = "Integer";
    private static final String Big = "BigDecimal";
    private static final String Lon = "Long";
    private static final String Sho = "Short";
    private static final String BooB = "Boolean";

    /**
     * map数据 保存到实体
     *
     * @param tClass
     * @param map
     * @return T
     * @author panwenzhuo
     * @date 2018-5-23 11:53
     */
    public static <T> T map2Object(Class<T> tClass, Map<String, Object> map) {
        return map2Object(tClass, map, false);
    }

    /**
     *  map数据 保存到实体
     * @author panwenzhuo
     * @date 2018-6-28 18:06
     * @param tClass
     * @param map
     * @param getTOrF  是否将  0 和 1 转为 flase true ，默认 否
     * @return T
     */
    public static <T> T map2Object(Class<T> tClass, Map<String, Object> map, boolean getTOrF) {
        try {
            T instance = tClass.newInstance();
            Field[] declaredFields = tClass.getDeclaredFields();
            Length length;
            Column column;

            for (Field declaredField : declaredFields) {
                length = declaredField.getAnnotation(Length.class);
                column = declaredField.getAnnotation(Column.class);
                //是否有 Length 注解限制长度
                int limit = 0;
                if (length != null) {
                    limit = length.value();
                }
                String fieldName = declaredField.getName();
                //是否转换 flase true
                boolean t = getTOrF;
                if(getTOrF){
                    if(fieldName.startsWith("is") || fieldName.startsWith("IS")){
                    }else {
                        t = false;
                    }
                }
                Class<?> type = declaredField.getType();
                Method method = null;
                try {
                    method = tClass.getMethod("set" + up(fieldName), type);
                } finally {
                    if(method == null){
                        continue;
                    }
                }
                String typeName = type.getSimpleName();
                //1 直接取
                Object obj = map.get(fieldName);
                if(obj == null){
                    //2 数据库注解取
                    if(column != null){
                        fieldName = column.name().toLowerCase();
                        obj = map.get(fieldName);
                    }
                    //3 自定义规则
                    if(obj == null){
                        obj = map.get(lowSql(fieldName));
                    }
                }
                switch (typeName) {
                    case Dou:
                        method.invoke(instance, dealDouble(deal(dealString(obj, t), limit)));
                        break;
                    case Str:
                        method.invoke(instance, deal(dealString(obj, t), limit));
                        break;
                    case Cal:
                        method.invoke(instance, dealTime(deal(dealString(obj, t), limit)));
                        break;
                    case Boo:
                        method.invoke(instance, new Boolean(dealString(obj, t)));
                        break;
                    case Int:
                        method.invoke(instance, dealInt(dealString(obj, t)));
                        break;
                    case Big:
                        method.invoke(instance, dealBig(dealString(obj, t)));
                        break;
                    case Lon:
                        Long lo;
                        try {
                            lo =  new Long(dealString(obj, t));
                        } catch (Exception e){
                            lo = null;
                        }
                        method.invoke(instance, lo);
                        break;
                    case Sho:
                        Short sh;
                        try {
                            sh =  new Short(dealString(obj, t));
                        } catch (Exception e){
                            sh = null;
                        }
                        method.invoke(instance, sh);
                        break;
                    case BooB:
                        method.invoke(instance, new Boolean(dealString(obj, t)));
                        break;
                    default:
                        System.out.println(method.getName() + "返回值类型： "+ typeName + " 不支持");
                        break;
                }
            }
            return instance;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *  如果是数据库字段， 可能跟实体不一样，处理一下
     * @author panwenzhuo
     * @date 2018-7-19 11:23
     * @param fieldName
     * @return java.lang.String
     */
    private static String lowSql(String fieldName) {
        if(!fieldName.contains("_")){
            return "";
        }
        String r = "";
        for (String s : fieldName.split("_")) {
            r +=  low(s) + "_";
        }
        return r.substring(0,r.length()-1);
    }

    private static Object dealInt(String s) {
        if (StringUtils.isBlank(s)) {
            return new Integer(0);
        }
        return new Integer(s);
    }

    private static BigDecimal dealBig(String s) {
        if (StringUtils.isBlank(s)) {
            return new BigDecimal(0);
        }
        return new BigDecimal(s);
    }


    private static Double dealDouble(Object obj) {
        Double aDouble = 0d;
        if (obj == null) {
            return aDouble;
        }
        try {
            aDouble = Double.valueOf(obj.toString());
        } catch (Exception e) {
        }
        return aDouble;
    }

    private static XMLGregorianCalendar dealTime(Object object) {
        String ob = "0";
        if (object != null && StringUtils.isNotBlank(dealString(object, false))) {
            ob = object.toString();
        }
        Long time = Long.parseLong(ob);
        GregorianCalendar gcal = new GregorianCalendar();
        gcal.setTimeInMillis(time);
        try {
            return DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 很多限长的字段， 超长了取前面的  不够长的 默认后面加0算了，
     *
     * @param str
     * @param number
     * @return java.lang.String
     * @author panwenzhuo
     * @date 2018-5-23 10:18
     */
    private static String deal(String str, int number) {
        if (number == 0) {
            return str;
        }
        if (str.length() > number) {
            return str.substring(0, number);
        }
        String o = "0000000000000000000000000000000000000000000";
        if (str.length() != number) {
            return str + o.substring(0, number - str.length());
        }
        return str;
    }

    public static void main(String[] args) {
        System.out.println(deal("无", 24));
    }

    private static String dealString(Object obj, boolean getTOrF) {
        if (obj == null) {
            return "";
        }
        String r = obj.toString();
        if (getTOrF && StringUtils.isNotBlank(r)) {
            r = getTOrF(r);
        }
        return r;
    }

    public static String up(String s) {
        if (s == null) {
            return "";
        }
        if (s.length() < 2) {
            return s;
        }
        String s1 = s.substring(0, 1);
        String s2 = s.substring(1, s.length());
        return s1.toUpperCase() + s2;
    }
    public static String low(String s) {
        if (s == null) {
            return "";
        }
        if (s.length() < 2) {
            return s;
        }
        String s1 = s.substring(0, 1);
        String s2 = s.substring(1, s.length());
        return s1.toLowerCase() + s2;
    }

    /**
     * 是0 1的转成 TrueOrFalse
     *
     * @param str 输入值 0 1
     * @return TrueOrFalse
     */
    private static String getTOrF(String str) {
        if (StringUtils.isBlank(str)) {
            return "false";
        }
        if ("1".equals(str)) {
            return "true";
        } else if ("0".equals(str)) {
            return "false";
        } else {
            return str;
        }
    }
}
