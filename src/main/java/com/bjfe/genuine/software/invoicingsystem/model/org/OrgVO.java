package com.bjfe.genuine.software.invoicingsystem.model.org;
/**
 * Created by Administrator on 2017-12-28.
 */

import com.bjfe.genuine.software.invoicingsystem.model.dept.DeptVO;
import com.bjfe.genuine.software.invoicingsystem.model.pub.SuperPojo;

import java.io.Serializable;
import java.util.List;

/**
 * 组织实体类
 * 编写人：李宗泽
 */
public class OrgVO extends SuperPojo implements Serializable{
    //SerialVersionUID字段对序列化扩展有用，为了以后扩展或者缩减字段时不会造成反序列化出错。
    private static final long serialVersionUID = -7502361451464998085L;
    private String pk_org;
    private String name;
    private String code;
    private String pk_fatherorg;
    private String creator;
    private String creationtime;
    private String pk_psndoc;
    private List<DeptVO> deptList;

    public OrgVO(String pk_psndoc,String pk_org, String name, String code, String pk_fatherorg,String creator,String creationtime,List<DeptVO> deptList) {
        this.pk_org = pk_org;
        this.name = name;
        this.code = code;
        this.pk_fatherorg = pk_fatherorg;
        this.creator = creator;
        this.creationtime = creationtime;
        this.deptList=deptList;
        this.pk_psndoc= pk_psndoc;
    }
    public OrgVO(){
        super();
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

    public String getPk_fatherorg() {
        return pk_fatherorg;
    }

    public void setPk_fatherorg(String pk_fatherorg) {
        this.pk_fatherorg = pk_fatherorg;
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

    public List<DeptVO> getDeptList() {
        return deptList;
    }

    public void setDeptList(List<DeptVO> deptList) {
        this.deptList = deptList;
    }

    public String getPk_psndoc() {
        return pk_psndoc;
    }

    public void setPk_psndoc(String pk_psndoc) {
        this.pk_psndoc = pk_psndoc;
    }
}

