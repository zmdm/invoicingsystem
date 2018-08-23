package com.bjfe.genuine.software.invoicingsystem.model.softapply;

/**
 * Created by Administrator on 2018/1/11.
 */

/**
 * 软件使用需求申请实体子类
 * 编写人：宋超洋
 */
public class SoftApplyBVO {
    private String pk_soft_apply_b;
    private String pk_soft_apply;
    private String crowno;
    private String pk_softtype;
    private String softtype_pk;
    private String pk_soft;
    private String soft_pk;
    private String softpurpose;
    private String funquantity;
    private int appnum;
    private String isdomestic;
    private String vnote;
    private String version;
    private boolean operation;

    public SoftApplyBVO(String version,boolean operation,String soft_pk,String softtype_pk,String pk_soft_apply_b, String pk_soft_apply, String crowno, String pk_softtype, String pk_soft, String softpurpose, String funquantity, int appnum, String isdomestic, String vnote) {
        this.operation = operation;
        this.pk_soft_apply_b = pk_soft_apply_b;
        this.pk_soft_apply = pk_soft_apply;
        this.crowno = crowno;
        this.pk_softtype = pk_softtype;
        this.pk_soft = pk_soft;
        this.softpurpose = softpurpose;
        this.funquantity = funquantity;
        this.appnum = appnum;
        this.isdomestic = isdomestic;
        this.vnote = vnote;
        this.soft_pk=soft_pk;
        this.softtype_pk = softtype_pk;
        this.version= version;
    }
    public SoftApplyBVO(){
        super();
    }

    public String getPk_soft_apply_b() {
        return pk_soft_apply_b;
    }

    public void setPk_soft_apply_b(String pk_soft_apply_b) {
        this.pk_soft_apply_b = pk_soft_apply_b;
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

    public String getPk_softtype() {
        return pk_softtype;
    }

    public void setPk_softtype(String pk_softtype) {
        this.pk_softtype = pk_softtype;
    }

    public String getPk_soft() {
        return pk_soft;
    }

    public void setPk_soft(String pk_soft) {
        this.pk_soft = pk_soft;
    }

    public String getSoftpurpose() {
        return softpurpose;
    }

    public void setSoftpurpose(String softpurpose) {
        this.softpurpose = softpurpose;
    }

    public String getFunquantity() {
        return funquantity;
    }

    public void setFunquantity(String funquantity) {
        this.funquantity = funquantity;
    }

    public int getAppnum() {
        return appnum;
    }

    public void setAppnum(int appnum) {
        this.appnum = appnum;
    }

    public String getIsdomestic() {
        return isdomestic;
    }

    public void setIsdomestic(String isdomestic) {
        this.isdomestic = isdomestic;
    }

    public String getVnote() {
        return vnote;
    }

    public void setVnote(String vnote) {
        this.vnote = vnote;
    }

    public String getSofttype_pk() {
        return softtype_pk;
    }

    public void setSofttype_pk(String softtype_pk) {
        this.softtype_pk = softtype_pk;
    }

    public String getSoft_pk() {
        return soft_pk;
    }

    public void setSoft_pk(String soft_pk) {
        this.soft_pk = soft_pk;
    }

    public boolean isOperation() {
        return operation;
    }

    public void setOperation(boolean operation) {
        this.operation = operation;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
