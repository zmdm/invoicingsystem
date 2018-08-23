package com.bjfe.genuine.software.invoicingsystem.service.softorder.impl;

import com.bjfe.genuine.software.invoicingsystem.mapper.softapply.ISoftApplyDao;
import com.bjfe.genuine.software.invoicingsystem.mapper.softorder.ISoftOrderDao;
import com.bjfe.genuine.software.invoicingsystem.mapper.software.ISoftInfoDao;
import com.bjfe.genuine.software.invoicingsystem.mapper.software.ISoftTypeDao;
import com.bjfe.genuine.software.invoicingsystem.model.pub.constant.ConstantVO;
import com.bjfe.genuine.software.invoicingsystem.model.pub.exception.GlobalException;
import com.bjfe.genuine.software.invoicingsystem.model.rjlx.SoftTypeVO;
import com.bjfe.genuine.software.invoicingsystem.model.rjxx.SoftInfoVO;
import com.bjfe.genuine.software.invoicingsystem.model.softorder.SoftOrderBVO;
import com.bjfe.genuine.software.invoicingsystem.model.softorder.SoftOrderHVO;
import com.bjfe.genuine.software.invoicingsystem.service.softorder.ISoftOrderService;
import com.bjfe.genuine.software.invoicingsystem.util.paging.PagingUtilVO;
import com.bjfe.genuine.software.invoicingsystem.util.pk.SnowflakeIdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by Administrator on 2018/1/11.
 */

/**
 * 软件采购计划表业务逻辑实现类
 * 编写人：宋超洋
 */
