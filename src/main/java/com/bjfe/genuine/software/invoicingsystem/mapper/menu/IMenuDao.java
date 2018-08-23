package com.bjfe.genuine.software.invoicingsystem.mapper.menu;

import com.bjfe.genuine.software.invoicingsystem.model.cdl.MenuItemVO;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/4 0004.
 */
/**
 * 菜单数据持久接口
 * 编写人：张敏
 */
public interface IMenuDao {
	/**
	 * 查询显示菜单信息
	 * @return
	 * @throws SQLException
	 */
	public List<MenuItemVO> selectMenuList(Map map)throws SQLException;

	/**
	 * 查询所有菜单项
	 * @return
	 * @throws SQLException
     */
	public List<MenuItemVO> selectMenuAll()throws SQLException;

	/**
	 * 查询菜单信息数量
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public int selectMenuCount(Map map)throws SQLException;

	/**
	 * 添加菜单信息
	 * @param menuVO
	 * @return
	 * @throws SQLException
	 */
	public int insertMenu(MenuItemVO menuVO)throws SQLException;

	/**
	 * 删除菜单信息
	 * @param pk_menu
	 * @return
	 * @throws SQLException
	 */
	public int deleteMenu(String[] pk_menu)throws SQLException;

	/**
	 * 修改菜单信息
	 * @param menuVO
	 * @return
	 * @throws SQLException
	 */
	public int updateMenu(MenuItemVO menuVO)throws SQLException;

	/**
	 * 查询所有权限菜单信息是否分配
	 * @return
	 */
	public List<MenuItemVO> editMenuNoPower(String pk_permid)throws SQLException;

	/**
	 * 通过过一级编码查询二级编码数量
	 * @param pk_father
	 * @return
	 * @throws SQLException
     */
	public long selectMenuCountByFatherCode(String pk_father)throws SQLException;

	/**
	 * 编辑菜单项状态
	 * @return
	 * @throws SQLException
     */
	public int editMenuIsenable(@Param("isenable")int isenable, @Param("cfunid") String []cfunids)throws SQLException;

	/**
	 * 通过权限查询分配的权限
	 * @param pk_permid
	 * @return
	 * @throws SQLException
     */
	public List<MenuItemVO> selectMenuByPermiddsion(String pk_permid)throws SQLException;

	/**
	 * 通过登陆用户查询相应职责
	 * @param cuserid
	 * @return
	 * @throws SQLException
     */
	public List<MenuItemVO> selectMenuByuser(String cuserid)throws SQLException;

	/**
	 * 查询上级编码的个数
	 * @return
	 * @throws SQLException
     */
	public int selectMenuPkFatherCount()throws SQLException;

	/**
	 * 通过功能名查询菜单项数量
	 * @return
	 * @throws SQLException
     */
	public int selectMenuCountByFunname(MenuItemVO menuItemVO)throws SQLException;

	/**
	 * 通过主键查询功能名称
	 * @param cfunid
	 * @return
	 * @throws SQLException
     */
	public String selectMenuNameBycfunid(String cfunid)throws SQLException;
}


