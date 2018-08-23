package com.bjfe.genuine.software.invoicingsystem.service.softmaindetail;

/**
 * Created by Administrator on 2018/1/28.
 */

import com.bjfe.genuine.software.invoicingsystem.model.pub.exception.GlobalException;
import com.bjfe.genuine.software.invoicingsystem.model.softmaindetail.SoftMaindetailBVO;
import com.bjfe.genuine.software.invoicingsystem.model.softmaindetail.SoftMaindetailSubVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * 软件安装维护明细业务逻辑接口
 * 编写人：宋超洋
 */
public interface ISoftMaindetailService {
    /**
     * 查询显示软件安装维护明细信息
     * @param whereMap
     * @return
     * @throws GlobalException
     */
    public Map selectSoftMaindetailList(Map whereMap)throws GlobalException;

    /**
     * 导入excel
     * @param file
     * @param fileName
     * @return
     * @throws GlobalException
     */
    public Map importEcexl(MultipartFile file, String fileName,Map map) throws GlobalException;

    /**
     * 添加软件安装维护子表
     * @param map
     * @return
     * @throws GlobalException
     */
    public SoftMaindetailBVO insertSoftMaindetail(Map map)throws GlobalException;
}
