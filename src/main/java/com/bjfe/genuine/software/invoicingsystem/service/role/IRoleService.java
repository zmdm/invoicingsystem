package com.bjfe.genuine.software.invoicingsystem.service.role;

import com.bjfe.genuine.software.invoicingsystem.model.jsgl.RoleVO;
import com.bjfe.genuine.software.invoicingsystem.model.pub.exception.GlobalException;

import java.util.Map;

/**
 * Created by Administrator on 2018/1/3 0003.
 */
/**
 * 角色service
 * 编写人：张敏
 */
public interface IRoleService {

	/**
	 * 添加角色信息
	 * @param roleVo
	 * @return
	 * @throws GlobalException
	 */
	public Map insertRole(RoleVO roleVo)throws GlobalException;

	/**
	 * 查询显示角色信息
	 * @param whereMap
	 * @return
	 * @throws GlobalException
	 */
	public Map selectRoleList(Map whereMap)throws GlobalException;

	/**
	 * 删除角色信息
	 * @param pk_role
	 * @return
	 * @throws GlobalException
	 */
	public Map deleteRole(String[] pk_role)throws GlobalException;

	/**
	 * 修改角色信息
	 * @param roleVo
	 * @return
	 * @throws GlobalException
	 */
	public Map updateRole(RoleVO roleVo)throws GlobalException;

	/**
	 * 通过角色查询关联的用户和权限
	 * @return
	 * @throws GlobalException
     */
	public Map selectUserAndPermissionByRole(String pk_role)throws GlobalException;
}
