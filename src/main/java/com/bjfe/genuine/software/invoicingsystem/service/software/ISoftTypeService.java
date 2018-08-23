package com.bjfe.genuine.software.invoicingsystem.service.software;

/**
 * Created by Administrator on 2018/1/9.
 */

import com.bjfe.genuine.software.invoicingsystem.model.pub.exception.GlobalException;
import com.bjfe.genuine.software.invoicingsystem.model.rjlx.SoftTypeVO;

import java.util.List;
import java.util.Map;

/**
 * 软件类型业务逻辑接口
 * 编写人：宋超洋
 */
public interface ISoftTypeService {
    /**
     * 添加软件类型
     * @return
     * @throws GlobalException
     */
    public Map insertSoftType(SoftTypeVO softTypeVO)throws GlobalException;

    /**
     * 查询软件类型
     * @return
     * @throws GlobalException
     */
    public Map selectSoftTypeList(Map whereMap)throws GlobalException;

    /**
     * 删除软件类型
     * @param pk_softtype
     * @return
     * @throws GlobalException
     */
    public Map deleteSoftType(String []pk_softtype)throws GlobalException;

    /**
     * 修改软件类型
     * @param softTypeVO
     * @return
     * @throws GlobalException
     */
    public Map updateSoftType(SoftTypeVO softTypeVO)throws GlobalException;

}
