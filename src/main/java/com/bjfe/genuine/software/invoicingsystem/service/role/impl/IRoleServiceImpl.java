package com.bjfe.genuine.software.invoicingsystem.service.role.impl;
import com.bjfe.genuine.software.invoicingsystem.mapper.cuser.ICuserDao;
import com.bjfe.genuine.software.invoicingsystem.mapper.dept.IDeptDao;
import com.bjfe.genuine.software.invoicingsystem.mapper.org.IOrgDao;
import com.bjfe.genuine.software.invoicingsystem.mapper.qxgl.IPermissionDao;
import com.bjfe.genuine.software.invoicingsystem.mapper.role.IRoleDao;
import com.bjfe.genuine.software.invoicingsystem.model.cuser.CuserVO;
import com.bjfe.genuine.software.invoicingsystem.model.dept.DeptVO;
import com.bjfe.genuine.software.invoicingsystem.model.jsgl.RoleVO;
import com.bjfe.genuine.software.invoicingsystem.model.org.OrgVO;
import com.bjfe.genuine.software.invoicingsystem.model.pub.constant.ConstantVO;
import com.bjfe.genuine.software.invoicingsystem.model.pub.exception.GlobalException;
import com.bjfe.genuine.software.invoicingsystem.model.qxgl.RightsManVO;
import com.bjfe.genuine.software.invoicingsystem.service.role.IRoleService;
import com.bjfe.genuine.software.invoicingsystem.util.paging.PagingUtilVO;
import com.bjfe.genuine.software.invoicingsystem.util.pk.SnowflakeIdWorker;
import com.bjfe.genuine.software.invoicingsystem.util.redis.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/3 0003.
 */
/**
 * 角色业务逻辑实现类
 * 编写人：张敏
 */
@Service("iRoleService")// iRoleService  这个类定义一个bean，iRoleService;
public class IRoleServiceImpl implements IRoleService{
	@Autowired  //@Autowired自动装配  寻找匹配的类型注入到该类中
	private RedisTemplate<String,Serializable> redisTemplate;
//
	@Resource(name = "IRoleDao")  //@Resource 按照bean的id注入 可以理解为引用
	private IRoleDao IRoleDao;//角色
	@Resource(name = "ICuserDao")
	private ICuserDao iCuserDao;//用户
	@Resource(name = "IPermissionDao")
	private IPermissionDao iPermissionDao;//权限管理
	/**
	 * 添加角色信息
	 * @param roleVO
	 * @return
	 * @throws GlobalException
	 */
	@Override
		//   @Override告诉下面这个方法是从父类/接口 继承过来的，需要你重写一次
//        这里insertRole实现了IRoleDao接口中的方法
	public Map insertRole(RoleVO roleVO) throws GlobalException {
		Map map = new HashMap<>();
		try {
				SnowflakeIdWorker worker = new SnowflakeIdWorker(9, 9);
				String pk_apply = new StringBuffer(ConstantVO.PK_ROLE).append(Long.toString(worker.nextId())).toString();
//			主键前缀+后缀存放到缓存区
				roleVO.setPk_role(pk_apply);
//			添加角色信息是根据主键添加的
			int count=IRoleDao.selectRoleCode(roleVO.getRolecode());
			if(count==0) {
				//			设置pojo里面的主键
				int result = IRoleDao.insertRole(roleVO);
				if (result == 1) {
					map.put("pk_role", roleVO.getPk_role());
//				如果有值 添加获取的角色主键 存放键值对  键:pk_role  值:角色主键
					map.put("org_pk", roleVO.getPk_org());
//               添加获取的组织 存放 键:org_pk    值:组织
					map.put("success", true);
				} else {
					map.put("success", false);
				}
			}
			else{
				map.put("success",false);
				map.put("info","编码重复，请重新输入！");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new GlobalException(e.getMessage());
		}
		return map;
	}




	/**
	 * 查询显示角色信息
	 * @param whereMap
	 * @return
	 * @throws GlobalException
	 */
	@Override
	public Map selectRoleList(Map whereMap) throws GlobalException {
		Map dataMap = null;
		ValueOperations<String, Serializable> valueOperations = redisTemplate.opsForValue();
//		opsForValue() 这个方法会返回一个默认的操作类
		List<DeptVO> deptlist = (ArrayList<DeptVO>)valueOperations.get("deptlist");
//		获取集合 只能存放DeptVO 部门类型的集合
		List<OrgVO> orgList = (ArrayList<OrgVO>)valueOperations.get("orglist");
		//		获取集合 只能存放OrgVO 组织类型的集合
		try {
			int total = IRoleDao.selectRoleCount(whereMap);
//			查询角色信息数量集合
			List<RoleVO> dataList = IRoleDao.selectRoleList(PagingUtilVO.pagingContain(whereMap,total));
//			查询的角色信息和数量 用分页的规则  显示角色信息
			dataMap = new HashMap<>();
			if (dataList!=null){
				orgList = RedisUtil.handleLis(deptlist,orgList);
//				组织的name数据和部门的相同数据  赋值给orgList
				dataMap.put("pagesize",PagingUtilVO.pagingContain(whereMap,total).get("pageSize"));
//				存放
				dataMap.put("total",total);
				dataMap.put("orglist",orgList);
				dataMap.put("datalist",dataList);
				dataMap.put("success",true);
			}else {
				dataMap.put("success",false);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new GlobalException(e.getMessage());
		}
		return dataMap;
	}

	/**
	 * 删除角色信息
	 * @param pk_role
	 * @return
	 * @throws GlobalException
	 */
	@Override
	public Map deleteRole(String[] pk_role) throws GlobalException {
		Map map = new HashMap<>();
		try {
			int result = IRoleDao.deleteRole(pk_role);
//			删除角色信息是根据删除主键执行的
			if (result >0){
//				删除的数据大于0 表示删除成功  否则失败
				map.put("success",true);
			}else {
				map.put("success",false);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new GlobalException(e.getMessage());
		}
		return map;
	}



	/**
	 * 修改角色信息
	 * @param RoleVO
	 * @return
	 * @throws GlobalException
	 */
	@Override
	public Map updateRole(RoleVO RoleVO) throws GlobalException {
		Map map = new HashMap<>();
		try {
			int result = IRoleDao.updateRole(RoleVO);
//			修改的是用户手动的通过键盘传入的数据
			if (result ==1){
//          如果有修改的次数为1 表示 修改成功 否者失败
				map.put("success",true);
			}else {
				map.put("success",false);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new GlobalException(e.getMessage());
		}
		return map;
	}

	/**
	 * 通过角色查询关联的用户和权限
	 * @return   一种java注解,一般写在方法的上面,说明该方法有返回值。
	 * @throws GlobalException
     */
	@Override
	public Map selectUserAndPermissionByRole(String pk_role) throws GlobalException {
		Map map = new HashMap<>();
		try {
			List<CuserVO> userList = iCuserDao.selectUserByRole(pk_role);
//			这里通过角色的主键 查询关联的用户
			List<RightsManVO> permissionList = iPermissionDao.selectRightByRole(pk_role);
//			这里通过角色的主键 查询关联的权限
			if (userList!=null || permissionList!=null){
//				关联的用户 和关联的权限 满足一个不为空的时候  就添加有关联的用户和权限
				map.put("userlist",userList);
				map.put("permissionlist",permissionList);
				map.put("success",true);
			}else {
				map.put("success",false);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new GlobalException(e.getMessage());
		}
		return map;
	}
}
