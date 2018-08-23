package com.bjfe.genuine.software.invoicingsystem.controller;

/**
 * Created by Administrator on 2018/1/17.
 */

import com.bjfe.genuine.software.invoicingsystem.model.cuser.CuserVO;
import com.bjfe.genuine.software.invoicingsystem.model.pub.exception.GlobalException;
import com.bjfe.genuine.software.invoicingsystem.model.qkmx.QkmxBVO;
import com.bjfe.genuine.software.invoicingsystem.model.softmaindetail.SoftMaindetailBVO;
import com.bjfe.genuine.software.invoicingsystem.model.softmaindetail.SoftMaindetailSubVO;
import com.bjfe.genuine.software.invoicingsystem.model.tain.TainVO;
import com.bjfe.genuine.software.invoicingsystem.service.cuser.ICuserService;
import com.bjfe.genuine.software.invoicingsystem.service.qkmx.IQkmxService;
import com.bjfe.genuine.software.invoicingsystem.service.softmaindetail.ISoftMaindetailService;
import com.bjfe.genuine.software.invoicingsystem.service.softmaindetail.impl.SoftMaindetailServiceImpl;
import com.bjfe.genuine.software.invoicingsystem.service.tain.ITainService;
import com.bjfe.genuine.software.invoicingsystem.util.activiti.ActivitiUtil;
import com.bjfe.genuine.software.invoicingsystem.util.paging.PagingUtilVO;
import com.bjfe.genuine.software.invoicingsystem.util.pk.DateUtil;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
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
 * 软件安装维护控制器
 * 编写人：宋超洋
 */
@CrossOrigin(origins = "*",maxAge = 1000)
@RequestMapping("/tain")
@Controller
public class TainController {
    @Resource(name = "iTainService")
    private ITainService iTainService;
    @Resource(name = "iCuserService")
    private ICuserService iCuserService;
    @Resource(name = "iSoftMaindetailService")
    private ISoftMaindetailService softMaindetailService;
    @Resource(name = "iQkmxService")
    private IQkmxService iQkmxService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private HistoryService historyService;
    @Autowired
    private ActivitiUtil activitiUtil;

    /**
     * 添加软件安装维护申请
     * @param tainVO
     * @return
     * @throws GlobalException
     */
    @RequestMapping(value = "/insertain",method = RequestMethod.POST)
    @ResponseBody
    public Object insertTain(@RequestBody TainVO tainVO)throws GlobalException{
        // 启动流程
        ProcessInstance pi= runtimeService.startProcessInstanceByKey("softMaintenance");
        tainVO.setProcessInstanceId(pi.getProcessInstanceId());
        Map map = iTainService.insertTain(tainVO);
        return map;
    }

    /**
     * 显示软件安装维护申请
     * @param whereMap
     * @return
     * @throws GlobalException
     */
    @RequestMapping(value = "/selecttainlist",method = RequestMethod.POST)
    @ResponseBody
    public Object selectTainList(@RequestBody Map whereMap)throws GlobalException{
        Map map = iTainService.selectTainList(whereMap);
        return map;
    }

    /**
     * 修改件安装维护申请
     * @param tainVO
     * @return
     * @throws GlobalException
     */
    @RequestMapping(value = "/updatetain",method = RequestMethod.POST)
    @ResponseBody
    public Object updateTainByPk(@RequestBody TainVO tainVO)throws GlobalException{
        Map map = iTainService.updateTain(tainVO);
        return map;
    }

