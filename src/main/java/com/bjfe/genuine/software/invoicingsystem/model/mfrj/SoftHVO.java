package com.bjfe.genuine.software.invoicingsystem.model.mfrj;

import com.bjfe.genuine.software.invoicingsystem.model.pub.ExcelSuperPojo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/1/22 0022.
 */
/**
 * 可使用免费软件清单实体主类
 * 编写人：张敏
 */

public class SoftHVO extends ExcelSuperPojo implements Serializable{
	private String pk_soft_free;
	private List<SoftBVO> SoftBVOList;

	public SoftHVO(String pk_soft_free, List<SoftBVO> softBVOList) {
		this.pk_soft_free = pk_soft_free;
		SoftBVOList = softBVOList;
	}
	public SoftHVO(){
		super();
	}

	public String getPk_soft_free() {
		return pk_soft_free;
	}

	public void setPk_soft_free(String pk_soft_free) {
		this.pk_soft_free = pk_soft_free;
	}

	public List<SoftBVO> getSoftBVOList() {
		return SoftBVOList;
	}

	public void setSoftBVOList(List<SoftBVO> softBVOList) {
		SoftBVOList = softBVOList;
	}
}
