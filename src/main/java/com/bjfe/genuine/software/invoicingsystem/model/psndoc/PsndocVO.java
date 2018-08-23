package com.bjfe.genuine.software.invoicingsystem.model.psndoc;
/**
 * Created by Administrator on 2017-12-28.
 */

import com.bjfe.genuine.software.invoicingsystem.model.pub.SuperPojo;

import java.io.Serializable;

/**
 * 人员信息实体类
 * 编写人：李宗泽
 */
public class PsndocVO extends SuperPojo implements Serializable{
    private String pk_psndoc;
    private int sex;
    private String telphone;
    private String post;
    private String email;
    private String pk_job;
    private String job_pk;
    private String pk_org;
    private String org_pk;
    private String name;
    private String code;
    private String pk_dept;
    private String dept_pk;
    private String creator;
    private String creationtime;
    private String idcard;
    private int enablestate;
    private String address;

    public PsndocVO(String job_pk,String pk_psndoc,String address,int enablestate,String idcard,String email,String post,String telphone,int sex,String pk_job,String pk_org, String name, String code, String pk_dept,String creator,String creationtime,String org_pk,String dept_pk) {
        this.job_pk = job_pk;
        this.address = address;
        this.enablestate = enablestate;
        this.idcard = idcard;
        this.email = email;
        this.post = post;
        this.telphone = telphone;
        this.pk_psndoc = pk_psndoc;
        this.sex = sex;
        this.pk_job = pk_job;
        this.pk_org = pk_org;
        this.name = name;
        this.code = code;
        this.pk_dept = pk_dept;
        this.creator = creator;
        this.creationtime = creationtime;
        this.dept_pk = dept_pk;
        this.org_pk = org_pk;
    }
    public PsndocVO(){
        super();
    }

    public String getJob_pk() {
        return job_pk;
    }

    public void setJob_pk(String job_pk) {
        this.job_pk = job_pk;
    }

    public String getPk_psndoc() {
        return pk_psndoc;
    }

    public void setPk_psndoc(String pk_psndoc) {
        this.pk_psndoc = pk_psndoc;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPk_job() {
        return pk_job;
    }

    public void setPk_job(String pk_job) {
        this.pk_job = pk_job;
    }

    public String getOrg_pk() {
        return org_pk;
    }

    public void setOrg_pk(String org_pk) {
        this.org_pk = org_pk;
    }

    public String getPk_org() {
        return pk_org;
    }

    public void setPk_org(String pk_org) {
        this.pk_org = pk_org;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public int getEnablestate() {
        return enablestate;
    }

    public void setEnablestate(int enablestate) {
        this.enablestate = enablestate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}



