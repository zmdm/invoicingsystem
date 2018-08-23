package com.bjfe.genuine.software.invoicingsystem.service.softsummary.impl;

import com.bjfe.genuine.software.invoicingsystem.mapper.computer.IComputerDao;
import com.bjfe.genuine.software.invoicingsystem.mapper.psndoc.IPsndocDao;
import com.bjfe.genuine.software.invoicingsystem.mapper.softorder.ISoftOrderDao;
import com.bjfe.genuine.software.invoicingsystem.mapper.softsummary.ISoftSummaryDao;
import com.bjfe.genuine.software.invoicingsystem.mapper.software.ISoftInfoDao;
import com.bjfe.genuine.software.invoicingsystem.mapper.software.ISoftTypeDao;
import com.bjfe.genuine.software.invoicingsystem.model.pub.constant.ConstantVO;
import com.bjfe.genuine.software.invoicingsystem.model.pub.exception.GlobalException;
import com.bjfe.genuine.software.invoicingsystem.model.rjlx.SoftTypeVO;
import com.bjfe.genuine.software.invoicingsystem.model.rjxx.SoftInfoVO;
import com.bjfe.genuine.software.invoicingsystem.model.softsummary.SoftSummaryBVO;
import com.bjfe.genuine.software.invoicingsystem.model.softsummary.SoftSummaryHVO;
import com.bjfe.genuine.software.invoicingsystem.service.softsummary.ISoftSummaryService;
import com.bjfe.genuine.software.invoicingsystem.util.excel.ExcelImportUtil;
import com.bjfe.genuine.software.invoicingsystem.util.paging.PagingUtilVO;
import com.bjfe.genuine.software.invoicingsystem.util.pk.DateUtil;
import com.bjfe.genuine.software.invoicingsystem.util.pk.SnowflakeIdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/27.
 */

/**
 * 软件使用情况业务逻辑实现类
 * 编写人：宋超洋
 */
@Service("iSoftSummaryService")
public class SoftSummaryServiceImpl implements ISoftSummaryService{
    @Autowired
    private RedisTemplate<String,Serializable> redisTemplate;
    @Resource(name = "ISoftSummaryDao")
    private ISoftSummaryDao iSoftSummaryDao;
    @Resource(name = "IPsndocDao")
    private IPsndocDao IPsndocDao;
    @Resource(name = "IComputerDao")
    private IComputerDao iComputerDao;
    @Resource(name = "ISoftOrderDao")
    private ISoftOrderDao iSoftOrderDao;

