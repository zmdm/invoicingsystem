package com.bjfe.genuine.software.invoicingsystem.service.softsummary;

/**
 * Created by Administrator on 2018/1/27.
 */

import com.bjfe.genuine.software.invoicingsystem.model.pub.exception.GlobalException;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * 软件使用情况数据逻辑接口
 * 编写人：宋超洋
 */
public interface ISoftSummaryService {
    /**
     * 查询显示软件使用情况
     * @return
     * @throws GlobalException
     */
    public Map selectSoftSummary(Map whereMap)throws GlobalException;

    /**
     * 导入excel
     * @param file
     * @param fileName
     * @return
     * @throws GlobalException
     */
    public Map importEcexl(MultipartFile file, String fileName,Map commonMap) throws GlobalException;

    /**
     * 添加软件使用情况汇总
     * @param map
     * @throws GlobalException
     */
    public void insertSoftSummary(Map map)throws GlobalException;
}
