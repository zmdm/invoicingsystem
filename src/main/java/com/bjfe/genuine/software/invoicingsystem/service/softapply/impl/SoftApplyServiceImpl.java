package com.bjfe.genuine.software.invoicingsystem.service.softapply.impl;

import com.bjfe.genuine.software.invoicingsystem.mapper.softapply.ISoftApplyDao;
import com.bjfe.genuine.software.invoicingsystem.mapper.software.ISoftInfoDao;
import com.bjfe.genuine.software.invoicingsystem.mapper.software.ISoftTypeDao;
import com.bjfe.genuine.software.invoicingsystem.model.pub.constant.ConstantVO;
import com.bjfe.genuine.software.invoicingsystem.model.pub.exception.GlobalException;
import com.bjfe.genuine.software.invoicingsystem.model.rjlx.SoftTypeVO;
import com.bjfe.genuine.software.invoicingsystem.model.rjxx.SoftInfoVO;
import com.bjfe.genuine.software.invoicingsystem.model.softapply.SoftApplyBVO;
import com.bjfe.genuine.software.invoicingsystem.model.softapply.SoftApplyHVO;
import com.bjfe.genuine.software.invoicingsystem.model.softorder.SoftOrderHVO;
import com.bjfe.genuine.software.invoicingsystem.service.softapply.ISoftApplyService;
import com.bjfe.genuine.software.invoicingsystem.service.software.ISoftInfoService;
import com.bjfe.genuine.software.invoicingsystem.service.software.ISoftTypeService;
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
 * Created by Administrator on 2018/1/11.
 */

/**
 * 软件使用需求申请业务逻辑实现类
 * 编写人：宋超洋
 */
@Service("iSoftApplyService")
public class SoftApplyServiceImpl implements ISoftApplyService{
    @Autowired
    private RedisTemplate<String,Serializable> redisTemplate;
    @Resource(name = "ISoftApplyDao")
    private ISoftApplyDao iSoftApplyDao;
    @Resource(name = "iSoftInfoService")
    private ISoftInfoService iSoftInfoService;
    @Resource(name = "iSoftTypeService")
    private ISoftTypeService iSoftTypeService;
    @Resource(name = "ISoftInfoDao")
    private ISoftInfoDao iSoftInfoDao;
    @Resource(name = "ISoftTypeDao")
    private ISoftTypeDao iSoftTypeDao;

