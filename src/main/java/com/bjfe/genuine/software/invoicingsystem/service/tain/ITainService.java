package com.bjfe.genuine.software.invoicingsystem.service.tain;

/**
 * Created by Administrator on 2018/1/17.
 */

import com.bjfe.genuine.software.invoicingsystem.model.pub.exception.GlobalException;
import com.bjfe.genuine.software.invoicingsystem.model.tain.TainVO;

import java.util.List;
import java.util.Map;

/**
 * 软件安装维护业务逻辑接口
 * 编写人：宋超洋
 */
public interface ITainService {
    /**
     * 添加软件安装维护申请
     * @param tainVO
     * @return
     * @throws GlobalException
     */
    public Map insertTain(TainVO tainVO)throws GlobalException;

    /**
     * 显示软件安装维护申请
     * @return
     * @throws GlobalException
     */
    public Map selectTainList(Map map)throws GlobalException;

    /**
     * 修改软件安装维护申请
     * @param tainVO
     * @return
     * @throws GlobalException
     */
    public Map updateTain(TainVO tainVO)throws GlobalException;

    /**
     * 软件安装维护申请确认
     * @param tainVO
     * @return
     * @throws GlobalException
     */
    public Map tainIsOK(TainVO tainVO)throws GlobalException;

    /**
     * 分配安装维护人
     * @param whereMap
     * @return
     * @throws GlobalException
     */
    /*public Map distributionTain(Map whereMap)throws GlobalException;*/

    /**
     * 修改软件采购状态
     * @throws GlobalException
     */
    public void  updateSoftTainBillstatus(TainVO tainVO)throws GlobalException;

    /**
     * 查询正在审核的采购申请
     * @return
     * @throws GlobalException
     */
    public Map selectSoftTainIsApply(Map map)throws GlobalException;
}
