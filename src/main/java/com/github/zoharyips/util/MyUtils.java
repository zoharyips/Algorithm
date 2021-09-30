package com.github.zoharyips.util;

import java.util.Random;

/**
 * <h3>大数工具类</h3>
 *
 * @author zohar
 * @version 1.0
 * 2021-10-01 01:36
 */
public class MyUtils {
    private static final Random RANDOM = new Random(System.currentTimeMillis());
    private static final StringBuffer STRING_BUFFER = new StringBuffer();


    /**
     * 生成一个正整型大数
     *
     * 尽量不要超过十万位，否则会很慢
     *
     * @return 正整型大数
     */
    public static String getBigNum() {
        int len = 0;
        while (len == 0) len = RANDOM.nextInt(10_0000);
        STRING_BUFFER.delete(0, STRING_BUFFER.length());
        int first = 0;
        while (first == 0) first = RANDOM.nextInt(10);
        STRING_BUFFER.append(first);
        for (int i = 1; i < len; i++) {
            STRING_BUFFER.append(RANDOM.nextInt(10));
        }
        return STRING_BUFFER.toString();
    }
}
