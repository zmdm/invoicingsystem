package com.bjfe.genuine.software.invoicingsystem.mapper.qxgl;

import com.bjfe.genuine.software.invoicingsystem.model.qxgl.RightsManVO;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
/**
 * Created by Administrator on 2018-01-04.
 */
/**
 * 权限管理数据持久接口
 * 编写人：李宗泽
 */
public interface IPermissionDao {
        /**
         * 查询显示权限
         * @return
         * @throws SQLException
         */
        public List<RightsManVO> selectRightsManList(Map map)throws SQLException;

        /**
         * 查询权限数量
         * @param map
         * @return
         * @throws SQLException
         */
        public int selectRightsManCount(Map map)throws SQLException;

        /**
         * 添加权限
         * @param rightsManVO
         * @return
         * @throws SQLException
         */
        public int insertRightsMan(RightsManVO rightsManVO)throws SQLException;

        /**
         * 删除权限
         * @param pk_permid
         * @return
         * @throws SQLException
         */
        public int deleteRightsMan(String[]pk_permid)throws SQLException;

        /**
         * 修改权限
         * @param rightsManVO
         * @return
         * @throws SQLException
         */
        public int updateRightsMan(RightsManVO rightsManVO)throws SQLException;

        /**
         * 查询所有角色权限信息是否分配
         * @return
         * @throws SQLException
         */
        public List<RightsManVO> editPowerNoRole(String pk_role)throws SQLException;

        /**
         * 通过角色查询关联的权限
         * @param pk_role
         * @return
         * @throws SQLException
         */
        public List<RightsManVO> selectRightByRole(String pk_role)throws SQLException;

    /**
     * 通过编码和名称查询权限数量
     * @param code
     * @param name
     * @return
     * @throws SQLException
     */
        public int selectPermissionCountBycodeAndname(@Param("code")String code,@Param("name")String name)throws SQLException;

}


