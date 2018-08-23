package com.bjfe.genuine.software.invoicingsystem.util.excel;

/**
 * Created by Administrator on 2018/1/23.
 */

/**
 * 判断Excel版本工具类
 * 编写人：宋超洋
 */
public class WDWUtil {
    // @描述：是否是2003的excel，返回true是2003
    public static boolean isExcel2003(String filePath)  {
        return filePath.matches("^.+\\.(?i)(xls)$");
    }

    //@描述：是否是2007的excel，返回true是2007
    public static boolean isExcel2007(String filePath)  {
        return filePath.matches("^.+\\.(?i)(xlsx)$");
    }
}
