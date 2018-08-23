package com.bjfe.genuine.software.invoicingsystem.model.tain;

/**
 * Created by Administrator on 2018/1/17.
 */

/**
 * 软件安装维护实体类
 * 编写人：宋超洋
 */
public class TainVO {
    private String pk_soft_tain;
    private String pk_deptdoc;
    private String creator;
    private String pk_psndoc;
    private String pk_org;
    private String org_pk;
    private String telphone;
    private String creationtime;
    private String approver;
    private String taudittime;
    private String pk_computer;
    private String computercode;
    private String soft_name;
    private String oldsoft;
    private String version;
    private String brand;
    private int applytype;
    private String pk_newsoft;
    private String newsoftname;
    private String newsoftversion;
    private String applyresult;
    private String pk_install;
    private String install_pk;
    private int billstatus;
    private int completion;
    private String usertime;
    private String installtime;
    private String processInstanceId; // 流程实例Id
    private String deptapprover; //本部门领导审核人
    private String depttaudittime;//本部门领导审核时间
    private String deptcomment;//本部门领导批语
    private String internationalapprover;//信息化部审核人
    private String internationaltaudittime;//信息化部审核时间
    private String internationalcomment;//信息化部批语
    private String id; //任务id
    private String matter; //批示

    public TainVO(String internationalcomment,String internationalapprover,String internationaltaudittime,
                  String deptapprover,String deptcomment,String depttaudittime,String id,String matter,
                  String processInstanceId,int confirm,String pk_psndoc,String install_pk,String usertime,
                  String installtime,int completion,int billstatus,String org_pk,String newsoftname,
                  String computercode,String pk_soft_tain, String pk_deptdoc, String creator, String pk_org,
                  String telphone, String creationtime, String approver, String taudittime, String pk_computer,
                  String soft_name, String version, String brand, int applytype, String pk_newsoft,
                  String newsoftversion, String applyresult, String pk_install,String oldsoft) {
        this.processInstanceId = processInstanceId;
        this.internationalcomment = internationalcomment;
        this.internationalapprover = internationalapprover;
        this.internationaltaudittime = internationaltaudittime;
        this.deptapprover = deptapprover;
        this.deptcomment = deptcomment;
        this.depttaudittime = depttaudittime;
        this.billstatus = billstatus;
        this.pk_soft_tain = pk_soft_tain;
        this.newsoftname = newsoftname;
        this.computercode=computercode;
        this.pk_deptdoc = pk_deptdoc;
        this.creator = creator;
        this.pk_psndoc = pk_psndoc;
        this.pk_org = pk_org;
        this.org_pk = org_pk;
        this.telphone = telphone;
        this.creationtime = creationtime;
        this.approver = approver;
        this.taudittime = taudittime;
        this.pk_computer = pk_computer;
        this.soft_name = soft_name;
        this.version = version;
        this.brand = brand;
        this.applytype = applytype;
        this.pk_newsoft = pk_newsoft;
        this.newsoftversion = newsoftversion;
        this.applyresult = applyresult;
        this.pk_install = pk_install;
        this.install_pk = install_pk;
        this.completion = completion;
        this.usertime= usertime;
        this.installtime = installtime;
        this.id = id;
        this.matter = matter;
        this.oldsoft = oldsoft;
    }
    public TainVO(){
        super();
    }

    public int getBillstatus() {
        return billstatus;
    }

    public void setBillstatus(int billstatus) {
        this.billstatus = billstatus;
    }

    public String getComputercode() {
        return computercode;
    }

    public void setComputercode(String computercode) {
        this.computercode = computercode;
    }

    public String getNewsoftname() {
        return newsoftname;
    }

    public void setNewsoftname(String newsoftname) {
        this.newsoftname = newsoftname;
    }

    public String getPk_soft_tain() {
        return pk_soft_tain;
    }

    public void setPk_soft_tain(String pk_soft_tain) {
        this.pk_soft_tain = pk_soft_tain;
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

    public String getPk_psndoc() {
        return pk_psndoc;
    }

    public void setPk_psndoc(String pk_psndoc) {
        this.pk_psndoc = pk_psndoc;
    }

    public String getPk_org() {
        return pk_org;
    }

    public void setPk_org(String pk_org) {
        this.pk_org = pk_org;
    }

    public String getOrg_pk() {
        return org_pk;
    }

    public void setOrg_pk(String org_pk) {
        this.org_pk = org_pk;
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

    public String getApprover() {
        return approver;
    }

    public void setApprover(String approver) {
        this.approver = approver;
    }

    public String getTaudittime() {
        return taudittime;
    }

    public void setTaudittime(String taudittime) {
        this.taudittime = taudittime;
    }

    public String getPk_computer() {
        return pk_computer;
    }

    public void setPk_computer(String pk_computer) {
        this.pk_computer = pk_computer;
    }

    public String getSoft_name() {
        return soft_name;
    }

    public void setSoft_name(String soft_name) {
        this.soft_name = soft_name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getApplytype() {
        return applytype;
    }

    public void setApplytype(int applytype) {
        this.applytype = applytype;
    }

    public String getPk_newsoft() {
        return pk_newsoft;
    }

    public void setPk_newsoft(String pk_newsoft) {
        this.pk_newsoft = pk_newsoft;
    }

    public String getNewsoftversion() {
        return newsoftversion;
    }

    public void setNewsoftversion(String newsoftversion) {
        this.newsoftversion = newsoftversion;
    }

    public String getApplyresult() {
        return applyresult;
    }

    public void setApplyresult(String applyresult) {
        this.applyresult = applyresult;
    }

    public String getPk_install() {
        return pk_install;
    }

    public void setPk_install(String pk_install) {
        this.pk_install = pk_install;
    }

    public int getCompletion() {
        return completion;
    }

    public void setCompletion(int completion) {
        this.completion = completion;
    }

    public String getUsertime() {
        return usertime;
    }

    public void setUsertime(String usertime) {
        this.usertime = usertime;
    }

    public String getInstalltime() {
        return installtime;
    }

    public void setInstalltime(String installtime) {
        this.installtime = installtime;
    }

    public String getInstall_pk() {
        return install_pk;
    }

    public void setInstall_pk(String install_pk) {
        this.install_pk = install_pk;
    }

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
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

    public String getOldsoft() {
        return oldsoft;
    }

    public void setOldsoft(String oldsoft) {
        this.oldsoft = oldsoft;
    }
}
