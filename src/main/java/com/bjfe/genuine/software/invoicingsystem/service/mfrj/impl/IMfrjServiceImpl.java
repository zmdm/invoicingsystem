package com.bjfe.genuine.software.invoicingsystem.service.mfrj.impl;

import com.bjfe.genuine.software.invoicingsystem.mapper.dept.IDeptDao;
import com.bjfe.genuine.software.invoicingsystem.mapper.menu.IMenuDao;
import com.bjfe.genuine.software.invoicingsystem.mapper.mfrj.IMfrjDao;
import com.bjfe.genuine.software.invoicingsystem.mapper.psndoc.IPsndocDao;
import com.bjfe.genuine.software.invoicingsystem.mapper.softapply.ISoftApplyDao;
import com.bjfe.genuine.software.invoicingsystem.mapper.software.ISoftInfoDao;
import com.bjfe.genuine.software.invoicingsystem.model.cuser.CuserVO;
import com.bjfe.genuine.software.invoicingsystem.model.dept.DeptVO;
import com.bjfe.genuine.software.invoicingsystem.model.mfrj.SoftBVO;
import com.bjfe.genuine.software.invoicingsystem.model.mfrj.SoftHVO;
import com.bjfe.genuine.software.invoicingsystem.model.psndoc.PsndocVO;
import com.bjfe.genuine.software.invoicingsystem.model.pub.constant.ConstantVO;
import com.bjfe.genuine.software.invoicingsystem.model.pub.exception.GlobalException;
import com.bjfe.genuine.software.invoicingsystem.model.rjxx.SoftInfoVO;
import com.bjfe.genuine.software.invoicingsystem.model.softapply.SoftApplyBVO;
import com.bjfe.genuine.software.invoicingsystem.service.mfrj.IMfrjService;
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
 * Created by Administrator on 2018/1/22 0022.
 */