    /**
     * 软件安装维护申请确认
     * @param tainVO
     * @return
     * @throws GlobalException
     */
    @RequestMapping(value = "tainisok",method = RequestMethod.POST)
    @ResponseBody
    public Object tainIsOK(@RequestBody TainVO tainVO, HttpSession session,HttpServletRequest request)throws GlobalException{
        Map map = new HashMap<>();
        CuserVO cuserVO = (CuserVO) session.getAttribute(request.getParameter("username"));
        if (tainVO.getUsertime() ==null && cuserVO.getPk_psndoc().equals(tainVO.getCreator())){
            tainVO.setUsertime(DateUtil.getTime());
            map = iTainService.tainIsOK(tainVO);
        }else if (!"".equals(tainVO.getUsertime()) && cuserVO.getPk_psndoc().equals(tainVO.getPk_install())){
            tainVO.setInstalltime(DateUtil.getTime());
            map = iTainService.tainIsOK(tainVO);
            Map whereMap = new HashMap<>();
            whereMap.put("pk_org",tainVO.getPk_org());
            whereMap.put("creator",tainVO.getPk_install());
            if (tainVO.getApplytype() ==0 || tainVO.getApplytype() ==2){
                whereMap.put("pk_soft",tainVO.getPk_newsoft());
                whereMap.put("detailtype",0);
            }else {
                whereMap.put("pk_soft",tainVO.getOldsoft());
                whereMap.put("detailtype",1);
            }
            whereMap.put("tain",tainVO);
            SoftMaindetailBVO softMaindetailBVO = softMaindetailService.insertSoftMaindetail(whereMap);
            whereMap.put("softMaindetailBVO",softMaindetailBVO);
            QkmxBVO qkmxBVO = iQkmxService.insertSoftDetail(whereMap);
            whereMap.put("qkmxBVO",qkmxBVO);

        }
        return map;
    }

    /**
     * 显示软件安装维护确认
     * @param whereMap
     * @return
     * @throws GlobalException
     */
    @RequestMapping(value = "/selecttainisoklist",method = RequestMethod.POST)
    @ResponseBody
    public Object selectTainIsOKList(@RequestBody Map whereMap)throws GlobalException{
        whereMap.put("billstatus",2);
        Map map = iTainService.selectTainList(whereMap);
        return map;
    }

    /**
     * 分配安装维护人
     * @param whereMap
     * @return
     * @throws GlobalException
     */
    /*@RequestMapping(value = "/distributiontain",method = RequestMethod.POST)
    @ResponseBody
    public Object distributionTain(@RequestBody Map whereMap)throws GlobalException{
        Map map = iTainService.distributionTain(whereMap);
        return map;
    }*/

    /**
     * 提交软件安装维护申请
     * @param map
     * @return
     * @throws GlobalException
     */
    @RequestMapping(value = "/starttainapply",method = RequestMethod.POST)
    @ResponseBody
    public Object startSoftTainApply(@RequestBody Map map)throws GlobalException{
        Map<String,Object> variables=new HashMap<String,Object>();
        String username = iCuserService.selectDeptLeaderUser(map);
        variables.put("msg", "提交");
        variables.put("deptuser",username);
        // 根据流程实例Id查询任务
        Task task=taskService.createTaskQuery().processInstanceId((String) map.get("processInstanceId")).singleResult();
        taskService.complete(task.getId(),variables);
        TainVO tainVO = new TainVO();
        tainVO.setBillstatus(1);
        tainVO.setPk_soft_tain((String) map.get("pk_soft_tain"));
        iTainService.updateSoftTainBillstatus(tainVO);
        map.put("billstatus",tainVO.getBillstatus());
        map.put("success",true);
        return map;
    }

