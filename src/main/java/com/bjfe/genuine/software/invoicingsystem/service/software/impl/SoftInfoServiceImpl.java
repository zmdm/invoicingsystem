package com.bjfe.genuine.software.invoicingsystem.service.software.impl;

import com.bjfe.genuine.software.invoicingsystem.mapper.software.ISoftInfoDao;
import com.bjfe.genuine.software.invoicingsystem.mapper.software.ISoftTypeDao;
import com.bjfe.genuine.software.invoicingsystem.model.pub.constant.ConstantVO;
import com.bjfe.genuine.software.invoicingsystem.model.pub.exception.GlobalException;
import com.bjfe.genuine.software.invoicingsystem.model.rjlx.SoftTypeVO;
import com.bjfe.genuine.software.invoicingsystem.model.rjxx.SoftInfoVO;
import com.bjfe.genuine.software.invoicingsystem.service.software.ISoftInfoService;
import com.bjfe.genuine.software.invoicingsystem.util.annotation.InitSoftParam;
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
 * Created by Administrator on 2018/1/9.
 */

/**
 * 软件信息业务逻辑实现类
 * 编写人：宋超洋
 */
@Service("iSoftInfoService")
public class SoftInfoServiceImpl implements ISoftInfoService{
    @Autowired
    private RedisTemplate<String,Serializable> redisTemplate;
    @Resource(name = "ISoftInfoDao")
    private ISoftInfoDao ISoftInfoDao;
    @Resource(name = "ISoftTypeDao")
    private ISoftTypeDao ISoftTypeDao;

    /**
     * 添加软件信息
     * @param softInfoVO
     * @return
     * @throws GlobalException
     */
    @Override
    @InitSoftParam
    public Map insertSoftInfo(SoftInfoVO softInfoVO) throws GlobalException {
        Map map = new HashMap<>();
        try {
            int count = ISoftInfoDao.selectSoftInfoCountBycode(softInfoVO.getName(),softInfoVO.getVersion());
            if (0==count){
                SnowflakeIdWorker worker = new SnowflakeIdWorker(9, 9);
                String pk_apply = new StringBuffer(ConstantVO.PK_SOFT).append(Long.toString(worker.nextId())).toString();
                softInfoVO.setPk_soft(pk_apply);
                Map resultMap = ISoftInfoDao.selectSoftInfoCountByPksofttype(softInfoVO.getPk_softtype());
                String softInfoCode = CodeUtil.softInfoCode((String) resultMap.get("count"),(String) resultMap.get("code"));
                softInfoVO.setCode(softInfoCode);
                ISoftInfoDao.insertSoftInfo(softInfoVO);
                map.put("pk_soft",softInfoVO.getPk_soft());
                map.put("code",softInfoCode);
                map.put("success",true);
            }else {
                map.put("success",false);
                map.put("info","软件名称和版本重复，请重新输入！");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
        return map;
    }

    /**
     * 查询显示软件信息
     * @param whereMap
     * @return
     * @throws GlobalException
     */
    @Override
    public Map selectSoftInfoList(Map whereMap) throws GlobalException {
        Map dataMap = null;
        ValueOperations<String, Serializable> valueOperations = redisTemplate.opsForValue();
        List<SoftTypeVO> softtypeList = (ArrayList<SoftTypeVO>)valueOperations.get("softtypelist");
        try {
            int total = ISoftInfoDao.selectSoftInfoCount(whereMap);
            List<SoftInfoVO> dataList = ISoftInfoDao.selectSoftInfoList(PagingUtilVO.pagingContain(whereMap,total));
            if (dataList!=null){
                dataMap = new HashMap<>();
                dataMap.put("success",true);
                dataMap.put("datalist",dataList);
                dataMap.put("softtypelist",softtypeList);
                dataMap.put("pagesize",PagingUtilVO.pagingContain(whereMap,total).get("pageSize"));
                dataMap.put("total",total);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
        return dataMap;
    }

    /**
     * 删除软件信息
     * @param pk_soft
     * @return
     * @throws GlobalException
     */
    @Override
    @InitSoftParam
    public Map deleteSoftInfo(String[] pk_soft) throws GlobalException {
        Map map = new HashMap<>();
        try {
            int result = ISoftInfoDao.deleteSoftInfo(pk_soft);
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
     * 修改软件信息
     * @param softInfoVO
     * @return
     * @throws GlobalException
     */
    @Override
    @InitSoftParam
    public Map updateSoftInfo(SoftInfoVO softInfoVO) throws GlobalException {
        Map map = new HashMap<>();
        try {
            int count = ISoftInfoDao.selectSoftInfoCountBycode(softInfoVO.getName(),softInfoVO.getVersion());
            if (count ==0){
                ISoftInfoDao.updateSoftInfo(softInfoVO);
                map.put("success",true);
            }else {
                map.put("info","软件名称和版本重复，请重新输入！");
                map.put("success",false);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
        return map;
    }
}
