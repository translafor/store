package com.wsq.store.web.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @description:
 * @projectName:store
 * @see:com.wsq.store.web.utils
 * @author:translafor
 * @createTime:2020/11/4 20:32
 * @version:1.0
 */
public class StringHandleUtils {
    public static boolean checkIsMobilePhone(String content) {
        String regex = "^[1][3,4,5,7,8,9][0-9]{9}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(content);
        return matcher.find();
    }
}
