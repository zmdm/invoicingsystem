package com.bjfe.genuine.software.invoicingsystem.model.qkmx;

/**
 * Created by Administrator on 2018/1/23 0023.
 */
/**
 * 软件使用情况明细表实体子类
 * 编写人：张敏.
 */
public class QkmxBVO {
	private String pk_soft_detail_b;
	private String pk_soft_detail;
	private int crowno;
	private String pk_deptdoc;
	private String pk_psndoc;
	private String pk_computer;
	private String computercode;
	private String computerbrand;
	private String pk_soft;
	private String softcode;
	private String softname;
	private String softversion;
	private String permis;
	private String pk_softtype;
	private String taudittime;
	private String pk_soft_detail_h;
	public QkmxBVO(){
		super();
	}

	public QkmxBVO(String softcode,String computercode,String pk_soft_detail_b, String pk_soft_detail, int crowno, String pk_deptdoc, String pk_psndoc, String pk_computer, String computerbrand, String pk_soft, String softname, String softversion, String permis, String pk_softtype, String taudittime, String pk_soft_detail_h) {
		this.pk_soft_detail_b = pk_soft_detail_b;
		this.pk_soft_detail = pk_soft_detail;
		this.crowno = crowno;
		this.pk_deptdoc = pk_deptdoc;
		this.pk_psndoc = pk_psndoc;
		this.pk_computer = pk_computer;
		this.computercode = computercode;
		this.computerbrand = computerbrand;
		this.pk_soft = pk_soft;
		this.softname = softname;
		this.softversion = softversion;
		this.permis = permis;
		this.pk_softtype = pk_softtype;
		this.taudittime = taudittime;
		this.pk_soft_detail_h=pk_soft_detail_h;
		this.softcode = softcode;
	}

	public String getSoftcode() {
		return softcode;
	}

	public void setSoftcode(String softcode) {
		this.softcode = softcode;
	}

	public String getComputerbrand() {
		return computerbrand;
	}

	public void setComputerbrand(String computerbrand) {
		this.computerbrand = computerbrand;
	}

	public int getCrowno() {
		return crowno;
	}

	public void setCrowno(int crowno) {
		this.crowno = crowno;
	}

	public String getPermis() {
		return permis;
	}

	public void setPermis(String permis) {
		this.permis = permis;
	}

	public String getPk_computer() {
		return pk_computer;
	}

	public void setPk_computer(String pk_computer) {
		this.pk_computer = pk_computer;
	}

	public String getComputercode() {
		return computercode;
	}

	public void setComputercode(String computercode) {
		this.computercode = computercode;
	}

	public String getPk_deptdoc() {
		return pk_deptdoc;
	}

	public void setPk_deptdoc(String pk_deptdoc) {
		this.pk_deptdoc = pk_deptdoc;
	}

	public String getPk_psndoc() {
		return pk_psndoc;
	}

	public void setPk_psndoc(String pk_psndoc) {
		this.pk_psndoc = pk_psndoc;
	}

	public String getPk_soft() {
		return pk_soft;
	}

	public void setPk_soft(String pk_soft) {
		this.pk_soft = pk_soft;
	}

	public String getPk_soft_detail() {
		return pk_soft_detail;
	}

	public void setPk_soft_detail(String pk_soft_detail) {
		this.pk_soft_detail = pk_soft_detail;
	}

	public String getPk_soft_detail_b() {
		return pk_soft_detail_b;
	}

	public void setPk_soft_detail_b(String pk_soft_detail_b) {
		this.pk_soft_detail_b = pk_soft_detail_b;
	}

	public String getPk_softtype() {
		return pk_softtype;
	}

	public void setPk_softtype(String pk_softtype) {
		this.pk_softtype = pk_softtype;
	}

	public String getSoftname() {
		return softname;
	}

	public void setSoftname(String softname) {
		this.softname = softname;
	}

	public String getSoftversion() {
		return softversion;
	}

	public void setSoftversion(String softversion) {
		this.softversion = softversion;
	}

	public String getTaudittime() {
		return taudittime;
	}

	public void setTaudittime(String taudittime) {
		this.taudittime = taudittime;
	}


	public String getPk_soft_detail_h() {
		return pk_soft_detail_h;
	}

	public void setPk_soft_detail_h(String pk_soft_detail_h) {
		this.pk_soft_detail_h = pk_soft_detail_h;
	}

}
