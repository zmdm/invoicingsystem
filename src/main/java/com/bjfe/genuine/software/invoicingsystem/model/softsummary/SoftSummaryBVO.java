package com.bjfe.genuine.software.invoicingsystem.model.softsummary;

/**
 * Created by Administrator on 2018/1/27.
 */

/**
 * 软件使用情况汇总实体子表类
 * 编写人：宋超洋
 */
public class SoftSummaryBVO {
    private String pk_soft_summary_b;
    private String pk_soft_summary;
    private int crowno;
    private String pk_soft;
    private String softcode;
    private String softname;
    private String softsource;
    private String pk_softtype;
    private String softversion;
    private String ordertime;
    private double price;
    private String permistype;
    private int permisnum;
    private String mayversion;
    private String permisdead;
    private String serialnum;
    private int Installnum;

    public SoftSummaryBVO(String softcode,String softname,String softsource,String pk_softtype,String pk_soft_summary_b, String pk_soft_summary, int crowno, String pk_soft, String softversion, String ordertime, double price, String permistype, int permisnum, String mayversion, String permisdead, String serialnum, int installnum) {
        this.pk_soft_summary_b = pk_soft_summary_b;
        this.pk_soft_summary = pk_soft_summary;
        this.crowno = crowno;
        this.pk_soft = pk_soft;
        this.softcode = softcode;
        this.softname = softname;
        this.softsource = softsource;
        this.softversion = softversion;
        this.ordertime = ordertime;
        this.price = price;
        this.permistype = permistype;
        this.permisnum = permisnum;
        this.mayversion = mayversion;
        this.permisdead = permisdead;
        this.serialnum = serialnum;
        this.Installnum = installnum;
        this.pk_softtype = pk_softtype;
    }
    public SoftSummaryBVO(){
        super();
    }

    public String getPk_soft_summary_b() {
        return pk_soft_summary_b;
    }

    public void setPk_soft_summary_b(String pk_soft_summary_b) {
        this.pk_soft_summary_b = pk_soft_summary_b;
    }

    public String getPk_soft_summary() {
        return pk_soft_summary;
    }

    public void setPk_soft_summary(String pk_soft_summary) {
        this.pk_soft_summary = pk_soft_summary;
    }

    public int getCrowno() {
        return crowno;
    }

    public void setCrowno(int crowno) {
        this.crowno = crowno;
    }

    public String getPk_soft() {
        return pk_soft;
    }

    public void setPk_soft(String pk_soft) {
        this.pk_soft = pk_soft;
    }

    public String getSoftversion() {
        return softversion;
    }

    public void setSoftversion(String softversion) {
        this.softversion = softversion;
    }

    public String getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(String ordertime) {
        this.ordertime = ordertime;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPermistype() {
        return permistype;
    }

    public void setPermistype(String permistype) {
        this.permistype = permistype;
    }

    public int getPermisnum() {
        return permisnum;
    }

    public void setPermisnum(int permisnum) {
        this.permisnum = permisnum;
    }

    public String getMayversion() {
        return mayversion;
    }

    public void setMayversion(String mayversion) {
        this.mayversion = mayversion;
    }

    public String getPermisdead() {
        return permisdead;
    }

    public void setPermisdead(String permisdead) {
        this.permisdead = permisdead;
    }

    public String getSerialnum() {
        return serialnum;
    }

    public void setSerialnum(String serialnum) {
        this.serialnum = serialnum;
    }

    public int getInstallnum() {
        return Installnum;
    }

    public void setInstallnum(int installnum) {
        Installnum = installnum;
    }

    public String getPk_softtype() {
        return pk_softtype;
    }

    public void setPk_softtype(String pk_softtype) {
        this.pk_softtype = pk_softtype;
    }

    public String getSoftcode() {
        return softcode;
    }

    public void setSoftcode(String softcode) {
        this.softcode = softcode;
    }

    public String getSoftname() {
        return softname;
    }

    public void setSoftname(String softname) {
        this.softname = softname;
    }

    public String getSoftsource() {
        return softsource;
    }

    public void setSoftsource(String softsource) {
        this.softsource = softsource;
    }
}
