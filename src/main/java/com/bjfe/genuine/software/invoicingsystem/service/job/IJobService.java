package com.bjfe.genuine.software.invoicingsystem.service.job;

/**
 * Created by Administrator on 2018/1/2.
 */

import com.bjfe.genuine.software.invoicingsystem.model.job.JobVO;
import com.bjfe.genuine.software.invoicingsystem.model.pub.exception.GlobalException;

import java.util.List;
import java.util.Map;

/**
 * 岗位业务逻辑接口
 * 编写人：宋超洋
 */
public interface IJobService {
    /**
     * 添加岗位
     * @param jobVO
     * @return
     * @throws GlobalException
     */
    public Map insertJob(JobVO jobVO)throws GlobalException;

    /**
     * 查询所有岗位
     * @return
     * @throws GlobalException
     */
    public Map selectJobList(Map whereMap)throws GlobalException;

    /**
     * 条件查询岗位兼职分页显示
     * @return
     * @throws GlobalException
     */
    public Map selectJobPage(Map whereMap)throws GlobalException;

    /**
     * 修改岗位
     * @param jobVO
     * @return
     * @throws GlobalException
     */
    public Map updateJob(JobVO jobVO)throws GlobalException;

    /**
     * 删除岗位
     * @return
     * @throws GlobalException
     */
    public Map deleteJob(String[] jobs)throws GlobalException;
}
