package com.bjfe.genuine.software.invoicingsystem.service.softmaindetail.impl;

import com.bjfe.genuine.software.invoicingsystem.mapper.computer.IComputerDao;
import com.bjfe.genuine.software.invoicingsystem.mapper.dept.IDeptDao;
import com.bjfe.genuine.software.invoicingsystem.mapper.psndoc.IPsndocDao;
import com.bjfe.genuine.software.invoicingsystem.mapper.softmaindetail.ISoftMaindetailDao;
import com.bjfe.genuine.software.invoicingsystem.mapper.softorder.ISoftOrderDao;
import com.bjfe.genuine.software.invoicingsystem.mapper.software.ISoftInfoDao;
import com.bjfe.genuine.software.invoicingsystem.mapper.software.ISoftTypeDao;
import com.bjfe.genuine.software.invoicingsystem.model.computer.ComputerVO;
import com.bjfe.genuine.software.invoicingsystem.model.dept.DeptVO;
import com.bjfe.genuine.software.invoicingsystem.model.psndoc.PsndocVO;
import com.bjfe.genuine.software.invoicingsystem.model.pub.constant.ConstantVO;
import com.bjfe.genuine.software.invoicingsystem.model.pub.exception.GlobalException;
import com.bjfe.genuine.software.invoicingsystem.model.rjlx.SoftTypeVO;
import com.bjfe.genuine.software.invoicingsystem.model.rjxx.SoftInfoVO;
import com.bjfe.genuine.software.invoicingsystem.model.softmaindetail.SoftMaindetailBVO;
import com.bjfe.genuine.software.invoicingsystem.model.softmaindetail.SoftMaindetailHVO;
import com.bjfe.genuine.software.invoicingsystem.model.softmaindetail.SoftMaindetailSubVO;
import com.bjfe.genuine.software.invoicingsystem.model.tain.TainVO;
import com.bjfe.genuine.software.invoicingsystem.service.softmaindetail.ISoftMaindetailService;
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
 * Created by Administrator on 2018/1/28.
 */

/**
 * 软件安装维护明细业务逻辑实现类
 * 编写人：宋超洋
 */
@Service("iSoftMaindetailService")
public class SoftMaindetailServiceImpl implements ISoftMaindetailService {
    @Autowired
    private RedisTemplate<String, Serializable> redisTemplate;
    @Resource(name = "ISoftMaindetailDao")
    private ISoftMaindetailDao iSoftMaindetailDao;
    @Resource(name = "ISoftOrderDao")
    private ISoftOrderDao iSoftOrderDao;

