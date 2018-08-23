package com.bjfe.genuine.software.invoicingsystem.mapper.role;

import com.bjfe.genuine.software.invoicingsystem.model.jsgl.RoleVO;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/3 0003.
 */
/**
 * 角色数据持久接口
 * 编写人：张敏
 */
public interface IRoleDao {
	/**
	 * 查询显示角色信息
	 * @return
	 * @throws SQLException
	 */
	public List<RoleVO> selectRoleList(Map map)throws SQLException;

	/**
	 * 查询角色信息数量
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public int selectRoleCount(Map map)throws SQLException;


	/**
	 * 通过编码查询角色
	 * @param rolecode
	 * @return
	 * @throws SQLException
	 */
	public int selectRoleCode(String rolecode)throws SQLException;


	/**
	 * 添加角色信息
	 * @param roleVO
	 * @return
	 * @throws SQLException
	 */
	public int insertRole(RoleVO roleVO)throws SQLException;

	/**
	 * 删除角色信息
	 * @param pk_role
	 * @return
	 * @throws SQLException
	 */
	public int deleteRole(String[] pk_role)throws SQLException;

	/**
	 * 修改角色信息
	 * @param roleVO
	 * @return
	 * @throws SQLException
	 */
	public int updateRole(RoleVO roleVO)throws SQLException;
}
