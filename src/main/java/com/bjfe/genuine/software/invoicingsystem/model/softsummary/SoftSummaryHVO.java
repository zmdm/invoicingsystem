package com.bjfe.genuine.software.invoicingsystem.model.softsummary;

/**
 * Created by Administrator on 2018/1/27.
 */

import com.bjfe.genuine.software.invoicingsystem.model.pub.ExcelSuperPojo;

import java.io.Serializable;
import java.util.List;

/**
 * 软件使用情况汇总实体主表类
 * 编写人：宋超洋
 */
public class SoftSummaryHVO extends ExcelSuperPojo implements Serializable{
    private String pk_soft_summary;
    private List<SoftSummaryBVO> softSummaryList;

    public SoftSummaryHVO(String pk_soft_summary, List<SoftSummaryBVO> softSummaryList) {
        this.pk_soft_summary = pk_soft_summary;
        this.softSummaryList = softSummaryList;
    }
    public SoftSummaryHVO(){
        super();
    }

    public String getPk_soft_summary() {
        return pk_soft_summary;
    }

    public void setPk_soft_summary(String pk_soft_summary) {
        this.pk_soft_summary = pk_soft_summary;
    }

    public List<SoftSummaryBVO> getSoftSummaryList() {
        return softSummaryList;
    }

    public void setSoftSummaryList(List<SoftSummaryBVO> softSummaryList) {
        this.softSummaryList = softSummaryList;
    }
}
