package com.bjfe.genuine.software.invoicingsystem.util.excel;

/**
 * Created by Administrator on 2018/1/23.
 */

import com.bjfe.genuine.software.invoicingsystem.model.computer.ComputerVO;
import com.bjfe.genuine.software.invoicingsystem.model.dept.DeptVO;
import com.bjfe.genuine.software.invoicingsystem.model.mfrj.SoftBVO;
import com.bjfe.genuine.software.invoicingsystem.model.mfrj.SoftHVO;
import com.bjfe.genuine.software.invoicingsystem.model.psndoc.PsndocVO;
import com.bjfe.genuine.software.invoicingsystem.model.qkmx.QkmxBVO;
import com.bjfe.genuine.software.invoicingsystem.model.rjlx.SoftTypeVO;
import com.bjfe.genuine.software.invoicingsystem.model.rjxx.SoftInfoVO;
import com.bjfe.genuine.software.invoicingsystem.model.softmaindetail.SoftMaindetailBVO;
import com.bjfe.genuine.software.invoicingsystem.model.softmaindetail.SoftMaindetailSubVO;
import com.bjfe.genuine.software.invoicingsystem.model.softsummary.SoftSummaryBVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 处理导入excel信息工具类
 * 编写人：宋超洋
 */
public class FreeSoftUtil {
    /**
     * 判断excel中免费软件清单信息
     * @param softBVO
     * @param map
     * @param commonMap
     * @return
     */
    public static Map handleFreeSoftB(SoftBVO softBVO, Map map, Map commonMap){
        Map resultMap = new HashMap<>();
        List<DeptVO> deptlist = (ArrayList)commonMap.get("deptlist");
        List<SoftInfoVO> softInfoList=(ArrayList)commonMap.get("softinfolist");
        for (int i=0;i<deptlist.size();i++){
            DeptVO deptVO = deptlist.get(i);
            if (deptVO.getName().equals(map.get("pk_deptdoc"))){
                softBVO.setPk_deptdoc(deptVO.getPk_deptdoc());
                break;
            }
        }
        for (int i=0;i<softInfoList.size();i++){
            SoftInfoVO softInfoVO = softInfoList.get(i);
            if (softInfoVO.getCode().equals(map.get("softcode")) && softInfoVO.getName().equals(map.get("softname")) && softInfoVO.getVersion().equals(map.get("softversion"))
                    && softInfoVO.getSoft_source().equals(map.get("soft_source"))&& softInfoVO.getSoft_vendors().equals(map.get("soft_vendors"))){
                softBVO.setPk_soft(softInfoVO.getPk_soft());
                break;
            }
        }
        if (softBVO.getPk_deptdoc()==null||softBVO.getPk_soft()==null){
            resultMap.put("error","软件相关信息或者部门名称有误，请重新输入!");
        }else {
            resultMap.put("softBVO",softBVO);
        }
        return resultMap;
    }