    /**
     * 查询代办任务
     * @param map
     * @param request
     * @return
     */
    @RequestMapping(value = "/selectSofttainTask",method = RequestMethod.POST)
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
            o = selectSoftApplyTask(request,map);
        }
        return o;
    }
    /**
     * 查询软件安装维护代办任务
     * @return
     */
    public Object selectSoftApplyTask(HttpServletRequest request,Map map)throws GlobalException{
        Map resultMap = new HashMap<>();
        //根据接受人获取该用户的任务
        List<Task> tasks = taskService.createTaskQuery()
                .taskCandidateUser(request.getParameter("username"))
                .list();
        Map dataMap = iTainService.selectSoftTainIsApply(map);
        List<TainVO> tainVOList =(ArrayList) dataMap.get("list");
        List list = new ArrayList<>();
        for (Task task : tasks){
            for (int i=0;i<tainVOList.size();i++){
                TainVO tainVO = tainVOList.get(i);
                if (task.getProcessInstanceId().equals(tainVO.getProcessInstanceId())&& 1== tainVO.getBillstatus()){
                    tainVO.setId(task.getId());
                    tainVO.setMatter(task.getName());
                    list.add(tainVO);
                }
            }
        }
        Map mapTemp = PagingUtilVO.pagingListContain(map,list);
        resultMap.put("pagenumber",mapTemp.get("pagenumber"));
        resultMap.put("pageCount",mapTemp.get("pageCount"));
        resultMap.put("pageSize",mapTemp.get("pageSize"));
        resultMap.put("total",mapTemp.get("total"));
        resultMap.put("tainVOList",mapTemp.get("datalist"));
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
        Map dataMap = iTainService.selectSoftTainIsApply(map);
        List<TainVO> tainVOList =(ArrayList) dataMap.get("list");
        List<Object> list = new ArrayList<>();
        for (Task task : tasks){
            for (int i=0;i<tainVOList.size();i++){
                TainVO tainVO = tainVOList.get(i);
                if (task.getProcessInstanceId().equals(tainVO.getProcessInstanceId())&& 1== tainVO.getBillstatus()){
                    tainVO.setId(task.getId());
                    tainVO.setMatter(task.getName());
                    list.add(tainVO);
                }
            }
        }
        Map mapTemp = PagingUtilVO.pagingListContain(map,list);
        resultMap.put("pagenumber",mapTemp.get("pagenumber"));
        resultMap.put("pageCount",mapTemp.get("pageCount"));
        resultMap.put("pageSize",mapTemp.get("pageSize"));
        resultMap.put("total",mapTemp.get("total"));
        resultMap.put("tainVOList",mapTemp.get("datalist"));
        resultMap.put("psndoclist",dataMap.get("psndoclist"));
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
    public Object SoftApplyApproval(@RequestBody Map map){
        Object o;
        if ("信息化部".equals(map.get("deptname"))){
            o =internationOperation(map);
        }else {
            o = softTainApproval(map);
        }
        return o;
    }

    /**
     * 本部门领导批准软件使用申请
     * @return
     * @throws GlobalException
     */
    public Object softTainApproval(Map map)throws GlobalException{
        Map<String,Object> variables=new HashMap<String,Object>();
        Map resultMap = new HashMap<>();
        TainVO tainVO = new TainVO();
        int billstatus = 0;
        if ((int)map.get("adopt") ==0){
            billstatus =1;
            String username = iCuserService.selectInternationDeptUser(map);
            variables.put("msg","通过");
            variables.put("internationdept",username);
            taskService.complete((String) map.get("id"),variables);
        }else if ((int)map.get("adopt") ==1){
            map.put("testDefKey","_6");
            activitiUtil.AcitvitReject(map);
            billstatus = 3;
        }
        tainVO.setBillstatus(billstatus);
        tainVO.setDeptapprover((String) map.get("pk_psndoc"));
        tainVO.setDepttaudittime(DateUtil.getTime());
        tainVO.setDeptcomment((String) map.get("comment"));
        tainVO.setPk_soft_tain((String) map.get("pk_soft_tain"));
        iTainService.updateSoftTainBillstatus(tainVO);
        resultMap.put("success",true);
        return resultMap;
    }

    /**
     * 信息化部门审核
     * @param map
     * @return
     * @throws GlobalException
     */

    public Object internationOperation(@RequestBody Map map)throws GlobalException{
        Map resultMap = new HashMap<>();
        TainVO tainVO =new TainVO();
        int billstatus = 1;
        if ((int)map.get("adopt") ==0){
            billstatus =2;
            taskService.complete((String) map.get("id"));
        }else if ((int)map.get("adopt") ==1){
            map.put("testDefKey","_3");
            activitiUtil.AcitvitReject(map);
        }
        tainVO.setBillstatus(billstatus);
        tainVO.setInternationalapprover((String) map.get("pk_psndoc"));
        tainVO.setInternationaltaudittime(DateUtil.getTime());
        tainVO.setInternationalcomment((String) map.get("comment"));
        tainVO.setPk_soft_tain((String) map.get("pk_soft_tain"));
        tainVO.setPk_install((String) map.get("pk_install"));
        iTainService.updateSoftTainBillstatus(tainVO);
        resultMap.put("success",true);
        return resultMap;
    }

    /**
     * 查询已办任务
     * @param map
     * @param map
     * @return
     */
    @RequestMapping(value = "/selectSofttainHisTask.do",method = RequestMethod.POST)
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
            o = selectSoftTainHisTask(request,map);
        }
        return o;
    }

    /**
     * 查询软件安装维护个人已办任务
     * @return
     */
    public Object selectSoftTainHisTask(HttpServletRequest request,Map map)throws GlobalException{
        Map resultMap = new HashMap<>();
        //根据接受人获取该用户的任务
        List<HistoricTaskInstance> tasks = historyService.createHistoricTaskInstanceQuery() // 创建历史任务实例查询
                .taskAssignee(request.getParameter("username")) // 指定办理人
                .finished() // 查询已经完成的任务
                .list();
        Map dataMap = iTainService.selectSoftTainIsApply(map);
        List<TainVO> tainVOList =(ArrayList) dataMap.get("list");
        List list = new ArrayList<>();
        for (HistoricTaskInstance  task : tasks){
            for (TainVO tainVO : tainVOList){
                if (!task.getProcessInstanceId().equals(tainVO.getProcessInstanceId())){
                    tainVO.setId(task.getId());
                    tainVO.setMatter(task.getName());
                    list.add(tainVO);
                }
            }
        }
        Map mapTemp = PagingUtilVO.pagingListContain(map,list);
        resultMap.put("pagenumber",mapTemp.get("pagenumber"));
        resultMap.put("pageCount",mapTemp.get("pageCount"));
        resultMap.put("pageSize",mapTemp.get("pageSize"));
        resultMap.put("total",mapTemp.get("total"));
        resultMap.put("tainVOList",mapTemp.get("datalist"));
        resultMap.put("success",true);
        return resultMap;
    }

    /**
     * 查询软件安装维护组已办任务
     * @return
     */
    public Object selectSoftApplyCandidateHisTask(HttpServletRequest request,Map map)throws GlobalException{
        Map resultMap = new HashMap<>();
        //根据接受人获取该用户的任务
        List<HistoricTaskInstance> tasks = historyService.createHistoricTaskInstanceQuery(). // 创建历史任务实例查询
                taskCandidateUser(request.getParameter("username")) // 指定组中的办理人
                .finished()
                .list();
        Map dataMap = iTainService.selectSoftTainIsApply(map);
        List<TainVO> tainVOList =(ArrayList) dataMap.get("list");
        List list = new ArrayList<>();
        for (HistoricTaskInstance  task : tasks){
            for (TainVO tainVO : tainVOList){
                if (!task.getProcessInstanceId().equals(tainVO.getProcessInstanceId())){
                    tainVO.setId(task.getId());
                    tainVO.setMatter(task.getName());
                    list.add(tainVO);
                }
            }
        }
        Map mapTemp = PagingUtilVO.pagingListContain(map,list);
        resultMap.put("pagenumber",mapTemp.get("pagenumber"));
        resultMap.put("pageCount",mapTemp.get("pageCount"));
        resultMap.put("pageSize",mapTemp.get("pageSize"));
        resultMap.put("total",mapTemp.get("total"));
        resultMap.put("tainVOList",mapTemp.get("datalist"));
        resultMap.put("success",true);
        return resultMap;
    }

}
