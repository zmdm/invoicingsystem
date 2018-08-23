package com.bjfe.genuine.software.invoicingsystem.util.redis;

/**
 * Created by Administrator on 2018/1/5.
 */

import com.bjfe.genuine.software.invoicingsystem.model.dept.DeptVO;
import com.bjfe.genuine.software.invoicingsystem.model.job.JobVO;
import com.bjfe.genuine.software.invoicingsystem.model.org.OrgVO;
import com.bjfe.genuine.software.invoicingsystem.model.psndoc.PsndocVO;

import java.util.ArrayList;
import java.util.List;

/**
 * redis处理数据工具类
 * 编写人：宋超洋
 */
public class RedisUtil {
    public static List<OrgVO> handleLis(List<DeptVO> deptlist,List<OrgVO> orgList){
        List<OrgVO> list =handleLis(null,deptlist,orgList);
        return list;
    }
    /**
     * 处理人员信息数据集合
     * @param joblist
     * @param deptlist
     * @param orgList
     * @return
     */
    public static List<OrgVO> handleLis(List<JobVO> joblist, List<DeptVO> deptlist, List<OrgVO> orgList){
        for (int i=0;i<orgList.size();i++){
            OrgVO orgVO = orgList.get(i);
            List<DeptVO> list1 = new ArrayList<>();
            for (int a =0;a<deptlist.size();a++){
                DeptVO deptVO = deptlist.get(a);
                List<JobVO> list2 = new ArrayList<>();
                if (orgVO.getName().equals(deptlist.get(a).getPk_org())){
                    list1.add(deptlist.get(a));
                }
                if (joblist !=null){
                    for (int b = 0;b<joblist.size();b++){
                        if (deptVO.getName().equals(joblist.get(b).getPk_deptdoc())){
                            list2.add(joblist.get(b));
                        }
                    }
                }
                deptVO.setJobList(list2);
            }
            orgVO.setDeptList(list1);
        }
        return orgList;
    }
}
