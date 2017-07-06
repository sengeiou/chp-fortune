package com.creditharmony.fortune.utils;

import java.util.Random;

import com.creditharmony.common.util.DateUtils;

/**
 * 生成20位随机码
 * @Class Name SerialNum
 * @author 胡体勇
 * @Create In 2016年3月14日
 */
public class SerialNum {
	
	  /**
     * 根据传入的数字， 产生 【年月日时分秒毫秒+随机数】.
     * 格式：yyyyMMddHHmmssSSS + '-' + 四位随机数
     * @return 20位 随机数
     */
    public static  String getSerialNum() {
        Random random = new Random();
        String sRand = "";
        for (int i = 0; i < 2; i++) {
            String rand = String.valueOf(random.nextInt(10));
            sRand += rand;
        }
        for (int i = 0; i < 2; i++) {
            random = new Random();
            String rand = String.valueOf(random.nextInt(10));
            sRand += rand;
        }
        
        StringBuilder randNoSb = new StringBuilder();
        randNoSb.append(DateUtils.getDate("yyyyMMddHHmmssSSS"))
                .append("-")
                .append(sRand);
        
        return randNoSb.toString();
    }
    
}
