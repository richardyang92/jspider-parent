package com.ry.jspider.core.util;

import java.io.UnsupportedEncodingException;

/**
 * Created by yangyang on 2016/12/25.
 */
public class CharacterUtil {

    public static String gbk2UTF8(String gbkString) throws UnsupportedEncodingException {
        return new String(gbkString.getBytes(), "UTF-8");
    }
}
