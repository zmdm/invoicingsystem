package com.bjfe.genuine.software.invoicingsystem.model.rjxx;

/**
 * Created by Administrator on 2017/12/28 0028.
 */

import com.bjfe.genuine.software.invoicingsystem.model.pub.SuperPojo;

import java.io.Serializable;

/**
 * 软件信息类
 * 编写人：张敏
 */

public class SoftInfoVO extends SuperPojo implements Serializable{
	private String pk_soft;
	private String code;
	private String name;
	private String version;
	private String pk_softtype;
	private String softtype_pk;
	private String soft_source;
	private String soft_vendors;
	//过滤判断软件类型时是否免费新增字段
	private String isfree;

	public SoftInfoVO(String soft_source,String soft_vendors,String softtype_pk,String pk_soft, String code, String name, String pk_softtype,String version){
		this.pk_soft=pk_soft;
		this.code=code;
		this.name=name;
		this.version=version;
		this.pk_softtype=pk_softtype;
		this.softtype_pk = softtype_pk;
		this.soft_source = soft_source;
		this.soft_vendors = soft_vendors;
	}
	public SoftInfoVO(){
		super();
	}
	public String getPk_soft() {
		return pk_soft;
	}

	public void setPk_soft(String pk_soft) {
		this.pk_soft = pk_soft;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPk_softtype() {
		return pk_softtype;
	}

	public void setPk_softtype(String pk_softtype) {
		this.pk_softtype = pk_softtype;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSofttype_pk() {
		return softtype_pk;
	}

	public void setSofttype_pk(String softtype_pk) {
		this.softtype_pk = softtype_pk;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getSoft_source() {
		return soft_source;
	}

	public void setSoft_source(String soft_source) {
		this.soft_source = soft_source;
	}

	public String getSoft_vendors() {
		return soft_vendors;
	}

	public void setSoft_vendors(String soft_vendors) {
		this.soft_vendors = soft_vendors;
	}

	public String getIsfree() {
		return isfree;
	}

	public void setIsfree(String isfree) {
		this.isfree = isfree;
	}
}
