package com.bjfe.genuine.software.invoicingsystem.controller;

/**
 * Created by Administrator on 2018/2/6.
 */

import org.activiti.engine.RepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 工作流部署控制层
 * 编写人：宋超洋
 */
@CrossOrigin(origins = "*", maxAge = 1000)
@Controller
@RequestMapping("/activiti")
public class ApplyController {
    @Autowired
    private RepositoryService repositoryService;

    /**
     * 软件使用申请流程部署
     */
    @RequestMapping("/activitideploy")
    public void deployAndStart(){
        //流程部署
        repositoryService
                .createDeployment()
                .name("软件使用申请")
                .addClasspathResource("diagrams/employeeApply.bpmn")
                .addClasspathResource("diagrams/employeeApply.png")
                .deploy();
        System.out.println("软件使用申请流程部署成功！");
    }

    /**
     * 软件采购流程部署
     */
    @RequestMapping("/activitiorder")
    public void orderAndStart(){
        //流程部署
        repositoryService
                .createDeployment()
                .name("软件采购申请")
                .addClasspathResource("diagrams/softOrder.bpmn")
                .addClasspathResource("diagrams/softOrder.png")
                .deploy();
        System.out.println("软件采购流程部署成功！");
    }

    /**
     * 软件安装维护流程部署
     */
    @RequestMapping("/activititain")
    public void tainAndStart(){
        //流程部署
        repositoryService
                .createDeployment()
                .name("软件安装维护申请")
                .addClasspathResource("diagrams/softMaintenance.bpmn")
                .addClasspathResource("diagrams/softMaintenance.png")
                .deploy();
        System.out.println("软件安装维护流程部署成功！");
    }
}
