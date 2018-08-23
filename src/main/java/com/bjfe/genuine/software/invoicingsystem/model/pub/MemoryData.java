package com.bjfe.genuine.software.invoicingsystem.model.pub;

/**
 * Created by Administrator on 2017/12/8.
 */

import java.util.HashMap;
import java.util.Map;

/**
 * 内存数据类
 * 编写人：宋超洋
 */
public class MemoryData {
    private static Map<String, Object> userIDMap = new HashMap<>();

    public static Map<String, Object> getUserIDMap() {
        return userIDMap;
    }

    public static void setUserIDMap(Map<String, Object> userIDMap) {
        MemoryData.userIDMap = userIDMap;
    }
}
