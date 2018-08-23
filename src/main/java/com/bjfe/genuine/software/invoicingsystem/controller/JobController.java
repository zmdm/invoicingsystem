package com.bjfe.genuine.software.invoicingsystem.controller;
import com.bjfe.genuine.software.invoicingsystem.model.job.JobVO;
import com.bjfe.genuine.software.invoicingsystem.model.pub.exception.GlobalException;
import com.bjfe.genuine.software.invoicingsystem.service.job.IJobService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/2 0002.
 */
/**
 * 岗位控制器类
 * 编写人：张敏
 */
@CrossOrigin(origins = "*",maxAge = 1000)
@RequestMapping("/job")
@Controller
public class JobController {
	@Resource(name = "iJobService")
	private IJobService iJobService;

	/**
	 * 添加岗位
	 * @param jobVO
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(value = "/insertjob",method = RequestMethod.POST)
	@ResponseBody
	public Object insertJob(@RequestBody JobVO jobVO)throws GlobalException{
		Map map = iJobService.insertJob(jobVO);
		return map;
	}

	/**
	 * 查询所有部门
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(value = "/queryjoblist",method = RequestMethod.POST)
	@ResponseBody
	public Object selectDeptList(@RequestBody Map whereMap)throws GlobalException{
		Map map = new HashMap<>();
		map = iJobService.selectJobList(whereMap);
		map.put("success",true);
		return map;
	}

	/**
	 * 条件查询岗位兼职分页显示
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(value = "/queryjoblistPage",method = RequestMethod.POST)
	@ResponseBody
	public Object selectJobPage(@RequestBody Map whereMap)throws GlobalException {
		Map map=new HashMap<>();
		map=iJobService.selectJobPage(whereMap);
		map.put("success",true);
		return map;
	}


	/**
	 * 删除岗位
	 * @param map
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(value = "/deletejob",method = RequestMethod.POST)
	@ResponseBody
	public Object deleteJob(@RequestBody Map<String,String>map)throws GlobalException{
		String pk = map.get("pk_job");
		String []pk_job = pk.split(",");
		Map resultMap = iJobService.deleteJob(pk_job);
		return resultMap;
	}

	/**
	 * 修改岗位
	 * @param jobVO
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(value = "/updatejob",method = RequestMethod.POST)
	@ResponseBody
	public Object updateJob(@RequestBody JobVO jobVO)throws GlobalException{
		Map map = iJobService.updateJob(jobVO);
		return map;
	}

}