@Service("iSoftOrderService")
public class SoftOrderServiceImpl implements ISoftOrderService{
    @Autowired
    private RedisTemplate<String,Serializable> redisTemplate;
    @Resource(name = "ISoftOrderDao")
    private ISoftOrderDao iSoftOrderDao;
    @Resource(name = "ISoftApplyDao")
    private ISoftApplyDao iSoftApplyDao;
    @Resource(name = "ISoftTypeDao")
    private ISoftTypeDao ISoftTypeDao;
    @Resource(name = "ISoftInfoDao")
    private ISoftInfoDao ISoftInfoDao;
    /**
     * 保存软件采购计划表
     * @param softOrderHVO
     * @return
     * @throws GlobalException
     */
    @Override
    public Map insertSoftOrder(SoftOrderHVO softOrderHVO) throws GlobalException {
        Map map =new HashMap<>();
        try {
            SnowflakeIdWorker worker = new SnowflakeIdWorker(9, 9);
            String pk_soft_order = new StringBuffer(ConstantVO.PK_SOFT_ORDER).append(Long.toString(worker.nextId())).toString();
            softOrderHVO.setPk_soft_order(pk_soft_order);
            int result = iSoftOrderDao.insertSoftOrderHVO(softOrderHVO);
            if (result >0){
                List<SoftOrderBVO> list = softOrderHVO.getSoftOrderBVOList();
                List<String> stringList = new ArrayList<>();
                for (int i=0;i<list.size();i++){
                    String pk_soft_order_b = new StringBuffer(ConstantVO.PK_SOFT_ORDER_B).append(Long.toString(worker.nextId())).toString();
                    SoftOrderBVO softOrderBVO  = list.get(i);
                    softOrderBVO.setPk_soft_order(softOrderHVO.getPk_soft_order());
                    softOrderBVO.setPk_soft_order_b(pk_soft_order_b);
                    stringList.add(softOrderBVO.getPk_soft_apply());
                }
                List newList = new ArrayList<>(new HashSet(stringList));
                iSoftOrderDao.insertSoftOrderBVO(list);
                iSoftApplyDao.updateSoftApplyBVOBillstatusBypk_softapply(newList);
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
     * 条件查询软件采购计划表
     * @param whereMap
     * @return
     * @throws GlobalException
     */
    @Override
    public Map selectSoftOrderList(Map whereMap) throws GlobalException {
        Map dataMap = new HashMap<>();
        ValueOperations<String, Serializable> valueOperations = redisTemplate.opsForValue();
        List<SoftTypeVO> softTypeList = (ArrayList<SoftTypeVO>)valueOperations.get("softtypelist");
        List<SoftInfoVO> softInfoList = (ArrayList<SoftInfoVO>)valueOperations.get("softinfolist");
        try {
            int total = iSoftOrderDao.selectSoftOrderCount(whereMap);
            List<SoftOrderHVO> dataList = iSoftOrderDao.selectSoftOrderList(PagingUtilVO.pagingContain(whereMap,total));
            if (dataList!=null){
                List<SoftOrderBVO> softOrderBVOList = iSoftOrderDao.selectSoftOrderBVOList();
                for (int i=0;i<dataList.size();i++){
                    SoftOrderHVO softOrderHVO = dataList.get(i);
                    List<SoftOrderBVO> list = new ArrayList<>();
                    for (int b=0;b<softOrderBVOList.size();b++){
                        SoftOrderBVO softOrderBVO = softOrderBVOList.get(b);
                        if (softOrderHVO.getPk_soft_order().equals(softOrderBVO.getPk_soft_order())){
                            list.add(softOrderBVO);
                        }
                    }
                    softOrderHVO.setSoftOrderBVOList(list);
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
     * 修改软件采购计划表
     * @param softOrderBVOList
     * @return
     * @throws GlobalException
     */
    @Override
    public Map updateSoftOrder(List<SoftOrderBVO> softOrderBVOList) throws GlobalException {
        Map map = new HashMap<>();
        int result = 0;
        try {
            for (int i=0;i<softOrderBVOList.size();i++){
                result +=iSoftOrderDao.updateSoftOrder(softOrderBVOList.get(i));
            }
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
     * 保存软件采购计划子表信息
     * @param softOrderBVOList
     * @return
     * @throws GlobalException
     */
    @Override
    public Map insertSoftOrderBVO(List<SoftOrderBVO> softOrderBVOList) throws GlobalException {
        Map map = new HashMap<>();
        try {
            SnowflakeIdWorker worker = new SnowflakeIdWorker(9, 9);
            for (int i =0;i<softOrderBVOList.size();i++){
                String pk_soft_order_b = new StringBuffer(ConstantVO.PK_SOFT_APPLY_B).append(Long.toString(worker.nextId())).toString();
                SoftOrderBVO softOrderBVO = softOrderBVOList.get(i);
                softOrderBVO.setPk_soft_order_b(pk_soft_order_b);
            }
            int result = iSoftOrderDao.insertSoftOrderBVO(softOrderBVOList);
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
     * 查询经办人通过审批的软件使用需求申请
     * @return
     * @throws SQLException
     */
    public Map selectSoftApplyIsOk(String creator)throws GlobalException{
        Map map =new HashMap<>();
        try {
            List<SoftOrderBVO> list = iSoftOrderDao.selectSoftApplyIsOk(creator);
            if (list!=null){
                //判断查询结果是否-免费软件类型
                if(list.size() > 0){
                    String[] pk_softs = new String[list.size()];//where 条件
                    for(int i=0;i < list.size();i++){
                        String pk_soft = list.get(i).getSoft_pk() == null ? "" : list.get(i).getSoft_pk();
                        pk_softs[i] = pk_soft;
                    }
//                    SoftOrderBVO softOrderVo = list.get(0);
//                    String softPk = softOrderVo.getSoft_pk();//子表的软件类型主键
                    List<SoftInfoVO> infoVo = ISoftInfoDao.selectSoftVoByPkSoft(pk_softs);

                    //初始化符合条件的VO集合
                    List<SoftOrderBVO> rstlist = new ArrayList<SoftOrderBVO>();

                    for(int i=0;i<list.size();i++){
                        SoftOrderBVO orderBBVO = list.get(i);
                        String orderPkSoft = orderBBVO.getSoft_pk();
                        for(int j=0;j<infoVo.size();j++){
                            SoftInfoVO infoVVo = infoVo.get(j);
                            String infoPkSoft = infoVVo.getPk_soft();
                            if(orderPkSoft != null && orderPkSoft.equals(infoPkSoft)){
                                rstlist.add(orderBBVO);//将对比过后pk_soft符合条件的数据放在初始化结果集rstlist中
                            }
                        }
                    }

                    if(rstlist.size() > 0){
                        map.put("success",true);
                        map.put("datalist",rstlist);
                    }
                }
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
     * 修改软件采购状态
     * @param softOrderHVO
     * @throws GlobalException
     */
    @Override
    public void updateSoftOrderBillstatus(SoftOrderHVO softOrderHVO) throws GlobalException {
        try {
            iSoftOrderDao.updateSoftOrderBillstatus(softOrderHVO);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
    }

    /**
     * 查询正在审核的采购申请
     * @return
     * @throws GlobalException
     */
    @Override
    public List<SoftOrderHVO> selectSoftOrderIsApply(Map map) throws GlobalException {
        List<SoftOrderHVO> softOrderHVOList = null;
        List<SoftOrderBVO> softOrderBVOList = null;
        try {
            softOrderHVOList = iSoftOrderDao.selectSoftOrderIsApply(map);
            softOrderBVOList = iSoftOrderDao.selectSoftOrderBVOList();
            for (int i=0;i<softOrderHVOList.size();i++){
                SoftOrderHVO softOrderHVO = softOrderHVOList.get(i);
                List<SoftOrderBVO> list = new ArrayList<>();
                for (int b=0;b<softOrderBVOList.size();b++){
                    SoftOrderBVO softOrderBVO = softOrderBVOList.get(b);
                    if (softOrderHVO.getPk_soft_order().equals(softOrderBVO.getPk_soft_order())){
                        list.add(softOrderBVO);
                    }
                }
                softOrderHVO.setSoftOrderBVOList(list);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
        return softOrderHVOList;
    }
}
