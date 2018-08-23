package com.bjfe.genuine.software.invoicingsystem.controller;

/**
 * Created by Administrator on 2018/1/11.
 */

import com.bjfe.genuine.software.invoicingsystem.model.cuser.CuserVO;
import com.bjfe.genuine.software.invoicingsystem.model.pub.exception.GlobalException;
import com.bjfe.genuine.software.invoicingsystem.model.softapply.SoftApplyBVO;
import com.bjfe.genuine.software.invoicingsystem.model.softapply.SoftApplyHVO;
import com.bjfe.genuine.software.invoicingsystem.model.softorder.SoftOrderHVO;
import com.bjfe.genuine.software.invoicingsystem.service.cuser.ICuserService;
import com.bjfe.genuine.software.invoicingsystem.service.mfrj.IMfrjService;
import com.bjfe.genuine.software.invoicingsystem.service.softapply.ISoftApplyService;
import com.bjfe.genuine.software.invoicingsystem.service.softorder.ISoftOrderService;
import com.bjfe.genuine.software.invoicingsystem.util.activiti.ActivitiUtil;
import com.bjfe.genuine.software.invoicingsystem.util.paging.PagingUtilVO;
import com.bjfe.genuine.software.invoicingsystem.util.pk.DateUtil;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricIdentityLink;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmActivity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.pvm.process.ProcessDefinitionImpl;
import org.activiti.engine.impl.pvm.process.TransitionImpl;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 软件使用需求申请控制器
 * 编写人：宋超洋
 */
@CrossOrigin(origins = "*",maxAge = 1000)
@RequestMapping("/softapply")
@Controller
public class SoftApplyController {
    private Log logger = LogFactory.getLog(SoftApplyController.class);
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private HistoryService historyService;
    @Autowired
    private ActivitiUtil activitiUtil;
    @Resource(name = "iSoftApplyService")
    private ISoftApplyService iSoftApplyService;
    @Resource(name = "iCuserService")
    private ICuserService iCuserService;
    @Resource(name = "iSoftOrderService")
    private ISoftOrderService iSoftOrderService;
    @Resource(name = "iMfrjService")
    private IMfrjService iMfrjService;

    /**
     * 添加软件使用需求申请
     * @return
     * @throws GlobalException
     */
    @RequestMapping(value = "/insertsoftapply",method = RequestMethod.POST)
    @ResponseBody
    public Object insertSoftApply(@RequestBody SoftApplyHVO softApplyHVO)throws GlobalException{
        // 启动流程
        ProcessInstance pi= runtimeService.startProcessInstanceByKey("employeeApply");
        softApplyHVO.setProcessInstanceId(pi.getProcessInstanceId());
        Map map = iSoftApplyService.insertSoftApply(softApplyHVO);
        return map;
    }

    /**
     * 条件查询软件使用需求申请
     * @param whereMap
     * @return
     * @throws GlobalException
     */
    @RequestMapping(value = "/selectsoftapplylist",method = RequestMethod.POST)
    @ResponseBody
    public Object selectSoftApplyList(@RequestBody Map whereMap)throws GlobalException{
        Map map = iSoftApplyService.selectSoftApplyList(whereMap);
        return map;
    }

    /**
     * 修改软件使用需求申请字表信息
     * @param softApplyBVO
     * @return
     * @throws GlobalException
     */
    @RequestMapping(value = "/updatesoftapply",method = RequestMethod.POST)
    @ResponseBody
    public Object updateSoftApply(@RequestBody SoftApplyBVO softApplyBVO)throws GlobalException{
        Map map = iSoftApplyService.updateSoftApply(softApplyBVO);
        return map;
    }

    /**
     * 保存软件使用需求申请子表信息
     * @return
     * @throws GlobalException
     */
    @RequestMapping(value = "/insertsoftapplybvo",method = RequestMethod.POST)
    @ResponseBody
    public Object insertSoftApplyBVO(@RequestBody List<SoftApplyBVO> list)throws GlobalException{

        Map map = iSoftApplyService.insertSoftApplyBVO(list);
        return map;
    }

    /**
     * 删除软件需求申请表子表信息
     * @return
     * @throws GlobalException
     */
    @RequestMapping(value = "/deletesoftapplyb",method = RequestMethod.POST)
    @ResponseBody
    public Object deleteSoftApplyB(@RequestBody List<String> list)throws GlobalException{
        Map map = iSoftApplyService.deleteSoftApplyB(list);
        return map;
    }

