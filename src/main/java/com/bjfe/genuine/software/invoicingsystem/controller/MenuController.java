package com.bjfe.genuine.software.invoicingsystem.controller;
import com.bjfe.genuine.software.invoicingsystem.model.cdl.MenuItemVO;
import com.bjfe.genuine.software.invoicingsystem.model.pub.exception.GlobalException;
import com.bjfe.genuine.software.invoicingsystem.service.menu.IMenuService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/4 0004.
 */
/**
 * 菜单控制器类
 * 编写人：张敏
 */
@CrossOrigin(origins = "*",maxAge = 1000)
@RequestMapping("/menu")
@Controller
public class MenuController {
	@Resource(name = "iMenuService")
	private IMenuService iMenuService;


	/**
	 * 添加菜单信息
	 * @param meunVO
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(value = "/insertmenu",method = RequestMethod.POST)
	@ResponseBody
	public Object insertRole(@RequestBody MenuItemVO meunVO)throws GlobalException{
		Map map = iMenuService.insertMenu(meunVO);
		return map;
	}


	/**
	 * 查询显示菜单信息
	 * @param whereMap
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(value = "/selectmenulist",method = RequestMethod.POST)
	@ResponseBody
	public Object selectMenuList(@RequestBody Map whereMap)throws GlobalException{
		Map map = iMenuService.selectMenuList(whereMap);
		return map;
	}

	/**
	 * 修改菜单信息
	 * @param menuVO
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(value = "/updatemenu",method = RequestMethod.POST)
	@ResponseBody
	public Object updateMenu(@RequestBody MenuItemVO menuVO)throws GlobalException{
		Map map = iMenuService.updateMenu(menuVO);
		return map;
	}

	/**
	 * 删除菜单信息
	 * @param map
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(value = "/deletemenu",method = RequestMethod.POST)
	@ResponseBody
	public Object deleteMenu(@RequestBody Map<String,String> map)throws GlobalException{
		String pk=map.get("cfunid");
		String[] pk_menu=pk.split(",");
		Map resultMap = iMenuService.deleteMenu(pk_menu);
		return resultMap;
	}

	/**
	 * 查询所有权限菜单信息是否分配
	 * @return
	 */
	@RequestMapping(value = "/powermenu",method = RequestMethod.GET)
	@ResponseBody
	public Object editUserNoRole(@RequestParam("pk_permid") String pk_permid)throws GlobalException{
		Map map = iMenuService.editMenuNoPower(pk_permid);
		return map;
	}

	/**
	 * 修改用户状态
	 * @param whereMap
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(value = "/editmenuisenable",method = RequestMethod.POST)
	@ResponseBody
	public Object updateUserState(@RequestBody Map whereMap)throws GlobalException{
		Map map = iMenuService.editMenuIsenable(whereMap);
		return map;
	}

	/**
	 * 通过权限查询分配的权限
	 * @return
	 * @throws GlobalException
     */
	@RequestMapping(value = "/permissionmenulist")
	@ResponseBody
	public Object selectMenuByPermissionmenu(@RequestParam("pk_permid")String pk_permid)throws GlobalException{
		Map resultMap = iMenuService.selectMenuByPermissionmenu(pk_permid);
		return resultMap;
	}
}
