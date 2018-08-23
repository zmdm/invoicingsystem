package com.bjfe.genuine.software.invoicingsystem.mapper.softsummary;

/**
 * Created by Administrator on 2018/1/27.
 */

import com.bjfe.genuine.software.invoicingsystem.model.softsummary.SoftSummaryBVO;
import com.bjfe.genuine.software.invoicingsystem.model.softsummary.SoftSummaryHVO;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 软件使用情况数据持久层
 * 编写人：宋超洋
 */
public interface ISoftSummaryDao {
    /**
     * 通过组织查询软件使用情况主表数据
     * @param pk_org
     * @return
     * @throws SQLException
     */
    public SoftSummaryHVO selectSoftSummaryHVOByPk_org(String pk_org)throws SQLException;

    /**
     * 通过组织查询询软件使用情况子表数据
     * @param map
     * @return
     * @throws SQLException
     */
    public List<SoftSummaryBVO> selectSoftSummaryBVOByPk_org(Map map)throws SQLException;

    /**
     * 通过组织查询询软件使用情况子表数量
     * @param map
     * @return
     * @throws SQLException
     */
    public int selectSoftSummaryBVOCountByPk_org(Map map)throws SQLException;

    /**
     * 添加软件使用情况主表信息
     * @param softSummaryHVO
     * @return
     * @throws SQLException
     */
    public int insertSoftSummaryHVO(SoftSummaryHVO softSummaryHVO)throws SQLException;

    /**
     * 添加软件使用情况子表信息
     * @param list
     * @return
     * @throws SQLException
     */
    public int insertSoftSummaryBVO(List<SoftSummaryBVO> list)throws SQLException;

    /**
     * 查询组中的软件
     * @param map
     * @return
     * @throws SQLException
     */
    public SoftSummaryBVO selectSoftSummaryBVOBypk_softAndPK_org(Map map)throws SQLException;

    /**
     * 修改软件安装数量
     * @param softSummaryBVO
     * @throws SQLException
     */
    public void updateSoftSummaryBVOinstallnum(SoftSummaryBVO softSummaryBVO)throws SQLException;
}