    /**
     * 删除软件需求申请表主表信息
     * @return
     * @throws GlobalException
     */
    @RequestMapping(value = "/deletesoftapply",method = RequestMethod.POST)
    @ResponseBody
    public Object deleteSoftApplyB(@RequestBody Map whereMap)throws GlobalException{
        Map map = iSoftApplyService.deleteSoftApply(whereMap);
        return map;
    }

    /**
     * 提交软件使用申请
     * @param map
     * @return
     */
    @RequestMapping(value = "startsoftapply",method = RequestMethod.POST)
    @ResponseBody
    public Object startSoftApply(@RequestBody Map map){
        Map<String,Object> variables=new HashMap<String,Object>();
        String username = iCuserService.selectDeptLeaderUser(map);
        variables.put("msg", "提交");
        variables.put("deptuser", username);
         // 根据流程实例Id查询任务
        Task task=taskService.createTaskQuery().processInstanceId((String   ) map.get("processInstanceId")).singleResult();
        taskService.complete(task.getId(),variables);
        SoftApplyHVO softApplyHVO = new SoftApplyHVO();
        softApplyHVO.setBillstatus(1);
        softApplyHVO.setPk_soft_apply((String) map.get("pk_soft_apply"));
        iSoftApplyService.updateSoftApplyBillstatus(softApplyHVO);
        map.put("billstatus",softApplyHVO.getBillstatus());
        map.put("success",true);
        return map;
    }

    /**
     * 查询代办任务
     * @param map
     * @param request
     * @return
     */
    @RequestMapping(value = "/selectSoftApplyTask",method = RequestMethod.POST)
    @ResponseBody
    public Object softApplyOperation(@RequestBody Map map,HttpServletRequest request){
        Object o;
        if ("信息化部".equals(map.get("deptname"))){
            o =selectSoftCandidateApplyTask(request,map);
        }else if ("财务部".equals(map.get("deptname"))){
            o = selectSoftCandidateApplyTask(request,map);
        }else if ("总经理".equals(map.get("jobname"))){
            o = selectSoftCandidateApplyTask(request,map);
        }else {
            o = selectSoftApplyAssigneeTask(request,map);
        }
        return o;
    }

    /**
     * 查询个人软件申请代办任务
     * @return
     */
    public Object selectSoftApplyAssigneeTask(HttpServletRequest request,Map map)throws GlobalException{
        Map resultMap = new HashMap<>();
        //根据接受人获取该用户的任务
        List<Task> tasks = taskService.createTaskQuery()
                .taskCandidateUser(request.getParameter("username"))
                .list();
        List<SoftApplyHVO> softApplyHVOList = iSoftApplyService.selectSoftApplyIsApply(map);
        List list = new ArrayList<>();
        for (Task task : tasks){
            for (int i =0;i<softApplyHVOList.size();i++){
                SoftApplyHVO softApplyHVO = softApplyHVOList.get(i);
                if (task.getProcessInstanceId().equals(softApplyHVO.getProcessInstanceId())&& 1== softApplyHVO.getBillstatus()){
                    softApplyHVOList.remove(softApplyHVO);
                    softApplyHVO.setId(task.getId());
                    softApplyHVO.setMatter(task.getName());
                    list.add(softApplyHVO);
                }
            }
        }
        Map dataMap = PagingUtilVO.pagingListContain(map,list);
        resultMap.put("pagenumber",dataMap.get("pagenumber"));
        resultMap.put("pageCount",dataMap.get("pageCount"));
        resultMap.put("pageSize",dataMap.get("pageSize"));
        resultMap.put("total",dataMap.get("total"));
        resultMap.put("softApplyHVOList",dataMap.get("datalist"));
        resultMap.put("success",true);
        return resultMap;
    }

