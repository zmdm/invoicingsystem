package com.bjfe.genuine.software.invoicingsystem.service.qxgl;

/**
 * Created by Administrator on 2017/11/10.
 */

import com.bjfe.genuine.software.invoicingsystem.model.permissionmenu.PermissionMenuVO;
import com.bjfe.genuine.software.invoicingsystem.model.pub.exception.GlobalException;

import java.util.List;

/**
 * 权限菜单关系业务逻辑接口
 * 编写人：宋超洋
 */
public interface IPermissionMenurService {
    /**
     * 编辑权限菜单关系
     * @param newPowerMenuList
     * @return
     */
    public int editPowerMenu(List<PermissionMenuVO> newPowerMenuList)throws GlobalException;
}
