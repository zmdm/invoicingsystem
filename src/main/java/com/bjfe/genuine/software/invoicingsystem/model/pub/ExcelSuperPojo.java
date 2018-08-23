package com.bjfe.genuine.software.invoicingsystem.model.pub;

/**
 * Created by Administrator on 2018/1/27.
 */

/**
 * excel主表公共字段类
 * 编写人:宋超洋
 */
public class ExcelSuperPojo {
    private String creator;
    private String pk_org;
    private String telphone;
    private String creationtime;

    public ExcelSuperPojo(String creator, String pk_org, String telphone, String creationtime) {
        this.creator = creator;
        this.pk_org = pk_org;
        this.telphone = telphone;
        this.creationtime = creationtime;
    }
    public ExcelSuperPojo(){
        super();
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
}
