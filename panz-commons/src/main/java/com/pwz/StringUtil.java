package com.pwz;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**********************************************************
 * Copyright ? 2014，福建亿同世纪软件有限公司
 * All Rights Reserved.
 *
 * 文件名称： StringUtil.java
 * 摘    要： [简要描述本文件的内容]
 *
 * 初始版本：1.0.0
 * 原 作 者：panwz
 * 完成日期： 2017年1月14日 下午12:26:45 
	
 * 当前版本： 1.0.0
 * 作    者： panwz
 * 完成日期： 2017年1月14日 下午12:26:45 
 * 
 *****************************************************************/
public final class StringUtil {

	/**
	 * 空字符串.
	 */
	public static final String EMPTY_STRING = "";
	
	/**
	 * 私有构造函数.
	 */
	private StringUtil(){
		
	}
	
	 /**
     * 传入str字符串如（test,test,test）转化为sql中in操作的字符串如（'test','test','test'）。
     * 
     * @param str
     *            需要转换的字符串
     * @return 转换后的字符串
     */
    protected static String changeToInStatus(String str) {
        String resultStr = "";
        if (str != null && !str.equals("")) {
            if (str.contains(",")) {
                String[] strs = str.split(",");
                for (int i = 0; i < strs.length; i++) {
                    if (resultStr.equals("")) {
                        resultStr = "'" + strs[i] + "'";
                    } else {
                        resultStr = resultStr + ",'" + strs[i] + "'";
                    }
                }
            } else {
                resultStr = "'" + str + "'";
            }
        }
        return resultStr;
    }
    
    /**
     * 获取字符串最前面的字母.
     * 
     * @param strSource
     *            需要转换的字符串
     * @return 获取到的字母
     */
    public static String getFirstChar(String strSource) {
        String strReturn = "";
        
        char[] charSource = strSource.toCharArray();
        for (int i = 0; i < charSource.length; i++) {
            if (Pattern.compile("[a-zA-Z]+")
                .matcher(String.valueOf(charSource[i])).find()) {
                strReturn += charSource[i];
                break;
            }
        }
        return strReturn;
    }
    
    /**
     * 传入strs数组转化为逗号隔开的字符串.
     * 
     * @param strs
     *            原字符串
     * @return result_str
     *         转换后的字符串
     */
    public static String changeToStr(String[] strs) {
        String resultStr = "";
        for (int i = 0; i < strs.length; i++) {
            if (resultStr.equals("")) {
                resultStr = strs[i];
            } else {
                resultStr = resultStr + "," + strs[i];
            }
        }
        return resultStr;
    }
    
    /**
     * 对传入字串中的左边部分的指定的字符进行清除。<br>
     * 如：字串",23423,23,43,56,56,",使用""替换左边部分的",",新的字串为"23423,23,43,56,56,"。
     * 
     * @param str
     *            原字符串
     * @param sourceChar
     *            原字符，即要被清除的字串
     * @return 清除非法字后的字串
     */
    public static String leftTrim(String str, char sourceChar) {
        if (str.length() > 0 && sourceChar == (str.substring(0, 1)).charAt(0)) {
            str = str.substring(1, str.length());
            // 进行递归清除，直到不存在要清除的字符为止
            str = StringUtil.leftTrim(str, sourceChar);
        }
        return str;
    }
    
    /**
     * 对传入字串中的右边部分的指定的字符进行清除。<br>
     * 如：字串",23423,23,43,56,56,",使用""替换左边部分的",",新的字串为",23423,23,43,56,56"。
     * 
     * @param str
     *            原字符串
     * @param sourceChar
     *            原字符，即要被清除的字串
     * @return 清除非法字后的字串
     */
    public static String rightTrim(String str, char sourceChar) {
        int length = str.length();
        if (length > 0
            && sourceChar == (str.substring(length - 1, length)).charAt(0)) {
            str = str.substring(0, length - 1);
            // 进行递归清除，直到不存在要清除的字符为止
            str = StringUtil.rightTrim(str, sourceChar);
        }
        return str;
    }
    
    /**
     * 去除左右非法字串。<br>
     * 如：字串",23423,23,43,56,56,",使用""替换",",新的字串为"23423,23,43,56,56"。
     * 
     * @param str
     *            原字符串
     * @param sourceChar
     *            原字符，即要被清除的字串
     * @return 清除非法字后的字串
     */
    public static String trim(String str, char sourceChar) {
        // 先进行左清除
        str = leftTrim(str, sourceChar);
        // 再进行右清除
        str = rightTrim(str, sourceChar);
        
        return str;
        
    }
    
    /**
     * 分割字符串.
     * 
     * @param str
     *            要分割的字符串
     * @param spilitSign
     *            字符串的分割标志
     * @return 分割后得到的字符串数组
     */
    public static String[] stringSplit(String str, String spilitSign) {
        String[] spilitString = str.split(spilitSign);
        if (spilitString[0].equals("")) {
            String[] newString = new String[spilitString.length - 1];
            for (int i = 1; i < spilitString.length; i++) {
                newString[i - 1] = spilitString[i];
            }
            return newString;
        } else {
            return spilitString;
        }
    }
    
    /**
     * 用特殊的字符连接字符串。
     * 
     * @param strings
     *            要连接的字符串数组
     * @param spilitSign
     *            连接字符
     * @return 连接字符串
     */
    public static String stringConnect(String[] strings, String spilitSign) {
        String str = "";
        for (int i = 0; i < strings.length; i++) {
            str += strings[i] + spilitSign;
        }
        return str;
    }
    
