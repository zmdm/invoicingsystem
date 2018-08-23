package com.bjfe.genuine.software.invoicingsystem.util.paging;

/**
 * Created by Administrator on 2017/11/22.
 */

import com.bjfe.genuine.software.invoicingsystem.model.cdl.MenuItemVO;

import java.util.*;

/**
 * 分页条件生成工具类
 * 编写人：宋超洋
 */
public class PagingUtilVO {
    /**
     * 分页条件生成
     * @param whereMap
     * @param total
     * @return
     */
    public static Map pagingContain(Map whereMap,long total){
        String str = (String) whereMap.get("pagecount");
        long pageNumber =Long.parseLong((String) whereMap.get("pagenumber"));
        long pageCount =Long.parseLong((String) whereMap.get("pagecount"));
        Long pageSize=total/pageCount;
        if(total%pageCount>=0){
            pageSize+=1;
        }
        Long skipNumber=(pageNumber-1)*pageCount;
        whereMap.put("pageCount",pageCount);
        whereMap.put("skipNumber",skipNumber);
        whereMap.put("pageSize",pageSize);
        return whereMap;
    }

    /**
     * 操作list进行分页
     * @param whereMap
     * @param list
     * @return
     */
    public static Map pagingListContain(Map whereMap,List list){
        List listTemp = new ArrayList<>();
        int total = list.size();
        int pageNumber =Integer.parseInt((String) whereMap.get("pagenumber"));
        int pageCount =Integer.parseInt((String) whereMap.get("pagecount"));
        int pageSize=total/pageCount;
        if(total%pageCount>0){
            pageSize+=1;
        }
        int skipNumber=(pageNumber-1)*pageCount;
        for (int i = skipNumber;i<list.size();i++){
            listTemp.add(list.get(i));
            if (pageCount == listTemp.size()){
                break;
            }
        }
        whereMap.put("total",total);
        whereMap.put("pagenumber",pageNumber);
        whereMap.put("pageCount",pageCount);
        whereMap.put("pageSize",pageSize);
        whereMap.put("datalist",listTemp);
        whereMap.put("success",true);
        return whereMap;
    }
    /**
     * list排序方法
     * @param list
     */
    @SuppressWarnings("unchecked")
    public static void sortStringMethod(List list){
        Collections.sort(list, new Comparator(){
            @Override
            public int compare(Object o1, Object o2) {
                MenuItemVO m1=(MenuItemVO)o1;
                MenuItemVO m2=(MenuItemVO)o2;
                return m1.getFuncode().compareTo(m2.getFuncode());
            }
        });
    }
}