    /**
     * 判断excel中使用软件情况明细清单信息
     * @param qkmxBVO
     * @param map
     * @param commonMap
     * @return
     */
    public static Map handleUseSoftB(QkmxBVO qkmxBVO,Map map,Map commonMap){
        Map resultMap = new HashMap<>();
        List<ComputerVO> computerList =(ArrayList) commonMap.get("computerlist");
        List<DeptVO> deptlist = (ArrayList)commonMap.get("deptlist");
        List<SoftInfoVO> softInfoList=(ArrayList)commonMap.get("softinfolist");
        List<PsndocVO> psndocList = (ArrayList)commonMap.get("psndoclist");
        List<SoftTypeVO> softTypeList = (ArrayList<SoftTypeVO>)commonMap.get("softtypelist");
        StringBuilder sb = new StringBuilder();
        for (int i=0;i<deptlist.size();i++){
            DeptVO deptVO = deptlist.get(i);
            if (deptVO.getName().equals(map.get("pk_deptdoc"))&&deptVO.getOrg_pk().equals(commonMap.get("pk_org"))){
                qkmxBVO.setPk_deptdoc(deptVO.getPk_deptdoc());
                break;
            }
        }
        for (int i=0;i<softInfoList.size();i++){
            SoftInfoVO softInfoVO = softInfoList.get(i);
            if (softInfoVO.getCode().equals(map.get("softcode")) && softInfoVO.getName().equals(map.get("softname")) && softInfoVO.getVersion().equals(map.get("softversion"))){
                qkmxBVO.setPk_soft(softInfoVO.getPk_soft());
                break;
            }
        }
        for (int i=0;i<computerList.size();i++){
            ComputerVO computerVO = computerList.get(i);
            if (computerVO.getCode().equals(map.get("computercode")) && computerVO.getBrand().equals(map.get("computerbrand"))&&computerVO.getPk_org().equals(commonMap.get("pk_org"))){
                qkmxBVO.setPk_computer(computerVO.getPk_computer());
                break;
            }
        }
        for (int i=0;i<psndocList.size();i++){
            PsndocVO psndocVO = psndocList.get(i);
            if (psndocVO.getOrg_pk().equals(commonMap.get("pk_org")) && psndocVO.getName().equals(map.get("pk_psndoc"))){
                qkmxBVO.setPk_psndoc(psndocVO.getPk_psndoc());
                break;
            }
        }
        for (int i =0;i<softTypeList.size();i++){
            SoftTypeVO softTypeVO = softTypeList.get(i);
            if (softTypeVO.getName().equals(map.get("pk_softtype"))){
                qkmxBVO.setPk_softtype(softTypeVO.getPk_softtype());
                break;
            }
        }
        if (qkmxBVO.getPk_deptdoc()==null){
            sb.append("部门信息有误，");
        }
        if (qkmxBVO.getPk_computer()==null){
            sb.append("计算机信息有误，");
        }
        if (qkmxBVO.getPk_soft()==null){
            sb.append("软件信息有误，");
        }
        if (qkmxBVO.getPk_softtype() ==null){
            sb.append("软件类型有误，");
        }
        if (qkmxBVO.getPk_psndoc() ==null){
            sb.append("姓名有误，");
        }
        if (sb.toString().length() !=0){
            resultMap.put("error",sb.toString()+"请重新输入!");
        }else {
            resultMap.put("qkmxBVO",qkmxBVO);
        }
        return resultMap;
    }

    /**
     * 判断excel软件使用汇总情况
     * @param softSummaryBVO
     * @param map
     * @param commonMap
     * @return
     */
    public static Map hanldeUseSummaryB(SoftSummaryBVO softSummaryBVO,Map map,Map commonMap){
        Map resultMap = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        List<SoftTypeVO> softTypeList = (ArrayList)commonMap.get("softtypelist");
        List<SoftInfoVO> softInfoList=(ArrayList)commonMap.get("softinfolist");
        for (int i=0;i<softTypeList.size();i++){
            SoftTypeVO softTypeVO = softTypeList.get(i);
            if (softTypeVO.getName().equals(map.get("softtype"))){
                softSummaryBVO.setPk_softtype(softTypeVO.getPk_softtype());
                map.put("pk_softtype",softTypeVO.getName());
                break;
            }
        }
        for (int i=0;i<softInfoList.size();i++){
            SoftInfoVO softInfoVO = softInfoList.get(i);
            if (softInfoVO.getCode().equals(map.get("softcode")) && softInfoVO.getName().equals(map.get("softname")) && softInfoVO.getVersion().equals(map.get("softversion"))){
                softSummaryBVO.setPk_soft(softInfoVO.getPk_soft());
                map.put("softsource",softInfoVO.getSoft_source());
                break;
            }
        }
        if (softSummaryBVO.getPk_softtype()==null){
            sb.append("软件类型有误，");
        }
        if (softSummaryBVO.getPk_soft()==null){
            sb.append("软件信息有误，");
        }
        if (sb.toString().length() !=0){
            resultMap.put("error",sb.toString()+"请重新输入!");
        }else {
            resultMap.put("softSummaryBVO",softSummaryBVO);
        }
        return resultMap;
    }

