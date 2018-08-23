package com.bjfe.genuine.software.invoicingsystem.util.code;

/**
 * Created by Administrator on 2017/12/29.
 */

/**
 * 编码工具类
 * 编写人：宋超洋
 */
public class CodeUtil {
    /**
     * 生成人员信息编码
     * @param count
     * @param code
     * @return
     */
    public static String psndocCode(String count,String code){
        return jobCode(count,code);
    }

    /**
     * 生成软件类型编码
     * @param count
     * @return
     */
    public static String softTypeCode(String count){
        int i = Integer.parseInt(count);
        return menuCode(i);
    }

    /**
     * 生成软件信息编码
     * @param count
     * @return
     */
    public static String softInfoCode(String count,String code){
        return jobCode(count,code);
    }
    /**
     * 生成部门编码
     * @return
     */
    public static String deptCode(String count,String code){
        int i=0;
        StringBuilder first = new StringBuilder();
        if (count !=null){
            count = count.substring(count.length()-2,count.length());
            i = Integer.parseInt(count);
        }
        i+=1;
        if (i<10){
            first.append(code+"0"+i);
        }else {
            first.append(code+i);
        }
        return first.toString();
    }

    /**
     * 生成岗位编码
     * @param count
     * @return
     */
    public static String jobCode(String count,String code){
        int i=0;
        StringBuilder first = new StringBuilder();
        if (count !=null){
            count = count.substring(count.length()-4,count.length());
            i = Integer.parseInt(count);
        }
        i+=1;
        if (i<10){
            first.append(code+"000"+i);
        }else if (i<100){
            first.append(code+"00"+i);
        }else if (i<1000){
            first.append(code+"0"+i);
        }else {
            first.append(code+i);
        }
        return first.toString();
    }

    /**
     * 生成菜单项编码
     * @param count
     * @return
     */
    public static String menuCode(long count){
        count+=1;
        StringBuilder first = new StringBuilder();
        if (count<10){
            first.append("0"+count);
        }else {
            first.append(count);
        }
        return first.toString();
    }
}
