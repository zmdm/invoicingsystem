package com.bjfe.genuine.software.invoicingsystem.model.computer;

/**
 * Created by Administrator on 2018/1/16.
 */

import com.bjfe.genuine.software.invoicingsystem.model.pub.SuperPojo;

import java.io.Serializable;

/**
 * 计算机管理实体类
 * 编写人：宋超洋
 */
public class ComputerVO extends SuperPojo implements Serializable{
    private static final long serialVersionUID = -7502361451464998085L;
    private String pk_computer;
    private String code;
    private String name;
    private String brand;
    private String pk_psndoc;
    private String psndoc_pk;
    private String vlocation;
    private String pk_soft;
    private String soft_name;
    private String version;
    private String vnote;
    private String pk_org;

    public ComputerVO(String pk_org ,String psndoc_pk,String pk_computer, String code, String name, String brand, String pk_psndoc, String vlocation, String pk_soft, String soft_name, String version, String vnote) {
        this.pk_computer = pk_computer;
        this.psndoc_pk =psndoc_pk;
        this.code = code;
        this.name = name;
        this.brand = brand;
        this.pk_psndoc = pk_psndoc;
        this.vlocation = vlocation;
        this.pk_soft = pk_soft;
        this.soft_name = soft_name;
        this.version = version;
        this.vnote = vnote;
        this.pk_org = pk_org;
    }
    public ComputerVO(){
        super();
    }

    public String getPk_computer() {
        return pk_computer;
    }

    public void setPk_computer(String pk_computer) {
        this.pk_computer = pk_computer;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getPk_psndoc() {
        return pk_psndoc;
    }

    public void setPk_psndoc(String pk_psndoc) {
        this.pk_psndoc = pk_psndoc;
    }

    public String getVlocation() {
        return vlocation;
    }

    public void setVlocation(String vlocation) {
        this.vlocation = vlocation;
    }

    public String getPk_soft() {
        return pk_soft;
    }

    public void setPk_soft(String pk_soft) {
        this.pk_soft = pk_soft;
    }

    public String getSoft_name() {
        return soft_name;
    }

    public void setSoft_name(String soft_name) {
        this.soft_name = soft_name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getVnote() {
        return vnote;
    }

    public void setVnote(String vnote) {
        this.vnote = vnote;
    }

    public String getPsndoc_pk() {
        return psndoc_pk;
    }

    public void setPsndoc_pk(String psndoc_pk) {
        this.psndoc_pk = psndoc_pk;
    }

    public String getPk_org() {
        return pk_org;
    }

    public void setPk_org(String pk_org) {
        this.pk_org = pk_org;
    }
}
