package com.bjfe.genuine.software.invoicingsystem.service.psndoc.impl;

/**
 * Created by Administrator on 2018/1/2.
 */

import com.bjfe.genuine.software.invoicingsystem.mapper.dept.IDeptDao;
import com.bjfe.genuine.software.invoicingsystem.mapper.job.IJobDao;
import com.bjfe.genuine.software.invoicingsystem.mapper.org.IOrgDao;
import com.bjfe.genuine.software.invoicingsystem.mapper.psndoc.IPsndocDao;
import com.bjfe.genuine.software.invoicingsystem.model.dept.DeptVO;
import com.bjfe.genuine.software.invoicingsystem.model.job.JobVO;
import com.bjfe.genuine.software.invoicingsystem.model.org.OrgVO;
import com.bjfe.genuine.software.invoicingsystem.model.psndoc.PsndocVO;
import com.bjfe.genuine.software.invoicingsystem.model.pub.constant.ConstantVO;
import com.bjfe.genuine.software.invoicingsystem.model.pub.exception.GlobalException;
import com.bjfe.genuine.software.invoicingsystem.service.psndoc.IPsndocService;
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
 * 人员业务逻辑实现类
 * 编写人：宋超洋
 */
@Service("iPsndocService")
public class IPsndocServiceImpl implements IPsndocService{
    @Autowired
    private RedisTemplate<String,Serializable> redisTemplate;
    @Resource(name = "IPsndocDao")
    private IPsndocDao IPsndocDao;
    @Resource(name = "IJobDao")
    private IJobDao IJobDao;
    @Resource(name = "IDeptDao")
    private IDeptDao IDeptDao;
    @Resource(name = "IOrgDao")
    private IOrgDao IOrgDao;

    /**
     * 添加人员信息
     * @param psndocVO
     * @return
     * @throws GlobalException
     */
    @Override
    @InitParam
    public Map insertPsndoc(PsndocVO psndocVO) throws GlobalException {
        Map map = new HashMap<>();
        try {
            SnowflakeIdWorker worker = new SnowflakeIdWorker(9, 9);
            String pk_apply = new StringBuffer(ConstantVO.PK_PSNDOC).append(Long.toString(worker.nextId())).toString();
            psndocVO.setPk_psndoc(pk_apply);
            Map<String,Object> codeMap = IPsndocDao.selectPsndocCountByJob(psndocVO.getPk_job());//
            psndocVO.setCode(CodeUtil.psndocCode((String ) codeMap.get("count"),(String)codeMap.get("code")));
            int result = IPsndocDao.insertPsndoc(psndocVO);
            if (result==1){
                map.put("code",psndocVO.getCode());
                map.put("pk_psndoc",psndocVO.getPk_psndoc());
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
     * 查询显示人员信息
     * @param whereMap
     * @return
     * @throws GlobalException
     */
    @Override
    public Map selectPsndocList(Map whereMap) throws GlobalException {
        Map dataMap = null;
        ValueOperations<String, Serializable> valueOperations = redisTemplate.opsForValue();
        List<JobVO> joblist = (ArrayList<JobVO>)valueOperations.get("joblist");
        List<DeptVO> deptlist = (ArrayList<DeptVO>)valueOperations.get("deptlist");
        List<OrgVO> orgList = (ArrayList<OrgVO>)valueOperations.get("orglist");
        try {
            int total = IPsndocDao.selectPsndocCount(whereMap);
            List<PsndocVO> dataList = IPsndocDao.selectPsndocList(PagingUtilVO.pagingContain(whereMap,total));
            dataMap = new HashMap<>();
                /*for (int i=0;i<orgList.size();i++){
                    OrgVO orgVO = orgList.get(i);
                    List<DeptVO> list1 = new ArrayList<>();
                    for (int a =0;a<deptlist.size();a++){
                        DeptVO deptVO = deptlist.get(a);
                        List<JobVO> list2 = new ArrayList<>();
                        if (orgVO.getName().equals(deptlist.get(a).getPk_org())){
                            list1.add(deptlist.get(a));
                        }
                        for (int b = 0;b<joblist.size();b++){
                            if (deptVO.getName().equals(joblist.get(b).getPk_deptdoc())){
                                list2.add(joblist.get(b));
                            }
                        }
                        deptVO.setJobList(list2);
                    }
                    orgVO.setDeptList(list1);
                }*/
                orgList = RedisUtil.handleLis(joblist,deptlist,orgList);
                dataMap.put("pagesize",PagingUtilVO.pagingContain(whereMap,total).get("pageSize"));
                dataMap.put("total",total);
                dataMap.put("orglist",orgList);
                dataMap.put("datalist",dataList);
                dataMap.put("success",true);


        } catch (SQLException e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
        return dataMap;
    }

    /**
     * 删除人员信息
     * @param pk_psndocs
     * @return
     * @throws GlobalException
     */
    @Override
    @InitParam
    public Map deletePsndoc(String[] pk_psndocs) throws GlobalException {
        Map map = new HashMap<>();
        try {
            int result = IPsndocDao.deletePsndoc(pk_psndocs);
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
     * 修改人员信息
     * @param psndocVO
     * @return
     * @throws GlobalException
     */
    @Override
    @InitParam
    public Map updatePsndoc(PsndocVO psndocVO) throws GlobalException {
        Map map = new HashMap<>();
        try {
            Map<String,Object> codeMap = IPsndocDao.selectPsndocCountByJob(psndocVO.getPk_job());//
            psndocVO.setCode(CodeUtil.psndocCode((String ) codeMap.get("count"),(String)codeMap.get("code")));
            int result = IPsndocDao.updatePsndoc(psndocVO);
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

    /**
     * 修改人员信息状态
     * @param whereMap
     * @return
     * @throws GlobalException
     */
    @Override
    @InitParam
    public Map updatePsndocEnableState(Map whereMap) throws GlobalException {
        Map map = null;
        try {
            String pk_psndoc = (String) whereMap.get("pk_psndoc");
            String []pk_psndocs = pk_psndoc.split(",");
            int result = IPsndocDao.updatePsndocIstate((Integer) whereMap.get("enablestate"),pk_psndocs);
            if (result >0) {
                map=new HashMap<>();
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
