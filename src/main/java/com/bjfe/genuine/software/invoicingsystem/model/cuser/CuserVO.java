package com.bjfe.genuine.software.invoicingsystem.model.cuser;

/**
 * Created by Administrator on 2017-12-28.
 */


import com.bjfe.genuine.software.invoicingsystem.model.pub.SuperPojo;

/**
 * 用户实体类
 * 编写人：李宗泽
 */
public class CuserVO extends SuperPojo{
    private String pk_psndoc;
    private String psndoc_pk;
    private String cuserid;
    private String password;
    private String newpass;
    private String pk_org;
    private String org_pk;
    private String username;
    private String usercode;
    private String creator;
    private String pk_creator;
    private String creationtime;
    private int enablestate;
    private String pk_dept;
    private String dept_pk;
    private String telphone;
    private String jobname;


    public CuserVO(String jobname,String pk_creator,String dept_pk,String telphone,String psndoc_pk,String org_pk,String pk_psndoc,int enablestate,String cuserid,String password,String pk_org, String username, String usercode,String creator,String creationtime,String newpass,String pk_dept) {
        this.psndoc_pk = psndoc_pk;
        this.org_pk = org_pk;
        this.enablestate = enablestate;
        this.pk_psndoc = pk_psndoc;
        this.cuserid = cuserid;
        this.password = password;
        this.pk_org = pk_org;
        this.username = username;
        this.usercode = usercode;
        this.creator = creator;
        this.pk_creator=pk_creator;
        this.creationtime = creationtime;
        this.newpass = newpass;
        this.pk_dept = pk_dept;
        this.dept_pk = dept_pk;
        this.telphone = telphone;
        this.jobname = jobname;
    }
    public CuserVO(){
        super();
    }

    public String getPsndoc_pk() {
        return psndoc_pk;
    }

    public void setPsndoc_pk(String psndoc_pk) {
        this.psndoc_pk = psndoc_pk;
    }

    public String getOrg_pk() {
        return org_pk;
    }

    public void setOrg_pk(String org_pk) {
        this.org_pk = org_pk;
    }

    public String getPk_psndoc() {
        return pk_psndoc;
    }

    public void setPk_psndoc(String pk_psndoc) {
        this.pk_psndoc = pk_psndoc;
    }

    public String getCuserid() {
        return cuserid;
    }

    public void setCuserid(String cuserid) {
        this.cuserid = cuserid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNewpass() {
        return newpass;
    }

    public void setNewpass(String newpass) {
        this.newpass = newpass;
    }

    public String getPk_org() {
        return pk_org;
    }

    public void setPk_org(String pk_org) {
        this.pk_org = pk_org;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCreationtime() {
        return creationtime;
    }

    public void setCreationtime(String creationtime) {
        this.creationtime = creationtime;
    }

    public int getEnablestate() {
        return enablestate;
    }

    public void setEnablestate(int enablestate) {
        this.enablestate = enablestate;
    }

    public String getPk_dept() {
        return pk_dept;
    }

    public void setPk_dept(String pk_dept) {
        this.pk_dept = pk_dept;
    }

    public String getDept_pk() {
        return dept_pk;
    }

    public void setDept_pk(String dept_pk) {
        this.dept_pk = dept_pk;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public String getPk_creator() {
        return pk_creator;
    }

    public void setPk_creator(String pk_creator) {
        this.pk_creator = pk_creator;
    }

    public String getJobname() {
        return jobname;
    }

    public void setJobname(String jobname) {
        this.jobname = jobname;
    }
}



