package com.bjfe.genuine.software.invoicingsystem.service.dept.impl;

import com.bjfe.genuine.software.invoicingsystem.mapper.dept.IDeptDao;
import com.bjfe.genuine.software.invoicingsystem.mapper.org.IOrgDao;
import com.bjfe.genuine.software.invoicingsystem.model.dept.DeptVO;
import com.bjfe.genuine.software.invoicingsystem.model.org.OrgVO;
import com.bjfe.genuine.software.invoicingsystem.model.pub.constant.ConstantVO;
import com.bjfe.genuine.software.invoicingsystem.model.pub.exception.GlobalException;
import com.bjfe.genuine.software.invoicingsystem.service.dept.IDeptService;
import com.bjfe.genuine.software.invoicingsystem.util.annotation.InitParam;
import com.bjfe.genuine.software.invoicingsystem.util.code.CodeUtil;
import com.bjfe.genuine.software.invoicingsystem.util.paging.PagingUtilVO;
import com.bjfe.genuine.software.invoicingsystem.util.pk.SnowflakeIdWorker;
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
@Service("iDeptService")
public class IDeptServiceImpl implements IDeptService{
    @Autowired
    private RedisTemplate<String,Serializable> redisTemplate;
    @Resource(name = "IDeptDao")
    private IDeptDao IDeptDao;
    @Resource(name = "IOrgDao")
    private IOrgDao IOrgDao;

    /**
     * 添加部门
     * @param deptVO
     * @return
     * @throws GlobalException
     */
    @Override
    @InitParam
    public Map insertDept(DeptVO deptVO)throws GlobalException{
        Map map = new HashMap<>();
        try {
            SnowflakeIdWorker worker = new SnowflakeIdWorker(9, 9);
            String pk_deptdoc = new StringBuffer(ConstantVO.PK_DEPT).append(Long.toString(worker.nextId())).toString();
            deptVO.setPk_deptdoc(pk_deptdoc);
            Map codeMap = IDeptDao.selectDeptByOrgCount(deptVO.getPk_org());
            String code = CodeUtil.deptCode((String) codeMap.get("count"),(String)codeMap.get("code"));
            deptVO.setCode(code);
            int result = IDeptDao.insertDept(deptVO);
            if (result ==1){
                map.put("pk_org",deptVO.getPk_org());
                map.put("pk_dept",deptVO.getPk_deptdoc());
                map.put("deptcode",deptVO.getCode());
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
     * redis查询所有部门
     * @return
     * @throws GlobalException
     */
    @Override
    public Map selectDeptList(Map whereMap) throws GlobalException {
        Map map = new HashMap<>();
        ValueOperations<String, Serializable> valueOperations = redisTemplate.opsForValue();
        List<DeptVO> deptlist = (ArrayList<DeptVO>)valueOperations.get("deptlist");
        List<OrgVO> orgList = (ArrayList<OrgVO>)valueOperations.get("orglist");
        Map dataMap = PagingUtilVO.pagingListContain(whereMap,deptlist);
        map.put("pagenumber",dataMap.get("pagenumber"));
        map.put("pageCount",dataMap.get("pageCount"));
        map.put("pageSize",dataMap.get("pageSize"));
        map.put("total",dataMap.get("total"));
        map.put("deptlist",dataMap.get("datalist"));
        map.put("orglist",orgList);
        return map;
    }

    /**
     * 条件查询兼分页显示部门
     * @param WhereMap
     * @return
     * @throws GlobalException
     */
    public Map selectDeptPage(Map WhereMap)throws GlobalException{
        Map dataMap = new HashMap<>();
        ValueOperations<String, Serializable> valueOperations = redisTemplate.opsForValue();
        List<DeptVO> deptlist = (ArrayList<DeptVO>)valueOperations.get("deptlist");
        List<OrgVO> orgList = (ArrayList<OrgVO>)valueOperations.get("orglist");
        try {
            int total=IDeptDao.selectDeptCount(WhereMap);
            List<DeptVO> dataList=IDeptDao.selectDeptPage(PagingUtilVO.pagingContain(WhereMap,total));
            if(dataList!=null){
                dataMap.put("pagesize",PagingUtilVO.pagingContain(WhereMap,total).get("pageSize"));
                dataMap.put("total",total);
                dataMap.put("datalist",dataList);
                dataMap.put("deptlist",deptlist);
                dataMap.put("orglist",orgList);
                dataMap.put("success",true);
            }
            else{
                dataMap.put("false",false);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }

        return  dataMap;
    }

    /**
     * 删除部门
     * @param depts
     * @return
     * @throws GlobalException
     */
    @InitParam
    public Map deleteDept(String[] depts) throws GlobalException {
        Map map=new HashMap<>();
        try {
            int result = IDeptDao.deleteDepts(depts);
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
     * 修改部门
     * @param deptVO
     * @return
     * @throws GlobalException
     */
    @InitParam
    public Map updateDept(DeptVO deptVO) throws GlobalException {
        Map map = new HashMap<>();
        try {
            Map codeMap = IDeptDao.selectDeptByOrgCount(deptVO.getPk_org());
            String code = CodeUtil.deptCode((String) codeMap.get("count"),(String)codeMap.get("code"));
            deptVO.setCode(code);
            int result = IDeptDao.updateDept(deptVO);
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
