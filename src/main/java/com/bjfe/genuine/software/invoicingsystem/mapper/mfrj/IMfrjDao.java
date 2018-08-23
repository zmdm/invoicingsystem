package com.bjfe.genuine.software.invoicingsystem.mapper.mfrj;

import com.bjfe.genuine.software.invoicingsystem.model.mfrj.SoftBVO;
import com.bjfe.genuine.software.invoicingsystem.model.mfrj.SoftHVO;


import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/22 0022.
 */
/**
 * 免费软件清单接口
 * 编写人：张敏
 */
public interface IMfrjDao {
	/**
	 * 查询可使用免费软件清单子表信息
	 * @return
	 */
	public List<SoftBVO> querySoftHBByID(Map whereMap) throws SQLException;

	/**
	 * 查询可使用免费软件清单子表信息数量
	 * @return
	 * @throws SQLException
     */
	public int selectSoftBVOCount(Map whereMap)throws SQLException;
	/**
	 * 添加可使用免费软件清单主表
	 * @return
	 */
	public void insertSoftHVO(SoftHVO softHVO)throws SQLException;
	/**
	 * 添加可使用免费软件清单子表
	 * @return
	 */
	public void insertSoftBVO(List<SoftBVO> list)throws SQLException;
}
