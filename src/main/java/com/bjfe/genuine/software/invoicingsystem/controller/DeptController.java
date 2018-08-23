package com.bjfe.genuine.software.invoicingsystem.controller;

import com.bjfe.genuine.software.invoicingsystem.model.dept.DeptVO;
import com.bjfe.genuine.software.invoicingsystem.model.pub.exception.GlobalException;
import com.bjfe.genuine.software.invoicingsystem.service.dept.IDeptService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 部门控制器类
 * 编写人：李宗泽
 */
/**
 * Created by Administrator on 2018-01-02.
 */

@CrossOrigin(origins = "*",maxAge = 1000)
@RequestMapping("/dept")
@Controller
public class DeptController {
    @Resource(name = "iDeptService")
    private IDeptService iDeptService;

    /**
     * 添加部门
     * @param deptVO
     * @return
     * @throws GlobalException
     */
    @RequestMapping(value = "/insertdept",method = RequestMethod.POST)
    @ResponseBody
    public Object insertDept(@RequestBody DeptVO deptVO)throws GlobalException{
        Map map = iDeptService.insertDept(deptVO);
        return map;
    }

    /**
     * 查询所有部门
     * @return
     * @throws GlobalException
     */
    @RequestMapping(value = "/querydeptlist",method = RequestMethod.POST)
    @ResponseBody
    public Object selectDeptList(@RequestBody Map whereMap)throws GlobalException{
        Map map = new HashMap<>();
        map = iDeptService.selectDeptList(whereMap);
        map.put("success",true);
        return map;
    }


    /**
     * 条件查询部门
     * @return
     * @throws GlobalException
     */
    @RequestMapping(value = "/querydeptpage",method = RequestMethod.POST)
    @ResponseBody
    public Object selectDeptPage(@RequestBody Map whereMap)throws GlobalException{
        Map map = new HashMap<>();
        map = iDeptService.selectDeptPage(whereMap);
        map.put("success",true);
        return map;
    }


    /**
     * 修改部门
     * @param deptVO
     * @return
     * @throws GlobalException
     */
    @RequestMapping(value = "/updatedept",method = RequestMethod.POST)
    @ResponseBody
    public Object updateOrg(@RequestBody DeptVO deptVO)throws GlobalException{
        Map map = iDeptService.updateDept(deptVO);
        return map;
    }

    /**
     * 删除部门
     * @param map
     * @return
     * @throws GlobalException
     */
    @RequestMapping(value = "/deletedept",method = RequestMethod.POST)
    @ResponseBody
    public Object deleteOrg(@RequestBody Map<String,String>map)throws GlobalException{
        String pk = map.get("pk_deptdoc");
        String []pk_deptdoc = pk.split(",");
        Map resultMap = iDeptService.deleteDept(pk_deptdoc);
        return resultMap;
    }
}
