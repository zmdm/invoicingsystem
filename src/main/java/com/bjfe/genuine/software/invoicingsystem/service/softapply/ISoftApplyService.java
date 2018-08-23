package com.bjfe.genuine.software.invoicingsystem.service.softapply;

/**
 * Created by Administrator on 2018/1/11.
 */

import com.bjfe.genuine.software.invoicingsystem.model.pub.exception.GlobalException;
import com.bjfe.genuine.software.invoicingsystem.model.softapply.SoftApplyBVO;
import com.bjfe.genuine.software.invoicingsystem.model.softapply.SoftApplyHVO;

import java.util.List;
import java.util.Map;

/**
 * 软件使用需求申请业务逻辑接口
 * 编写人：宋超洋
 */
public interface ISoftApplyService {
    /**
     * 保存软件使用需求申请
     * @return
     * @throws GlobalException
     */
    public Map insertSoftApply(SoftApplyHVO softApplyHVO)throws GlobalException;

    /**
     * 条件查询软件使用需求申请
     * @param whereMap
     * @return
     * @throws GlobalException
     */
    public Map selectSoftApplyList(Map whereMap)throws GlobalException;

    /**
     * 修改软件使用需求申请字表信息
     * @param softApplyBVO
     * @return
     * @throws GlobalException
     */
    public Map updateSoftApply(SoftApplyBVO softApplyBVO)throws GlobalException;

    /**
     * 保存软件使用需求申请子表信息
     * @param softApplyBVOList
     * @return
     * @throws GlobalException
     */
    public Map insertSoftApplyBVO(List<SoftApplyBVO> softApplyBVOList)throws GlobalException;

    /**
     * 删除软件需求申请表子表信息
     * @param list
     * @return
     * @throws GlobalException
     */
    public Map deleteSoftApplyB(List<String> list)throws GlobalException;

    /**
     * 删除软件需求申请表主表信息
     * @param whereMap
     * @return
     * @throws GlobalException
     */
    public Map deleteSoftApply(Map whereMap)throws GlobalException;

    /**
     * 修改软件申请表状态
     * @throws GlobalException
     */
    public void  updateSoftApplyBillstatus(SoftApplyHVO softApplyHVO)throws GlobalException;

    /**
     * 查询正在审核的申请
     * @return
     * @throws GlobalException
     */
    public List<SoftApplyHVO> selectSoftApplyIsApply(Map map)throws GlobalException;

    /**
     * 验证验证软件类型名称和软件信息名称
     * @param map
     * @return
     * @throws GlobalException
     */
    public Map testSoftTypeAndSoftInfo(Map map)throws GlobalException;
}
