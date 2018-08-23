package com.bjfe.genuine.software.invoicingsystem.mapper.cuser;

import com.bjfe.genuine.software.invoicingsystem.model.cdl.MenuItemVO;
import com.bjfe.genuine.software.invoicingsystem.model.cuser.CuserVO;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
/**
 * Created by Administrator on 2018-01-03.
 */
/**
 * 用户数据持久接口
 * 编写人：李宗泽
 */
public interface ICuserDao {
    /**
     * 查询显示用户
     * @return
     * @throws SQLException
     */
    public List<CuserVO> selectCuserList(Map map)throws SQLException;

    /**
     * 查询用户数量
     * @param map
     * @return
     * @throws SQLException
     */
    public int selectCuserCount(Map map)throws SQLException;

    /**
     * 添加用户
     * @param cuserVO
     * @return
     * @throws SQLException
     */
    public int insertCuser(CuserVO cuserVO)throws SQLException;

    /**
     * 删除用户
     * @param cuserid
     * @return
     * @throws SQLException
     */
    public int deleteCuser(String[]cuserid)throws SQLException;

    /**
     * 修改用户
     * @param cuserVO
     * @return
     * @throws SQLException
     */
    public int updateCuser(CuserVO cuserVO)throws SQLException;

    /**
     * 通过角色查询关联的用户
     * @return
     * @throws SQLException
     */
    public List<CuserVO> selectUserByRole(String pk_role)throws SQLException;

    /**
     * 通过用户编码和密码查询用户
     * @return
     */
    public CuserVO customerByCodeAndPass(CuserVO cuserVO)throws SQLException;

    /**
     * 查询所有用户角色信息是否分配
     * @param pk_role
     * @return
     * @throws SQLException
     */
    public List<CuserVO> editUserNoRole(String pk_role)throws SQLException;

    /**
     * 编辑用户状态
     * @return
     * @throws SQLException
     */
    public int updatePsndocIstate(@Param("enablestate") int enablestate,@Param("cuserids") String[]cuserids)throws SQLException;

    /**
     * 通过编码查询用户数量
     * @param usercode,username
     * @return
     * @throws SQLException
     */
    public int selectPsndocByCode(@Param("username") String username,@Param("usercode") String usercode)throws SQLException;

    /**
     * 查询所有用户
     * @return
     * @throws SQLException
     */
    public List<CuserVO> selectUserAll()throws SQLException;

    /**
     * 查询原始密码
     * @return
     * @throws SQLException
     */
    public String selectOldPass(String cuserid)throws SQLException;


    /**
     * 查询本部门领导用户名称
     * @param map
     * @return
     * @throws SQLException
     */
    public String selectDeptLeaderUser(Map map)throws SQLException;

    /**
     * 查询信息化部门用户
     * @param map
     * @return
     * @throws SQLException
     */
    public List<String> selectInternationDeptUser(Map map)throws SQLException;

    /**
     * 查询财务部门用户
     * @param map
     * @return
     * @throws SQLException
     */
    public List<String> selectFinanceDeptUser(Map map)throws SQLException;

    /**
     * 查询单位领导用户
     * @param map
     * @return
     * @throws SQLException
     */
    public List<String> selectOrgLederUser(Map map)throws SQLException;
}
