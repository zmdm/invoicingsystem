package com.bjfe.genuine.software.invoicingsystem.model.cdl;

import com.bjfe.genuine.software.invoicingsystem.model.pub.SuperPojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/12/28 0028.
 */
/**
 * 菜单项类
 * 编写人：张敏
 */
public class MenuItemVO extends SuperPojo implements Comparable<MenuItemVO>,Serializable {
		private String cfunid;
		private String funcode;
		private String pk_father;
		private String funname;
		private String location;
		private int isenable;
		private String creator;
		private String creationtime;
		private List<MenuItemVO> menuItemVOList;


		public MenuItemVO(List<MenuItemVO> menuItemVOList,String pk_father,String cfunid, String funcode, String funname, String location, int isenable, String creator, String creationtime){
			this.cfunid=cfunid;
			this.pk_father = pk_father;
			this.funcode=funcode;
			this.funname=funname;
			this.location=location;
			this.isenable=isenable;
			this.creator=creator;
			this.creationtime=creationtime;
			this.menuItemVOList = menuItemVOList;

		}
		public MenuItemVO(){
			super();
		}
		public String getCfunid() {
			return cfunid;
		}

		public String getPk_father() {
			return pk_father;
		}

		public void setPk_father(String pk_father) {
			this.pk_father = pk_father;
		}

		public void setCfunid(String cfunid) {
			this.cfunid = cfunid;
		}

		public String getFuncode() {
			return funcode;
		}

		public void setFuncode(String funcode) {
			this.funcode = funcode;
		}

		public String getFunname() {
			return funname;
		}

		public void setFunname(String funname) {
			this.funname = funname;
		}

		public int getIsenable() {
			return isenable;
		}

		public void setIsenable(int isenable) {
			this.isenable = isenable;
		}

		public String getLocation() {
			return location;
		}

		public void setLocation(String location) {
			this.location = location;
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

	public List<MenuItemVO> getMenuItemVOList() {
		return menuItemVOList;
	}

	public void setMenuItemVOList(List<MenuItemVO> menuItemVOList) {
		this.menuItemVOList = menuItemVOList;
	}

	@Override
	public int compareTo(MenuItemVO o) {
		int i=1;
		if ("".equals(this.pk_father) && "".equals(o.getPk_father()) ){
			i=  this.pk_father.compareTo(o.getPk_father());
		}
		if(this.pk_father.equals(o.getPk_father())){
			return this.pk_father.compareTo(o.getPk_father());
		}
		return i;
	}
}
