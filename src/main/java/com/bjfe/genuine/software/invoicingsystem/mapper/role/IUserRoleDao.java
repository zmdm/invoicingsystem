package com.bjfe.genuine.software.invoicingsystem.mapper.role;

/**
 * Created by Administrator on 2018/1/4.
 */

import com.bjfe.genuine.software.invoicingsystem.model.userrole.UserRoleVO;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;
import java.util.List;

/**
 * 用户角色数据持久接口
 * 编写人：宋超洋
 */
public interface IUserRoleDao {
    /**
     * 添加用户角色关系
     * @return
     */
    public int insertUserRole(UserRoleVO userRoleVO)throws SQLException;
    /**
     * 通过角色主键查询角色用户关系
     * @param pk_role
     * @return
     */
    public List<UserRoleVO> selectUserRoleByConditon(String pk_role)throws SQLException;

    /**
     * 把dr置为1进行删除用户角色关系
     * @return
     */
    public int deleteUserRole(@Param("list") List<String> pkUserList, @Param("pk_role") String pk_role)throws SQLException;
    /**
     * 修改表中已有用户角色dr字段
     * @param pk_user
     * @param pk_role
     * @throws SQLException
     */
    public void updateUserRoleDr(@Param("cuserid")String pk_user,@Param("pk_role")String pk_role)throws SQLException;
}
