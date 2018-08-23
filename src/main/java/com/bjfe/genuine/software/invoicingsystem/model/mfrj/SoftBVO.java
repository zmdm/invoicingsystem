package com.bjfe.genuine.software.invoicingsystem.model.mfrj;

/**
 * Created by Administrator on 2018/1/22 0022.
 */
/**
 *可使用免费软件清单实体子类
 * 编写人：张敏
 */
public class SoftBVO {
	private String pk_soft_free_b;
	private String pk_soft_free;
	private int crowno;
	private String pk_soft;
	private String softcode;
	private String softname;
	private String softversion;
	private String soft_source;
	private String soft_vendors;
	private String softtelphone;
	private String pk_deptdoc;
	private String reason;
	private String pk_soft_free_h;//子表中的主表主键别名查询
	public SoftBVO(){
		super();
	}

	public SoftBVO(String softcode,String pk_soft_free_b, String pk_soft_free, int crowno, String pk_soft, String softname, String softversion, String soft_source, String soft_vendors, String softtelphone, String pk_deptdoc, String reason) {
		this.pk_soft_free_b = pk_soft_free_b;
		this.pk_soft_free = pk_soft_free;
		this.crowno = crowno;
		this.pk_soft = pk_soft;
		this.softcode = softcode;
		this.softname = softname;
		this.softversion = softversion;
		this.soft_source = soft_source;
		this.soft_vendors = soft_vendors;
		this.softtelphone = softtelphone;
		this.pk_deptdoc = pk_deptdoc;
		this.reason = reason;
	}

	public int getCrowno() {
		return crowno;
	}

	public void setCrowno(int crowno) {
		this.crowno = crowno;
	}

	public String getPk_deptdoc() {
		return pk_deptdoc;
	}

	public void setPk_deptdoc(String pk_deptdoc) {
		this.pk_deptdoc = pk_deptdoc;
	}

	public String getPk_soft() {
		return pk_soft;
	}

	public void setPk_soft(String pk_soft) {
		this.pk_soft = pk_soft;
	}

	public String getSoftcode() {
		return softcode;
	}

	public void setSoftcode(String softcode) {
		this.softcode = softcode;
	}

	public String getPk_soft_free() {
		return pk_soft_free;
	}

	public void setPk_soft_free(String pk_soft_free) {
		this.pk_soft_free = pk_soft_free;
	}

	public String getPk_soft_free_b() {
		return pk_soft_free_b;
	}

	public void setPk_soft_free_b(String pk_soft_free_b) {
		this.pk_soft_free_b = pk_soft_free_b;
	}

	public String getPk_soft_free_h() {
		return pk_soft_free_h;
	}

	public void setPk_soft_free_h(String pk_soft_free_h) {
		this.pk_soft_free_h = pk_soft_free_h;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
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

	public String getSoftname() {
		return softname;
	}

	public void setSoftname(String softname) {
		this.softname = softname;
	}

	public String getSofttelphone() {
		return softtelphone;
	}

	public void setSofttelphone(String softtelphone) {
		this.softtelphone = softtelphone;
	}

	public String getSoftversion() {
		return softversion;
	}

	public void setSoftversion(String softversion) {
		this.softversion = softversion;
	}
}
