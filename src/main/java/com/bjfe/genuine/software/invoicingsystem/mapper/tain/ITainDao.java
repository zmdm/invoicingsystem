package com.bjfe.genuine.software.invoicingsystem.mapper.tain;

/**
 * Created by Administrator on 2018/1/17.
 */

import com.bjfe.genuine.software.invoicingsystem.model.tain.TainVO;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 软件安装维护数据持久接口
 * 编写人：宋超洋
 */
public interface ITainDao {
    /**
     * 添加软件安装维护申请
     * @return
     * @throws SQLException
     */
    public int insertTain(TainVO tainVO)throws SQLException;

    /**
     * 显示软件安装维护申请
     * @return
     * @throws SQLException
     */
    public List<TainVO> selectTainList(Map wehereMap)throws SQLException;

    /**
     * 查询软件安装维护申请数量
     * @param whereMap
     * @return
     * @throws SQLException
     */
    public int selectTainCount(Map whereMap)throws SQLException;

    /**
     * 修改软件安装维护申请
     * @param tainVO
     * @return
     * @throws SQLException
     */
    public int updateTaninByPk(TainVO tainVO)throws SQLException;

    /**
     * 软件安装维护申请确认
     * @param tainVO
     * @return
     * @throws SQLException
     */
    public int TainIsOK(TainVO tainVO)throws SQLException;

    /**
     * 显示软件安装维护申请确认
     * @return
     * @throws SQLException
     */
    public List<TainVO> selectTainIsOKList(Map wehereMap)throws SQLException;

    /**
     * 分配安装维护人
     * @param whereMap
     * @return
     * @throws SQLException
     */
    /*public int distributionTain(Map whereMap)throws SQLException;*/

    /**
     * 修改软件安装维护申请表状态
     * @param tainVO
     * @throws SQLException
     */
    public void updateSoftTainBillstatus(TainVO tainVO)throws SQLException;

    /**
     * 查询正在审核的申请
     * @return
     * @throws SQLException
     */
    public List<TainVO> selectSoftTainIsApply(Map map)throws SQLException;

}
