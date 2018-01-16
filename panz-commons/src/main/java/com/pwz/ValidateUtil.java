package com.pwz;

import java.util.Collection;
import java.util.Map;
import java.util.regex.Pattern;

/**********************************************************
 * Copyright ? 2014，福建亿同世纪软件有限公司
 * All Rights Reserved.
 *
 * 文件名称： ValidateUtil.java
 * 摘    要： [简要描述本文件的内容]
 *
 * 初始版本：1.0.0
 * 原 作 者：panwz
 * 完成日期： 2017年1月14日 下午12:32:48 
	
 * 当前版本： 1.0.0
 * 作    者： panwz
 * 完成日期： 2017年1月14日 下午12:32:48 
 * 
 *****************************************************************/
public final class ValidateUtil {

	/**
     * 整型为空.
     */
    public static final Integer INTEGER_NULL = -2137971323;
    
    /**
     * 私有构造.
     */
    private ValidateUtil() {
        
    }
    
    /**
     * 是否不为空.
     * 
     * @param s
     *            需要判断的字符串
     * @return 是否为空
     */
    public static boolean isNotNull(String s) {
        return s != null;
    }
    
    /**
     * 是否为空.
     * 
     * @param s
     *            需要判断的字符串
     * @return 是否为空
     */
    public static boolean isNull(String s) {
        return s == null;
    }
    
    /**
     * 是否为true.
     * 
     * @param b
     *            需要判断的boolean值
     * @return 真或假
     */
    public static boolean isTrue(Boolean b) {
        return b != null && b;
    }
    
    /**
     * 是否为false.
     * 
     * @param b
     *            需要判断的boolean值
     * @return 真或假
     */
    public static boolean isFalse(Boolean b) {
        return b == null || !b;
    }
    
    /**
     * 是否不为空.
     * 
     * @param s
     *            需要判断的字符串
     * @return 真或假
     */
    public static boolean isNotEmpty(String s) {
        return s != null && !s.equals("");
    }
    
    /**
     * 是否为空.
     * 
     * @param s
     *            需要判断的字符串
     * @return 真或假
     */
    public static boolean isEmpty(String s) {
        return s == null || s.equals("");
    }
    
    /**
     * 是否不为空.
     * 
     * @param i
     *            需要判断的字符串
     * @return 真或假
     */
    public static boolean isNotEmpty(Integer i) {
        return i != null && i != INTEGER_NULL;
    }
    
    /**
     * 是否为空.
     * 
     * @param i
     *            需要判断的Integer值
     * @return 真或假
     */
    public static boolean isEmpty(Integer i) {
        return i == null || i == 0;
    }
    
    /**
     * 是否不为空.
     * 
     * @param l
     *            需要判断的Long值
     * @return 真或假
     */
    public static boolean isNotEmpty(Long l) {
        return l != null && l != 0L;
    }
    
    /**
     * 是否为空 .
     * 
     * @param l
     *            需要判断的Long值
     * @return 真或假
     */
    public static boolean isEmpty(Long l) {
        return l == null || l == 0L;
    }
    
    /**
     * 列表是否不为空.
     * 
     * @param l
     *            需要判断的集合
     * @return 真或假
     */
    public static boolean isNotEmpty(Collection<?> l) {
        return l != null && l.size() > 0;
    }
    
    /**
     * 列表是否为空.
     * 
     * @param l
     *            需要判断的集合
     * @return 真或假
     */
    public static boolean isEmpty(Collection<?> l) {
        return l == null || l.size() == 0;
    }
    
    /**
     * map是否不为空.
     * 
     * @param m
     *            需要判断map
     * @return 真或假
     */
    public static boolean isNotEmpty(Map<?, ?> m) {
        return m != null && m.size() > 0;
    }
    
    /**
     * map是否为空 .
     * 
     * @param m
     *            需要判断的map
     * @return 真或假
     */
    public static boolean isEmpty(Map<?, ?> m) {
        return m == null || m.size() == 0;
    }
    
    /**
     * 最小长度，大于等于 .
     * 
     * @param s
     *            需要判断的字符串
     * @param length
     *            最小长度
     * @return 真或假
     */
    public static boolean minLength(String s, int length) {
        return isNull(s) || s.length() >= length;
    }
    
    /**
     * 最大长度，小于等于 .
     * 
     * @param s
     *            需要判断的字符串
     * @param length
     *            最大长度
     * @return 真或假
     */
    public static boolean maxLength(String s, int length) {
        return isNull(s) || s.length() <= length;
    }
    
    /**
     * 检查email输入是否正确 , 正确的书写格式为 username@domain.
     * 
     * @param s
     *            需要判断的邮箱
     * @return 真或假
     */
    public static boolean validateEmail(String s) {
        return isEmpty(s)
            || s.matches("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a"
                + "-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");
    }
    
