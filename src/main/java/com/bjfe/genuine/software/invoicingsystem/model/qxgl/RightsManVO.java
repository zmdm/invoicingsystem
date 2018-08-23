package com.bjfe.genuine.software.invoicingsystem.model.qxgl;

/**
 * Created by Administrator on 2017/12/28 0028.
 */

		import com.bjfe.genuine.software.invoicingsystem.model.pub.SuperPojo;

		import java.util.Date;

/**
 * 权限管理类
 * 编写人：张敏
 */
public class RightsManVO extends SuperPojo{
	private String pk_permid;
	private  String code;
	private String name;
	private String memo;
	private String pk_org;
	private String org_pk;
	private  String creator;
	private String creationtime;
	public RightsManVO(String org_pk, String pk_permid, String code, String name, String memo, String pk_org, String creator, String creationtime){
		this.org_pk = org_pk;
		this.pk_permid=pk_permid;
		this.code=code;
		this.name=name;
		this.memo=memo;
		this.pk_org=pk_org;
		this.creator=creator;
		this.creationtime=creationtime;
	}
	public RightsManVO(){
		super();
	}

	public String getOrg_pk() {
		return org_pk;
	}

	public void setOrg_pk(String org_pk) {
		this.org_pk = org_pk;
	}

	public String getPk_permid() {
		return pk_permid;
	}

	public void setPk_permid(String pk_permid) {
		this.pk_permid = pk_permid;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
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
}
