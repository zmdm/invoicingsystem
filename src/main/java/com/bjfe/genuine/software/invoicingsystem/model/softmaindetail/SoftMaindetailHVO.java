package com.bjfe.genuine.software.invoicingsystem.model.softmaindetail;

import com.bjfe.genuine.software.invoicingsystem.model.pub.ExcelSuperPojo;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/1/28.
 */

/**
 * 软件安装维护明细主表实体类
 * 编写人：宋超洋
 */
public class SoftMaindetailHVO extends ExcelSuperPojo implements Serializable{
    private String pk_soft_maindetail;

    public SoftMaindetailHVO(String creator, String pk_org, String telphone, String creationtime, String pk_soft_maindetail) {
        super();
        this.pk_soft_maindetail = pk_soft_maindetail;
    }
    public SoftMaindetailHVO(){
        super();
    }

    public String getPk_soft_maindetail() {
        return pk_soft_maindetail;
    }

    public void setPk_soft_maindetail(String pk_soft_maindetail) {
        this.pk_soft_maindetail = pk_soft_maindetail;
    }
}
