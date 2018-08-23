package com.bjfe.genuine.software.invoicingsystem.service.qkmx;

import com.bjfe.genuine.software.invoicingsystem.model.pub.exception.GlobalException;
import com.bjfe.genuine.software.invoicingsystem.model.qkmx.QkmxBVO;
import com.bjfe.genuine.software.invoicingsystem.model.qkmx.QkmxHVO;
import com.bjfe.genuine.software.invoicingsystem.model.tain.TainVO;
import org.springframework.web.multipart.MultipartFile;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/23 0023.
 */

/**
 * 软件使用情况明细表业务逻辑接口
 */
public interface IQkmxService {
	/**
	 * 分页显示查询
	 * @param paramMap
	 * @return
	 * @throws GlobalException
     */
	public Map selectQkmxPage(Map paramMap) throws GlobalException;

	/**
	 * 导入excel
	 * @param file
	 * @param fileName
	 * @return
	 * @throws GlobalException
     */
	public Map importEcexl(MultipartFile file, String fileName,Map commonMap) throws GlobalException;

	/**
	 * 添加软件使用情况明细
	 * @param map
	 * @throws GlobalException
     */
	public QkmxBVO insertSoftDetail(Map map)throws GlobalException;
}
