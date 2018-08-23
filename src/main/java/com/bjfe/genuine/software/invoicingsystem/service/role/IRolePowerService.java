package com.bjfe.genuine.software.invoicingsystem.service.role;

/**
 * Created by Administrator on 2017/11/10.
 */

import com.bjfe.genuine.software.invoicingsystem.model.pub.exception.GlobalException;
import com.bjfe.genuine.software.invoicingsystem.model.rolepermission.RolePermissionVO;

import java.util.List;

/**
 * 角色权限关系业务逻辑接口
 * 编写人：宋超洋
 */
public interface IRolePowerService {
    /**
     * 编辑角色权限关系
     * @param newRolePowerList
     * @return
     */
    public int editRolePower(List<RolePermissionVO> newRolePowerList)throws GlobalException;
}
