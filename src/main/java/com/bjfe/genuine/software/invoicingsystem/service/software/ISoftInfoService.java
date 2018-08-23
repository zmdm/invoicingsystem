package com.bjfe.genuine.software.invoicingsystem.service.software;

/**
 * Created by Administrator on 2018/1/9.
 */

import com.bjfe.genuine.software.invoicingsystem.model.pub.exception.GlobalException;
import com.bjfe.genuine.software.invoicingsystem.model.rjxx.SoftInfoVO;

import java.util.Map;

/**
 * 软件信息业务逻辑接口
 * 编写人：宋超洋
 */
public interface ISoftInfoService {
    /**
     * 添加软件信息
     * @return
     * @throws GlobalException
     */
    public Map insertSoftInfo(SoftInfoVO softInfoVO)throws GlobalException;

    /**
     * 查询软件信息
     * @param whereMap
     * @return
     * @throws GlobalException
     */
    public Map selectSoftInfoList(Map whereMap)throws GlobalException;

    /**
     * 删除软件信息
     * @param pk_soft
     * @return
     * @throws GlobalException
     */
    public Map deleteSoftInfo(String[] pk_soft)throws GlobalException;

    /**
     * 修改软件信息
     * @param softInfoVO
     * @return
     * @throws GlobalException
     */
    public Map updateSoftInfo(SoftInfoVO softInfoVO)throws GlobalException;
}
