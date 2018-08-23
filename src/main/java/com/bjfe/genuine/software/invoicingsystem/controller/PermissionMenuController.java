package com.bjfe.genuine.software.invoicingsystem.controller;

/**
 * Created by Administrator on 2017/11/10.
 */

import com.bjfe.genuine.software.invoicingsystem.model.permissionmenu.PermissionMenuVO;
import com.bjfe.genuine.software.invoicingsystem.model.pub.exception.GlobalException;
import com.bjfe.genuine.software.invoicingsystem.service.qxgl.IPermissionMenurService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 权限菜单关系控制类
 * 编写人：宋超洋
 */
@CrossOrigin(origins = "*", maxAge = 1000)
@RequestMapping("/powermenu")
@Controller
public class PermissionMenuController {
    @Resource(name = "IPermissionMenurService")
    private IPermissionMenurService IPermissionMenurService;

    /**
     * 管理权限菜单关系
     * @param list
     * @return
     * @throws GlobalException
     */
    @RequestMapping(value = "/powermenuedit",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Object editPowerMenu(@RequestBody List<PermissionMenuVO> list)throws GlobalException {
        Map map = new HashMap<>();
        if(list!=null&&list.size()>0) {
            int result = IPermissionMenurService.editPowerMenu(list);
            if (result > 0) {
                map.put("success", true);
            } else {
                map.put("success", false);
            }
        }
        return map;
    }
}
