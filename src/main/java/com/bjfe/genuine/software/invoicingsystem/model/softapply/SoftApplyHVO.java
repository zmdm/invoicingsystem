package com.bjfe.genuine.software.invoicingsystem.model.softapply;

/**
 * Created by Administrator on 2018/1/11.
 */

import java.util.List;

/**
 * 软件使用需求申请实体类
 * 编写人：宋超洋
 */
public class SoftApplyHVO {
    private String pk_soft_apply;
    private String pk_deptdoc;
    private String creator;
    private String pk_org;
    private String telphone;
    private String creationtime;
    private String deptapprover;
    private String depttaudittime;
    private int billstatus;
    private String processInstanceId; // 流程实例Id
    private String matter; //批示
    private String id; // 任务id
    private String deptcomment; //批语
    private String internationalapprover; //信息化部审核人
    private String internationaltaudittime; //信息化部审核时间
    private String internationalcomment; //信息化部审核批语
    private int type;
    private List<SoftApplyBVO> softApplyBVOList;

    public SoftApplyHVO(int type,String internationalapprover,String internationalcomment,String internationaltaudittime,String deptcomment,String id,String matter,String processInstanceId,int billstatus,String pk_soft_apply, String pk_deptdoc, String creator, String pk_org, String telphone, String creationtime, String deptapprover, String depttaudittime, List<SoftApplyBVO> softApplyBVOList) {
        this.deptcomment = deptcomment;
        this.type = type;
        this.id =id;
        this.processInstanceId = processInstanceId;
        this.pk_soft_apply = pk_soft_apply;
        this.billstatus=billstatus;
        this.pk_deptdoc = pk_deptdoc;
        this.creator = creator;
        this.pk_org = pk_org;
        this.telphone = telphone;
        this.creationtime = creationtime;
        this.deptapprover = deptapprover;
        this.depttaudittime = depttaudittime;
        this.matter = matter;
        this.softApplyBVOList = softApplyBVOList;
        this.internationalapprover = internationalapprover;
        this.internationalcomment = internationalcomment;
        this.internationaltaudittime = internationaltaudittime;
    }
    public SoftApplyHVO(){
        super();
    }

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    public String getPk_soft_apply() {
        return pk_soft_apply;
    }

    public void setPk_soft_apply(String pk_soft_apply) {
        this.pk_soft_apply = pk_soft_apply;
    }

    public String getPk_deptdoc() {
        return pk_deptdoc;
    }

    public void setPk_deptdoc(String pk_deptdoc) {
        this.pk_deptdoc = pk_deptdoc;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getPk_org() {
        return pk_org;
    }

    public void setPk_org(String pk_org) {
        this.pk_org = pk_org;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public String getCreationtime() {
        return creationtime;
    }

    public void setCreationtime(String creationtime) {
        this.creationtime = creationtime;
    }

    public List<SoftApplyBVO> getSoftApplyBVOList() {
        return softApplyBVOList;
    }

    public void setSoftApplyBVOList(List<SoftApplyBVO> softApplyBVOList) {
        this.softApplyBVOList = softApplyBVOList;
    }

    public int getBillstatus() {
        return billstatus;
    }

    public void setBillstatus(int billstatus) {
        this.billstatus = billstatus;
    }

    public String getMatter() {
        return matter;
    }

    public void setMatter(String matter) {
        this.matter = matter;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeptapprover() {
        return deptapprover;
    }

    public void setDeptapprover(String deptapprover) {
        this.deptapprover = deptapprover;
    }

    public String getDepttaudittime() {
        return depttaudittime;
    }

    public void setDepttaudittime(String depttaudittime) {
        this.depttaudittime = depttaudittime;
    }

    public String getDeptcomment() {
        return deptcomment;
    }

    public void setDeptcomment(String deptcomment) {
        this.deptcomment = deptcomment;
    }

    public String getInternationalapprover() {
        return internationalapprover;
    }

    public void setInternationalapprover(String internationalapprover) {
        this.internationalapprover = internationalapprover;
    }

    public String getInternationaltaudittime() {
        return internationaltaudittime;
    }

    public void setInternationaltaudittime(String internationaltaudittime) {
        this.internationaltaudittime = internationaltaudittime;
    }

    public String getInternationalcomment() {
        return internationalcomment;
    }

    public void setInternationalcomment(String internationalcomment) {
        this.internationalcomment = internationalcomment;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
