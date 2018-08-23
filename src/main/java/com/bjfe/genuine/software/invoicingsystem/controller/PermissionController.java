package com.bjfe.genuine.software.invoicingsystem.controller;

import com.bjfe.genuine.software.invoicingsystem.model.qxgl.RightsManVO;
import com.bjfe.genuine.software.invoicingsystem.model.pub.exception.GlobalException;
import com.bjfe.genuine.software.invoicingsystem.service.qxgl.IPermissionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;
/**
 * Created by Administrator on 2018-01-04.
 */
/**
 * 权限控制器类
 * 编写人：李宗泽
 */
@CrossOrigin(origins = "*",maxAge = 1000)
@RequestMapping("/rightsman")
@Controller
public class PermissionController {
        @Resource(name = "iPermissionService")
        private IPermissionService IPermissionService;

        /**
         * 添加权限
         * @param rightsManVO
         * @return
         * @throws GlobalException
         */
        @RequestMapping(value = "/insertrightsman",method = RequestMethod.POST)
        @ResponseBody
        public Object insertRightsMan(@RequestBody RightsManVO rightsManVO)throws GlobalException{
            Map map = IPermissionService.insertRightsMan(rightsManVO);
            return map;
        }

        /**
         * 查询显示权限
         * @param whereMap
         * @return
         * @throws GlobalException
         */
        @RequestMapping(value = "/selectrightsmanlist",method = RequestMethod.POST)
        @ResponseBody
        public Object selectRightsManList(@RequestBody Map whereMap)throws GlobalException{
            Map map = IPermissionService.selectRightsManList(whereMap);
            return map;
        }

        /**
         * 修改权限
         * @param rightsManVO
         * @return
         * @throws GlobalException
         */
        @RequestMapping(value = "/updaterightsman",method = RequestMethod.POST)
        @ResponseBody
        public Object updateRightsMan(@RequestBody RightsManVO rightsManVO)throws GlobalException{
            Map map = IPermissionService.updateRightsMan(rightsManVO);
            return map;
        }

        /**
         * 删除权限
         * @param map
         * @return
         * @throws GlobalException
         */
        @RequestMapping(value = "/deleterightsMan",method = RequestMethod.POST)
        @ResponseBody
        public Object deleteRightsMan(@RequestBody Map<String,String> map)throws GlobalException{
            String cd=map.get("pk_permid");
            String[]pk_permid=cd.split(",");
            Map resultMap = IPermissionService.deleteRightsMan(pk_permid);
            return resultMap;
        }
        /**
         * 查询所有角色权限信息是否分配
         * @return
         * @throws GlobalException
         */
        @RequestMapping(value = "/rolepower",method = RequestMethod.GET)
        @ResponseBody
        public Object editPowerNoRole(@RequestParam("pk_role") String pk_role)throws GlobalException{
            Map map = IPermissionService.editPowerNoRole(pk_role);
            return map;
        }
    }




