package com.bjfe.genuine.software.invoicingsystem.model.qkmx;

import com.bjfe.genuine.software.invoicingsystem.model.pub.ExcelSuperPojo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/1/23 0023.
 */
/**
 * 软件使用情况明细表实体主类
 * 编写人：张敏.
 */
public class QkmxHVO extends ExcelSuperPojo implements Serializable{
	private String pk_soft_detail;
    private List<QkmxBVO>qkmxBVOList;

	public QkmxHVO(String pk_soft_detail, List<QkmxBVO> qkmxBVOList) {
		this.pk_soft_detail = pk_soft_detail;
		this.qkmxBVOList = qkmxBVOList;
	}
	public QkmxHVO(){
		super();
	}

	public String getPk_soft_detail() {
		return pk_soft_detail;
	}

	public void setPk_soft_detail(String pk_soft_detail) {
		this.pk_soft_detail = pk_soft_detail;
	}

	public List<QkmxBVO> getQkmxBVOList() {
		return qkmxBVOList;
	}

	public void setQkmxBVOList(List<QkmxBVO> qkmxBVOList) {
		this.qkmxBVOList = qkmxBVOList;
	}
}
