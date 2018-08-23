package com.bjfe.genuine.software.invoicingsystem.controller;

import com.bjfe.genuine.software.invoicingsystem.model.pub.exception.GlobalException;
import com.bjfe.genuine.software.invoicingsystem.model.userrole.UserRoleVO;
import com.bjfe.genuine.software.invoicingsystem.service.role.IUserRoleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/11/7.
 */
@CrossOrigin(origins = "*", maxAge = 1000)
@RequestMapping("/userrole")
@Controller
public class UserRoleController {
    @Resource(name = "IUserRoleService")
    private IUserRoleService IUserRoleService;

    /**
     * 管理用户角色关系
     * @param list
     * @return
     */
    @RequestMapping(value = "/userroleedit",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Object editUserRole(@RequestBody List<UserRoleVO> list)throws GlobalException{
        Map map = new HashMap<>();
        if(list!=null&&list.size()>0) {
            int result = IUserRoleService.editUserRole(list);
            if (result > 0) {
                map.put("success", true);
            } else {
                map.put("success", false);
            }
        }
        return map;
    }
}
