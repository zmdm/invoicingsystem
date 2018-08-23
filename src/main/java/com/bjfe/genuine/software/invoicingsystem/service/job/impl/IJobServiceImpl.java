package com.bjfe.genuine.software.invoicingsystem.service.job.impl;

import com.bjfe.genuine.software.invoicingsystem.mapper.dept.IDeptDao;
import com.bjfe.genuine.software.invoicingsystem.mapper.job.IJobDao;
import com.bjfe.genuine.software.invoicingsystem.mapper.org.IOrgDao;
import com.bjfe.genuine.software.invoicingsystem.model.dept.DeptVO;
import com.bjfe.genuine.software.invoicingsystem.model.job.JobVO;
import com.bjfe.genuine.software.invoicingsystem.model.org.OrgVO;
import com.bjfe.genuine.software.invoicingsystem.model.pub.constant.ConstantVO;
import com.bjfe.genuine.software.invoicingsystem.model.pub.exception.GlobalException;
import com.bjfe.genuine.software.invoicingsystem.service.job.IJobService;
import com.bjfe.genuine.software.invoicingsystem.util.annotation.InitParam;
import com.bjfe.genuine.software.invoicingsystem.util.code.CodeUtil;
import com.bjfe.genuine.software.invoicingsystem.util.paging.PagingUtilVO;
import com.bjfe.genuine.software.invoicingsystem.util.pk.SnowflakeIdWorker;
import com.bjfe.genuine.software.invoicingsystem.util.redis.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/2.
 */

/**
 * 岗位业务逻辑实现类
 * 编写人：宋超洋
 */
@Service("iJobService")
public class IJobServiceImpl implements IJobService{
    @Autowired
    private RedisTemplate<String,Serializable> redisTemplate;
    @Resource(name = "IJobDao")
    private IJobDao IJobDao;
    @Resource(name = "IDeptDao")
    private IDeptDao IDeptDao;
    @Resource(name = "IOrgDao")
    private IOrgDao IOrgDao;

    /**
     * 添加岗位
     * @param jobVO
     * @return
     * @throws GlobalException
     */
    @InitParam
    public Map insertJob(JobVO jobVO)throws GlobalException{
        Map map = new HashMap<>();
        try {
            SnowflakeIdWorker worker = new SnowflakeIdWorker(9, 9);
            String pk_apply = new StringBuffer(ConstantVO.PK_JOB).append(Long.toString(worker.nextId())).toString();
            jobVO.setPk_job(pk_apply);
            Map codeMap = IJobDao.selectJobCountByDept(jobVO.getPk_deptdoc());
            jobVO.setCode(CodeUtil.jobCode((String) codeMap.get("count"),(String) codeMap.get("code")));
            int result = IJobDao.insertJob(jobVO);
            if (result ==1){
                map.put("pk_job",jobVO.getPk_job());
                map.put("jobcode",jobVO.getCode());
                map.put("success",true);
            }else {
                map.put("success",true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 查询所有岗位
     * @return
     * @throws GlobalException
     */
    @Override
    public Map selectJobList(Map whereMap) throws GlobalException {
        Map map = new HashMap<>();
        ValueOperations<String, Serializable> valueOperations = redisTemplate.opsForValue();
        List<JobVO> joblist = (ArrayList<JobVO>)valueOperations.get("joblist");
        List<DeptVO> deptlist = (ArrayList<DeptVO>)valueOperations.get("deptlist");
        List<OrgVO> orgList = (ArrayList<OrgVO>)valueOperations.get("orglist");
            /*for (int i=0;i<orgList.size();i++){
                OrgVO orgVO = orgList.get(i);
                List<DeptVO> list = new ArrayList<>();
                for (int a =0;a<deptlist.size();a++){
                    if (orgVO.getName().equals(deptlist.get(a).getPk_org())){
                        list.add(deptlist.get(a));
                    }
                }
                orgVO.setDeptList(list);
            }*/
            orgList = RedisUtil.handleLis(deptlist,orgList);
        Map dataMap = PagingUtilVO.pagingListContain(whereMap,joblist);
        map.put("pagenumber",dataMap.get("pagenumber"));
        map.put("pageCount",dataMap.get("pageCount"));
        map.put("pageSize",dataMap.get("pageSize"));
        map.put("total",dataMap.get("total"));
        map.put("joblist",dataMap.get("datalist"));
        map.put("orgList",orgList);
        return map;
    }

    /**
     * 条件查询岗位兼职分页显示
     * @return
     * @throws GlobalException
     */
    public Map selectJobPage(Map whereMap)throws GlobalException{
        Map dataMap =new HashMap<>();
        ValueOperations<String, Serializable> valueOperations = redisTemplate.opsForValue();
        List<DeptVO> deptlist = (ArrayList<DeptVO>)valueOperations.get("deptlist");
        List<OrgVO> orgList = (ArrayList<OrgVO>)valueOperations.get("orglist");
        try {
            int total= IJobDao.selectJobCount(whereMap);
            List<JobVO> dataList=IJobDao.selectJobPage(PagingUtilVO.pagingContain(whereMap,total));
            if(dataList!=null){
                orgList = RedisUtil.handleLis(deptlist,orgList);
                dataMap.put("pagesize",PagingUtilVO.pagingContain(whereMap,total).get("pageSize"));
                dataMap.put("total",total);
                dataMap.put("orglist",orgList);
                dataMap.put("datalist",dataList);
                dataMap.put("success",true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }

        return  dataMap;
    }

    /**
     * 删除岗位
     * @param jobs
     * @return
     * @throws GlobalException
     */
    @InitParam
    public Map deleteJob(String[] jobs) throws GlobalException {
        Map map=new HashMap<>();
        try {
            int result = IJobDao.deleteJobs(jobs);
            if (result >0){
                map.put("success",true);
            }else {
                map.put("success",false);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
        return map;
    }

    /**
     * 修改岗位
     * @param jobVO
     * @return
     * @throws GlobalException
     */
    @InitParam
    public Map updateJob(JobVO jobVO) throws GlobalException {
        Map map = new HashMap<>();
        try {
            Map codeMap = IJobDao.selectJobCountByDept(jobVO.getPk_deptdoc());
            jobVO.setCode(CodeUtil.jobCode((String) codeMap.get("count"),(String) codeMap.get("code")));
            int result = IJobDao.updateJob(jobVO);
            if (result ==1){
                map.put("success",true);
            }else {
                map.put("success",false);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
        return map;
    }
}
