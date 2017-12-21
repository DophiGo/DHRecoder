package com.example.user.qrrecoder.utils;

import com.hdl.elog.ELog;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by dxs on 2017/12/20.
 */

public class StringUtils {
    public static boolean isEmail(String email) {
        return isMatch(RegexConstants.REGEX_EMAIL, email);
    }

    /**
     * 判断是否匹配正则
     *
     * @param regex 正则表达式
     * @param input 要匹配的字符串
     * @return {@code true}: 匹配<br>{@code false}: 不匹配
     */
    public static boolean isMatch(final String regex, final CharSequence input) {
        return input != null && input.length() > 0 && Pattern.matches(regex, input);
    }

    /**
     * 获取匹配组
     *
     * @param regex 正则表达式
     * @param input 待匹配字符串
     * @param group 取的匹配组
     * @return 匹配的字符串
     */
    public static String getMatcher(final String regex, final CharSequence input, int group) {
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(input);
        while (m.find()) {
           return m.group(1);
        }
        return null;
    }

    public static String getFormat(String src, Object... parems) {
        return String.format(src, parems);
    }
}