    /**
     * 查询组软件申请代办任务
     * @return
     */
    public Object selectSoftCandidateApplyTask(HttpServletRequest request,Map map)throws GlobalException{
        Map resultMap = new HashMap<>();
        //根据接受人获取该用户的任务
        List<Task> tasks = taskService.createTaskQuery()
                .taskCandidateUser(request.getParameter("username"))
                .list();
        List<SoftApplyHVO> softApplyHVOList = iSoftApplyService.selectSoftApplyIsApply(map);
        List<SoftOrderHVO> softOrderHVOList = iSoftOrderService.selectSoftOrderIsApply(map);
        List<Object> list = new ArrayList<>();
        for (Task task : tasks){
            for (int i=0;i<softApplyHVOList.size();i++){
                SoftApplyHVO softApplyHVO = softApplyHVOList.get(i);
                if (task.getProcessInstanceId().equals(softApplyHVO.getProcessInstanceId())&& 1== softApplyHVO.getBillstatus()){
                    softApplyHVO.setId(task.getId());
                    softApplyHVO.setMatter(task.getName());
                    list.add(softApplyHVO);
                }
            }
            for (int i =0;i<softOrderHVOList.size();i++){
                SoftOrderHVO softOrderHVO = softOrderHVOList.get(i);
                if (task.getProcessInstanceId().equals(softOrderHVO.getProcessInstanceId())&& 1==softOrderHVO.getBillstatus()){
                    softOrderHVO.setId(task.getId());
                    softOrderHVO.setMatter(task.getName());
                    list.add(softOrderHVO);
                }
            }
        }
        Map dataMap = PagingUtilVO.pagingListContain(map,list);
        resultMap.put("pagenumber",dataMap.get("pagenumber"));
        resultMap.put("pageCount",dataMap.get("pageCount"));
        resultMap.put("pageSize",dataMap.get("pageSize"));
        resultMap.put("total",dataMap.get("total"));
        resultMap.put("softApplyHVOList",dataMap.get("datalist"));
        resultMap.put("success",true);
        return resultMap;
    }

    /**
     * 审核代办任务
     * @param map
     * @param map
     * @return
     */
    @RequestMapping(value = "/appling",method = RequestMethod.POST)
    @ResponseBody
    public Object SoftApplyApproval(@RequestBody Map map,HttpServletRequest request,HttpSession session){
        Object o;
        if ("信息化部".equals(map.get("deptname"))){
            o =informatizationoperation(map,request,session);
        }else {
            o = userSoftApplyApproval(map);
        }
        return o;
    }

    /**
     * 本部门领导批准软件使用申请
     * @return
     * @throws GlobalException
     */
    public Object userSoftApplyApproval(Map map)throws GlobalException{
        Map<String,Object> variables=new HashMap<String,Object>();
        Map resultMap = new HashMap<>();
        SoftApplyHVO softApplyHVO = new SoftApplyHVO();
        int billstatus = 0;
        if ((int)map.get("adopt") ==0){
            billstatus =1;
            String username = iCuserService.selectInternationDeptUser(map);
            variables.put("msg","通过");
            variables.put("internationdept",username);
            taskService.complete((String) map.get("id"),variables);
        }else if ((int)map.get("adopt") ==1){
            map.put("testDefKey","_4");
            activitiUtil.AcitvitReject(map);
            billstatus = 3;
        }
        softApplyHVO.setBillstatus(billstatus);
        softApplyHVO.setDeptapprover((String) map.get("pk_psndoc"));
        softApplyHVO.setDepttaudittime(DateUtil.getTime());
        softApplyHVO.setDeptcomment((String) map.get("comment"));
        softApplyHVO.setPk_soft_apply((String) map.get("pk_soft_apply"));
        iSoftApplyService.updateSoftApplyBillstatus(softApplyHVO);
        resultMap.put("success",true);
        return resultMap;
    }

    /**
     * 信息化部门操作软件使用申请
     * @return
     * @throws GlobalException
     */
    public Object informatizationoperation(Map map,HttpServletRequest request,HttpSession session)throws GlobalException{
        Map resultMap = new HashMap<>();
        SoftApplyHVO softApplyHVO = null;
        int billstatus = 1;
        if ((int)map.get("adopt") ==0){
            taskService.complete((String) map.get("id"));
            billstatus =2;
            CuserVO cuserVO = (CuserVO) session.getAttribute(request.getParameter("username"));
            iMfrjService.insertFreeSoft(cuserVO,map);
        }else if ((int)map.get("adopt") ==1){
            map.put("testDefKey","_5");
            activitiUtil.AcitvitReject(map);
        }
        softApplyHVO = new SoftApplyHVO();
        softApplyHVO.setBillstatus(billstatus);
        softApplyHVO.setInternationalapprover((String) map.get("pk_psndoc"));
        softApplyHVO.setInternationaltaudittime(DateUtil.getTime());
        softApplyHVO.setInternationalcomment((String) map.get("comment"));
        softApplyHVO.setPk_soft_apply((String) map.get("pk_soft_apply"));
        iSoftApplyService.updateSoftApplyBillstatus(softApplyHVO);
        resultMap.put("success",true);
        return resultMap;
    }

