package com.bjfe.genuine.software.invoicingsystem.model.softorder;

/**
 * Created by Administrator on 2018/1/11.
 */

/**
 * 软件采购计划表实体子类
 * 编写人：宋超洋
 */
public class SoftOrderBVO {
    private String pk_soft_order_b;
    private String pk_soft_order;
    private String pk_soft_apply;
    private String crowno;
    private String softversion;
    private String pk_soft;
    private String soft_pk;
    private double price;
    private String ordnum;
    private double total;
    private String vnote;
    private int timelimit;

    public SoftOrderBVO(String pk_soft_apply,int timelimit,String soft_pk,String pk_soft_order_b, String pk_soft_order, String crowno, String softversion, String pk_soft, double price, String ordnum, double total, String vnote) {
        this.pk_soft_order_b = pk_soft_order_b;
        this.pk_soft_order = pk_soft_order;
        this.pk_soft_apply = pk_soft_apply;
        this.soft_pk = soft_pk;
        this.crowno = crowno;
        this.softversion = softversion;
        this.pk_soft = pk_soft;
        this.price = price;
        this.ordnum = ordnum;
        this.total = total;
        this.vnote = vnote;
        this.timelimit = timelimit;
    }
    public SoftOrderBVO(){
        super();
    }
    public String getPk_soft_order_b() {
        return pk_soft_order_b;
    }

    public void setPk_soft_order_b(String pk_soft_order_b) {
        this.pk_soft_order_b = pk_soft_order_b;
    }

    public String getPk_soft_order() {
        return pk_soft_order;
    }

    public void setPk_soft_order(String pk_soft_order) {
        this.pk_soft_order = pk_soft_order;
    }

    public String getPk_soft_apply() {
        return pk_soft_apply;
    }

    public void setPk_soft_apply(String pk_soft_apply) {
        this.pk_soft_apply = pk_soft_apply;
    }

    public String getCrowno() {
        return crowno;
    }

    public void setCrowno(String crowno) {
        this.crowno = crowno;
    }

    public String getSoftversion() {
        return softversion;
    }

    public void setSoftversion(String softversion) {
        this.softversion = softversion;
    }

    public String getPk_soft() {
        return pk_soft;
    }

    public void setPk_soft(String pk_soft) {
        this.pk_soft = pk_soft;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getOrdnum() {
        return ordnum;
    }

    public void setOrdnum(String ordnum) {
        this.ordnum = ordnum;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getVnote() {
        return vnote;
    }

    public void setVnote(String vnote) {
        this.vnote = vnote;
    }

    public double getPrice() {
        return price;
    }

    public String getSoft_pk() {

        return soft_pk;
    }

    public void setSoft_pk(String soft_pk) {
        this.soft_pk = soft_pk;
    }

    public int getTimelimit() {
        return timelimit;
    }

    public void setTimelimit(int timelimit) {
        this.timelimit = timelimit;
    }
}
