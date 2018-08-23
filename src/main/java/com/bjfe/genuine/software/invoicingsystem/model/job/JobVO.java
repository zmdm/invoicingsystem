package com.bjfe.genuine.software.invoicingsystem.model.job;
/**
 * Created by Administrator on 2017-12-28.
 */

import com.bjfe.genuine.software.invoicingsystem.model.psndoc.PsndocVO;
import com.bjfe.genuine.software.invoicingsystem.model.pub.SuperPojo;

import java.io.Serializable;
import java.util.List;

/**
 * 部门实体类
 * 编写人：李宗泽
 */
public class JobVO extends SuperPojo implements Serializable{
    private static final long serialVersionUID = -7502361451464998085L;
    private String pk_job;
    private String pk_org;
    private String org_pk;
    private String name;
    private String code;
    private String pk_deptdoc;
    private String dept_pk;
    private String creator;
    private String creationtime;

    public JobVO(String pk_job,String pk_org, String name, String code, String pk_deptdoc,String creator,String creationtime,String dept_pk,String org_pk) {
        this.pk_job = pk_job;
        this.org_pk = org_pk;
        this.pk_org = pk_org;
        this.name = name;
        this.code = code;
        this.pk_deptdoc = pk_deptdoc;
        this.creator = creator;
        this.creationtime = creationtime;
        this.dept_pk = dept_pk;
    }
    public JobVO(){
        super();
    }

    public String getPk_job() {
        return pk_job;
    }

    public void setPk_job(String pk_job) {
        this.pk_job = pk_job;
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
    public String getCreationtime() {
        return creationtime;
    }

    public void setCreationtime(String creationtime) {
        this.creationtime = creationtime;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getDept_pk() {
        return dept_pk;
    }

    public void setDept_pk(String dept_pk) {
        this.dept_pk = dept_pk;
    }

    public String getOrg_pk() {
        return org_pk;
    }

    public void setOrg_pk(String org_pk) {
        this.org_pk = org_pk;
    }
}