    /**
     * 查询已办任务
     * @param map
     * @param map
     * @return
     */
    @RequestMapping(value = "/selectSoftApplyHisTask.do",method = RequestMethod.POST)
    @ResponseBody
    public Object selectSoftApplyHisTaskisOk(@RequestBody Map map,HttpServletRequest request){
        Object o;
        if ("信息化部".equals(map.get("deptname"))){
            o =selectSoftApplyCandidateHisTask(request,map);
        }else if ("财务部".equals(map.get("deptname"))){
            o = selectSoftApplyCandidateHisTask(request,map);
        }else if ("总经理".equals(map.get("jobname"))){
            o = selectSoftApplyCandidateHisTask(request,map);
        }else {
            o = selectSoftApplyAssigneeHisTask(request,map);
        }
        return o;
    }

    /**
     * 查询软件申请组已办任务
     * @return
     */
    public Object selectSoftApplyCandidateHisTask(HttpServletRequest request,Map map)throws GlobalException{
        Map resultMap = new HashMap<>();
        //根据接受人获取该用户的任务
        List<HistoricTaskInstance> tasks = historyService.createHistoricTaskInstanceQuery(). // 创建历史任务实例查询
                    taskCandidateUser(request.getParameter("username")) // 指定组中的办理人
                    .finished()
                    .list();
        List<SoftApplyHVO> softApplyHVOList = iSoftApplyService.selectSoftApplyIsApply(map);
        List<SoftOrderHVO> softOrderHVOList = iSoftOrderService.selectSoftOrderIsApply(map);
        List list = new ArrayList<>();
        for (HistoricTaskInstance  task : tasks){
            for (SoftApplyHVO softApplyHVO : softApplyHVOList){
                if (task.getProcessInstanceId().equals(softApplyHVO.getProcessInstanceId())){
                    softApplyHVO.setId(task.getId());
                    softApplyHVO.setMatter(task.getName());
                    list.add(softApplyHVO);
                }
            }
            for (int i =0;i<softOrderHVOList.size();i++){
                SoftOrderHVO softOrderHVO = softOrderHVOList.get(i);
                if (task.getProcessInstanceId().equals(softOrderHVO.getProcessInstanceId())&& 1==softOrderHVO.getBillstatus()){
                    softOrderHVO.setId(task.getId());
                    softOrderHVO.setMatter(task.getName());
                    list.add(softOrderHVO);
                }
            }
        }
        Map dataMap = PagingUtilVO.pagingListContain(map,list);
        resultMap.put("pagenumber",dataMap.get("pagenumber"));
        resultMap.put("pageCount",dataMap.get("pageCount"));
        resultMap.put("pageSize",dataMap.get("pageSize"));
        resultMap.put("total",dataMap.get("total"));
        resultMap.put("softApplyHVOList",dataMap.get("datalist"));
        resultMap.put("success",true);
        return resultMap;
    }

    /**
     * 查询软件申请个人已办任务
     * @return
     */
    public Object selectSoftApplyAssigneeHisTask(HttpServletRequest request,Map map)throws GlobalException{
        Map resultMap = new HashMap<>();
        //根据接受人获取该用户的任务
        List<HistoricTaskInstance> tasks = historyService.createHistoricTaskInstanceQuery() // 创建历史任务实例查询
                .taskCandidateUser(request.getParameter("username")) // 指定办理人
                .finished() // 查询已经完成的任务
                .list();
        List<SoftApplyHVO> softApplyHVOList = iSoftApplyService.selectSoftApplyIsApply(map);
        List<SoftApplyHVO> softApplyHVOs = new ArrayList<>();
        for (HistoricTaskInstance  task : tasks){
            for (SoftApplyHVO softApplyHVO : softApplyHVOList){
                if (task.getProcessInstanceId().equals(softApplyHVO.getProcessInstanceId())){
                    softApplyHVO.setId(task.getId());
                    softApplyHVO.setMatter(task.getName());
                    softApplyHVOs.add(softApplyHVO);
                }
            }
        }
        Map dataMap = PagingUtilVO.pagingListContain(map,softApplyHVOs);
        resultMap.put("pagenumber",dataMap.get("pagenumber"));
        resultMap.put("pageCount",dataMap.get("pageCount"));
        resultMap.put("pageSize",dataMap.get("pageSize"));
        resultMap.put("total",dataMap.get("total"));
        resultMap.put("softApplyHVOList",dataMap.get("datalist"));
        resultMap.put("success",true);
        return resultMap;
    }

    /**
     * 验证软件类型名称和软件信息名称
     * @return
     * @throws GlobalException
     */
    @RequestMapping(value = "/testsofttypeandsoftinfo",method = RequestMethod.POST)
    @ResponseBody
    public Object testSoftTypeAndSoftInfo(@RequestBody Map map)throws GlobalException{
        Map resultMap = iSoftApplyService.testSoftTypeAndSoftInfo(map);
        return resultMap;
    }
}
