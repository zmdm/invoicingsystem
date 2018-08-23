package com.bjfe.genuine.software.invoicingsystem.mapper.job;
import com.bjfe.genuine.software.invoicingsystem.model.job.JobVO;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/2 0002.
 */
/**
 * 岗位数据持久接口
 * 编写人：张敏
 */
public interface IJobDao {
	/**
	 * 添加岗位
	 * @return
	 */
	public int insertJob(JobVO jobVO)throws SQLException;

	/**
	 * 查询所有岗位
	 * @return
	 * @throws SQLException
     */
	public List<JobVO> selectJobList()throws SQLException;

	/**
	 * 条件查询岗位兼职分页显示
	 * @return
	 * @throws SQLException
	 */
	public List<JobVO> selectJobPage(Map map)throws SQLException;


	/**
	 * 修改岗位
	 * @param jobVO
	 * @return
	 * @throws SQLException
	 */
	public int updateJob(JobVO jobVO)throws SQLException;

	/**
	 * 删除岗位
	 * @param pk_job
	 * @return
	 * @throws SQLException
	 */
	public int deleteJobs(String []pk_job)throws SQLException;

	/**
	 * 通过部门查询相关岗位数量
	 * @param pk_dept
	 * @return
	 * @throws SQLException
     */
	public Map selectJobCountByDept(String pk_dept)throws SQLException;


	/**
	 * 查询岗位总数量
	 * @param
	 * @return
	 * @throws SQLException
	 */
	public int selectJobCount(Map map)throws SQLException;

}


