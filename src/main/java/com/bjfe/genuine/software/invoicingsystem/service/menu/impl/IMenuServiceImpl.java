package com.bjfe.genuine.software.invoicingsystem.service.menu.impl;

import com.bjfe.genuine.software.invoicingsystem.mapper.menu.IMenuDao;
import com.bjfe.genuine.software.invoicingsystem.model.cdl.MenuItemVO;
import com.bjfe.genuine.software.invoicingsystem.model.pub.constant.ConstantVO;
import com.bjfe.genuine.software.invoicingsystem.model.pub.exception.GlobalException;
import com.bjfe.genuine.software.invoicingsystem.service.menu.IMenuService;
import com.bjfe.genuine.software.invoicingsystem.util.annotation.InitMenuParam;
import com.bjfe.genuine.software.invoicingsystem.util.code.CodeUtil;
import com.bjfe.genuine.software.invoicingsystem.util.paging.PagingUtilVO;
import com.bjfe.genuine.software.invoicingsystem.util.pk.SnowflakeIdWorker;
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
 * Created by Administrator on 2018/1/4 0004.
 */
/**
 * 菜单业务逻辑实现类
 * 编写人：张敏
 */
@Service("iMenuService")
public class IMenuServiceImpl implements IMenuService{
	@Autowired
	private RedisTemplate<String,Serializable> redisTemplate;
	@Resource(name = "IMenuDao")
	private IMenuDao IMenuDao;
	/**
	 * 添加菜单信息
	 * @param menuVO
	 * @return
	 * @throws GlobalException
	 */
	@InitMenuParam
	public Map insertMenu(MenuItemVO menuVO) throws GlobalException {
		Map map = new HashMap<>();
		try {
			int i = IMenuDao.selectMenuCountByFunname(menuVO);
			if (0==i){
				SnowflakeIdWorker worker = new SnowflakeIdWorker(9, 9);
				String pk_apply = new StringBuffer(ConstantVO.PK_MENU).append(Long.toString(worker.nextId())).toString();
				menuVO.setCfunid(pk_apply);
				if (!"".equals(menuVO.getPk_father())){
					long count = IMenuDao.selectMenuCountByFatherCode(menuVO.getPk_father());
					menuVO.setFuncode(CodeUtil.menuCode(count));
				}else {
					long count = IMenuDao.selectMenuPkFatherCount();
					menuVO.setFuncode(CodeUtil.menuCode(count));
				}
				int result = IMenuDao.insertMenu(menuVO);
				if (result==1){
					map.put("funcode",menuVO.getPk_father()+menuVO.getFuncode());
					map.put("cfunid",menuVO.getCfunid());
					map.put("success",true);
				}else {
					map.put("success",false);
				}
			}else {
				map.put("success",false);
				map.put("info","功能名称不能重复，请重新输入！");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new GlobalException(e.getMessage());
		}
		return map;
	}



	/**
	 * 查询显示菜单信息
	 * @param whereMap
	 * @return
	 * @throws GlobalException
	 */

	public Map selectMenuList(Map whereMap) throws GlobalException {
		Map dataMap = null;
		try {
			int total = IMenuDao.selectMenuCount(whereMap);
			List<MenuItemVO> dataList = IMenuDao.selectMenuList(PagingUtilVO.pagingContain(whereMap,total));
			dataMap = new HashMap<>();
			if (dataList!=null){
				dataMap.put("pagesize",PagingUtilVO.pagingContain(whereMap,total).get("pageSize"));
				dataMap.put("total",total);
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
	 * 查询所有菜单项
	 * @return
	 * @throws GlobalException
     */
	@Override
	public Map selectMenuAll() throws GlobalException {
		Map map = new HashMap<>();
		ValueOperations<String, Serializable> valueOperations = redisTemplate.opsForValue();
		List<MenuItemVO> menulist = (ArrayList<MenuItemVO>)valueOperations.get("menulist");
		map.put("datalist",menulist);
		map.put("success",true);
		return map;
	}

	/**
	 * 删除菜单信息
	 * @param pk_menu
	 * @return
	 * @throws GlobalException
	 */
	@InitMenuParam
	public Map deleteMenu(String[] pk_menu) throws GlobalException {
		Map map = new HashMap<>();
		try {
			int result = IMenuDao.deleteMenu(pk_menu);
			if (result >0){
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
	 * 修改菜单信息
	 * @param MenuVO
	 * @return
	 * @throws GlobalException
	 */
	@InitMenuParam
	public Map updateMenu(MenuItemVO MenuVO) throws GlobalException {
		Map map = new HashMap<>();
		int result = 0;
		try {
			String funname = IMenuDao.selectMenuNameBycfunid(MenuVO.getCfunid());
			if (funname.equals(MenuVO.getFunname())){
				result = IMenuDao.updateMenu(MenuVO);
			}else {
				int i = IMenuDao.selectMenuCountByFunname(MenuVO);
				if (0==i){
					result = IMenuDao.updateMenu(MenuVO);
				}else {
					map.put("success",false);
					map.put("info","功能名称不能重复，请重新输入！");
				}
			}
			if (result ==1){
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
	 * 查询所有权限菜单信息是否分配
	 * @param pk_power
	 * @return
	 * @throws GlobalException
     */
	@Override
	public Map editMenuNoPower(String pk_power) throws GlobalException {
		Map map =new HashMap<>();
		try {
			List<MenuItemVO> list = IMenuDao.editMenuNoPower(pk_power);
			List<MenuItemVO> listTemp1 = new ArrayList<>();
			PagingUtilVO.sortStringMethod(list);
			if (list.size()>0){
				for (int i=0;i<list.size();i++){
					MenuItemVO menuItem1 = list.get(i);
					List<MenuItemVO> listTemp = new ArrayList<>();
					if ("".equals(menuItem1.getPk_father())){
						for (int b=0;b<list.size();b++){
							MenuItemVO menuItem2 = list.get(b);
							if (menuItem1.getFuncode().equals(menuItem2.getPk_father())){
								listTemp.add(menuItem2);
							}
						}
					}
					menuItem1.setMenuItemVOList(listTemp);
					if (menuItem1.getMenuItemVOList().size() !=0 || "".equals(menuItem1.getPk_father()) ){
						listTemp1.add(menuItem1);
					}
				}
				map.put("datalist",listTemp1);
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
	 * 编辑菜单项状态
	 * @param whereMap
	 * @return
	 * @throws GlobalException
     */
	@Override
	public Map editMenuIsenable(Map whereMap) throws GlobalException {
		Map map = new HashMap<>();
		try {
			String pk = (String) whereMap.get("cfunid");
			String []cuserids = pk.split(",");
			int result = IMenuDao.editMenuIsenable((Integer) whereMap.get("isenable"),cuserids);
			if (result>0){
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
	 * 通过权限查询分配的权限
	 * @param pk_permid
	 * @return
	 * @throws GlobalException
     */
	@Override
	public Map selectMenuByPermissionmenu(String pk_permid) throws GlobalException {
		Map map = new HashMap<>();
		try {
			List<MenuItemVO> dataList = IMenuDao.selectMenuByPermiddsion(pk_permid);
			if (dataList.size()>0){
				map.put("success",true);
				map.put("datalist",dataList);
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
