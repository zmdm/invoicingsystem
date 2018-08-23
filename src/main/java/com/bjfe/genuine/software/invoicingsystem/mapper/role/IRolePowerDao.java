package com.bjfe.genuine.software.invoicingsystem.mapper.role;

/**
 * Created by Administrator on 2017/11/10.
 */

import com.bjfe.genuine.software.invoicingsystem.model.rolepermission.RolePermissionVO;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;
import java.util.List;

/**
 * 角色权限关系数据持久接口
 * 编写人：宋超洋
 */
public interface IRolePowerDao {
    /**
     * 添加角色权限关系
     * @param rolePermissionVO
     * @return
     */
    public int insertRolePower(RolePermissionVO rolePermissionVO)throws SQLException;

    /**
     * 通过角色主键查询角色权限关系
     * @param pk_role
     * @param pk_role
     * @return
     * @throws SQLException
     */
    public List<RolePermissionVO> selectRolePowerByCondition(String pk_role)throws SQLException;

    /**
     * 把dr置为1进行删除角色权限关系
     * @param pkPowerList
     * @param pk_role
     * @return
     */
    public int deleteRolePower(@Param("list") List<String> pkPowerList, @Param("pk_role") String pk_role)throws SQLException;

    /**
     * 修改表中已有角色权限dr字段
     * @param pk_role
     * @param pk_power
     * @throws SQLException
     */
    public void  updateRolePowerDr(@Param("pk_role") String pk_role, @Param("pk_permid") String pk_power)throws SQLException;
}
