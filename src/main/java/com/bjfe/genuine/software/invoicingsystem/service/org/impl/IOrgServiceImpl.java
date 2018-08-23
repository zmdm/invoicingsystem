package com.bjfe.genuine.software.invoicingsystem.service.org.impl;

/**
 * Created by Administrator on 2018/1/2.
 */

import com.bjfe.genuine.software.invoicingsystem.mapper.org.IOrgDao;
import com.bjfe.genuine.software.invoicingsystem.model.org.OrgVO;
import com.bjfe.genuine.software.invoicingsystem.model.pub.constant.ConstantVO;
import com.bjfe.genuine.software.invoicingsystem.model.pub.exception.GlobalException;
import com.bjfe.genuine.software.invoicingsystem.service.org.IOrgService;
import com.bjfe.genuine.software.invoicingsystem.util.annotation.InitParam;
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
 * 组织业务逻辑实现类
 * 编写人：宋超洋
 */
@Service("iOrgService")
public class IOrgServiceImpl implements IOrgService{
    @Autowired
    private RedisTemplate<String,Serializable> redisTemplate;
    @Resource(name = "IOrgDao")// @Resource默认按名称装配

    private IOrgDao IOrgDao;

    /**
     * 添加组织
     * @param orgVO
     * @return
     * @throws GlobalException
     */
    @Override
    @InitParam
    public Map insertOrg(OrgVO orgVO)throws GlobalException{
        Map map = new HashMap<>();
        try {
            SnowflakeIdWorker worker = new SnowflakeIdWorker(9, 9);
            String pk_apply = new StringBuffer(ConstantVO.PK_ORG).append(Long.toString(worker.nextId())).toString();
            orgVO.setPk_org(pk_apply);
            int count = IOrgDao.selectOrgByCode(orgVO.getCode());
            if (count ==0){
                int result = IOrgDao.insertOrg(orgVO);
                if (result ==1){
                    map.put("pk_org",orgVO.getPk_org());
                    map.put("success",true);
                }else {
                    map.put("success",false);
                }
            }else {
                map.put("success",false);
                map.put("info","编码重复，请重新输入！");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * redis查询所有组织
     * @return
     * @throws GlobalException
     */
    @Override
    public Map selectOrgList(Map map) throws GlobalException {
        ValueOperations<String, Serializable> valueOperations = redisTemplate.opsForValue();
        List<OrgVO> orgList = (ArrayList<OrgVO>)valueOperations.get("orglist");
        Map dataMap = PagingUtilVO.pagingListContain(map,orgList);
        return dataMap;
    }

    /**
     * 修改组织
     * @param orgVO
     * @return
     * @throws GlobalException
     */
    @Override
    @InitParam
    public Map updateOrg(OrgVO orgVO) throws GlobalException {
        Map map = new HashMap<>();
        try {
            int result = IOrgDao.updateOrg(orgVO);
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
     * 删除组织
     * @param orgs
     * @return
     * @throws GlobalException
     */
    @Override
    @InitParam
    public Map deleteOrg(String[] orgs) throws GlobalException {
        Map map=new HashMap<>();
        try {
            int result = IOrgDao.deleteOrgs(orgs);
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
     * 条件查询组织
     *@param whereMap
     * @return
     * @throws GlobalException
     */
    @Override
    public Map selectOrgPage(Map whereMap) throws GlobalException {
        ValueOperations<String, Serializable> valueOperations = redisTemplate.opsForValue();
        List<OrgVO> orgList = (ArrayList<OrgVO>)valueOperations.get("orglist");
        Map dataMap = new HashMap<>();
        try {
            int total =IOrgDao.selectOrgCount(whereMap);
            List<OrgVO> dataList = IOrgDao.selectOrgPage(PagingUtilVO.pagingContain(whereMap,total));
            dataMap = new HashMap<>();
            if (dataList!=null){
				dataMap.put("pagesize",PagingUtilVO.pagingContain(whereMap,total).get("pageSize"));
				dataMap.put("total",total);
				dataMap.put("datalist",orgList);
                dataMap.put("orglist",dataList);
				dataMap.put("success",true);
			}else {
				dataMap.put("success",false);
			}
        } catch (SQLException e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
        return dataMap;
    }
}

