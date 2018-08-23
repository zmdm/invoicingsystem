package com.bjfe.genuine.software.invoicingsystem.service.role;

/**
 * Created by Administrator on 2017/11/7.
 */

import com.bjfe.genuine.software.invoicingsystem.model.pub.exception.GlobalException;
import com.bjfe.genuine.software.invoicingsystem.model.userrole.UserRoleVO;

import java.util.List;

/**
 * 用户角色关系数据逻辑接口
 * 编写人：宋超洋
 */
public interface IUserRoleService {
    /**
     * 添加用户角色关系
     * @param newUserRolebList
     * @return
     */
    public int editUserRole(List<UserRoleVO> newUserRolebList)throws GlobalException;
}