    /**
     * 判断excel软件安装维护情况子表
     * @param softMaindetailBVO
     * @param map
     * @param commonMap
     * @return
     */
    public static Map hanldeSoftMaindetailBVOExcel(SoftMaindetailBVO softMaindetailBVO, Map map, Map commonMap){
        Map resultMap = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        List<SoftTypeVO> softTypeList = (ArrayList)commonMap.get("softtypelist");
        List<SoftInfoVO> softInfoList=(ArrayList)commonMap.get("softinfolist");
        for (int i=0;i<softTypeList.size();i++){
            SoftTypeVO softTypeVO = softTypeList.get(i);
            if (softTypeVO.getName().equals(map.get("softtype"))){
                resultMap.put("softtype",map.get("softtype"));
                break;
            }
        }
        for (int i=0;i<softInfoList.size();i++){
            SoftInfoVO softInfoVO = softInfoList.get(i);
            if (softInfoVO.getCode().equals(map.get("softcode")) && softInfoVO.getName().equals(map.get("softname")) && softInfoVO.getVersion().equals(map.get("softversion"))){
                softMaindetailBVO.setPk_soft(softInfoVO.getPk_soft());
                break;
            }
        }
        if (resultMap.get("softtype")==null){
            sb.append("软件信息有误，");
        }
        if (softMaindetailBVO.getPk_soft()==null){
            sb.append("软件类型有误，");
        }
        if (sb.toString().length() !=0){
            resultMap.put("error",sb.toString()+"请重新输入!");
        }else {
            resultMap.remove("softtype");
            resultMap.put("softMaindetailBVO",softMaindetailBVO);
        }
        return resultMap;
    }

    /**
     * 判断excel软件安装维护情况明细
     * @param softMaindetailSubVO
     * @param map
     * @param commonMap
     * @return
     */
    public static Map hanldeSoftMaindetailSubExcel(SoftMaindetailSubVO softMaindetailSubVO, Map map, Map commonMap){
        Map resultMap = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        List<ComputerVO> computerList =(ArrayList) commonMap.get("computerlist");
        List<DeptVO> deptlist = (ArrayList)commonMap.get("deptlist");
        List<PsndocVO> psndocList = (ArrayList)commonMap.get("psndoclist");
        for (int i=0;i<deptlist.size();i++){
            DeptVO deptVO = deptlist.get(i);
            if (deptVO.getName().equals(map.get("pk_deptdoc"))&&deptVO.getOrg_pk().equals(commonMap.get("pk_org"))){
                softMaindetailSubVO.setPk_deptdoc(deptVO.getPk_deptdoc());
                break;
            }
        }
        for (int i=0;i<computerList.size();i++){
            ComputerVO computerVO = computerList.get(i);
            if (computerVO.getCode().equals(map.get("computercode")) && computerVO.getBrand().equals(map.get("computerbrand"))&&computerVO.getPk_org().equals(commonMap.get("pk_org"))){
                softMaindetailSubVO.setPk_computer(computerVO.getPk_computer());
                break;
            }
        }
        for (int i=0;i<psndocList.size();i++){
            PsndocVO psndocVO = psndocList.get(i);
            if (psndocVO.getOrg_pk().equals(commonMap.get("pk_org")) && psndocVO.getName().equals(map.get("pk_psndoc"))){
                softMaindetailSubVO.setPk_psndoc(psndocVO.getPk_psndoc());
                break;
            }
        }
        if (softMaindetailSubVO.getPk_deptdoc()==null){
            sb.append("部门信息有误，");
        }
        if (softMaindetailSubVO.getPk_computer()==null){
            sb.append("计算机信息有误，");
        }
        if (softMaindetailSubVO.getPk_psndoc() ==null){
            sb.append("使用人姓名有误，");
        }
        if (sb.toString().length() !=0){
            resultMap.put("error",sb.toString()+"请重新输入!");
        }else {
            resultMap.put("softMaindetailSubVO",softMaindetailSubVO);
        }
        return resultMap;
    }
}
