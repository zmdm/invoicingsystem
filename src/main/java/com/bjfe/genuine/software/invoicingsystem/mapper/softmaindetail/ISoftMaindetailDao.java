package com.bjfe.genuine.software.invoicingsystem.mapper.softmaindetail;

/**
 * Created by Administrator on 2018/1/28.
 */

import com.bjfe.genuine.software.invoicingsystem.model.pub.exception.GlobalException;
import com.bjfe.genuine.software.invoicingsystem.model.softmaindetail.SoftMaindetailBVO;
import com.bjfe.genuine.software.invoicingsystem.model.softmaindetail.SoftMaindetailHVO;
import com.bjfe.genuine.software.invoicingsystem.model.softmaindetail.SoftMaindetailSubVO;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 软件安装维护明细数据持久接口
 * 编写人：宋超洋
 */
public interface ISoftMaindetailDao {
    /**
     * 查询显示软件安装维护子表信息
     * @return
     * @throws GlobalException
     */
    public List<SoftMaindetailBVO> selectSoftMaindetailBVOList(Map whereMap)throws SQLException;

    /**
     * 查询显示软件安装维护明细表信息
     * @return
     * @throws GlobalException
     */
    public List<SoftMaindetailSubVO> selectSoftMaindetailSubVOList(Map whereMap)throws SQLException;

    /**
     * 查询显示软件安装维护子表数量
     * @param whereMap
     * @return
     * @throws GlobalException
     */
    public int selectSoftMaindetailBVOCount(Map whereMap)throws SQLException;

    /**
     * 添加软件安装维护主表信息
     * @param softMaindetailHVO
     * @return
     * @throws SQLException
     */
    public int insertSoftMaindetailHVO(SoftMaindetailHVO softMaindetailHVO)throws SQLException;

    /**
     * 添加软件安装维护子表信息
     * @param softMaindetailBVO
     * @return
     * @throws SQLException
     */
    public int insertSoftMaindetailBVO(SoftMaindetailBVO softMaindetailBVO)throws SQLException;

    /**
     * 添加软件安装维护子表明细
     * @param list
     * @return
     * @throws SQLException
     */
    public int insertSoftMaindetailSub(List<SoftMaindetailSubVO> list)throws SQLException;

    /**
     * 通过组织查询软件安装维护子表
     * @param map
     * @return
     * @throws SQLException
     */
    public SoftMaindetailBVO selectSoftMaindetailBVOByPk_orgAndCreator(Map map)throws SQLException;

    /**
     * 通过通过子表查询明细表数量
     * @param str
     * @return
     * @throws SQLException
     */
    public int selectSoftMaindetailSubCountBySoftMaindetailBVO(String str)throws SQLException;
}
