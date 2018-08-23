package com.bjfe.genuine.software.invoicingsystem.service.qxgl.impl;

import com.bjfe.genuine.software.invoicingsystem.mapper.qxgl.IPermissionDao;
import com.bjfe.genuine.software.invoicingsystem.model.org.OrgVO;
import com.bjfe.genuine.software.invoicingsystem.model.qxgl.RightsManVO;
import com.bjfe.genuine.software.invoicingsystem.model.pub.constant.ConstantVO;
import com.bjfe.genuine.software.invoicingsystem.model.pub.exception.GlobalException;
import com.bjfe.genuine.software.invoicingsystem.service.qxgl.IPermissionService;
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
 * Created by Administrator on 2018-01-04.
 */
/**
 * 权限业务逻辑实现类
 * 编写人：李宗泽
 */
@Service("iPermissionService")
public class IPermissionServiceImpl implements IPermissionService {
        @Autowired
        private RedisTemplate<String,Serializable> redisTemplate;
        @Resource(name = "IPermissionDao")
        private IPermissionDao IPermissionDao;

        /**
         * 添加权限
         * @param rightsManVO
         * @return
         * @throws GlobalException
         */
        @Override
        public Map insertRightsMan(RightsManVO rightsManVO) throws GlobalException {
            Map map = new HashMap<>();
            try {
                int count = IPermissionDao.selectPermissionCountBycodeAndname(rightsManVO.getCode(),rightsManVO.getName());
                if (0==count){
                    SnowflakeIdWorker worker = new SnowflakeIdWorker(9, 9);
                    String pk_apply = new StringBuffer(ConstantVO.PK_PERMID).append(Long.toString(worker.nextId())).toString();
                    rightsManVO.setPk_permid(pk_apply);
                    IPermissionDao.insertRightsMan(rightsManVO);
                    map.put("pk_permid", rightsManVO.getPk_permid());
                    map.put("success",true);
                }else {
                    map.put("success",false);
                    map.put("info","名称或编码重复，请重新输入！");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new GlobalException(e.getMessage());
            }
            return map;
        }

        /**
         * 查询显示权限
         * @param whereMap
         * @return
         * @throws GlobalException
         */
        public Map selectRightsManList(Map whereMap) throws GlobalException {
            Map dataMap = null;
            ValueOperations<String, Serializable> valueOperations = redisTemplate.opsForValue();
            List<OrgVO> orgList = (ArrayList<OrgVO>)valueOperations.get("orglist");
            try {
                int total = IPermissionDao.selectRightsManCount(whereMap);
                List<RightsManVO> dataList = IPermissionDao.selectRightsManList(PagingUtilVO.pagingContain(whereMap,total));
                dataMap = new HashMap<>();
                if (dataList!=null){
                    dataMap.put("pagesize",PagingUtilVO.pagingContain(whereMap,total).get("pageSize"));
                    dataMap.put("total",total);
                    dataMap.put("datalist",dataList);
                    dataMap.put("orglist",orgList);
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

        /**
         * 删除权限
         * @param pk_permid
         * @return
         * @throws GlobalException
         */
        public Map deleteRightsMan(String[] pk_permid) throws GlobalException {
            Map map = new HashMap<>();
            try {
                int result = IPermissionDao.deleteRightsMan(pk_permid);
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
         * 修改权限
         * @param rightsManVO
         * @return
         * @throws GlobalException
         */
        public Map updateRightsMan(RightsManVO rightsManVO) throws GlobalException {
            Map map = new HashMap<>();
            try {
                int result = IPermissionDao.updateRightsMan(rightsManVO);
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
         * 查询所有角色权限信息是否分配
         * @param pk_role
         * @return
         * @throws GlobalException
         */
        @Override
        public Map editPowerNoRole(String pk_role) throws GlobalException {
            Map map = new HashMap<>();
            try {
                List<RightsManVO> powerList = IPermissionDao.editPowerNoRole(pk_role);
                if (powerList.size()>0){
                    map.put("success",true);
                    map.put("datalist",powerList);
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