    /**
     * 替换字符串中要替换的所有字符串.
     * 
     * @param s
     *            源字符串
     * @param oldChar
     *            需要被替换的字符串
     * @param newChar
     *            需要替换成的字符串
     * @return 替换后的字符串
     */
    public static String replaceAll(String s, String oldChar, String newChar) {
        String s2 = s;
        if (s != null) {
            while (s2.indexOf(oldChar) != -1) {
                s2 = s2.replace(oldChar, newChar);
            }
        }
        return s2;
    }
    
    /**
     * 根据判断是否匹配源字符串.
     * 
     * @param sourceStr
     *            源数据
     * @param regex
     *            正则
     * @return 是否匹配
     */
    public static boolean matcherString(String sourceStr, String regex) {
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(sourceStr);
        if (m.matches()) {
            return true;
        }
        return false;
    }
    
    /**
     * 根据判断是否null,如果为null返回 emptyString,不为null，返回原值.
     * 
     * @param str
     *            源数据
     * @return 判断后的字符串
     */
    public static String defaultString(String str) {
        if (str != null) {
            return str;
        } else {
            return "";
        }
    }
    
    /**
     * 判断数据源中是否包含某个字符串.
     * 
     * @param source
     *            数据源数组
     * @param target
     *            对象字符串
     * @return 是否含有
     */
	public static boolean contain(String[] source, String target) {
        if (null == target || target.equals("") || null == source
            || 0 == source.length) {
            return false;
        }
        for (String str : source) {
            if (str.trim().toUpperCase().equals(target.trim().toUpperCase())) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * 删除word标签.
     * 
     * @param htmlStr
     *            字符串
     * @return 格式化后的字符串
     */
    public static String delWordTag(String htmlStr) {
        String regExScript = "<script[^>]*?>[\\s\\S]*?<\\/script>"; // 定义script的正则表达式
        Matcher matcherScript = Pattern.compile(regExScript,
            Pattern.CASE_INSENSITIVE).matcher(htmlStr);
        htmlStr = matcherScript.replaceAll(""); // 过滤script标签
        
        String regExStyle = "<style[^>]*?>[\\s\\S]*?<\\/style>"; // 定义style的正则表达式
        Matcher matcherStyle = Pattern.compile(regExStyle,
            Pattern.CASE_INSENSITIVE).matcher(htmlStr);
        htmlStr = matcherStyle.replaceAll(""); // 过滤style标签
        
        String regExWord = "(<w\\:worddocument[^>]*?>[\\s\\S]*?<\\/w\\:worddocument>)|"
            + "(<w\\:latentstyles[^>]*?>[\\s\\S]*?<\\/w\\:latentstyles>)"; // 定义word的正则表达式
        Matcher matcherWord = Pattern.compile(regExWord,
            Pattern.CASE_INSENSITIVE).matcher(htmlStr);
        htmlStr = matcherWord.replaceAll(""); // 过滤style标签
        
        String regExWord2 = "<\\!\\-\\-\\[[^>]*?\\]>[\\s\\S]*?<\\!\\[[^>]*?\\]\\-\\->";
        Matcher matcherWord2 = Pattern.compile(regExWord2,
            Pattern.CASE_INSENSITIVE).matcher(htmlStr);
        return matcherWord2.replaceAll("");
    }
    
    /**
     * 替换字符串中所有的IP地址为目标IP地址.
     * 
     * @param sourceStr
     *            源字符串
     * @param targetIpStr
     *            目标IP地址
     * @return 替换后的字符串
     */
    public static String replaceIpAddr(String sourceStr, String targetIpStr) {
        sourceStr = sourceStr.replaceAll("localhost", "127.0.0.1");
        String regexStr = ".*(\\d{3}(\\.\\d{1,3}){3}).*";
        String sourceIpStr = sourceStr.replaceAll(regexStr, "$1");
        if (sourceIpStr.equals(sourceStr)) {
            return sourceStr;
        }
        return sourceStr.replace(sourceIpStr, targetIpStr);
    }
    
    /**
     * 删除html标签.
     * 
     * @param htmlStr
     *            字符串
     * @return 格式化后的字符串
     */
    public static String delHTMLTag(String htmlStr) {
        if (htmlStr == null) {
            return htmlStr;
        }
        htmlStr = delWordTag(htmlStr);
        
        String regExHtml = "<[^>]+>"; // 定义HTML标签的正则表达式
        
        Matcher matcherHtml = Pattern.compile(regExHtml,
            Pattern.CASE_INSENSITIVE).matcher(htmlStr);
        htmlStr = matcherHtml.replaceAll(""); // 过滤html标签
        
        htmlStr = replaceAll(htmlStr, "&ensp;", "");
        htmlStr = replaceAll(htmlStr, "&emsp;", "");
        htmlStr = replaceAll(htmlStr, "&nbsp;", "");
        htmlStr = replaceAll(htmlStr, "&lt;", "");
        htmlStr = replaceAll(htmlStr, "&gt;", "");
        htmlStr = replaceAll(htmlStr, "&amp;", "");
        htmlStr = replaceAll(htmlStr, "&quot;", "");
        htmlStr = replaceAll(htmlStr, "&copy;", "");
        htmlStr = replaceAll(htmlStr, "&reg;", "");
        htmlStr = replaceAll(htmlStr, "™", "");
        htmlStr = replaceAll(htmlStr, "&times;", "");
        htmlStr = replaceAll(htmlStr, "&divide;", "");
        
        htmlStr = replaceAll(htmlStr, "\n", "");
        htmlStr = replaceAll(htmlStr, "\r", "");
        htmlStr = replaceAll(htmlStr, "\t", "");
        return htmlStr.trim(); // 返回文本字符串
    }
}

