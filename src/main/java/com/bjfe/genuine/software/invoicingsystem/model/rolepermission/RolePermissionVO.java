package com.bjfe.genuine.software.invoicingsystem.model.rolepermission;

import com.bjfe.genuine.software.invoicingsystem.model.pub.SuperPojo;

import javax.security.auth.Subject;

/**
 * Created by Administrator on 2018/1/4.
 */

/**
 * 角色权限关系实体类
 * 编写人：宋超洋
 */
public class RolePermissionVO extends SuperPojo{
    private String pk_role_power;
    private String pk_role;
    private String pk_permid;

    public RolePermissionVO(String pk_role_power, String pk_role, String pk_permid) {
        this.pk_role_power = pk_role_power;
        this.pk_role = pk_role;
        this.pk_permid = pk_permid;
    }
    public RolePermissionVO(){
        super();
    }
    public String getPk_role_power() {
        return pk_role_power;
    }

    public void setPk_role_power(String pk_role_power) {
        this.pk_role_power = pk_role_power;
    }

    public String getPk_role() {
        return pk_role;
    }

    public void setPk_role(String pk_role) {
        this.pk_role = pk_role;
    }

    public String getPk_permid() {
        return pk_permid;
    }

    public void setPk_permid(String pk_permid) {
        this.pk_permid = pk_permid;
    }
}
