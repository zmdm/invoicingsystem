package com.bjfe.genuine.software.invoicingsystem.controller;

import com.bjfe.genuine.software.invoicingsystem.model.jsgl.RoleVO;
import com.bjfe.genuine.software.invoicingsystem.model.pub.exception.GlobalException;
import com.bjfe.genuine.software.invoicingsystem.service.role.IRoleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/3 0003.
 */
/**
 * 用户控制器类
 * 编写人：张敏
 */
@CrossOrigin(origins = "*",maxAge = 1000)
//  @CrossOrigin 可以处理跨域请求，让你能访问不是一个域的文件
@RequestMapping("/role")
//  RequestMapping是一个用来处理请求地址映射的注解，可用于类或方法上。用于类上，表示类中的所有响应请求的方法都是以该地址作为父路径。
@Controller
//  表示这是一个Controller类
public class RoleController {
	@Resource(name = "iRoleService")
	private IRoleService iRoleService;

	/**
	 * 添加角色信息
	 * @param roleVO
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(value = "/insertrole",method = RequestMethod.POST)
//	@RequestMapping  实际的地址
	@ResponseBody
	//      @responseBody注解的作用是将controller的方法返回的对象通过适当的转换器转换为指定的格式之后，
	//      写入到response对象的body区，通常用来返回JSON数据或者是XML
	public Object insertRole(@RequestBody RoleVO roleVO)throws GlobalException{
		Map map = iRoleService.insertRole(roleVO);
		return map;
	}


	/**
	 * 查询显示角色信息
	 * @param whereMap
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(value = "/selectrolelist",method = RequestMethod.POST)
	@ResponseBody
	public Object selectPsndocList(@RequestBody Map whereMap)throws GlobalException{
		Map map = iRoleService.selectRoleList(whereMap);
		return map;
	}

	/**
	 * 修改角色信息
	 * @param roleVO
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(value = "/updaterole",method = RequestMethod.POST)
	@ResponseBody
	public Object updateRole(@RequestBody RoleVO roleVO)throws GlobalException{
		Map map = iRoleService.updateRole(roleVO);
		return map;
	}

	/**
	 * 删除角色信息
	 * @param map
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(value = "/deleterole",method = RequestMethod.POST)
	@ResponseBody
	public Object deletePsndoc(@RequestBody Map<String,String> map)throws GlobalException{
		String pk=map.get("pk_role");
		String[] pk_role=pk.split(",");
		Map resultMap = iRoleService.deleteRole(pk_role);
		return resultMap;
	}

	/**
	 * 查询角色查询关联的用户和权限
	 * @return
	 * @throws GlobalException
     */
	@RequestMapping("/selectuserandpermission")
	@ResponseBody
	public Object selectUserAndPermissionByRole(@RequestParam("pk_role") String pk_role )throws GlobalException{
		Map map = iRoleService.selectUserAndPermissionByRole(pk_role);
		return map;
	}
}
