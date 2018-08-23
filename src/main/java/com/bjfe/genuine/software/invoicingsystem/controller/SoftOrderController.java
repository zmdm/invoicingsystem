package com.bjfe.genuine.software.invoicingsystem.controller;

/**
 * Created by Administrator on 2018/1/11.
 */

import com.bjfe.genuine.software.invoicingsystem.model.pub.exception.GlobalException;
import com.bjfe.genuine.software.invoicingsystem.model.softapply.SoftApplyBVO;
import com.bjfe.genuine.software.invoicingsystem.model.softapply.SoftApplyHVO;
import com.bjfe.genuine.software.invoicingsystem.model.softorder.SoftOrderBVO;
import com.bjfe.genuine.software.invoicingsystem.model.softorder.SoftOrderHVO;
import com.bjfe.genuine.software.invoicingsystem.service.cuser.ICuserService;
import com.bjfe.genuine.software.invoicingsystem.service.softapply.ISoftApplyService;
import com.bjfe.genuine.software.invoicingsystem.service.softorder.ISoftOrderService;
import com.bjfe.genuine.software.invoicingsystem.util.activiti.ActivitiUtil;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 软件采购计划表控制器
 * 编写人：宋超洋
 */
@CrossOrigin(origins = "*",maxAge = 1000)
@RequestMapping("/softorder")
@Controller
public class SoftOrderController {
    @Resource(name = "iSoftOrderService")
    private ISoftOrderService iSoftOrderService;
    @Resource(name = "iCuserService")
    private ICuserService iCuserService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private HistoryService historyService;
    @Autowired
    private ActivitiUtil activitiUtil;

    /**
     * 添加软件采购计划
     * @return
     * @throws GlobalException
     */
    @RequestMapping(value = "/insertsoftorder",method = RequestMethod.POST)
    @ResponseBody
    public Object insertSoftOrder(@RequestBody SoftOrderHVO softOrderHVO)throws GlobalException{
        // 启动流程
        ProcessInstance pi= runtimeService.startProcessInstanceByKey("softOrder");
        softOrderHVO.setProcessInstanceId(pi.getProcessInstanceId());
        Map map = iSoftOrderService.insertSoftOrder(softOrderHVO);
        return map;
    }

    /**
     * 条件查询软件采购计划
     * @param whereMap
     * @return
     * @throws GlobalException
     */
    @RequestMapping(value = "/selectsoftorderlist",method = RequestMethod.POST)
    @ResponseBody
    public Object selectSoftOrderList(@RequestBody Map whereMap)throws GlobalException{
        Map map = iSoftOrderService.selectSoftOrderList(whereMap);
        return map;
    }

    /**
     * 修改软件采购计划子表信息
     * @param list
     * @return
     * @throws GlobalException
     */
    @RequestMapping(value = "/updatesoftorder",method = RequestMethod.POST)
    @ResponseBody
    public Object updateSoftApply(@RequestBody List<SoftOrderBVO> list)throws GlobalException{
        Map map = iSoftOrderService.updateSoftOrder(list);
        return map;
    }

    /**
     * 保存软件采购计划子表信息
     * @return
     * @throws GlobalException
     */
    @RequestMapping(value = "/insertsoftorderbvo",method = RequestMethod.POST)
    @ResponseBody
    public Object insertSoftOrderBVO(@RequestBody List<SoftOrderBVO> list)throws GlobalException{
        Map map = iSoftOrderService.insertSoftOrderBVO(list);
        return map;
    }

    /**
     * 查询经办人通过审批的软件使用需求申请
     * @param map
     * @return
     * @throws GlobalException
     */
    @RequestMapping(value = "/softapplyisok",method = RequestMethod.POST)
    @ResponseBody
    public Object selectSoftApplyIsOk(@RequestBody Map<String,String> map)throws GlobalException{
        Map whereMap = iSoftOrderService.selectSoftApplyIsOk(map.get("creator"));
        return whereMap;
    }

    /**
     * 提交软件采购申请
     * @return
     * @throws GlobalException
     */
    @RequestMapping(value = "/softorderapply",method = RequestMethod.POST)
    @ResponseBody
    public Object startSoftOrderApply(@RequestBody Map map)throws GlobalException{
        Map<String,Object> variables=new HashMap<String,Object>();
        String username = iCuserService.selectInternationDeptUser(map);
        variables.put("msg", "通过");
        variables.put("internationdept",username);
        // 根据流程实例Id查询任务
        Task task=taskService.createTaskQuery().processInstanceId((String) map.get("processInstanceId")).singleResult();
        taskService.complete(task.getId(),variables);
        SoftOrderHVO softOrderHVO =new SoftOrderHVO();
        softOrderHVO.setBillstatus(1);
        softOrderHVO.setPk_soft_order((String) map.get("pk_soft_order"));
        iSoftOrderService.updateSoftOrderBillstatus(softOrderHVO);
        map.put("billstatus",softOrderHVO.getBillstatus());
        map.put("success",true);
        return map;
    }

