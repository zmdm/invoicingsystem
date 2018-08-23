package com.bjfe.genuine.software.invoicingsystem.mapper.computer;

/**
 * Created by Administrator on 2018/1/16.
 */

import com.bjfe.genuine.software.invoicingsystem.model.computer.ComputerVO;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 计算机管理数据持久接口
 * 编写人：宋超洋
 */
public interface IComputerDao {
    /**
     * 添加计算机管理信息
     * @return
     * @throws SQLException
     */
    public int insertComputer(ComputerVO computerVO)throws SQLException;

    /**
     * 查询显示计算机管理信息
     * @return
     * @throws SQLException
     */
    public List<ComputerVO> selectComputerList()throws SQLException;

    /**
     * 修改计算机管理信息
     * @param computerVO
     * @return
     * @throws SQLException
     */
    public int updateComputer(ComputerVO computerVO)throws SQLException;

    /**
     * 删除计算机管理信息
     * @param list
     * @return
     * @throws SQLException
     */
    public int deleteComputer(List<String> list)throws SQLException;

    /**
     * 通过编码查询计算机管理
     * @param code
     * @return
     * @throws SQLException
     */
    public ComputerVO selectComputerByCode(String code)throws SQLException;

    /**
     * 通过组织查询使用人数
     * @param whereMap
     * @return
     * @throws SQLException
     */
    public int selectUseCount(Map whereMap)throws SQLException;

    /**
     * 通过组织查询计算机数量
     * @return
     * @throws SQLException
     */
    public int selectComputerCountByPk_org(Map whereMap)throws SQLException;
}
