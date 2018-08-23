package com.bjfe.genuine.software.invoicingsystem.service.softorder;

/**
 * Created by Administrator on 2018/1/11.
 */

import com.bjfe.genuine.software.invoicingsystem.model.pub.exception.GlobalException;
import com.bjfe.genuine.software.invoicingsystem.model.softapply.SoftApplyBVO;
import com.bjfe.genuine.software.invoicingsystem.model.softapply.SoftApplyHVO;
import com.bjfe.genuine.software.invoicingsystem.model.softorder.SoftOrderBVO;
import com.bjfe.genuine.software.invoicingsystem.model.softorder.SoftOrderHVO;

import java.util.List;
import java.util.Map;

/**
 * 软件采购计划表业务逻辑接口
 * 编写人：宋超洋
 */
public interface ISoftOrderService {
    /**
     * 保存软件采购计划表
     * @return
     * @throws GlobalException
     */
    public Map insertSoftOrder(SoftOrderHVO softOrderHVO)throws GlobalException;

    /**
     * 条件查询软件采购计划表
     * @param whereMap
     * @return
     * @throws GlobalException
     */
    public Map selectSoftOrderList(Map whereMap)throws GlobalException;

    /**
     * 修改软件软件采购计划表
     * @param softOrderBVOList
     * @return
     * @throws GlobalException
     */
    public Map updateSoftOrder(List<SoftOrderBVO> softOrderBVOList)throws GlobalException;

    /**
     * 保存软件采购计划子表信息
     * @param softOrderBVOList
     * @return
     * @throws GlobalException
     */
    public Map insertSoftOrderBVO(List<SoftOrderBVO> softOrderBVOList)throws GlobalException;

    /**
     * 查询经办人通过审批的软件使用需求申请
     * @param creator
     * @return
     * @throws GlobalException
     */
    public Map selectSoftApplyIsOk(String creator)throws GlobalException;

    /**
     * 修改软件采购状态
     * @throws GlobalException
     */
    public void  updateSoftOrderBillstatus(SoftOrderHVO softOrderHVO)throws GlobalException;

    /**
     * 查询正在审核的采购申请
     * @return
     * @throws GlobalException
     */
    public List<SoftOrderHVO> selectSoftOrderIsApply(Map map)throws GlobalException;
}
