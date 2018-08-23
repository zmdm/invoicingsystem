package com.bjfe.genuine.software.invoicingsystem.service.psndoc;

/**
 * Created by Administrator on 2018/1/2.
 */

import com.bjfe.genuine.software.invoicingsystem.model.psndoc.PsndocVO;
import com.bjfe.genuine.software.invoicingsystem.model.pub.exception.GlobalException;

import java.util.Map;

/**
 * 人员业务逻辑接口
 * 编写人：宋超洋
 */
public interface IPsndocService {
    /**
     * 添加人员信息
     * @param psndocVO
     * @return
     * @throws GlobalException
     */
    public Map insertPsndoc(PsndocVO psndocVO)throws GlobalException;

    /**
     * 查询显示人员信息
     * @param whereMap
     * @return
     * @throws GlobalException
     */
    public Map selectPsndocList(Map whereMap)throws GlobalException;

    /**
     * 删除人员信息
     * @param pk_psndocs
     * @return
     * @throws GlobalException
     */
    public Map deletePsndoc(String[]pk_psndocs)throws GlobalException;

    /**
     * 修改人员信息
     * @param psndocVO
     * @return
     * @throws GlobalException
     */
    public Map updatePsndoc(PsndocVO psndocVO)throws GlobalException;

    /**
     * 修改人员信息状态
     * @param whereMap
     * @return
     * @throws GlobalException
     */
    public Map updatePsndocEnableState(Map whereMap)throws GlobalException;
}
