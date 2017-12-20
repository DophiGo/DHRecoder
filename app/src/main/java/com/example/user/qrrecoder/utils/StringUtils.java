package com.example.user.qrrecoder.utils;

import java.util.regex.Pattern;

/**
 * Created by dxs on 2017/12/20.
 */

public class StringUtils {
    public static boolean isEmail(String email){
        return isMatch(RegexConstants.REGEX_EMAIL,email);
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
}
