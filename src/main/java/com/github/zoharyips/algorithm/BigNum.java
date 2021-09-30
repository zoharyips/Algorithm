package com.github.zoharyips.algorithm;

import com.github.zoharyips.util.MyUtils;
import org.apache.commons.lang3.StringUtils;

import java.math.BigInteger;
import java.util.Optional;
import java.util.Random;

/**
 * <h3>大数算法</h3>
 * 实现大数的加减乘除
 *
 * @author zohar
 * @version 1.0
 * 2021-10-01 00:20
 */
public final class BigNum {

    public static void main(String[] args) {
        Random random = new Random(System.currentTimeMillis());
        int times = 0;
        while (times == 0) times = random.nextInt(1000);
        for (int i = 0; i < times; i++) {
            String num1 = MyUtils.getBigNum();
            String num2 = MyUtils.getBigNum();
            Optional<String> optional = add(num1, num2);
            BigInteger bigInteger1 = new BigInteger(num1);
            BigInteger bigInteger2 = new BigInteger(num2);
            if (!optional.isPresent()) {
                System.err.printf("No result: \nnum1: %s\nnum2: %s%n", num1, num2);
                return;
            }
            if (!StringUtils.equals(bigInteger1.add(bigInteger2).toString(), optional.get())) {
                System.err.printf("No match: \nnum1: %s\nnum2: %s%n", num1, num2);
                return;
            }
        }
        System.out.printf("Pass %d tests%n", times);
    }



    public static Optional<String> add(String num1, String num2) {
        if (StringUtils.isBlank(num1) && StringUtils.isBlank(num2))
            return Optional.empty();
        if (StringUtils.isBlank(num1))
            return Optional.of(num2);
        if (StringUtils.isBlank(num2))
            return Optional.of(num1);

        /* 大数相加，结果位数最多是最长一个数的位数加一 */
        StringBuilder resBuilder = new StringBuilder();
        char[] chars1 = num1.toCharArray();
        char[] chars2 = num2.toCharArray();
        int idx1 = chars1.length - 1;
        int idx2 = chars2.length - 1;
        int flag = 0, tmp;
        for (; idx1 > -1 && idx2 > -1; flag = tmp / 10, idx1--, idx2--) {
            tmp = chars1[idx1] - '0' + chars2[idx2] - '0' + flag;
            resBuilder.append(tmp % 10);
        }
        for (; idx1 > -1; flag = tmp / 10, idx1--) {
            tmp = chars1[idx1] - '0' + flag;
            resBuilder.append(tmp % 10);
        }
        for (; idx2 > -1; flag = tmp / 10, idx2--) {
            tmp = chars2[idx2] - '0' + flag;
            resBuilder.append(tmp % 10);
        }
        if (flag == 1)
            resBuilder.append(1);

        return Optional.of(resBuilder.reverse().toString());
    }

}
