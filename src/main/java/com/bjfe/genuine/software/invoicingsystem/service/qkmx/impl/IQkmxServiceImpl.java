package com.bjfe.genuine.software.invoicingsystem.service.qkmx.impl;

import com.bjfe.genuine.software.invoicingsystem.mapper.computer.IComputerDao;
import com.bjfe.genuine.software.invoicingsystem.mapper.dept.IDeptDao;
import com.bjfe.genuine.software.invoicingsystem.mapper.psndoc.IPsndocDao;
import com.bjfe.genuine.software.invoicingsystem.mapper.qkmx.IQkmxDao;
import com.bjfe.genuine.software.invoicingsystem.mapper.software.ISoftInfoDao;
import com.bjfe.genuine.software.invoicingsystem.mapper.software.ISoftTypeDao;
import com.bjfe.genuine.software.invoicingsystem.model.computer.ComputerVO;
import com.bjfe.genuine.software.invoicingsystem.model.dept.DeptVO;
import com.bjfe.genuine.software.invoicingsystem.model.psndoc.PsndocVO;
import com.bjfe.genuine.software.invoicingsystem.model.pub.constant.ConstantVO;
import com.bjfe.genuine.software.invoicingsystem.model.pub.exception.GlobalException;
import com.bjfe.genuine.software.invoicingsystem.model.qkmx.QkmxBVO;
import com.bjfe.genuine.software.invoicingsystem.model.qkmx.QkmxHVO;
import com.bjfe.genuine.software.invoicingsystem.model.rjlx.SoftTypeVO;
import com.bjfe.genuine.software.invoicingsystem.model.rjxx.SoftInfoVO;
import com.bjfe.genuine.software.invoicingsystem.model.softmaindetail.SoftMaindetailBVO;
import com.bjfe.genuine.software.invoicingsystem.model.softmaindetail.SoftMaindetailSubVO;
import com.bjfe.genuine.software.invoicingsystem.model.tain.TainVO;
import com.bjfe.genuine.software.invoicingsystem.service.qkmx.IQkmxService;
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
 * Created by Administrator on 2018/1/23 0023.
 */

/**
 * 软件使用情况明细表业务逻辑实现类
 * 编写人：宋超洋
 */
@Service("iQkmxService")
public class IQkmxServiceImpl implements IQkmxService {
	@Autowired
	private RedisTemplate<String,Serializable> redisTemplate;
	@Resource(name = "IQkmxDao")
	private IQkmxDao iQkmxDao;

