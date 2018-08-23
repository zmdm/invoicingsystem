package com.bjfe.genuine.software.invoicingsystem.model.softmaindetail;

/**
 * Created by Administrator on 2018/1/27.
 */

import java.util.List;

/**
 * 软件安装维护明细子表实体类
 * 编写人：宋超洋
 */
public class SoftMaindetailBVO {
    private String pk_soft_maindetail_b;
    private String pk_soft_maindetail;
    private String pk_soft;
    private String softcode;
    private String softname;
    private String softversion;
    private String softtype;
    private int permisnum;
    private String permis;
    private String permisdead;
    private List<SoftMaindetailSubVO> softMaindetailSubList;

    public SoftMaindetailBVO(String softcode,String softname,String softversion,String softtype,List<SoftMaindetailSubVO> softMaindetailSubList,String pk_soft_maindetail_b, String pk_soft_maindetail, String pk_soft, int permisnum, String permis, String permisdead) {
        this.pk_soft_maindetail_b = pk_soft_maindetail_b;
        this.pk_soft_maindetail = pk_soft_maindetail;
        this.pk_soft = pk_soft;
        this.softcode=softcode;
        this.softname = softname;
        this.softversion = softversion;
        this.softtype = softtype;
        this.permisnum = permisnum;
        this.permis = permis;
        this.permisdead = permisdead;
        this.softMaindetailSubList = softMaindetailSubList;
    }
    public SoftMaindetailBVO(){
        super();
    }

    public String getPk_soft_maindetail_b() {
        return pk_soft_maindetail_b;
    }

    public void setPk_soft_maindetail_b(String pk_soft_maindetail_b) {
        this.pk_soft_maindetail_b = pk_soft_maindetail_b;
    }

    public String getPk_soft_maindetail() {
        return pk_soft_maindetail;
    }

    public void setPk_soft_maindetail(String pk_soft_maindetail) {
        this.pk_soft_maindetail = pk_soft_maindetail;
    }

    public String getPk_soft() {
        return pk_soft;
    }

    public void setPk_soft(String pk_soft) {
        this.pk_soft = pk_soft;
    }

    public int getPermisnum() {
        return permisnum;
    }

    public void setPermisnum(int permisnum) {
        this.permisnum = permisnum;
    }

    public String getPermis() {
        return permis;
    }

    public void setPermis(String permis) {
        this.permis = permis;
    }

    public String getPermisdead() {
        return permisdead;
    }

    public void setPermisdead(String permisdead) {
        this.permisdead = permisdead;
    }

    public List<SoftMaindetailSubVO> getSoftMaindetailSubList() {
        return softMaindetailSubList;
    }

    public void setSoftMaindetailSubList(List<SoftMaindetailSubVO> softMaindetailSubList) {
        this.softMaindetailSubList = softMaindetailSubList;
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

    public String getSoftversion() {
        return softversion;
    }

    public void setSoftversion(String softversion) {
        this.softversion = softversion;
    }

    public String getSofttype() {
        return softtype;
    }

    public void setSofttype(String softtype) {
        this.softtype = softtype;
    }
}
