package com.bjfe.genuine.software.invoicingsystem.mapper.org;

/**
 * Created by Administrator on 2017/12/29.
 */

import com.bjfe.genuine.software.invoicingsystem.model.org.OrgVO;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 组织数据持久接口
 * 编写人：宋超洋
 */
public interface IOrgDao {
    /**
     * 添加组织
     * @return
     */
    public int insertOrg(OrgVO orgVO)throws SQLException;

    /**
     * 查询组织总数量
     * @return
     * @throws SQLException
     */
    public int selectOrgCount(Map whereMap)throws SQLException;

    /**
     * 查询所有组织
     * @return
     * @throws SQLException
     */
    public ArrayList<OrgVO> selectOrgList()throws SQLException;

    /**
     * 条件查询
     * @return
     * @throws SQLException
     */
    public ArrayList<OrgVO> selectOrgPage(Map map)throws SQLException;

    /**
     * 修改组织
     * @param orgVO
     * @return
     * @throws SQLException
     */
    public int updateOrg(OrgVO orgVO)throws SQLException;

    /**
     * 删除组织
     * @param pk_org
     * @return
     * @throws SQLException
     */
    public int deleteOrgs(String []pk_org)throws SQLException;

    /**
     * 通过编码查询组织
     * @return
     * @throws SQLException
     */
    public int selectOrgByCode(String code)throws SQLException;
}
