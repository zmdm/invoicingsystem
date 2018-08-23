package com.bjfe.genuine.software.invoicingsystem.model.rjlx;

/**
 * Created by Administrator on 2017/12/28 0028.
 */


import com.bjfe.genuine.software.invoicingsystem.model.pub.SuperPojo;

import java.io.Serializable;

/**
 * 软件类型类
 * 编写人：张敏
 */
public class SoftTypeVO extends SuperPojo implements Serializable{

	private String pk_softtype;
	private String code;
	private String name;
	private int isfree;
	public SoftTypeVO(String pk_softtype, String code, String name,int isfree){
		this.pk_softtype=pk_softtype;
		this.code=code;
		this.name=name;
		this.isfree = isfree;
	}
	public SoftTypeVO(){
		super();
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getIsfree() {
		return isfree;
	}

	public void setIsfree(int isfree) {
		this.isfree = isfree;
	}
}
