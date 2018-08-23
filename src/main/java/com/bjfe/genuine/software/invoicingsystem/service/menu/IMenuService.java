package com.bjfe.genuine.software.invoicingsystem.service.menu;

import com.bjfe.genuine.software.invoicingsystem.model.cdl.MenuItemVO;
import com.bjfe.genuine.software.invoicingsystem.model.pub.exception.GlobalException;

import java.util.Map;

/**
 * Created by Administrator on 2018/1/4 0004.
 */
public interface IMenuService {

	/**
	 * 添加菜单信息
	 * @param menuVo
	 * @return
	 * @throws GlobalException
	 */
	public Map insertMenu(MenuItemVO menuVo)throws GlobalException;

	/**
	 * 查询显示菜单信息
	 * @param whereMap
	 * @return
	 * @throws GlobalException
	 */
	public Map selectMenuList(Map whereMap)throws GlobalException;

	/**
	 * 删除菜单信息
	 * @param pk_menu
	 * @return
	 * @throws GlobalException
	 */
	public Map deleteMenu(String[] pk_menu)throws GlobalException;

	/**
	 * 修改菜单信息
	 * @param menuVo
	 * @return
	 * @throws GlobalException
	 */
	public Map updateMenu(MenuItemVO menuVo)throws GlobalException;

	/**
	 * 查询所有权限菜单信息是否分配
	 * @return
	 */
	public Map editMenuNoPower(String pk_power)throws GlobalException;

	/**
	 * 查询所有菜单项
	 * @return
	 * @throws GlobalException
     */
	public Map selectMenuAll()throws GlobalException;

	/**
	 * 编辑菜单项状态
	 * @return
	 * @throws GlobalException
     */
	public Map editMenuIsenable(Map whereMap)throws GlobalException;

	/**
	 * 通过权限查询分配的权限
	 * @return
	 * @throws GlobalException
     */
	public Map selectMenuByPermissionmenu(String pk_permid)throws GlobalException;
}

