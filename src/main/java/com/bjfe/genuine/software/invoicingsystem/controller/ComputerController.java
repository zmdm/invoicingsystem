package com.bjfe.genuine.software.invoicingsystem.controller;

import com.bjfe.genuine.software.invoicingsystem.model.computer.ComputerVO;
import com.bjfe.genuine.software.invoicingsystem.model.cuser.CuserVO;
import com.bjfe.genuine.software.invoicingsystem.model.pub.exception.GlobalException;
import com.bjfe.genuine.software.invoicingsystem.service.computer.IComputerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/16.
 */

/**
 * 计算机管理控制器类
 * 编写人：宋超洋
 */
@CrossOrigin(origins = "*",maxAge = 1000)
@RequestMapping("/computer")
@Controller
public class ComputerController {
    @Resource(name = "iComputerService")
    private IComputerService iComputerService;

    /**
     * 添加计算机信息
     * @param computerVO
     * @return
     * @throws GlobalException
     */
    @RequestMapping(value = "/insertcomputer",method = RequestMethod.POST)
    @ResponseBody
    public Object insertComputer(@RequestBody ComputerVO computerVO)throws GlobalException{
        Map map = iComputerService.insertComputer(computerVO);
        return map;
    }

    /**
     * 显示计算机信息
     * @return
     * @throws GlobalException
     */
    @RequestMapping(value = "/selectcomputerlist",method = RequestMethod.POST)
    @ResponseBody
    public Object selectComputerList(@RequestBody Map whereMap, HttpSession session,HttpServletRequest request)throws GlobalException{
        CuserVO cuserVO = (CuserVO) session.getAttribute(request.getParameter("username"));
        whereMap.put("pk_org",cuserVO.getPk_org());
        Map map = iComputerService.selectComputerList(whereMap);
        return map;
    }

    /**
     * 修改计算机信息
     * @param computerVO
     * @return
     * @throws GlobalException
     */
    @RequestMapping(value = "/updatecomputer",method = RequestMethod.POST)
    @ResponseBody
    public Object updateComputer(@RequestBody ComputerVO computerVO)throws GlobalException{
        Map map = iComputerService.updateComputer(computerVO);
        return map;
    }

    /**
     * 删除计算机信息
     * @param list
     * @return
     * @throws GlobalException
     */
    @RequestMapping(value = "/deletecomputer",method = RequestMethod.POST)
    @ResponseBody
    public Object deleteComputer(@RequestBody List<String> list)throws GlobalException{
        Map map = iComputerService.deleteComputer(list);
        return map;
    }
}
