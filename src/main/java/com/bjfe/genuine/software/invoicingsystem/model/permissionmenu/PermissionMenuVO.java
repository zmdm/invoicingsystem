package com.bjfe.genuine.software.invoicingsystem.model.permissionmenu;

import com.bjfe.genuine.software.invoicingsystem.model.pub.SuperPojo;

/**
 * Created by Administrator on 2018/1/4.
 */

/**
 * 权限菜单关系实体类
 * 编写人：宋超洋
 */
public class PermissionMenuVO extends SuperPojo{
    private String pk_permid_cfunid;
    private String pk_permid;
    private String cfunid;

    public PermissionMenuVO(String pk_permid_cfunid, String pk_permid, String cfunid) {
        this.pk_permid_cfunid = pk_permid_cfunid;
        this.pk_permid = pk_permid;
        this.cfunid = cfunid;
    }
    public PermissionMenuVO(){
        super();
    }
    public String getPk_permid_cfunid() {
        return pk_permid_cfunid;
    }

    public void setPk_permid_cfunid(String pk_permid_cfunid) {
        this.pk_permid_cfunid = pk_permid_cfunid;
    }

    public String getPk_permid() {
        return pk_permid;
    }

    public void setPk_permid(String pk_permid) {
        this.pk_permid = pk_permid;
    }

    public String getCfunid() {
        return cfunid;
    }

    public void setCfunid(String cfunid) {
        this.cfunid = cfunid;
    }
}
