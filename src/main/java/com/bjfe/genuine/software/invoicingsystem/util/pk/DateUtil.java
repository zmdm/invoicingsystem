package com.bjfe.genuine.software.invoicingsystem.util.pk;

/**
 * Created by Administrator on 2017/10/23.
 */

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 获取时间工具类
 */
public class DateUtil {
    /**
     * 获取时间戳
     * @return
     */
    public static String getTimeStamp(){
        return setTime("yyyyMMddHHmmss");
    }

    /**
     * 获取时间
     * 格式：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String getTime(){
        return setTime("yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 获取时间
     * 格式：yyyy-MM-dd
     * @return
     */
    public static String getTime2(){
        return setTime("yyyy-MM-dd");
    }
    /**
     * 获取时间或时间戳
     * @param dataFormat 格式：yyyy-MM-dd HH:mm:ss、yyyy-MM-dd 等等
     * @return
     */
    public static String setTime(String dataFormat){
        Date date =new Date();
        SimpleDateFormat sdf=new SimpleDateFormat(dataFormat);
        String str=sdf.format(date);
        return str;
    }
    public static void main(String []args){
       System.out.println(getTime2());
    }
}
