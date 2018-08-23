package com.bjfe.genuine.software.invoicingsystem.service.computer.impl;

import com.bjfe.genuine.software.invoicingsystem.mapper.computer.IComputerDao;
import com.bjfe.genuine.software.invoicingsystem.mapper.dept.IDeptDao;
import com.bjfe.genuine.software.invoicingsystem.mapper.org.IOrgDao;
import com.bjfe.genuine.software.invoicingsystem.mapper.psndoc.IPsndocDao;
import com.bjfe.genuine.software.invoicingsystem.mapper.software.ISoftInfoDao;
import com.bjfe.genuine.software.invoicingsystem.model.computer.ComputerVO;
import com.bjfe.genuine.software.invoicingsystem.model.dept.DeptVO;
import com.bjfe.genuine.software.invoicingsystem.model.job.JobVO;
import com.bjfe.genuine.software.invoicingsystem.model.org.OrgVO;
import com.bjfe.genuine.software.invoicingsystem.model.psndoc.PsndocVO;
import com.bjfe.genuine.software.invoicingsystem.model.pub.constant.ConstantVO;
import com.bjfe.genuine.software.invoicingsystem.model.pub.exception.GlobalException;
import com.bjfe.genuine.software.invoicingsystem.model.rjxx.SoftInfoVO;
import com.bjfe.genuine.software.invoicingsystem.service.computer.IComputerService;
import com.bjfe.genuine.software.invoicingsystem.util.annotation.InitComputerParam;
import com.bjfe.genuine.software.invoicingsystem.util.annotation.InitParam;
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
 * Created by Administrator on 2018/1/16.
 */
@Service("iComputerService")
public class ComputerServiceImpl implements IComputerService{
    @Autowired
    private RedisTemplate<String,Serializable> redisTemplate;
    @Resource(name = "IComputerDao")
    private IComputerDao iComputerDao;
    @Resource(name = "IOrgDao")
    private IOrgDao iOrgDao;
    @Resource(name = "IDeptDao")
    private IDeptDao iDeptDao;
    @Resource(name = "IPsndocDao")
    private IPsndocDao iPsndocDao;
    @Resource(name = "ISoftInfoDao")
    private ISoftInfoDao iSoftInfoDao;

    /**
     * 添加计算机管理信息
     * @param computerVO
     * @return
     * @throws GlobalException
     */
    @Override
    @InitComputerParam
    public Map insertComputer(ComputerVO computerVO) throws GlobalException {
        Map map = new HashMap<>();
        try {
            ComputerVO computerVOTemp = iComputerDao.selectComputerByCode(computerVO.getCode());
            if (computerVOTemp ==null){
                SnowflakeIdWorker worker = new SnowflakeIdWorker(9, 9);
                String pk_computer = new StringBuffer(ConstantVO.PK_COMPUTER).append(Long.toString(worker.nextId())).toString();
                computerVO.setPk_computer(pk_computer);
                int result = iComputerDao.insertComputer(computerVO);
                if (result ==1){
                    map.put("pk_computer",computerVO.getPk_computer());
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
            throw new GlobalException(e.getMessage());
        }
        return map;
    }

    /**
     * 显示计算机管理信息
     * @return
     * @throws GlobalException
     */
    @Override
    public Map selectComputerList(Map whereMap) throws GlobalException {
        Map map = new HashMap<>();
        ValueOperations<String, Serializable> valueOperations = redisTemplate.opsForValue();
        List<PsndocVO> personList = (ArrayList<PsndocVO>)valueOperations.get("psndoclist");
        List<ComputerVO> computerList = (ArrayList<ComputerVO>)valueOperations.get("computerlist");
        List<SoftInfoVO> softInfoList = (ArrayList<SoftInfoVO>)valueOperations.get("softinfolist");
        List<PsndocVO> listTemp = new ArrayList<>();
        for (int i=0;i<personList.size();i++){
            if (whereMap.get("pk_org").equals(personList.get(i).getOrg_pk())){
                listTemp.add(personList.get(i));
            }
        }
        Map dataMap = PagingUtilVO.pagingListContain(whereMap,computerList);
        map.put("pagenumber",dataMap.get("pagenumber"));
        map.put("pageCount",dataMap.get("pageCount"));
        map.put("pageSize",dataMap.get("pageSize"));
        map.put("total",dataMap.get("total"));
        map.put("computerlist",dataMap.get("datalist"));
        map.put("psndoclist",listTemp);
        map.put("softinfolist",softInfoList);
        map.put("success",true);
        return map;
    }

    /**
     * 修改计算机管理信息
     * @param computerVO
     * @return
     * @throws GlobalException
     */
    @Override
    @InitComputerParam
    public Map updateComputer(ComputerVO computerVO) throws GlobalException {
        Map map = new HashMap<>();
        try {
            int result = iComputerDao.updateComputer(computerVO);
            if (result ==1){
                map.put("success",true);
            }else {
                map.put("success",false);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
        return  map;
    }

    /**
     * 删除计算机管理信息
     * @param list
     * @return
     * @throws GlobalException
     */
    @Override
    @InitComputerParam
    public Map deleteComputer(List<String> list) throws GlobalException {
        Map map =new HashMap<>();
        ValueOperations<String, Serializable> valueOperations = redisTemplate.opsForValue();
        try {
            int result =iComputerDao.deleteComputer(list);
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
}
