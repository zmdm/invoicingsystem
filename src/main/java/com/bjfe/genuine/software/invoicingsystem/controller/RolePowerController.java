package com.bjfe.genuine.software.invoicingsystem.controller;

/**
 * Created by Administrator on 2017/11/10.
 */

import com.bjfe.genuine.software.invoicingsystem.model.pub.exception.GlobalException;
import com.bjfe.genuine.software.invoicingsystem.model.rolepermission.RolePermissionVO;
import com.bjfe.genuine.software.invoicingsystem.service.role.IRolePowerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 角色权限关系控制类
 * 编写人：宋超洋
 */
@CrossOrigin(origins = "*", maxAge = 1000)
@RequestMapping("/rolepower")
@Controller
public class RolePowerController {
    @Resource(name = "IRolePowerService")
    private IRolePowerService iRolePowerService;

    /**
     * 管理角色权限关系
     * @param list
     * @return
     * @throws GlobalException
     */
    @RequestMapping(value = "/rolepoweredit",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Object editUserRole(@RequestBody List<RolePermissionVO> list)throws GlobalException{
        Map map = new HashMap<>();
        if(list!=null&&list.size()>0) {
            int result = iRolePowerService.editRolePower(list);
            if (result > 0) {
                map.put("success", true);
            } else {
                map.put("success", false);
            }
        }
        return map;
    }
}
