package io.github.talelin.latticy.common.util;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class CommonUtil {

    /**
     * 生成编号
     *
     * @param prefix 前缀
     * @Author: Wangxb
     * @Date: 2021/1/23
     **/
    public static String getIdNumber(String prefix) {
        SimpleDateFormat sfDate = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        //得到17位时间如：20170411094039080
        String strDate = sfDate.format(new Date());
        String substring = strDate.substring(8);
        //为了防止高并发重复,再获取1个随机数
        String random = getRandom620(1);
        return prefix + substring + random;
    }

    /**
     * 判断数组字符中是否存在 某一个字符串
     * @param arrary
     * @return
     */
    public static Boolean contains(String[] arrary,String s) {
        for(int i=0;i<arrary.length;i++) {
            if (arrary[i].equals(s)) {//循环查找字符串数组中的每个字符串中是否包含所有查找的内容
                return true;//查找到了就返回真，不在继续查询
            }
        }
            return false;
    }
    /**
     * 获取6-10 的随机位数数字
     *
     * @param length 想要生成的长度
     * @return result
     */
    public static String getRandom620(Integer length) {
        String result = "";
        Random rand = new Random();
        int n = 20;
        if (null != length && length > 0) {
            n = length;
        }
        int randInt = 0;
        for (int i = 0; i < n; i++) {
            randInt = rand.nextInt(10);
            result += randInt;
        }
        return result;
    }

    /**
     *除数 / 被除数 =reslut
     * @param Divisor   除数
     * @param dividend  被除数
     * @param remainder 余数位数
     * @return
     */
    public static  BigDecimal getDivide(Integer Divisor, Integer dividend, int remainder) {
        BigDecimal result = new BigDecimal(Divisor).divide(new BigDecimal(dividend), remainder, BigDecimal.ROUND_HALF_UP);
        return result;
    }
}
