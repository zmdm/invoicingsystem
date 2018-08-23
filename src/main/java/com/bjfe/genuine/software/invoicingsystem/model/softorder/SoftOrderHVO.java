package com.bjfe.genuine.software.invoicingsystem.model.softorder;

/**
 * Created by Administrator on 2018/1/11.
 */

import java.util.List;

/**
 * 软件采购计划表实体类
 * 编写人：宋超洋
 */
public class SoftOrderHVO {
    private String pk_soft_order;
    private String pk_deptdoc;
    private String pk_soft_apply;
    private String creator;
    private String pk_org;
    private String telphone;
    private String creationtime;
    private String internationalapprover; //信息化部审核人
    private String internationaltaudittime;//信息化部审核时间
    private int billstatus;
    private String processInstanceId; // 流程实例Id
    private String internationalcomment; //信息化部审核批语
    private String financeapprover; //财务部审核人
    private String financetaudittime;//财务部审核时间
    private String financecomment;//财务部审核批语
    private String orgapprover;//单位领导审核人
    private String orgtaudittime;//单位领导审核时间
    private String orgcomment;//单位领导审核批语
    private String id; // 任务id
    private String matter; //批示
    private int type;
    private List<SoftOrderBVO> softOrderBVOList;

    public SoftOrderHVO(int type,String financeapprover,String financecomment,String financetaudittime,String orgapprover,
                        String orgcomment,String orgtaudittime,String id,String matter,
                        String internationaltaudittime,String processInstanceId,int billstatus,String pk_soft_apply,
                        String pk_soft_order, String pk_deptdoc, String creator, String pk_org, String telphone,
                        String creationtime, String internationalapprover, String internationalcomment,
                        List<SoftOrderBVO> softOrderBVOList) {
        this.id = id;
        this.type = type;
        this.matter = matter;
        this.financeapprover = financeapprover;
        this.financecomment = financecomment;
        this.financetaudittime = financetaudittime;
        this.orgapprover = orgapprover;
        this.orgcomment = orgcomment;
        this.orgtaudittime = orgtaudittime;
        this.internationalcomment = internationalcomment;
        this.processInstanceId = processInstanceId;
        this.pk_soft_order = pk_soft_order;
        this.pk_deptdoc = pk_deptdoc;
        this.pk_soft_apply = pk_soft_apply;
        this.creator = creator;
        this.pk_org = pk_org;
        this.telphone = telphone;
        this.creationtime = creationtime;
        this.internationalapprover = internationalapprover;
        this.internationaltaudittime = internationaltaudittime;
        this.billstatus =billstatus;
        this.softOrderBVOList = softOrderBVOList;
    }
    public SoftOrderHVO(){
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMatter() {
        return matter;
    }

    public void setMatter(String matter) {
        this.matter = matter;
    }

    public String getPk_soft_apply() {
        return pk_soft_apply;
    }

    public void setPk_soft_apply(String pk_soft_apply) {
        this.pk_soft_apply = pk_soft_apply;
    }

    public String getPk_soft_order() {
        return pk_soft_order;
    }

    public void setPk_soft_order(String pk_soft_order) {
        this.pk_soft_order = pk_soft_order;
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

    public int getBillstatus() {
        return billstatus;
    }

    public void setBillstatus(int billstatus) {
        this.billstatus = billstatus;
    }

    public List<SoftOrderBVO> getSoftOrderBVOList() {
        return softOrderBVOList;
    }

    public void setSoftOrderBVOList(List<SoftOrderBVO> softOrderBVOList) {
        this.softOrderBVOList = softOrderBVOList;
    }

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    public String getFinanceapprover() {
        return financeapprover;
    }

    public void setFinanceapprover(String financeapprover) {
        this.financeapprover = financeapprover;
    }

    public String getFinancetaudittime() {
        return financetaudittime;
    }

    public void setFinancetaudittime(String financetaudittime) {
        this.financetaudittime = financetaudittime;
    }

    public String getFinancecomment() {
        return financecomment;
    }

    public void setFinancecomment(String financecomment) {
        this.financecomment = financecomment;
    }

    public String getOrgapprover() {
        return orgapprover;
    }

    public void setOrgapprover(String orgapprover) {
        this.orgapprover = orgapprover;
    }

    public String getOrgtaudittime() {
        return orgtaudittime;
    }

    public void setOrgtaudittime(String orgtaudittime) {
        this.orgtaudittime = orgtaudittime;
    }

    public String getOrgcomment() {
        return orgcomment;
    }

    public void setOrgcomment(String orgcomment) {
        this.orgcomment = orgcomment;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