    /**
     * 查询显示软件安装维护明细信息
     * @param whereMap
     * @return
     * @throws GlobalException
     */
    @Override
    public Map selectSoftMaindetailList(Map whereMap) throws GlobalException {
        Map dataMap = new HashMap<>();
        ValueOperations<String, Serializable> valueOperations = redisTemplate.opsForValue();
        List<SoftInfoVO> softInfoList = (ArrayList<SoftInfoVO>) valueOperations.get("softinfolist");
        List<SoftTypeVO> softTypeList = (ArrayList<SoftTypeVO>) valueOperations.get("softtypelist");
        try {
            int total = iSoftMaindetailDao.selectSoftMaindetailBVOCount(whereMap);
            List<SoftMaindetailBVO> softMaindetailList = iSoftMaindetailDao.selectSoftMaindetailBVOList(PagingUtilVO.pagingContain(whereMap, total));
            List<SoftMaindetailSubVO> softMaindetailSubList = iSoftMaindetailDao.selectSoftMaindetailSubVOList(whereMap);
            if (softMaindetailList != null) {
                for (int i = 0; i < softMaindetailList.size(); i++) {
                    SoftMaindetailBVO softMaindetailBVO = softMaindetailList.get(i);
                    List<SoftMaindetailSubVO> listTemp = new ArrayList<>();
                    for (int b = 0; b < softMaindetailSubList.size(); b++) {
                        SoftMaindetailSubVO softMaindetailSubVO = softMaindetailSubList.get(b);
                        if (softMaindetailBVO.getPk_soft_maindetail_b().equals(softMaindetailSubVO.getPk_soft_maindetail_b())) {
                            listTemp.add(softMaindetailSubVO);
                        }
                    }
                    softMaindetailBVO.setSoftMaindetailSubList(listTemp);
                }
                dataMap = new HashMap<>();
                dataMap.put("pagesize", PagingUtilVO.pagingContain(whereMap, total).get("pageSize"));
                dataMap.put("total", total);
                dataMap.put("datalist", softMaindetailList);
                dataMap.put("softinfolist", softInfoList);
                dataMap.put("softtypelist", softTypeList);
                dataMap.put("success", true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
        return dataMap;
    }

    /**
     * 导入excel
     *
     * @param file
     * @param fileName
     * @return
     * @throws GlobalException
     */
    @Override
    public Map importEcexl(MultipartFile file, String fileName,Map commonMap) throws GlobalException {
        Map map = new HashMap<>();
        ValueOperations<String, Serializable> valueOperations = redisTemplate.opsForValue();
        List<SoftInfoVO> softInfoList = (ArrayList<SoftInfoVO>) valueOperations.get("softinfolist");
        List<SoftTypeVO> softTypeList = (ArrayList<SoftTypeVO>) valueOperations.get("softtypelist");
        List<ComputerVO> computerList = (ArrayList<ComputerVO>) valueOperations.get("computerlist");
        List<PsndocVO> psndocList = (ArrayList<PsndocVO>) valueOperations.get("psndoclist");
        List<DeptVO> deptlist = (ArrayList<DeptVO>) valueOperations.get("deptlist");
        try {
            commonMap.put("softinfolist", softInfoList);
            commonMap.put("softtypelist", softTypeList);
            commonMap.put("deptlist",deptlist);
            commonMap.put("psndoclist",psndocList);
            commonMap.put("computerlist",computerList);
            commonMap.put("sheet", 3);
            int temp = 0;
            Map dataMap = ExcelImportUtil.getExcelInfo(fileName, file, commonMap);
            if (dataMap.get("error") == null) {
                SnowflakeIdWorker worker = new SnowflakeIdWorker(9, 9);
                SoftMaindetailHVO softMaindetailHVO = (SoftMaindetailHVO) valueOperations.get("softmaindetail"+commonMap.get("pk_org")+commonMap.get("creator"));
                if (softMaindetailHVO == null) {
                    softMaindetailHVO = new SoftMaindetailHVO();
                    String pk_soft_detail = new StringBuffer(ConstantVO.PK_SOFT_DETAIL).append(Long.toString(worker.nextId())).toString();
                    softMaindetailHVO.setCreationtime(DateUtil.getTime2());
                    softMaindetailHVO.setPk_org((String) commonMap.get("pk_org"));
                    softMaindetailHVO.setCreator((String) commonMap.get("creator"));
                    softMaindetailHVO.setPk_soft_maindetail(pk_soft_detail);
                    iSoftMaindetailDao.insertSoftMaindetailHVO(softMaindetailHVO);
                    valueOperations.set("softmaindetail" + softMaindetailHVO.getPk_org() + softMaindetailHVO.getCreator(), (Serializable) softMaindetailHVO);
                }
                SoftMaindetailBVO softMaindetailBVO = (SoftMaindetailBVO) dataMap.get("softMaindetailBVO");
                commonMap.put("pk_soft",softMaindetailBVO.getPk_soft());
                softMaindetailBVO = iSoftMaindetailDao.selectSoftMaindetailBVOByPk_orgAndCreator(commonMap);
                if (softMaindetailBVO ==null){
                    String pk_soft_maindetail_b = new StringBuffer(ConstantVO.PK_SOFT_MAINDETAIL).append(Long.toString(worker.nextId())).toString();
                    softMaindetailBVO = (SoftMaindetailBVO) dataMap.get("softMaindetailBVO");
                    softMaindetailBVO.setPk_soft_maindetail(softMaindetailHVO.getPk_soft_maindetail());
                    softMaindetailBVO.setPk_soft_maindetail_b(pk_soft_maindetail_b);
                    iSoftMaindetailDao.insertSoftMaindetailBVO(softMaindetailBVO);
                }
                List<SoftMaindetailSubVO> softMaindetailSubList = (ArrayList) dataMap.get("softMaindetailSubList");
                for (int i = 0; i < softMaindetailSubList.size(); i++) {
                    SoftMaindetailSubVO softMaindetailSubVO = softMaindetailSubList.get(i);
                    String pk_soft_maindetail_sub = new StringBuffer(ConstantVO.PK_SOFT_MAINDETAIL).append(Long.toString(worker.nextId())).toString();
                    softMaindetailSubVO.setPk_soft_maindetail_b(softMaindetailBVO.getPk_soft_maindetail_b());
                    softMaindetailSubVO.setPk_soft_maindetail_sub(pk_soft_maindetail_sub);
                    softMaindetailSubVO.setCrowno(i+1);
                    temp += 1;

                }
                Map mapTemp = new HashMap<>();
                List dataList = (ArrayList)dataMap.get("datalist");
                mapTemp.put("pk_soft_maindetail_b",softMaindetailBVO.getPk_soft_maindetail_b());
                dataList.add(mapTemp);
                if (temp == softMaindetailSubList.size()) {
                    iSoftMaindetailDao.insertSoftMaindetailSub(softMaindetailSubList);
                    map.put("datalist", dataList);
                    map.put("sublist",dataMap.get("sublist"));
                    map.put("success", true);
                    map.put("info", "上传成功!");
                }
            } else {
                map.put("success", false);
                map.put("info", dataMap.get("error"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
        return map;
    }

    /**
     * 添加软件安装维护子表
     * @param map
     * @return
     * @throws GlobalException
     */
    @Override
    public SoftMaindetailBVO insertSoftMaindetail(Map map) throws GlobalException {
        List<SoftMaindetailSubVO> list = new ArrayList<>();
        SnowflakeIdWorker worker = new SnowflakeIdWorker(9, 9);
        ValueOperations<String, Serializable> valueOperations = redisTemplate.opsForValue();
        try {
            SoftMaindetailHVO softMaindetailHVO = (SoftMaindetailHVO) valueOperations.get("softmaindetail"+map.get("pk_org")+map.get("creator"));
            if (softMaindetailHVO == null) {
                softMaindetailHVO = new SoftMaindetailHVO();
                String pk_soft_detail = new StringBuffer(ConstantVO.PK_SOFT_DETAIL).append(Long.toString(worker.nextId())).toString();
                softMaindetailHVO.setCreationtime(DateUtil.getTime2());
                softMaindetailHVO.setPk_org((String) map.get("pk_org"));
                softMaindetailHVO.setCreator((String) map.get("creator"));
                softMaindetailHVO.setPk_soft_maindetail(pk_soft_detail);
                iSoftMaindetailDao.insertSoftMaindetailHVO(softMaindetailHVO);
                valueOperations.set("softmaindetail" + softMaindetailHVO.getPk_org() + softMaindetailHVO.getCreator(), (Serializable) softMaindetailHVO);
            }
            SoftMaindetailBVO softMaindetailBVO =  iSoftMaindetailDao.selectSoftMaindetailBVOByPk_orgAndCreator(map);
            if (softMaindetailBVO == null){
                String pk_soft_maindetail_b = new StringBuffer(ConstantVO.PK_SOFT_MAINDETAIL).append(Long.toString(worker.nextId())).toString();
                softMaindetailHVO = new SoftMaindetailHVO();
                softMaindetailBVO.setPk_soft_maindetail(softMaindetailHVO.getPk_soft_maindetail());
                softMaindetailHVO.setPk_soft_maindetail(pk_soft_maindetail_b);
                softMaindetailBVO.setPk_soft((String) map.get("pk_soft"));
                Map condionMap = iSoftOrderDao.selectSoftOrderBypk_softAndPk_org(map);
                softMaindetailBVO.setPermis((String) condionMap.get("permis"));
                softMaindetailBVO.setPermisnum((int)condionMap.get("ordnum"));
                softMaindetailBVO.setPermisdead((String) condionMap.get("softversion"));
                iSoftMaindetailDao.insertSoftMaindetailHVO(softMaindetailHVO);
            }
            TainVO tainVO = (TainVO) map.get("tain");
            String pk_soft_maindetail_sub = new StringBuffer(ConstantVO.PK_SOFT_MAINDETAIL).append(Long.toString(worker.nextId())).toString();
            SoftMaindetailSubVO softMaindetailSubVO = new SoftMaindetailSubVO();
            softMaindetailSubVO.setPk_soft_maindetail_sub(pk_soft_maindetail_sub);
            softMaindetailSubVO.setPk_soft_maindetail_b(softMaindetailBVO.getPk_soft_maindetail_b());
            int crowno = iSoftMaindetailDao.selectSoftMaindetailSubCountBySoftMaindetailBVO(softMaindetailBVO.getPk_soft_maindetail_b());
            softMaindetailSubVO.setCrowno(crowno);
            if (tainVO.getApplytype() ==0 || tainVO.getApplytype() ==2){
                softMaindetailSubVO.setDetailtype(0);
            }else {
                softMaindetailSubVO.setDetailtype(1);
            }
            softMaindetailSubVO.setPk_psndoc(tainVO.getCreator());
            softMaindetailSubVO.setPk_deptdoc(tainVO.getPk_deptdoc());
            softMaindetailSubVO.setSoftversion(tainVO.getPk_newsoft());
            softMaindetailSubVO.setSoftdate(tainVO.getCreationtime());
            softMaindetailSubVO.setInstallnum(1);
            list.add(softMaindetailSubVO);
            iSoftMaindetailDao.insertSoftMaindetailSub(list);
            return softMaindetailBVO;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
    }
}
