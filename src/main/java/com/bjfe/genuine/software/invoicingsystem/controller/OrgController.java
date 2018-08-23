package com.bjfe.genuine.software.invoicingsystem.controller;

import com.bjfe.genuine.software.invoicingsystem.model.org.OrgVO;
import com.bjfe.genuine.software.invoicingsystem.model.pub.exception.GlobalException;
import com.bjfe.genuine.software.invoicingsystem.service.org.IOrgService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/29.
 */

/**
 * 组织控制器类
 * 编写人：宋超洋
 */
@CrossOrigin(origins = "*",maxAge = 1000)
@RequestMapping("/org")
@Controller
public class OrgController {
    @Resource(name = "iOrgService")
    private IOrgService iOrgService;

    /**
     * 添加组织
     * @param orgVO
     * @return
     * @throws GlobalException
     */
    @RequestMapping(value = "/insertorg",method = RequestMethod.POST)
    @ResponseBody
    public Object insertOrg(@RequestBody OrgVO orgVO)throws GlobalException{
        Map map = iOrgService.insertOrg(orgVO);
        return map;
    }

    /**
     * 查询所有组织
     * @return
     * @throws GlobalException
     */
    @RequestMapping(value = "/queryorg",method = RequestMethod.POST)
    @ResponseBody
    public Object selectOrgList(@RequestBody Map whereMap)throws GlobalException{
        Map map  = iOrgService.selectOrgList(whereMap);
        return map;
    }

    /**
     * 查询所有组织
     * @return
     * @throws GlobalException
     */
    @RequestMapping(value = "/queryorgPage",method = RequestMethod.POST)
    @ResponseBody
    public Object selectOrgPage(@RequestBody Map whereMap)throws GlobalException{
        Map map  = iOrgService.selectOrgPage(whereMap);
        return map;
    }


    /**
     * 修改组织
     * @param orgVO
     * @return
     * @throws GlobalException
     */
    @RequestMapping(value = "/updateorg",method = RequestMethod.POST)
    @ResponseBody
    public Object updateOrg(@RequestBody OrgVO orgVO)throws GlobalException{
        Map map = iOrgService.updateOrg(orgVO);
        return map;
    }

    /**
     * 删除组织
     * @param map
     * @return
     * @throws GlobalException
     */
    @RequestMapping(value = "/deleteorg",method = RequestMethod.POST)
    @ResponseBody
    public Object deleteOrg(@RequestBody Map<String,String>map)throws GlobalException{
        String pk = map.get("pk_org");
        String []pk_org = pk.split(",");
        Map resultMap = iOrgService.deleteOrg(pk_org);
        return resultMap;
    }
}