    /**
     * 保存软件使用需求申请
     * @param softApplyHVO
     * @return
     * @throws GlobalException
     */
    @Override
    public Map insertSoftApply(SoftApplyHVO softApplyHVO) throws GlobalException {
        Map map =new HashMap<>();
        try {
            SnowflakeIdWorker worker = new SnowflakeIdWorker(9, 9);
            String pk_soft_apply = new StringBuffer(ConstantVO.PK_SOFT_APPLY).append(Long.toString(worker.nextId())).toString();
            softApplyHVO.setPk_soft_apply(pk_soft_apply);//设置主键
            softApplyHVO.setBillstatus(0);//设置状态
            int result = iSoftApplyDao.insertSoftApplyHVO(softApplyHVO);
            if (result >0){
                List<SoftApplyBVO> list = softApplyHVO.getSoftApplyBVOList();//通过主表主键获得子表的数据
                for (int i=0;i<list.size();i++){
                    String pk_soft_apply_b = new StringBuffer(ConstantVO.PK_SOFT_APPLY_B).append(Long.toString(worker.nextId())).toString();
                    SoftApplyBVO softApplyBVO = list.get(i);//得到子表一个一个的数据
                    softApplyBVO.setPk_soft_apply(pk_soft_apply);//设置子表的主表主键值
                    softApplyBVO.setPk_soft_apply_b(pk_soft_apply_b);//设置子表的主键值
                    if (!softApplyBVO.isOperation()){
                        SoftTypeVO softTypeVO = new SoftTypeVO();
                        softTypeVO.setName(softApplyBVO.getPk_softtype());
                        softTypeVO.setIsfree(1);
                        Map softTypeMap = iSoftTypeService.insertSoftType(softTypeVO);
                        SoftInfoVO softInfoVO = new SoftInfoVO();
                        softInfoVO.setName(softApplyBVO.getPk_soft());
                        softInfoVO.setVersion(softApplyBVO.getVersion());
                        softInfoVO.setPk_softtype((String) softTypeMap.get("pk_softtype"));
                        Map softInfoMap = iSoftInfoService.insertSoftInfo(softInfoVO);
                        softApplyBVO.setPk_soft((String) softInfoMap.get("pk_soft"));
                        softApplyBVO.setPk_softtype((String) softTypeMap.get("pk_softtype"));
                    }
                }
                iSoftApplyDao.insertSoftApplyBVO(list);
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
     * 条件查询软件使用需求申请
     * @param whereMap
     * @return
     * @throws GlobalException
     */
    @Override
    public Map selectSoftApplyList(Map whereMap) throws GlobalException {
        Map dataMap = new HashMap<>();
        ValueOperations<String, Serializable> valueOperations = redisTemplate.opsForValue();
        List<SoftTypeVO> softTypeList = (ArrayList<SoftTypeVO>)valueOperations.get("softtypelist");
        List<SoftInfoVO> softInfoList = (ArrayList<SoftInfoVO>)valueOperations.get("softinfolist");
        try {
            int total = iSoftApplyDao.selectSoftApplyCount(whereMap);
            List<SoftApplyHVO> dataList = iSoftApplyDao.selectSoftApplyList(PagingUtilVO.pagingContain(whereMap,total));
            if (dataList!=null){
                List<SoftApplyBVO> softApplyBVOList = iSoftApplyDao.selectSoftApplyBVOList();
                for (int i=0;i<dataList.size();i++){
                    SoftApplyHVO softApplyHVO = dataList.get(i);
                    List<SoftApplyBVO> list = new ArrayList<>();
                    for (int b=0;b<softApplyBVOList.size();b++){
                        SoftApplyBVO softApplyBVO = softApplyBVOList.get(b);
                        if (softApplyHVO.getPk_soft_apply().equals(softApplyBVO.getPk_soft_apply())){
                            list.add(softApplyBVO);
                        }
                    }
                    softApplyHVO.setSoftApplyBVOList(list);
                }
                dataMap.put("pagesize",PagingUtilVO.pagingContain(whereMap,total).get("pageSize"));
                dataMap.put("total",total);
                dataMap.put("datalist",dataList);
                dataMap.put("softinfolist",softInfoList);
                dataMap.put("softtypelist",softTypeList);
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
     * 修改软件使用需求申请字表信息
     * @param softApplyBVO
     * @return
     * @throws GlobalException
     */
    @Override
    public Map updateSoftApply(SoftApplyBVO softApplyBVO) throws GlobalException {
        Map map = new HashMap<>();
        int result = 0;
        try {
            if (!softApplyBVO.isOperation()){
                SoftTypeVO softTypeVO = new SoftTypeVO();
                softTypeVO.setName(softApplyBVO.getPk_softtype());
                softTypeVO.setIsfree(1);
                Map softTypeMap = iSoftTypeService.insertSoftType(softTypeVO);
                SoftInfoVO softInfoVO = new SoftInfoVO();
                softInfoVO.setName(softApplyBVO.getPk_soft());
                softInfoVO.setVersion(softApplyBVO.getVersion());
                softInfoVO.setPk_softtype((String) softTypeMap.get("pk_softtype"));
                Map softInfoMap = iSoftInfoService.insertSoftInfo(softInfoVO);
                softApplyBVO.setPk_soft((String) softInfoMap.get("pk_soft"));
                softApplyBVO.setPk_softtype((String) softTypeMap.get("pk_softtype"));
            }
                result =iSoftApplyDao.updateSoftApply(softApplyBVO);
            if (result>0){
                map.put("success",true);
            }else {
                map.put("success",false);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 保存软件使用需求申请子表信息
     * @param softApplyBVOList
     * @return
     * @throws GlobalException
     */
    @Override
    public Map insertSoftApplyBVO(List<SoftApplyBVO> softApplyBVOList) throws GlobalException {
        Map map = new HashMap<>();
        SnowflakeIdWorker worker = new SnowflakeIdWorker(9, 9);
        try {
            for (int i =0;i<softApplyBVOList.size();i++){
                String pk_soft_apply_b = new StringBuffer(ConstantVO.PK_SOFT_APPLY_B).append(Long.toString(worker.nextId())).toString();
                SoftApplyBVO softApplyBVO = softApplyBVOList.get(i);
                softApplyBVO.setPk_soft_apply_b(pk_soft_apply_b);
                if (!softApplyBVO.isOperation()){
                    SoftTypeVO softTypeVO = new SoftTypeVO();
                    softTypeVO.setName(softApplyBVO.getPk_softtype());
                    softTypeVO.setIsfree(1);
                    Map softTypeMap = iSoftTypeService.insertSoftType(softTypeVO);
                    SoftInfoVO softInfoVO = new SoftInfoVO();
                    softInfoVO.setName(softApplyBVO.getPk_soft());
                    softInfoVO.setVersion(softApplyBVO.getVersion());
                    softInfoVO.setPk_softtype((String) softTypeMap.get("pk_softtype"));
                    Map softInfoMap = iSoftInfoService.insertSoftInfo(softInfoVO);
                    softApplyBVO.setPk_soft((String) softInfoMap.get("pk_soft"));
                    softApplyBVO.setPk_softtype((String) softTypeMap.get("pk_softtype"));
                }
            }
            int result = iSoftApplyDao.insertSoftApplyBVO(softApplyBVOList);
            if (result>0){
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
     * 删除软件需求申请表子表信息
     * @param list
     * @return
     * @throws GlobalException
     */
    @Override
    public Map deleteSoftApplyB(List<String> list) throws GlobalException {
        Map map = new HashMap<>();
        try {
            int result = iSoftApplyDao.deleteSoftApplyB(list);
            if (result>0){
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
     * 删除软件需求申请表主表信息
     * @param whereMap
     * @return
     * @throws GlobalException
     */
    @Override
    public Map deleteSoftApply(Map whereMap) throws GlobalException {
        Map map = new HashMap<>();
        try {
            int result = iSoftApplyDao.deleteSoftApply(whereMap);
            if (result>0){
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
     * 修改软件申请表状态
     * @throws GlobalException
     */
    @Override
    public void  updateSoftApplyBillstatus(SoftApplyHVO softApplyHVO)throws GlobalException{
        try {
            iSoftApplyDao.updateSoftApplyBillstatus(softApplyHVO);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
    }

    /**
     * 查询正在审核的申请
     * @return
     * @throws GlobalException
     */
    @Override
    public List<SoftApplyHVO> selectSoftApplyIsApply(Map map) throws GlobalException {
        List<SoftApplyHVO> softApplyHVOList =null;
        List<SoftApplyBVO> softApplyBVOList=null;
        try {
            softApplyHVOList = iSoftApplyDao.selectSoftApplyIsApply(map);
            softApplyBVOList = iSoftApplyDao.selectSoftApplyBVOList();
            for (int i=0;i<softApplyHVOList.size();i++){
                SoftApplyHVO softApplyHVO = softApplyHVOList.get(i);
                List<SoftApplyBVO> list = new ArrayList<>();
                for (int b=0;b<softApplyBVOList.size();b++){
                    SoftApplyBVO softApplyBVO = softApplyBVOList.get(b);
                    if (softApplyHVO.getPk_soft_apply().equals(softApplyBVO.getPk_soft_apply())){
                        list.add(softApplyBVO);
                    }
                }
                softApplyHVO.setSoftApplyBVOList(list);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
        return softApplyHVOList;
    }

    /**
     * 验证验证软件类型名称和软件信息名称
     * @param map
     * @return
     * @throws GlobalException
     */
    @Override
    public Map testSoftTypeAndSoftInfo(Map map) throws GlobalException {
        Map resultMap = new HashMap<>();
        try {
            int i = iSoftInfoDao.selectSoftInfoCountByName((String) map.get("pk_soft"));
            int b = iSoftTypeDao.selectSoftTypeCountBynameAndcode((String)map.get("pk_softtype"));
            if (i!=0 || b!=0){
                resultMap.put("success",false);
                resultMap.put("info","软件类型或软件信息重复，请重新输入！");
            }else {
                resultMap.put("success",true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
        return resultMap;
    }
}
