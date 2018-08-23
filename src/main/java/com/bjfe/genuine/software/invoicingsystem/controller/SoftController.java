package com.bjfe.genuine.software.invoicingsystem.controller;

/**
 * Created by Administrator on 2018/1/9.
 */

import com.bjfe.genuine.software.invoicingsystem.model.pub.exception.GlobalException;
import com.bjfe.genuine.software.invoicingsystem.model.rjlx.SoftTypeVO;
import com.bjfe.genuine.software.invoicingsystem.model.rjxx.SoftInfoVO;
import com.bjfe.genuine.software.invoicingsystem.service.software.ISoftInfoService;
import com.bjfe.genuine.software.invoicingsystem.service.software.ISoftTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 软件控制器
 * 编写人：宋超洋
 */
@CrossOrigin(origins = "*",maxAge = 1000)
@RequestMapping("/software")
@Controller
public class SoftController {
    @Resource(name = "iSoftInfoService")
    private ISoftInfoService iSoftInfoService;
    @Resource(name = "iSoftTypeService")
    private ISoftTypeService iSoftTypeService;

    /**
     * 添加软件信息
     * @param softInfoVO
     * @return
     * @throws GlobalException
     */
    @RequestMapping(value = "/insertsoftinfo",method = RequestMethod.POST)
    @ResponseBody
    public Object insertSoftInfo(@RequestBody SoftInfoVO softInfoVO)throws GlobalException {
        Map map = iSoftInfoService.insertSoftInfo(softInfoVO);
        return map;
    }

    /**
     * 查询软件信息
     * @param whereMap
     * @return
     * @throws GlobalException
     */
    @RequestMapping(value = "/selectsoftinfolist",method = RequestMethod.POST)
    @ResponseBody
    public Object selectSoftInfoList(@RequestBody Map whereMap)throws GlobalException{
        Map map = iSoftInfoService.selectSoftInfoList(whereMap);
        return map;
    }

    /**
     * 修改软件信息
     * @param softInfoVO
     * @return
     * @throws GlobalException
     */
    @RequestMapping(value = "/updatesoftinfo",method = RequestMethod.POST)
    @ResponseBody
    public Object updateSoftInfo(@RequestBody SoftInfoVO softInfoVO)throws GlobalException{
        Map map = iSoftInfoService.updateSoftInfo(softInfoVO);
        return map;
    }

    /**
     * 删除软件信息
     * @param map
     * @return
     * @throws GlobalException
     */
    @RequestMapping(value = "/deletesoftinfo",method = RequestMethod.POST)
    @ResponseBody
    public Object deleteSoftInfo(@RequestBody Map<String,String> map)throws GlobalException{
        String cd=map.get("pk_soft");
        String[]pk_soft=cd.split(",");
        Map resultMap = iSoftInfoService.deleteSoftInfo(pk_soft);
        return resultMap;
    }

    /**
     * 添加软件类型
     * @param softTypeVO
     * @return
     * @throws GlobalException
     */
    @RequestMapping(value = "/insertsofttype",method = RequestMethod.POST)
    @ResponseBody
    public Object insertSoftType(@RequestBody SoftTypeVO softTypeVO)throws GlobalException {
        Map map = iSoftTypeService.insertSoftType(softTypeVO);
        return map;
    }

    /**
     * 查询软件类型
     * @param
     * @return
     * @throws GlobalException
     */
    @RequestMapping(value = "/selectsofttypelist",method = RequestMethod.POST)
    @ResponseBody
    public Object selectSoftTypeList(@RequestBody Map whereMap)throws GlobalException{
        Map map = iSoftTypeService.selectSoftTypeList(whereMap);
        return map;
    }

    /**
     * 修改软件类型
     * @param softTypeVO
     * @return
     * @throws GlobalException
     */
    @RequestMapping(value = "/updatesofttype",method = RequestMethod.POST)
    @ResponseBody
    public Object updateSoftType(@RequestBody SoftTypeVO softTypeVO)throws GlobalException{
        Map map = iSoftTypeService.updateSoftType(softTypeVO);
        return map;
    }

    /**
     * 删除软件类型
     * @param map
     * @return
     * @throws GlobalException
     */
    @RequestMapping(value = "/deletesofttype",method = RequestMethod.POST)
    @ResponseBody
    public Object deleteSoftType(@RequestBody Map<String,String> map)throws GlobalException{
        String cd=map.get("pk_softtype");
        String[]pk_softtype=cd.split(",");
        Map resultMap = iSoftTypeService.deleteSoftType(pk_softtype);
        return resultMap;
    }
}
