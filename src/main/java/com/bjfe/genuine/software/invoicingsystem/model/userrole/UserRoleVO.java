package com.bjfe.genuine.software.invoicingsystem.model.userrole;

import com.bjfe.genuine.software.invoicingsystem.model.pub.SuperPojo;

/**
 * Created by Administrator on 2018/1/4.
 */

/**
 * 用户角色关系实体类
 * 编写人：宋超洋
 */
public class UserRoleVO extends SuperPojo{
    private String pk_user_role;
    private String cuserid;
    private String pk_role;

    public UserRoleVO(String pk_user_role, String cuserid, String pk_role) {
        super();
        this.pk_user_role = pk_user_role;
        this.cuserid = cuserid;
        this.pk_role = pk_role;
    }
    public UserRoleVO(){
        super();
    }

    public String getPk_user_role() {
        return pk_user_role;
    }

    public void setPk_user_role(String pk_user_role) {
        this.pk_user_role = pk_user_role;
    }

    public String getCuserid() {
        return cuserid;
    }

    public void setCuserid(String cuserid) {
        this.cuserid = cuserid;
    }

    public String getPk_role() {
        return pk_role;
    }

    public void setPk_role(String pk_role) {
        this.pk_role = pk_role;
    }

}
