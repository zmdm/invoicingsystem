package com.bjfe.genuine.software.invoicingsystem.service.software.impl;

import com.bjfe.genuine.software.invoicingsystem.mapper.software.ISoftInfoDao;
import com.bjfe.genuine.software.invoicingsystem.mapper.software.ISoftTypeDao;
import com.bjfe.genuine.software.invoicingsystem.model.pub.constant.ConstantVO;
import com.bjfe.genuine.software.invoicingsystem.model.pub.exception.GlobalException;
import com.bjfe.genuine.software.invoicingsystem.model.rjlx.SoftTypeVO;
import com.bjfe.genuine.software.invoicingsystem.model.rjxx.SoftInfoVO;
import com.bjfe.genuine.software.invoicingsystem.service.software.ISoftInfoService;
import com.bjfe.genuine.software.invoicingsystem.service.software.ISoftTypeService;
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
 * 软件类型业务逻辑实现类
 * 编写人：宋超洋
 */
@Service("iSoftTypeService")
public class SoftTypeServiceImpl implements ISoftTypeService{
    @Autowired
    private RedisTemplate<String,Serializable> redisTemplate;
    @Resource(name = "ISoftTypeDao")
    private ISoftTypeDao ISoftTypeDao;

    /**
     * 添加软件类型
     * @param softTypeVO
     * @return
     * @throws GlobalException
     */
    @Override
    @InitSoftParam
    public Map insertSoftType(SoftTypeVO softTypeVO) throws GlobalException {
        Map map = new HashMap<>();
        try {
            int count = ISoftTypeDao.selectSoftTypeCountBynameAndcode(softTypeVO.getName());
            if (0 ==count){
                SnowflakeIdWorker worker = new SnowflakeIdWorker(9, 9);
                String pk_apply = new StringBuffer(ConstantVO.PK_SOFTTYPE).append(Long.toString(worker.nextId())).toString();
                String softTypeCode = CodeUtil.softTypeCode(ISoftTypeDao.selectSoftTypeCount());
                softTypeVO.setCode(softTypeCode);
                softTypeVO.setPk_softtype(pk_apply);
                ISoftTypeDao.insertSoftType(softTypeVO);
                map.put("pk_softtype",softTypeVO.getPk_softtype());
                map.put("code",softTypeCode);
                map.put("success",true);
            } else {
                map.put("success",false);
                map.put("info","名称重复，请重新输入！");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
        return map;
    }

    /**
     * 查询显示软件类型
     * @return
     * @throws GlobalException
     */
    @Override
    public Map selectSoftTypeList(Map whereMap) throws GlobalException {
        Map dataMap =new HashMap<>();
        ValueOperations<String, Serializable> valueOperations = redisTemplate.opsForValue();
        List<SoftTypeVO> dataList = (ArrayList<SoftTypeVO>)valueOperations.get("softtypelist");
        dataMap = PagingUtilVO.pagingListContain(whereMap,dataList);
        return dataMap;
    }

    /**
     * 删除软件类型
     * @param pk_softtype
     * @return
     * @throws GlobalException
     */
    @Override
    @InitSoftParam
    public Map deleteSoftType(String []pk_softtype) throws GlobalException {
        Map map = new HashMap<>();
        try {
            int result = ISoftTypeDao.deleteSoftType(pk_softtype);
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
     * 修改软件类型
     * @param softTypeVO
     * @return
     * @throws GlobalException
     */
    @Override
    @InitSoftParam
    public Map updateSoftType(SoftTypeVO softTypeVO) throws GlobalException {
        Map map = new HashMap<>();
        try {
            int result = ISoftTypeDao.updateSoftType(softTypeVO);
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
