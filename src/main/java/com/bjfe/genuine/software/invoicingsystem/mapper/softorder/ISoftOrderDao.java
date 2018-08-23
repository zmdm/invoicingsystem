package com.bjfe.genuine.software.invoicingsystem.mapper.softorder;

/**
 * Created by Administrator on 2018/1/11.
 */

import com.bjfe.genuine.software.invoicingsystem.model.softorder.SoftOrderBVO;
import com.bjfe.genuine.software.invoicingsystem.model.softorder.SoftOrderHVO;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 软件采购计划表数据持久接口
 * 编写人：宋超洋
 */
public interface ISoftOrderDao {
    /**
     * 添加软件采购计划主表
     * @param softOrderHVO
     * @return
     * @throws SQLException
     */
    public int insertSoftOrderHVO(SoftOrderHVO softOrderHVO)throws SQLException;

    /**
     * 添加软件采购计划子表
     * @param list
     * @return
     * @throws SQLException
     */
    public int insertSoftOrderBVO(List<SoftOrderBVO> list)throws SQLException;

    /**
     * 条件查询软件采购计划
     * @param whereMap
     * @return
     * @throws SQLException
     */
    public List<SoftOrderHVO> selectSoftOrderList(Map whereMap)throws SQLException;

    /**
     * 查询软件采购计划数量
     * @param whereMap
     * @return
     * @throws SQLException
     */
    public int selectSoftOrderCount(Map whereMap)throws SQLException;

    /**
     * 查询软件采购计划子表
     * @return
     * @throws SQLException
     */
    public List<SoftOrderBVO> selectSoftOrderBVOList()throws SQLException;

    /**
     * 修改软件采购计划
     * @param softOrderBVO
     * @return
     * @throws SQLException
     */
    public int updateSoftOrder(SoftOrderBVO softOrderBVO)throws SQLException;

    /**
     * 查询经办人通过审批的软件使用需求申请
     * @return
     * @throws SQLException
     */
    public List<SoftOrderBVO> selectSoftApplyIsOk(String creator)throws SQLException;

    /**
     * 修改软件申请表状态
     * @param softOrderHVO
     * @throws SQLException
     */
    public void updateSoftOrderBillstatus(SoftOrderHVO softOrderHVO)throws SQLException;

    /**
     * 查询正在审核的申请
     * @return
     * @throws SQLException
     */
    public List<SoftOrderHVO> selectSoftOrderIsApply(Map map)throws SQLException;

    /**
     * 通过组织和软件查询软件采购信息
     * @param map
     * @return
     * @throws SQLException
     */
    public Map selectSoftOrderBypk_softAndPk_org(Map map)throws SQLException;
}
