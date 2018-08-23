package com.bjfe.genuine.software.invoicingsystem.service.tain.impl;

import com.bjfe.genuine.software.invoicingsystem.mapper.computer.IComputerDao;
import com.bjfe.genuine.software.invoicingsystem.mapper.psndoc.IPsndocDao;
import com.bjfe.genuine.software.invoicingsystem.mapper.software.ISoftInfoDao;
import com.bjfe.genuine.software.invoicingsystem.mapper.tain.ITainDao;
import com.bjfe.genuine.software.invoicingsystem.model.computer.ComputerVO;
import com.bjfe.genuine.software.invoicingsystem.model.psndoc.PsndocVO;
import com.bjfe.genuine.software.invoicingsystem.model.pub.constant.ConstantVO;
import com.bjfe.genuine.software.invoicingsystem.model.pub.exception.GlobalException;
import com.bjfe.genuine.software.invoicingsystem.model.rjxx.SoftInfoVO;
import com.bjfe.genuine.software.invoicingsystem.model.tain.TainVO;
import com.bjfe.genuine.software.invoicingsystem.service.tain.ITainService;
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
 * Created by Administrator on 2018/1/17.
 */

/**
 * 软件安装维护业务逻辑实现类
 * 编写人：宋超洋
 */
@Service("iTainService")
public class TainServiceImpl implements ITainService{
    @Autowired
    private RedisTemplate<String,Serializable> redisTemplate;
    @Resource(name = "ITainDao")
    private ITainDao iTainDao;

    /**
     * 添加软件安装维护申请
     * @param tainVO
     * @return
     * @throws GlobalException
     */
    @Override
    public Map insertTain(TainVO tainVO) throws GlobalException {
        Map map =new HashMap<>();
        try {
            SnowflakeIdWorker worker = new SnowflakeIdWorker(9, 9);
            String pk_tain = new StringBuffer(ConstantVO.PK_TAIN).append(Long.toString(worker.nextId())).toString();
            tainVO.setPk_soft_tain(pk_tain);
            int result = iTainDao.insertTain(tainVO);
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
     * 显示软件安装维护申请
     * @return
     * @throws GlobalException
     */
    @Override
    public Map selectTainList(Map map) throws GlobalException {
        Map dataMap = new HashMap<>();
        ValueOperations<String, Serializable> valueOperations = redisTemplate.opsForValue();
        List<SoftInfoVO> softInfoList = (ArrayList<SoftInfoVO>)valueOperations.get("softinfolist");
        List<ComputerVO> computerList = (ArrayList<ComputerVO>)valueOperations.get("computerlist");
        List<TainVO> dataList = null;
        try {
            int total =0;
            String creator = (String) map.get("creator");
            if ("信息化部".equals(map.get("deptname"))){
                map.remove("creator");
                total = iTainDao.selectTainCount(map);
                List<TainVO> listTemp = iTainDao.selectTainList(PagingUtilVO.pagingContain(map,total));
                dataList = new ArrayList<>();
                for (int i=0;i<listTemp.size();i++){
                    TainVO tainVO = listTemp.get(i);
                    if (creator.equals(tainVO.getCreator()) || creator.equals(tainVO.getInstall_pk())){
                        dataList.add(tainVO);
                    }
                }
            }else {
                total = iTainDao.selectTainCount(map);
                dataList = iTainDao.selectTainList(PagingUtilVO.pagingContain(map,total));
            }
            if (dataList!=null){
                List<ComputerVO> computerVOList = new ArrayList<>();
                for (int i=0;i<computerList.size();i++){
                    if (creator.equals(computerList.get(i).getPsndoc_pk())){
                        computerVOList.add(computerList.get(i));
                    }
                }
                dataMap.put("pagesize",PagingUtilVO.pagingContain(map,total).get("pageSize"));
                dataMap.put("total",total);
                dataMap.put("datalist",dataList);
                dataMap.put("softinfolist",softInfoList);
                dataMap.put("computerlist",computerVOList);
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
     * 修改软件安装维护申请
     * @param tainVO
     * @return
     * @throws GlobalException
     */
    @Override
    public Map updateTain(TainVO tainVO) throws GlobalException {
        Map map = new HashMap<>();
        try {
            int result = iTainDao.updateTaninByPk(tainVO);
            if (result==1){
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
     * 软件安装维护申请确认
     * @param tainVO
     * @return
     * @throws GlobalException
     */
    @Override
    public Map tainIsOK(TainVO tainVO) throws GlobalException {
        Map map = new HashMap<>();
        try {
            int result = iTainDao.TainIsOK(tainVO);
            if (result ==1){
                map.put("success",true);
                map.put("usertime",tainVO.getUsertime());
                map.put("installtime",tainVO.getInstalltime());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
        return map;
    }

    /**
     * 分配安装维护人
     * @param whereMap
     * @return
     * @throws GlobalException
     */
    /*@Override
    public Map distributionTain(Map whereMap) throws GlobalException {
        Map map = new HashMap<>();
        try {
            int result = iTainDao.distributionTain(whereMap);
            if (result == 1){
                map.put("success",true);
            }else {
                map.put("success",false);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
        return map;
    }*/

    /**
     * 修改软件采购状态
     * @param tainVO
     * @throws GlobalException
     */
    @Override
    public void updateSoftTainBillstatus(TainVO tainVO) throws GlobalException {
        try {
            iTainDao.updateSoftTainBillstatus(tainVO);
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
    public Map selectSoftTainIsApply(Map map) throws GlobalException {
        Map resultMap = new HashMap<>();
        List<TainVO> list = null;
        ValueOperations<String, Serializable> valueOperations = redisTemplate.opsForValue();
        List<PsndocVO> personList = (ArrayList<PsndocVO>)valueOperations.get("psndoclist");
        try {
            List<PsndocVO> psndocVOlist = new ArrayList<>();
            for (int i=0;i<personList.size();i++){
                if (map.get("pk_org").equals(personList.get(i).getOrg_pk()) && map.get("deptname").equals(personList.get(i).getPk_dept())){
                    psndocVOlist.add(personList.get(i));
                }
            }
            list = iTainDao.selectSoftTainIsApply(map);
            resultMap.put("psndoclist",psndocVOlist);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
        resultMap.put("list",list);
        return resultMap;
    }
}
