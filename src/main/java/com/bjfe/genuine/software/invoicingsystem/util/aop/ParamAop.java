package com.bjfe.genuine.software.invoicingsystem.util.aop;

/**
 * Created by Administrator on 2018/2/27.
 */

import com.bjfe.genuine.software.invoicingsystem.util.annotation.InitComputerParam;
import com.bjfe.genuine.software.invoicingsystem.util.annotation.InitMenuParam;
import com.bjfe.genuine.software.invoicingsystem.util.annotation.InitParam;
import com.bjfe.genuine.software.invoicingsystem.util.annotation.InitSoftParam;
import com.bjfe.genuine.software.invoicingsystem.util.parma.ParamUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 数据初始切面类
 * 编写人：宋超洋
 */
@Component
@Aspect
public class ParamAop {
    @Autowired
    private ParamUtil util;
    private Log logger = LogFactory.getLog(ParamAop.class);

    @After("within(com.bjfe.genuine.software.invoicingsystem.service.*.impl..*) && @annotation(ip)")
    public void initDept(InitParam ip){
        util.initOrgParam();
        util.initDeptParam();
        util.initJobParam();
        util.initPersonParam();
    }

    @After("within(com.bjfe.genuine.software.invoicingsystem.service.computer.impl..*) && @annotation(icp)")
    public void initComputer(InitComputerParam icp){
        util.initComputerParam();
        util.initSoftInfoParam();
        util.initSoftTypeParam();
    }

    @After("within(com.bjfe.genuine.software.invoicingsystem.service.menu.impl..*) && @annotation(imp)")
    public void initMenu(InitMenuParam imp){
        util.initMenuParam();
    }

    @After("within(com.bjfe.genuine.software.invoicingsystem.service.software.impl..*) && @annotation(isp)")
    public void initMenu(InitSoftParam isp){
        util.initSoftTypeParam();
        util.initSoftInfoParam();
    }
}