@Service("iMfrjService")
public class IMfrjServiceImpl implements IMfrjService {
	@Autowired
	private RedisTemplate<String,Serializable> redisTemplate;
	@Resource(name = "IMfrjDao")
	private IMfrjDao IMfrjDao;
	@Resource(name = "ISoftApplyDao")
	private ISoftApplyDao iSoftApplyDao;
	/**
	 * 查询可使用免费软件清单
	 * @param whereMap
	 * @return
	 * @throws GlobalException
	 */
	public Map querySoftHBByID(Map whereMap)throws GlobalException{
		Map map = new HashMap<>();
		ValueOperations<String, Serializable> valueOperations = redisTemplate.opsForValue();
		List<DeptVO> deptlist = (ArrayList<DeptVO>)valueOperations.get("deptlist");
		List<SoftInfoVO> softInfoList = (ArrayList<SoftInfoVO>)valueOperations.get("softinfolist");
		try {
			int total = IMfrjDao.selectSoftBVOCount(whereMap);
			List<SoftBVO> dataList = IMfrjDao.querySoftHBByID(PagingUtilVO.pagingContain(whereMap,total));
			if (dataList!=null){
				List<DeptVO> listTemp = new ArrayList<>();
				for (int i =0;i<deptlist.size();i++){
					if (whereMap.get("pk_org").equals(deptlist.get(i).getOrg_pk())){
						listTemp.add(deptlist.get(i));
					}
				}
				map.put("pagesize",PagingUtilVO.pagingContain(whereMap,total).get("pageSize"));
				map.put("total",total);
				map.put("datalist",dataList);
				map.put("softinfolist",softInfoList);
				map.put("deptlist",listTemp);
				map.put("success",true);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new GlobalException(e.getMessage());
		}
		return map;
	}

	/**
	 * 导入excel
	 * @param file
	 * @return
	 * @throws GlobalException
     */
	@Override
	public Map importEcexl(MultipartFile file,String fileName,Map commonMap) throws GlobalException {
		Map map = new HashMap<>();
		ValueOperations<String, Serializable> valueOperations = redisTemplate.opsForValue();
		List<DeptVO> deptlist = (ArrayList<DeptVO>)valueOperations.get("deptlist");
		List<SoftInfoVO> softInfoList = (ArrayList<SoftInfoVO>)valueOperations.get("softinfolist");
		try {
			commonMap.put("deptlist",deptlist);
			commonMap.put("softinfolist",softInfoList);
			commonMap.put("sheet",0);
			int temp = 0;
			Map dataMap = ExcelImportUtil.getExcelInfo(fileName,file,commonMap);
			if (dataMap.get("error")==null){
				SnowflakeIdWorker worker = new SnowflakeIdWorker(9, 9);
				SoftHVO softHVO = (SoftHVO) valueOperations.get("softfree"+commonMap.get("pk_org")+commonMap.get("creator"));
				if (softHVO ==null){
					softHVO = new SoftHVO();
					String pk_soft_free = new StringBuffer(ConstantVO.PK_SOFT_FREE).append(Long.toString(worker.nextId())).toString();
					softHVO.setPk_soft_free(pk_soft_free);
					softHVO.setCreator((String) commonMap.get("creator"));
					softHVO.setPk_org((String) commonMap.get("pk_org"));
					softHVO.setTelphone((String) commonMap.get("telphone"));
					softHVO.setCreationtime(DateUtil.getTime2());
					dataMap.put("creator",softHVO.getCreator());
					dataMap.put("pk_org",softHVO.getPk_org());
					IMfrjDao.insertSoftHVO(softHVO);
					valueOperations.set("softfree"+commonMap.get("pk_org")+commonMap.get("creator"),softHVO);
				}
				int count = IMfrjDao.selectSoftBVOCount(dataMap);
				List<SoftBVO> dataList =(ArrayList<SoftBVO>)dataMap.get("softBVOList");
				for (int i =0;i<dataList.size();i++){
					SoftBVO softBVO = dataList.get(i);
					if (softBVO.getPk_soft()!=null&&softBVO.getPk_deptdoc()!=null){
						String pk_soft_free_b = new StringBuffer(ConstantVO.PK_SOFT_FREE_B).append(Long.toString(worker.nextId())).toString();
						softBVO.setPk_soft_free_b(pk_soft_free_b);
						softBVO.setPk_soft_free(softHVO.getPk_soft_free());
						softBVO.setCrowno(count+1);
						temp+=1;
					}
				}
				if (temp ==dataList.size()){
					IMfrjDao.insertSoftBVO(dataList);
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
	 * 添加免费软件清单
	 * @param cuserVO
	 * @param map
	 * @throws GlobalException
     */
	@Override
	public void insertFreeSoft(CuserVO cuserVO, Map map) throws GlobalException {
		ValueOperations<String, Serializable> valueOperations = redisTemplate.opsForValue();
		try {
			SoftHVO softHVO = (SoftHVO) valueOperations.get("softfree"+cuserVO.getPk_org()+cuserVO.getPk_psndoc());
			SnowflakeIdWorker worker = new SnowflakeIdWorker(9, 9);
			if (softHVO ==null){
                softHVO = new SoftHVO();
                String pk_soft_free = new StringBuffer(ConstantVO.PK_SOFT_FREE).append(Long.toString(worker.nextId())).toString();
                softHVO.setPk_soft_free(pk_soft_free);
                softHVO.setCreator(cuserVO.getPk_psndoc());
                softHVO.setPk_org(cuserVO.getPk_org());
                softHVO.setTelphone(cuserVO.getTelphone());
                softHVO.setCreationtime(DateUtil.getTime2());
                map.put("creator",softHVO.getCreator());
                map.put("pk_org",softHVO.getPk_org());
				IMfrjDao.insertSoftHVO(softHVO);
				valueOperations.set("softfree"+cuserVO.getPk_org()+cuserVO.getPk_psndoc(),softHVO);
            }
			int count = IMfrjDao.selectSoftBVOCount(map);
			List<SoftBVO> softBVOList = new ArrayList<>();
			List<SoftApplyBVO> softApplyBVOList = iSoftApplyDao.selectSoftApplyBVOBypk_soft_apply((String) map.get("pk_soft_apply"));
			for (SoftApplyBVO softApplyBVO : softApplyBVOList){
				if ("免费软件".equals(softApplyBVO.getPk_softtype())){
					SoftBVO softBVO = new SoftBVO();
					String pk_soft_free_b = new StringBuffer(ConstantVO.PK_SOFT_FREE_B).append(Long.toString(worker.nextId())).toString();
					softBVO.setPk_soft_free_b(pk_soft_free_b);
					softBVO.setPk_soft_free(softHVO.getPk_soft_free());
					softBVO.setCrowno(count+1);
					softBVO.setPk_soft(softApplyBVO.getSoft_pk());
					String deptname = iSoftApplyDao.selectApplyDeptBypk_soft_apply((String) map.get("pk_soft_apply"));
					softBVO.setPk_deptdoc(deptname);
					softBVO.setReason(softApplyBVO.getSoftpurpose());
					softBVOList.add(softBVO);
				}
			}
			if (0!=softBVOList.size()){
				IMfrjDao.insertSoftBVO(softBVOList);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new GlobalException(e.getMessage());
		}
	}
}
