package com.bjfe.genuine.software.invoicingsystem.model.pub;

/**
 * Created by Administrator on 2017/12/28.
 */

import com.bjfe.genuine.software.invoicingsystem.model.dept.DeptVO;

import java.util.List;

/**
 * 属性公共类
 * 编写人：宋超洋
 */
public class SuperPojo {
    //删除字段
    private int dr;
    //版本字段
    private int versionno;
    //时间戳
    private String ts;
    //表名
    private String tablename;
    //状态
    private int istate;
    //选中标志字段
    private boolean checked;

    public SuperPojo(int dr, int versionno, String ts, String tablename,int istate,boolean checked) {
        this.dr = dr;
        this.versionno = versionno;
        this.ts = ts;
        this.tablename = tablename;
        this.istate = istate;
        this.checked = checked;
    }
    public SuperPojo(){
        super();
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public int getDr() {
        return dr;
    }

    public void setDr(int dr) {
        this.dr = dr;
    }

    public int getVersionno() {
        return versionno;
    }

    public void setVersionno(int versionno) {
        this.versionno = versionno;
    }

    public String getTs() {
        return ts;
    }

    public void setTs(String ts) {
        this.ts = ts;
    }

    public String getTablename() {
        return tablename;
    }

    public void setTablename(String tablename) {
        this.tablename = tablename;
    }

    public int getIstate() {
        return istate;
    }

    public void setIstate(int istate) {
        this.istate = istate;
    }
}