	/**
	 * 分页显示
	 * @return
	 * @throws GlobalException
	 */
	public Map selectQkmxPage(Map paramMap) throws GlobalException{
		Map dataMap=null;//该方法的返回值
		ValueOperations<String, Serializable> valueOperations = redisTemplate.opsForValue();
		List<DeptVO> deptlist = (ArrayList<DeptVO>)valueOperations.get("deptlist");//获取这个deptlist对应的值
		List<SoftInfoVO> softInfoList = (ArrayList<SoftInfoVO>)valueOperations.get("softinfolist");
		List<ComputerVO> computerList = (ArrayList<ComputerVO>)valueOperations.get("computerlist");
		List<SoftTypeVO> softTypeList = (ArrayList<SoftTypeVO>)valueOperations.get("softtypelist");
		try {
			int total=iQkmxDao.selectQkmxBByCount(paramMap);//子表查询出来的数据数量
			List<QkmxBVO> dataList=iQkmxDao.selectQkmxBByID(PagingUtilVO.pagingContain(paramMap,total));
			if(dataList!=null){
				for (int i=0;i<deptlist.size();i++){
					DeptVO deptVO = deptlist.get(i);
					if (!paramMap.get("pk_org").equals(deptVO.getOrg_pk())){
						deptlist.remove(i);
					}
				}
				dataMap=new HashMap<>();
				dataMap.put("pagesize",PagingUtilVO.pagingContain(paramMap,total).get("pageSize"));
				dataMap.put("total",total);//表的数据数量
				dataMap.put("datalist",dataList);//分页的规则
				dataMap.put("softinfolist",softInfoList);//软件信息的数据
				dataMap.put("deptlist",deptlist);//部门的数据
				dataMap.put("computerlist",computerList);//计算机管理的数据
				dataMap.put("softtypelist",softTypeList);//软件类型的数据
				dataMap.put("success",true);
			}
		}catch (SQLException e){
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
		Map map=new HashMap<>();//定义一个返回值
		ValueOperations<String, Serializable> valueOperations = redisTemplate.opsForValue();
//		先从缓存里面查，如果缓存里面没有就去数据库中查找
		List<DeptVO> deptlist = (ArrayList<DeptVO>)valueOperations.get("deptlist");//部门
		List<SoftInfoVO> softInfoList = (ArrayList<SoftInfoVO>)valueOperations.get("softinfolist");//软件信息
		List<ComputerVO> computerList = (ArrayList<ComputerVO>)valueOperations.get("computerlist");//计算机管理
		List<PsndocVO> psndocList = (ArrayList<PsndocVO>)valueOperations.get("psndoclist");//人员
		List<SoftTypeVO> softTypeList = (ArrayList<SoftTypeVO>)valueOperations.get("softtypelist");//软件类型
		try {
			commonMap.put("deptlist",deptlist);
			commonMap.put("softinfolist",softInfoList);
			commonMap.put("computerlist",computerList);
			commonMap.put("psndoclist",psndocList);
			commonMap.put("softtypelist",softTypeList);
			commonMap.put("sheet",2);
			int temp = 0;
			Map dataMap = ExcelImportUtil.getExcelInfo(fileName,file,commonMap);
			if (dataMap.get("error")==null){
				SnowflakeIdWorker worker = new SnowflakeIdWorker(9, 9);
				QkmxHVO qkmxHVO = (QkmxHVO) valueOperations.get("qkmx"+commonMap.get("pk_org")+commonMap.get("creator"));
				if (qkmxHVO==null){
					qkmxHVO = new QkmxHVO();
					String pk_soft_detail = new StringBuffer(ConstantVO.PK_SOFT_DETAIL).append(Long.toString(worker.nextId())).toString();
					qkmxHVO.setCreationtime(DateUtil.getTime2());
					qkmxHVO.setTelphone((String) commonMap.get("telphone"));
					qkmxHVO.setPk_org((String) commonMap.get("pk_org"));
					qkmxHVO.setPk_soft_detail(pk_soft_detail);
					qkmxHVO.setCreator((String) commonMap.get("creator"));
					iQkmxDao.insertQkmxHByID(qkmxHVO);
					valueOperations.set("qkmx"+qkmxHVO.getPk_org()+qkmxHVO.getCreator(),(Serializable) qkmxHVO);
				}
				int count = iQkmxDao.selectQkmxBByCount(commonMap);
				List<QkmxBVO> qkmxList = (ArrayList)dataMap.get("qkmxBVOList");
				for (int i=0;i<qkmxList.size();i++){
					QkmxBVO qkmxBVO=qkmxList.get(i);
					if(qkmxBVO.getPk_computer()!=null && qkmxBVO.getPk_deptdoc()!=null && qkmxBVO.getPk_soft()!=null && qkmxBVO.getPk_psndoc()!=null){
						String pk_soft_detail_b = new StringBuffer(ConstantVO.PK_SOFT_DETAIL_B).append(Long.toString(worker.nextId())).toString();
						qkmxBVO.setPk_soft_detail(qkmxHVO.getPk_soft_detail());
						qkmxBVO.setPk_soft_detail_b(pk_soft_detail_b);
						qkmxBVO.setCrowno((count+1));
						temp+=1;
					}
				}
				if (temp ==qkmxList.size()){
					iQkmxDao.insertQkmxBByID(qkmxList);
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
	 * 添加软件使用情况明细
	 * @param map
	 * @throws GlobalException
     */
	@Override
	public QkmxBVO insertSoftDetail(Map map) throws GlobalException {
		ValueOperations<String, Serializable> valueOperations = redisTemplate.opsForValue();
		SnowflakeIdWorker worker = new SnowflakeIdWorker(9, 9);
		SoftMaindetailBVO softMaindetailBVO = (SoftMaindetailBVO) map.get("softMaindetailBVO");
		QkmxBVO qkmxBVO =null;
		try {
			QkmxHVO qkmxHVO = (QkmxHVO) valueOperations.get("qkmx"+map.get("pk_org")+map.get("creator"));
			if (qkmxHVO==null){
                qkmxHVO = new QkmxHVO();
                String pk_soft_detail = new StringBuffer(ConstantVO.PK_SOFT_DETAIL).append(Long.toString(worker.nextId())).toString();
                qkmxHVO.setCreationtime(DateUtil.getTime2());
                qkmxHVO.setTelphone((String) map.get("telphone"));
                qkmxHVO.setPk_org((String) map.get("pk_org"));
                qkmxHVO.setPk_soft_detail(pk_soft_detail);
                qkmxHVO.setCreator((String) map.get("creator"));
                iQkmxDao.insertQkmxHByID(qkmxHVO);
                valueOperations.set("qkmx"+qkmxHVO.getPk_org()+qkmxHVO.getCreator(),(Serializable) qkmxHVO);
            }
			TainVO tainVO = (TainVO) map.get("tain");
			String pk_soft_detail_b = new StringBuffer(ConstantVO.PK_SOFT_DETAIL_B).append(Long.toString(worker.nextId())).toString();
			qkmxBVO = new QkmxBVO();
			qkmxBVO.setPk_soft_detail_b(pk_soft_detail_b);
			qkmxBVO.setPk_soft_detail(qkmxHVO.getPk_soft_detail());
			qkmxBVO.setPermis(softMaindetailBVO.getPermisdead());
			qkmxBVO.setPk_softtype(tainVO.getPk_newsoft());
			qkmxBVO.setTaudittime(tainVO.getCreationtime());
			return qkmxBVO;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return qkmxBVO;
	}
}
