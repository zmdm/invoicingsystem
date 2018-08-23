package com.bjfe.genuine.software.invoicingsystem.service.mfrj;

import com.bjfe.genuine.software.invoicingsystem.model.cdl.MenuItemVO;
import com.bjfe.genuine.software.invoicingsystem.model.cuser.CuserVO;
import com.bjfe.genuine.software.invoicingsystem.model.mfrj.SoftBVO;
import com.bjfe.genuine.software.invoicingsystem.model.mfrj.SoftHVO;
import com.bjfe.genuine.software.invoicingsystem.model.pub.exception.GlobalException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/22 0022.
 */

/**
 * 可使用免费软件清单业务逻辑接口
 * 编写人：宋超洋
 */
public interface IMfrjService {

	/**
	 * 查询可使用免费软件清单
	 * @param whereMap
	 * @return
	 * @throws GlobalException
	 */
	public Map querySoftHBByID(Map whereMap)throws GlobalException;

	/**
	 * 导入excel
	 * @param file
	 * @return
	 * @throws GlobalException
     */
	public Map importEcexl(MultipartFile file,String fileName,Map commonMap)throws GlobalException;

	/**
	 * 添加免费软件清单
	 * @param cuserVO
	 * @param map
	 * @throws GlobalException
     */
	public void insertFreeSoft(CuserVO cuserVO,Map map)throws GlobalException;
}