    /**
     * 查询显示软件使用情况
     * @param whereMap
     * @return
     * @throws GlobalException
     */
    @Override
    public Map selectSoftSummary(Map whereMap) throws GlobalException {
        Map dataMap=null;//该方法的返回值
        ValueOperations<String, Serializable> valueOperations = redisTemplate.opsForValue();
        List<SoftInfoVO> softInfoList = (ArrayList<SoftInfoVO>)valueOperations.get("softinfolist");
        List<SoftTypeVO> softTypeList = (ArrayList<SoftTypeVO>)valueOperations.get("softtypelist");
        try {
            int total = iSoftSummaryDao.selectSoftSummaryBVOCountByPk_org(whereMap);
            List<SoftSummaryBVO> dataList = iSoftSummaryDao.selectSoftSummaryBVOByPk_org(PagingUtilVO.pagingContain(whereMap,total));
            if (dataList!=null){
                dataMap=new HashMap<>();
                dataMap.put("pagesize",PagingUtilVO.pagingContain(whereMap,total).get("pageSize"));
                dataMap.put("total",total);
                dataMap.put("datalist",dataList);
                dataMap.put("softinfolist",softInfoList);
                dataMap.put("softtypelist",softTypeList);
                dataMap.put("personcount",IPsndocDao.selectPsndocCount(whereMap));
                dataMap.put("usecount",iComputerDao.selectUseCount(whereMap));
                dataMap.put("computercount",iComputerDao.selectComputerCountByPk_org(whereMap));
                dataMap.put("servercount",null);
                dataMap.put("success",true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
        return dataMap;
    }

    /**
     * 导入excel
     * @param file
     * @param fileName
     * @return
     * @throws GlobalException
     */
    @Override
    public Map importEcexl(MultipartFile file, String fileName,Map commonMap) throws GlobalException {
        Map map=new HashMap<>();
        ValueOperations<String, Serializable> valueOperations = redisTemplate.opsForValue();
        List<SoftInfoVO> softInfoList = (ArrayList<SoftInfoVO>)valueOperations.get("softinfolist");
        List<SoftTypeVO> softTypeList = (ArrayList<SoftTypeVO>)valueOperations.get("softtypelist");
        try {
            commonMap.put("softinfolist",softInfoList);
            commonMap.put("softtypelist",softTypeList);
            commonMap.put("sheet",1);
            int temp = 0;
            Map dataMap = ExcelImportUtil.getExcelInfo(fileName,file,commonMap);
            if (dataMap.get("error")==null){
                SnowflakeIdWorker worker = new SnowflakeIdWorker(9, 9);
                SoftSummaryHVO softSummaryHVO = (SoftSummaryHVO) valueOperations.get("softsummary"+commonMap.get("pk_org")+commonMap.get("creator"));
                if (softSummaryHVO ==null){
                    softSummaryHVO = new SoftSummaryHVO();
                    String pk_soft_summary = new StringBuffer(ConstantVO.PK_SOFT_SUMMARY).append(Long.toString(worker.nextId())).toString();
                    softSummaryHVO.setCreationtime(DateUtil.getTime2());
                    softSummaryHVO.setPk_org((String) commonMap.get("pk_org"));
                    softSummaryHVO.setCreator((String) commonMap.get("creator"));
                    softSummaryHVO.setPk_soft_summary(pk_soft_summary);
                    iSoftSummaryDao.insertSoftSummaryHVO(softSummaryHVO);
                    valueOperations.set("softsummary"+softSummaryHVO.getPk_org()+softSummaryHVO.getCreator(),(Serializable) softSummaryHVO);
                }
                int count = iSoftSummaryDao.selectSoftSummaryBVOCountByPk_org(commonMap);
                List<SoftSummaryBVO> softSummaryBVOList = (ArrayList)dataMap.get("softSummaryBVO");
                for (int i=0;i<softSummaryBVOList.size();i++){
                    SoftSummaryBVO softSummaryBVO=softSummaryBVOList.get(i);
                    if( softSummaryBVO.getPk_soft()!=null && softSummaryBVO.getPk_softtype()!=null){
                        String pk_soft_summary_b = new StringBuffer(ConstantVO.PK_SOFT_SUMMARY_B).append(Long.toString(worker.nextId())).toString();
                        softSummaryBVO.setPk_soft_summary(softSummaryHVO.getPk_soft_summary());
                        softSummaryBVO.setPk_soft_summary_b(pk_soft_summary_b);
                        softSummaryBVO.setCrowno((count+1));
                        temp+=1;
                    }
                }
                if (temp ==softSummaryBVOList.size()){
                    iSoftSummaryDao.insertSoftSummaryBVO(softSummaryBVOList);
                    map.put("datalist",dataMap.get("datalist"));
                    map.put("success",true);
                    map.put("info","上传成功!");
                }
            }else {
                map.put("success",false);
                map.put("info",dataMap.get("error"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
        return map;
    }

    /**
     * 添加软件使用情况汇总
     * @param map
     * @throws GlobalException
     */
    @Override
    public void insertSoftSummary(Map map) throws GlobalException {
        ValueOperations<String, Serializable> valueOperations = redisTemplate.opsForValue();
        SnowflakeIdWorker worker = new SnowflakeIdWorker(9, 9);
        try {
            SoftSummaryHVO softSummaryHVO = (SoftSummaryHVO) valueOperations.get("softsummary"+map.get("pk_org")+map.get("creator"));
            if (softSummaryHVO ==null){
                softSummaryHVO = new SoftSummaryHVO();
                String pk_soft_summary = new StringBuffer(ConstantVO.PK_SOFT_SUMMARY).append(Long.toString(worker.nextId())).toString();
                softSummaryHVO.setCreationtime(DateUtil.getTime2());
                softSummaryHVO.setPk_org((String) map.get("pk_org"));
                softSummaryHVO.setCreator((String) map.get("creator"));
                softSummaryHVO.setPk_soft_summary(pk_soft_summary);
                iSoftSummaryDao.insertSoftSummaryHVO(softSummaryHVO);
                valueOperations.set("softsummary"+softSummaryHVO.getPk_org()+softSummaryHVO.getCreator(),(Serializable) softSummaryHVO);
            }
            SoftSummaryBVO softSummaryBVO = iSoftSummaryDao.selectSoftSummaryBVOBypk_softAndPK_org(map);
            Map conditonMap = iSoftOrderDao.selectSoftOrderBypk_softAndPk_org(map);
            if (softSummaryBVO==null){
                String pk_soft_summary_b = new StringBuffer(ConstantVO.PK_SOFT_SUMMARY_B).append(Long.toString(worker.nextId())).toString();
                softSummaryBVO = new SoftSummaryBVO();
                softSummaryBVO.setPk_soft_summary_b(pk_soft_summary_b);
                softSummaryBVO.setPk_soft_summary(softSummaryHVO.getPk_soft_summary());
                softSummaryBVO.setCrowno(1);
                softSummaryBVO.setPk_soft((String) map.get("pk_soft"));
                softSummaryBVO.setOrdertime((String) conditonMap.get("creationtime"));
                softSummaryBVO.setPrice((double)conditonMap.get("price"));
                softSummaryBVO.setPermistype("允许");
                softSummaryBVO.setPermisnum((int)conditonMap.get("ordnum"));
                softSummaryBVO.setMayversion((String) conditonMap.get("softversion"));
                softSummaryBVO.setPermisdead((String) conditonMap.get("permis"));
                softSummaryBVO.setInstallnum(1);
            }else if ((int)map.get("detailtype")==0){
                int a = softSummaryBVO.getInstallnum();
                softSummaryBVO.setInstallnum(a+1);
                iSoftSummaryDao.updateSoftSummaryBVOinstallnum(softSummaryBVO);
            }else if ((int)map.get("detailtype")==1){
                int a = softSummaryBVO.getInstallnum();
                softSummaryBVO.setInstallnum(a-1);
                iSoftSummaryDao.updateSoftSummaryBVOinstallnum(softSummaryBVO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
