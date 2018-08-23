package com.bjfe.genuine.software.invoicingsystem.model.jsgl;

import com.bjfe.genuine.software.invoicingsystem.model.pub.SuperPojo;

import java.util.Date;

/**
 * Created by Administrator on 2017/12/28 0028.
 */
/**
 * 角色管理类
 * 编写人：张敏
 */
public class RoleVO extends SuperPojo{
	private String pk_role;
	private String rolecode;
	private String rolememo;
	private String rolename;
	private String pk_org;
	private String org_pk;
	private String creator;
	private String creationtime;

	public RoleVO(String pk_role, String rolecode, String rolememo, String rolename, String pk_org, String creator, String creationtime,String org_pk) {
		this.pk_role = pk_role;
		this.rolecode = rolecode;
		this.rolememo = rolememo;
		this.rolename = rolename;
		this.pk_org = pk_org;
		this.org_pk = org_pk;
		this.creator = creator;
		this.creationtime = creationtime;
	}
	public RoleVO(){
		super();
	}

	public String getPk_role() {
		return pk_role;
	}

	public void setPk_role(String pk_role) {
		this.pk_role = pk_role;
	}

	public String getRolecode() {
		return rolecode;
	}

	public void setRolecode(String rolecode) {
		this.rolecode = rolecode;
	}

	public String getRolememo() {
		return rolememo;
	}

	public void setRolememo(String rolememo) {
		this.rolememo = rolememo;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public String getPk_org() {
		return pk_org;
	}

	public void setPk_org(String pk_org) {
		this.pk_org = pk_org;
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

	public String getOrg_pk() {
		return org_pk;
	}

	public void setOrg_pk(String org_pk) {
		this.org_pk = org_pk;
	}
}