    /**
     * 接收审核
     * @param map
     * @return
     */
    @RequestMapping(value = "applyorderoperation",method = RequestMethod.POST)
    @ResponseBody
    public Object applyOperation(@RequestBody Map map){
        Object o = null;
        if ("信息化部".equals(map.get("deptname"))){
            o = internationOperation(map);
        }else if ("财务部".equals(map.get("deptname"))){
            o = financeOperation(map);
        }else if ("总经理".equals(map.get("jobname"))){
            o = orgLeaderOperation(map);
        }
        return o;
    }

    /**
     * 信息化部门审核
     * @param map
     * @return
     * @throws GlobalException
     */
    public Object internationOperation(@RequestBody Map map)throws GlobalException{
        Map<String,Object> variables=new HashMap<String,Object>();
        Map resultMap = new HashMap<>();
        SoftOrderHVO softOrderHVO = new SoftOrderHVO();
        int billstatus = 0;
        if ((int)map.get("adopt") ==0){
            billstatus =1;
            String username = iCuserService.selectFinanceDeptUser(map);
            variables.put("msg","通过");
            variables.put("financedept",username);
            taskService.complete((String) map.get("id"),variables);
        }else if ((int)map.get("adopt") ==1){
            billstatus = 3;
            map.put("testDefKey","_4");
            activitiUtil.AcitvitReject(map);
        }
        softOrderHVO.setBillstatus(billstatus);
        softOrderHVO.setInternationalapprover((String) map.get("pk_psndoc"));
        softOrderHVO.setInternationalcomment((String) map.get("comment"));
        softOrderHVO.setInternationaltaudittime(DateUtil.getTime());
        softOrderHVO.setPk_soft_order((String) map.get("pk_soft_order"));
        iSoftOrderService.updateSoftOrderBillstatus(softOrderHVO);
        resultMap.put("success",true);
        return resultMap;
    }

    /**
     * 财务部门审核
     * @param map
     * @return
     * @throws GlobalException
     */
    public Object financeOperation(@RequestBody Map map)throws GlobalException{
        Map<String,Object> variables=new HashMap<String,Object>();
        Map resultMap = new HashMap<>();
        SoftOrderHVO softOrderHVO = new SoftOrderHVO();
        int billstatus = 1;
        if ((int)map.get("adopt") ==0){
            String username = iCuserService.selectOrgLederUser(map);
            variables.put("msg","通过");
            variables.put("orgleder",username);
            taskService.complete((String) map.get("id"),variables);
        }else if ((int)map.get("adopt") ==1){
            map.put("testDefKey","_5");
            activitiUtil.AcitvitReject(map);
        }
        softOrderHVO.setBillstatus(billstatus);
        softOrderHVO.setFinanceapprover((String) map.get("pk_psndoc"));
        softOrderHVO.setFinancecomment((String) map.get("comment"));
        softOrderHVO.setFinancetaudittime(DateUtil.getTime());
        softOrderHVO.setPk_soft_order((String) map.get("pk_soft_order"));
        iSoftOrderService.updateSoftOrderBillstatus(softOrderHVO);
        resultMap.put("success",true);
        return resultMap;
    }

    /**
     * 单位领导审核
     * @param map
     * @return
     * @throws GlobalException
     */
    public Object orgLeaderOperation(@RequestBody Map map)throws GlobalException{
        Map resultMap = new HashMap<>();
        SoftOrderHVO softOrderHVO =new SoftOrderHVO();
        int billstatus =1;
        if ((int)map.get("adopt") ==0){
            taskService.complete((String) map.get("id"));
            billstatus =2;
        }else if ((int)map.get("adopt") ==1){
            map.put("testDefKey","_6");
            activitiUtil.AcitvitReject(map);
        }
        softOrderHVO.setBillstatus(billstatus);
        softOrderHVO.setOrgapprover((String) map.get("pk_psndoc"));
        softOrderHVO.setOrgcomment((String) map.get("comment"));
        softOrderHVO.setOrgtaudittime(DateUtil.getTime());
        softOrderHVO.setPk_soft_order((String) map.get("pk_soft_order"));
        iSoftOrderService.updateSoftOrderBillstatus(softOrderHVO);
        resultMap.put("success",true);
        return resultMap;
    }
}
