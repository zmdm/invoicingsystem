package com.bjfe.genuine.software.invoicingsystem.service.computer;

/**
 * Created by Administrator on 2018/1/16.
 */

import com.bjfe.genuine.software.invoicingsystem.model.computer.ComputerVO;
import com.bjfe.genuine.software.invoicingsystem.model.pub.exception.GlobalException;

import java.util.List;
import java.util.Map;

/**
 * 计算机管理业务逻辑接口
 * 编写人：宋超洋
 */
public interface IComputerService {
    /**
     * 添加计算机管理信息
     * @param computerVO
     * @return
     * @throws GlobalException
     */
    public Map insertComputer(ComputerVO computerVO)throws GlobalException;

    /**
     * 查询显示计算机管理信息
     * @return
     * @throws GlobalException
     */
    public Map selectComputerList(Map whereMap)throws GlobalException;

    /**
     * 修改计算机管理信息
     * @return
     * @throws GlobalException
     */
    public Map updateComputer(ComputerVO computerVO)throws GlobalException;

    /**
     * 删除计算机管理信息
     * @param list
     * @return
     * @throws GlobalException
     */
    public Map deleteComputer(List<String> list)throws GlobalException;
}
