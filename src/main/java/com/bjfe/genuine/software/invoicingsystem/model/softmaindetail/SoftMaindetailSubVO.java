package com.bjfe.genuine.software.invoicingsystem.model.softmaindetail;

/**
 * Created by Administrator on 2018/1/27.
 */

/**
 * 软件安装维护明细子表实体明细类
 * 编写人：宋超洋
 */
public class SoftMaindetailSubVO {
    private String pk_soft_maindetail_sub;
    private String pk_soft_maindetail_b;
    private int crowno;
    private int detailtype;
    private String pk_computer;
    private String computercode;
    private String computerbrand;
    private String pk_psndoc;
    private String  pk_deptdoc;
    private String softversion;
    private String softdate;
    private int installnum;

    public SoftMaindetailSubVO(int detailtype,String computercode,String computerbrand,String pk_soft_maindetail_sub, String pk_soft_maindetail_b, int crowno, String pk_computer, String pk_psndoc, String pk_deptdoc, String softversion, String softdate, int installnum) {
        this.pk_soft_maindetail_sub = pk_soft_maindetail_sub;
        this.pk_soft_maindetail_b = pk_soft_maindetail_b;
        this.crowno = crowno;
        this.detailtype = detailtype;
        this.pk_computer = pk_computer;
        this.computercode = computercode;
        this.computerbrand = computerbrand;
        this.pk_psndoc = pk_psndoc;
        this.pk_deptdoc = pk_deptdoc;
        this.softversion = softversion;
        this.softdate = softdate;
        this.installnum = installnum;
    }
    public SoftMaindetailSubVO(){
        super();
    }

    public String getPk_soft_maindetail_sub() {
        return pk_soft_maindetail_sub;
    }

    public void setPk_soft_maindetail_sub(String pk_soft_maindetail_sub) {
        this.pk_soft_maindetail_sub = pk_soft_maindetail_sub;
    }

    public String getPk_soft_maindetail_b() {
        return pk_soft_maindetail_b;
    }

    public void setPk_soft_maindetail_b(String pk_soft_maindetail_b) {
        this.pk_soft_maindetail_b = pk_soft_maindetail_b;
    }

    public int getDetailtype() {
        return detailtype;
    }

    public void setDetailtype(int detailtype) {
        this.detailtype = detailtype;
    }

    public int getCrowno() {
        return crowno;
    }

    public void setCrowno(int crowno) {
        this.crowno = crowno;
    }

    public String getPk_computer() {
        return pk_computer;
    }

    public void setPk_computer(String pk_computer) {
        this.pk_computer = pk_computer;
    }

    public String getPk_psndoc() {
        return pk_psndoc;
    }

    public void setPk_psndoc(String pk_psndoc) {
        this.pk_psndoc = pk_psndoc;
    }

    public String getPk_deptdoc() {
        return pk_deptdoc;
    }

    public void setPk_deptdoc(String pk_deptdoc) {
        this.pk_deptdoc = pk_deptdoc;
    }

    public String getSoftversion() {
        return softversion;
    }

    public void setSoftversion(String softversion) {
        this.softversion = softversion;
    }

    public String getSoftdate() {
        return softdate;
    }

    public void setSoftdate(String softdate) {
        this.softdate = softdate;
    }

    public int getInstallnum() {
        return installnum;
    }

    public void setInstallnum(int installnum) {
        this.installnum = installnum;
    }

    public String getComputercode() {
        return computercode;
    }

    public void setComputercode(String computercode) {
        this.computercode = computercode;
    }

    public String getComputerbrand() {
        return computerbrand;
    }

    public void setComputerbrand(String computerbrand) {
        this.computerbrand = computerbrand;
    }
}
