package com.bjfe.genuine.software.invoicingsystem.mapper.qxgl;

/**
 * Created by Administrator on 2017/11/12.
 */

import com.bjfe.genuine.software.invoicingsystem.model.permissionmenu.PermissionMenuVO;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;
import java.util.List;

/**
 * 权限职责数据持久接口
 * 编写人：宋超洋
 */
public interface IPermissionMenuDao {
    /**
     * 添加权限菜单关系
     * @param powerMenuVO
     * @return
     */
    public int insertPowerMenu(PermissionMenuVO powerMenuVO)throws SQLException;

    /**
     * 通过角色主键查询权限菜单关系
     * @param pk_permid
     * @return
     * @throws SQLException
     */
    public List<PermissionMenuVO> selectPowerMenuByCondition(String pk_permid)throws SQLException;

    /**
     * 把dr置为1进行删除权限菜单关系
     * @param pkMenuList
     * @param pk_power
     * @return
     */
    public int deletePowerMenu(@Param("list") List<String> pkMenuList, @Param("pk_permid") String pk_power)throws SQLException;

    /**
     * 修改表中已有权限职责dr字段
     * @param pk_power
     * @param pk_menu
     * @throws SQLException
     */
    public void updatePowerMenuDr(@Param("pk_permid") String pk_power, @Param("cfunid") String pk_menu)throws SQLException;
}
