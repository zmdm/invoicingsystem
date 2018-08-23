package com.bjfe.genuine.software.invoicingsystem.controller;

/**
 * Created by Administrator on 2018/1/2.
 */

import com.bjfe.genuine.software.invoicingsystem.model.psndoc.PsndocVO;
import com.bjfe.genuine.software.invoicingsystem.model.pub.exception.GlobalException;
import com.bjfe.genuine.software.invoicingsystem.service.psndoc.IPsndocService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 人员信息控制器类
 * 编写人：宋超洋
 */
@CrossOrigin(origins = "*",maxAge = 1000)
@RequestMapping("/psndoc")
@Controller
public class PsndocController {
    @Resource(name = "iPsndocService")
    private IPsndocService iPsndocService;

    /**
     * 添加人员信息
     * @param psndocVO
     * @return
     * @throws GlobalException
     */
    @RequestMapping(value = "/insertpsndoc",method = RequestMethod.POST)
    @ResponseBody
    public Object insertPsndoc(@RequestBody PsndocVO psndocVO)throws GlobalException{
        Map map = iPsndocService.insertPsndoc(psndocVO);
        return map;
    }

    /**
     * 查询显示人员信息
     * @param whereMap
     * @return
     * @throws GlobalException
     */
    @RequestMapping(value = "/selectpsndoclist",method = RequestMethod.POST)
    @ResponseBody
    public Object selectPsndocList(@RequestBody Map whereMap)throws GlobalException{
        Map map = iPsndocService.selectPsndocList(whereMap);
        return map;
    }

    /**
     * 修改人员信息
     * @param psndocVO
     * @return
     * @throws GlobalException
     */
    @RequestMapping(value = "/updatepsndoc",method = RequestMethod.POST)
    @ResponseBody
    public Object updatePsndoc(@RequestBody PsndocVO psndocVO)throws GlobalException{
        Map map = iPsndocService.updatePsndoc(psndocVO);
        return map;
    }

    /**
     * 删除人员信息
     * @param map
     * @return
     * @throws GlobalException
     */
    @RequestMapping(value = "/deletepsndoc",method = RequestMethod.POST)
    @ResponseBody
    public Object deletePsndoc(@RequestBody Map<String,String> map)throws GlobalException{
        String pk=map.get("pk_psndoc");
        String[]pk_psndocs=pk.split(",");
        Map resultMap = iPsndocService.deletePsndoc(pk_psndocs);
        return resultMap;
    }

    /**
     * 修改人员信息状态
     * @param whereMap
     * @return
     * @throws GlobalException
     */
    @RequestMapping(value = "/updatepsndocstate",method = RequestMethod.POST)
    @ResponseBody
    public Object updatePsndocState(@RequestBody Map whereMap)throws GlobalException{
        Map map = iPsndocService.updatePsndocEnableState(whereMap);
        return map;
    }
}
