package com.bjfe.genuine.software.invoicingsystem.mapper.qkmx;

import com.bjfe.genuine.software.invoicingsystem.model.qkmx.QkmxBVO;
import com.bjfe.genuine.software.invoicingsystem.model.qkmx.QkmxHVO;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 软件使用情况明细数据持久接口
 * 编写人：张敏
 */
public interface IQkmxDao {

	/**
	 * 子表查询
	 * @return
	 * @throws SQLException
	 */
	public List<QkmxBVO> selectQkmxBByID(Map whereMap) throws SQLException;

	/**
	 * 查询子表数量
	 * @return
	 * @throws SQLException
	 */
	public int selectQkmxBByCount(Map whereMap) throws SQLException;

	/**
	 * 添加软件使用情况明细主表数据
	 * @return
	 * @throws SQLException
	 */
	public int insertQkmxHByID(QkmxHVO qkmxHVO) throws SQLException;

	/**
	 *添加软件使用情况明细子表数据
	 * @return
	 * @throws SQLException
	 */
	public int insertQkmxBByID(List<QkmxBVO> list) throws SQLException;


	/**
	 * 查询软件使用情况明细主表的总数量
	 * @return
	 * @throws SQLException
	 */
	public int selectQkmxCount() throws SQLException;
	/**
	 * 分页显示
	 * @return
	 * @throws SQLException
	 */
	//查询子表的数量
	public Map selectQkmxPage(Map map) throws SQLException;

	/**
	 * 主子表查询
	 * @return
	 * @throws SQLException
	 */
	public List<QkmxHVO> selectQkmxHBByID(String pk__soft_detail) throws SQLException;//通过主键查询的

	/**
	 * 修改软件使用情况明细表数据主表
	 * @return
	 * @throws SQLException
	 */
	public int updateQkmxHByID(QkmxHVO qkmxHVO) throws SQLException;

	/**
	 * 修改软件使用情况明细表数据子表
	 * @return
	 * @throws SQLException
	 */
	public int updateQkmxBByID(QkmxBVO qkmxBVO) throws SQLException;

	/**
	 * 删除软件使用情况明细表数据主表
	 * @return
	 * @throws SQLException
	 */
	public int deleteQkmxHByID(QkmxHVO qkmxHVO) throws SQLException;

	/**
	 * 删除软件使用情况明细表数据子表
	 * @return
	 * @throws SQLException
	 */
	public int deleteQkmxBByID(QkmxBVO qkmxBVO) throws SQLException;

}