    /**
     * 是否是日期 .
     * 
     * @param s
     *            需要判断的字符串
     * @return 真或假
     */
    public static boolean isDate(String s) {
        return isEmpty(s)
            || s.matches("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))"
                + "[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|"
                + "(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))"
                + "[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))"
                + "[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s("
                + "(([0-2]?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");
    }
    
    /**
     * 检查电话输入是否正确, 正确格式 012-87654321、0123-87654321、0123－7654321.
     * 
     * @param s
     *            需要判断的字符串
     * @return 真或假
     */
    public static boolean isTel(String s) {
        return isEmpty(s)
            || s.matches("^(([0\\+]\\d{2,3}-)?(0\\d{2,3})-)?(\\d{7,8})(-(\\d{3,}))?$");
    }
    
    /**
     * 检查手机输入是否正确 .
     * 
     * @param s
     *            需要判断的字符串
     * @return 真或假
     */
    public static boolean isMobile(String s) {
        return isEmpty(s) || s.matches("^[1][3,4,5,8]\\d{9}");
    }
    
    /**
     * 检查URL是否合法.
     * 
     * @param s
     *            需要判断的字符串
     * @return 真或假
     */
    public static boolean isUrl(String s) {
        return isEmpty(s)
            || s.matches("((http[s]{0,1}|ftp):\\/\\/)?[a-zA-Z0-9\\.\\-]+\\.([a-zA-Z]{2,4})(:\\d+)?("
                + "/[a-zA-Z0-9\\.\\-~!@#$%^&amp;*+?:_/=<>]*)?");
    }
    
    /**
     * 检查IP是否合法.
     * 
     * @param s
     *            需要判断的字符串
     * @return 真或假
     */
    public static boolean isIP(String s) {
        return isEmpty(s)
            || s.matches("\\b((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d"
                + "\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d"
                + "|2[0-4]\\d|25[0-5])\\b");
        
    }
    
    /**
     * 检查QQ是否合法，必须是数字，且首位不能为0，最长15位.
     * 
     * @param s
     *            需要判断的字符串
     * @return 真或假
     */
    public static boolean isQQ(String s) {
        return isEmpty(s) || s.matches("[1-9][0-9]{4,13}");
    }
    
    /**
     * 检查邮编是否合法 .
     * 
     * @param s
     *            需要判断的字符串
     * @return 真或假
     */
    public static boolean isPostCode(String s) {
        return isEmpty(s) || s.matches("[1-9]\\d{5}(?!\\d)");
    }
    
    /**
     * 检查身份证是否合法,15位或18位.
     * 
     * @param s
     *            需要判断的字符串
     * @return 真或假
     */
    public static boolean isIDCard(String s) {
        return isEmpty(s)
            || (s.matches("^(\\d{18,18}|\\d{15,15}|\\d{17,17}[x|X])$") && s
                .matches("^(\\d{6})(19|20"
                    + ")?(\\d{2})(0[1-9]|1[0-2])(0[1-9]|[1-2]\\d|3[0-1])(\\d{3})(\\d|x|X)?$"));
    }
    
    /**
     * 是否包含英文字母.
     * 
     * @param s
     *            需要判断的字符串
     * @return 真或假
     */
    public static boolean isContainLetter(String s) {
        return isEmpty(s) || Pattern.compile("[a-zA-Z]").matcher(s).find();
    }
    
    /**
     * 是否包含数字 .
     * 
     * @param s
     *            需要判断的字符串
     * @return 真或假
     */
    public static boolean isContainDigit(String s) {
        return isEmpty(s) || Pattern.compile("[0-9]").matcher(s).find();
    }
    
    /**
     * 是否包含中文 .
     * 
     * @param s
     *            需要判断的字符串
     * @return 真或假
     */
    public static boolean isContainChinese(String s) {
        return isEmpty(s)
            || Pattern.compile("[\u4e00-\u9fa5]").matcher(s).find();
    }
    
    /**
     * 是否是中文 .
     * 
     * @param s
     *            需要判断的字符串
     * @return 真或假
     */
    public static boolean isChinese(String s) {
        return isEmpty(s) || s.matches("[\u4e00-\u9fa5]+");
    }
    
    /**
     * 是否是数字 .
     * 
     * @param s
     *            需要判断的字符串
     * @return 真或假
     */
    public static boolean isDigit(String s) {
        return isEmpty(s) || s.matches("^\\d*$");
    }
    
    /**
     * 
     * 验证登录号.
     * 
     * @param s 需要判断的字符串
     * @return 真或假
     */
    public static boolean isLoginName(String s) {
    	return isNotEmpty(s) && s.matches("^[0-9a-zA-Z_]{1,}$");
    }
    
    /**
     * 
     * 验证登录密码.
     * 
     * @param s 需要判断的字符串
     * @return 真或假
     */
    public static boolean isLoginPwd(String s) {
    	return isNotEmpty(s) && s.matches("^[A-Za-z0-9\\!\\#\\$\\%\\^\\&\\*\\.\\~\\?\\@]{1,}$");
    }
  
}

