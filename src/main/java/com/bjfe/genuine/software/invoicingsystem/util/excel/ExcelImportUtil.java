package com.bjfe.genuine.software.invoicingsystem.util.excel;

/**
 * Created by Administrator on 2018/1/22.
 */

import com.bjfe.genuine.software.invoicingsystem.model.dept.DeptVO;
import com.bjfe.genuine.software.invoicingsystem.model.mfrj.SoftBVO;
import com.bjfe.genuine.software.invoicingsystem.model.mfrj.SoftHVO;
import com.bjfe.genuine.software.invoicingsystem.model.psndoc.PsndocVO;
import com.bjfe.genuine.software.invoicingsystem.model.qkmx.QkmxBVO;
import com.bjfe.genuine.software.invoicingsystem.model.rjxx.SoftInfoVO;
import com.bjfe.genuine.software.invoicingsystem.model.softmaindetail.SoftMaindetailBVO;
import com.bjfe.genuine.software.invoicingsystem.model.softmaindetail.SoftMaindetailSubVO;
import com.bjfe.genuine.software.invoicingsystem.model.softsummary.SoftSummaryBVO;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 导入excel工具类
 * 编写人：宋超洋
 */
public class ExcelImportUtil {

    /**
     * 验证EXCEL文件
     *
     * @param filePath
     * @return
     */
    public static boolean validateExcel(String filePath) {
        if (filePath == null || !(WDWUtil.isExcel2003(filePath) || WDWUtil.isExcel2007(filePath))) {
            String errorMsg = "文件名不是excel格式";
            return false;
        }
        return true;
    }

    /**
     * 判断空行
     *
     * @param row
     * @return
     */
    public static boolean isRowEmpty(Row row) {
        int temp = 0;
        for (int c = row.getFirstCellNum(); c < row.getLastCellNum(); c++) {
            Cell cell = row.getCell(c);
            if (cell != null && cell.getCellType() != Cell.CELL_TYPE_BLANK) {
                temp += 1;
            }
        }
        if (temp == 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 读EXCEL文件，获取客户信息集合
     *
     * @param fileName
     * @return
     */
    public static Map getExcelInfo(String fileName, MultipartFile Mfile, Map commonMap) {

        //把spring文件上传的MultipartFile转换成CommonsMultipartFile类型
       /* CommonsMultipartFile cf= (CommonsMultipartFile)Mfile; //获取本地存储路径
        File file = new  File("D:\\fileupload");
        //创建一个目录 （它的路径名由当前 File 对象指定，包括任一必须的父路径。）
        if (!file.exists()) file.mkdirs();
        //新建一个文件
        File file1 = new File("D:\\fileupload" + new Date().getTime() + ".xlsx");
        //将上传的文件写入新建的文件中
        try {
            cf.getFileItem().write(file1);
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        /*CommonsMultipartFile cf= (CommonsMultipartFile)Mfile; //获取本地存储路径
        DiskFileItem fi =(DiskFileItem) cf.getFileItem();*/
        //初始化客户信息的集合
        Map map = new HashMap<>();
        //File file = fi.getStoreLocation();
        //初始化输入流
        InputStream is = null;
        try {
            //验证文件名是否合格
            if (!validateExcel(fileName)) {
                return null;
            }
            //根据文件名判断文件是2003版本还是2007版本
            boolean isExcel2003 = true;
            if (WDWUtil.isExcel2007(fileName)) {
                isExcel2003 = false;
            }
            //根据新建的文件实例化输入流
            is = Mfile.getInputStream();
            //根据excel里面的内容读取客户信息
            map = getExcelInfo(is, isExcel2003, commonMap);
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    is = null;
                    e.printStackTrace();
                }
            }
        }
        return map;
    }

    /**
     * 根据excel里面的内容读取客户信息
     *
     * @param is          输入流
     * @param isExcel2003 excel是2003还是2007版本
     * @return
     * @throws IOException
     */
    public static Map getExcelInfo(InputStream is, boolean isExcel2003, Map commonMap) {
        Map map = null;
        try {
            /** 根据版本选择创建Workbook的方式 */
            Workbook wb = null;
            //当excel是2003时
            if (isExcel2003) {
                wb = new HSSFWorkbook(is);
            } else {//当excel是2007时
                wb = new XSSFWorkbook(is);
            }
            //读取Excel里面客户的信息
            map = readExcelValue(wb, commonMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 读取Excel里面客户的信息
     *
     * @param wb
     * @return
     */
    private static Map readExcelValue(Workbook wb, Map commonMap) {
        Map map = null;
        //得到第一个shell
        Sheet sheet = wb.getSheetAt((Integer) commonMap.get("sheet"));

        //得到Excel的行数
        int totalRows = sheet.getPhysicalNumberOfRows();
        //Excel的列数
        int totalCells = 0;

        //得到Excel的列数(前提是有行数)
        if (totalRows >= 1 && sheet.getRow(0) != null) {
            totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
        }
        if ((int) commonMap.get("sheet") == 0) {
            map = handleSoftFree(totalRows, sheet, totalCells, commonMap);
        } else if ((int) commonMap.get("sheet") == 2) {
            map = handleUseSoftSituationDeatil(totalRows, sheet, totalCells, commonMap);
        } else if ((int) commonMap.get("sheet") == 1) {
            map = handleUseSoftSituationSummary(totalRows, sheet, totalCells, commonMap);
        } else if ((int) commonMap.get("sheet") == 3) {
            map = handleSoftMaindetail(totalRows, sheet, totalCells, commonMap);
        }
        return map;
    }

    /**
     * 处理可使用免费软件清单的信息
     *
     * @param totalRows
     * @param sheet
     * @param totalCells
     * @param commonMap
     * @return
     */
    public static Map handleSoftFree(int totalRows, Sheet sheet, int totalCells, Map commonMap) {
        List<SoftBVO> softBVOList = new ArrayList<SoftBVO>();
        SoftBVO softBVO;
        Map dataMap = new HashMap<>();
        List<Map> dataList = new ArrayList<>();
        for (int r = 2; r < totalRows; r++) {
            Row row = sheet.getRow(r);
            softBVO = new SoftBVO();
            Map mapTemp = new HashMap<>();
            StringBuffer sb = new StringBuffer();
            boolean b = isRowEmpty(row);
            if (!b) {
                continue;
            }
            //循环Excel的列
            for (int c = 0; c < totalCells; c++) {
                Cell cell = row.getCell(c);
                int cellType = cell.getCellType();
                if (null != cell) {
                    if (c == 0) {
                        if (Cell.CELL_TYPE_NUMERIC == cellType) {
                            mapTemp.put("softcode", new DecimalFormat("#").format(cell.getNumericCellValue()));
                        } else if (Cell.CELL_TYPE_STRING == cellType && cell.getStringCellValue().toString().matches("^[0-9A-Za-z]+$")) {
                            mapTemp.put("softcode", cell.getStringCellValue());
                        } else {
                            sb.append("软件编码格式错误!");
                        }
                    } else if (c == 1) {
                        if (Cell.CELL_TYPE_STRING == cellType) {
                            mapTemp.put("softname", cell.getStringCellValue());
                        } else {
                            sb.append("软件名称格式错误!");
                        }
                    } else if (c == 2) {
                        if (Cell.CELL_TYPE_STRING == cellType) {
                            mapTemp.put("softversion", cell.getStringCellValue());
                        } else {
                            sb.append("软件版本格式错误!");
                        }
                    } else if (c == 3) {
                        if (Cell.CELL_TYPE_STRING == cellType) {
                            mapTemp.put("soft_source", cell.getStringCellValue());
                        } else {
                            sb.append("软件来源格式错误!");
                        }
                    } else if (c == 4) {
                        if (Cell.CELL_TYPE_STRING == cellType) {
                            mapTemp.put("soft_vendors", cell.getStringCellValue());
                        } else {
                            sb.append("软件厂商格式错误!");
                        }
                    } else if (c == 5) {
                        if (Cell.CELL_TYPE_STRING == cellType && cell.getStringCellValue().toString().matches("\\d{3}-\\d{8}|\\d{4}-\\d{7}")) {
                            softBVO.setSofttelphone(cell.getStringCellValue());
                            mapTemp.put("softtelphone", cell.getStringCellValue());
                        } else {
                            sb.append("软件厂商客服联系方式格式错误!");
                        }
                    } else if (c == 6) {
                        if (Cell.CELL_TYPE_STRING == cellType) {
                            mapTemp.put("pk_deptdoc", cell.getStringCellValue());
                        } else {
                            sb.append("部门格式格式错误!");
                        }
                    } else if (c == 7) {
                        if (Cell.CELL_TYPE_STRING == cellType) {
                            softBVO.setReason(cell.getStringCellValue());
                            mapTemp.put("reason", cell.getStringCellValue());
                        } else {
                            sb.append("使用原因格式格式错误!");
                        }
                    }
                }
            }
            if (sb.toString().length() != 0) {
                dataMap.put("error", "第" + (r - 1) + "行" + sb.toString());
                break;
            }
            dataList.add(mapTemp);
            Map tempMap = FreeSoftUtil.handleFreeSoftB(softBVO, mapTemp, commonMap);
            if (tempMap.containsKey("error")) {
                dataMap.put("error", "第" + (r - 1) + "行" + tempMap.get("error"));
                break;
            }
            //添加客户
            softBVOList.add((SoftBVO) tempMap.get("softBVO"));
        }
        dataMap.put("datalist", dataList);
        dataMap.put("softBVOList", softBVOList);
        return dataMap;
    }

    /**
     * 处理软件使用情况明细表
     *
     * @param totalRows
     * @param sheet
     * @param totalCells
     * @param commonMap
     * @return
     */
    public static Map handleUseSoftSituationDeatil(int totalRows, Sheet sheet, int totalCells, Map commonMap) {
        List<QkmxBVO> qkmxList = new ArrayList<QkmxBVO>();
        QkmxBVO qkmxBVO;
        Map dataMap = new HashMap<>();
        List<Map> dataList = new ArrayList<>();
        for (int r = 2; r < totalRows; r++) {
            Row row = sheet.getRow(r);
            qkmxBVO = new QkmxBVO();
            Map mapTemp = new HashMap<>();
            StringBuffer sb = new StringBuffer();
            boolean b = isRowEmpty(row);
            if (!b) {
                continue;
            }
            //循环Excel的列
            for (int c = 0; c < totalCells; c++) {
                Cell cell = row.getCell(c);
                int cellType = cell.getCellType();
                if (null != cell) {
                    if (c == 0) {
                        if (Cell.CELL_TYPE_STRING == cellType) {
                            mapTemp.put("pk_deptdoc", cell.getStringCellValue());
                        } else {
                            sb.append("部门格式格式错误!");
                        }
                    } else if (c == 1) {
                        if (Cell.CELL_TYPE_STRING == cellType) {
                            mapTemp.put("pk_psndoc", cell.getStringCellValue());
                        } else {
                            sb.append("姓名格式格式错误!");
                        }
                    } else if (c == 2) {
                        if (Cell.CELL_TYPE_NUMERIC == cellType) {
                            mapTemp.put("computercode", new DecimalFormat("#").format(cell.getNumericCellValue()));
                        } else if (Cell.CELL_TYPE_STRING == cellType) {
                            mapTemp.put("computercode", cell.getStringCellValue());
                        } else {
                            sb.append("计算机编码格式格式错误!");
                        }
                    } else if (c == 3) {
                        if (Cell.CELL_TYPE_STRING == cellType) {
                            mapTemp.put("computerbrand", cell.getStringCellValue());
                        }else if (Cell.CELL_TYPE_NUMERIC == cellType){
                            mapTemp.put("computerbrand", new DecimalFormat("#").format(cell.getNumericCellValue()));
                        }else {
                            sb.append("计算机品牌格式格式错误!");
                        }
                    } else if (c == 4) {
                        if (Cell.CELL_TYPE_NUMERIC == cellType) {
                            mapTemp.put("softcode", new DecimalFormat("#").format(cell.getNumericCellValue()));
                        } else if (Cell.CELL_TYPE_STRING == cellType && cell.getStringCellValue().toString().matches("^[0-9A-Za-z]+$")) {
                            mapTemp.put("softcode", cell.getStringCellValue());
                        } else {
                            sb.append("软件编码格式错误!");
                        }
                    } else if (c == 5) {
                        if (Cell.CELL_TYPE_STRING == cellType) {
                            mapTemp.put("softname", cell.getStringCellValue());
                        } else {
                            sb.append("软件名称格式错误!");
                        }
                    } else if (c == 6) {
                        if (Cell.CELL_TYPE_STRING == cellType) {
                            mapTemp.put("softversion", cell.getStringCellValue());
                        } else {
                            sb.append("软件版本格式错误!");
                        }
                    } else if (c == 7) {
                        if (Cell.CELL_TYPE_NUMERIC == cellType) {
                            qkmxBVO.setPermis(new DecimalFormat("#").format(cell.getNumericCellValue()));
                            mapTemp.put("permis", new DecimalFormat("#").format(cell.getNumericCellValue()));
                        } else {
                            sb.append("许可期限格式错误！");
                        }
                    } else if (c == 8) {
                        if (Cell.CELL_TYPE_STRING == cellType) {
                            mapTemp.put("pk_softtype", cell.getStringCellValue());
                        } else {
                            sb.append("软件类型格式错误!");
                        }
                    } else if (c == 9) {
                        if (Cell.CELL_TYPE_NUMERIC == cellType) {
                            SimpleDateFormat sdf = sdf = new SimpleDateFormat("yyyy-MM-dd");
                            Date date = cell.getDateCellValue();
                            String a = sdf.format(date);//根据需要取时间，date类型和String类型
                            qkmxBVO.setTaudittime(a);
                            mapTemp.put("taudittime", a);
                        } else {
                            sb.append("安装日期格式错误!");
                        }
                    }
                }
            }
            if (sb.toString().length() != 0) {
                dataMap.put("error", "第" + (r - 1) + "行" + sb.toString());
                break;
            }
            dataList.add(mapTemp);
            Map tempMap = FreeSoftUtil.handleUseSoftB(qkmxBVO, mapTemp, commonMap);
            if (tempMap.containsKey("error")) {
                dataMap.put("error", "第" + (r - 1) + "行" + tempMap.get("error"));
                break;
            }
            //添加客户
            qkmxList.add((QkmxBVO) tempMap.get("qkmxBVO"));
        }
        dataMap.put("datalist", dataList);
        dataMap.put("qkmxBVOList", qkmxList);
        return dataMap;
    }

    /**
     * 处理软件使用情况汇总表
     *
     * @param totalRows
     * @param sheet
     * @param totalCells
     * @param commonMap
     * @return
     */
    public static Map handleUseSoftSituationSummary(int totalRows, Sheet sheet, int totalCells, Map commonMap) {
        List<SoftSummaryBVO> softSummaryList = new ArrayList<>();
        SoftSummaryBVO softSummaryBVO;
        Map dataMap = new HashMap<>();
        List<Map> dataList = new ArrayList<>();
        for (int r = 2; r < totalRows; r++) {
            Row row = sheet.getRow(r);
            softSummaryBVO = new SoftSummaryBVO();
            Map mapTemp = new HashMap<>();
            StringBuffer sb = new StringBuffer();
            boolean b = isRowEmpty(row);
            if (!b) {
                continue;
            }
            //循环Excel的列
            for (int c = 0; c < totalCells; c++) {
                Cell cell = row.getCell(c);
                int cellType = cell.getCellType();
                if (null != cell) {
                    if (c == 0) {
                        if (Cell.CELL_TYPE_NUMERIC == cellType) {
                            mapTemp.put("softcode", new DecimalFormat("#").format(cell.getNumericCellValue()));
                        } else if (Cell.CELL_TYPE_STRING == cellType && cell.getStringCellValue().toString().matches("^[0-9A-Za-z]+$")) {
                            mapTemp.put("softcode", cell.getStringCellValue());
                        } else {
                            sb.append("软件编码格式错误!");
                        }
                    } else if (c == 1) {
                        if (Cell.CELL_TYPE_STRING == cellType) {
                            mapTemp.put("softname", cell.getStringCellValue());
                        } else {
                            sb.append("软件名称格式错误!");
                        }
                    } else if (c == 2) {
                        if (Cell.CELL_TYPE_STRING == cellType) {
                            mapTemp.put("softversion", cell.getStringCellValue());
                        } else {
                            sb.append("软件版本格式错误!");
                        }
                    } else if (c == 3) {
                        if (Cell.CELL_TYPE_STRING == cellType) {
                            if ("免费软件".equals(cell.getStringCellValue()) || "OEM软件".equals(cell.getStringCellValue())) {
                                c = 10;
                                continue;
                            }
                            mapTemp.put("softtype", cell.getStringCellValue());
                        } else {
                            sb.append("软件类型格式错误!");
                        }
                    } else if (c == 4) {
                        if (Cell.CELL_TYPE_NUMERIC == cellType) {
                            SimpleDateFormat sdf = sdf = new SimpleDateFormat("yyyy-MM-dd");
                            Date date = cell.getDateCellValue();
                            String a = sdf.format(date);//根据需要取时间，date类型和String类型
                            softSummaryBVO.setOrdertime(a);
                            mapTemp.put("ordertime", a);
                        } else {
                            sb.append("采购时间格式错误!");
                        }
                    }else if (c == 5) {
                        if (Cell.CELL_TYPE_NUMERIC == cellType) {
                            softSummaryBVO.setPrice(cell.getNumericCellValue());
                            mapTemp.put("price",cell.getNumericCellValue());
                        } else {
                            sb.append("采购金额格式错误!");
                        }
                    } else if (c == 6) {
                        if (Cell.CELL_TYPE_STRING == cellType) {
                            softSummaryBVO.setPermistype(cell.getStringCellValue());
                            mapTemp.put("permistype",cell.getStringCellValue());
                        } else {
                            sb.append("许可类型格式错误！");
                        }
                    } else if (c == 7) {
                        if (Cell.CELL_TYPE_NUMERIC == cellType) {
                            softSummaryBVO.setPermisnum((int) cell.getNumericCellValue());
                            mapTemp.put("permisnum",(int) cell.getNumericCellValue());
                        } else {
                            sb.append("许可数量格式错误!");
                        }
                    } else if (c == 8) {
                        if (Cell.CELL_TYPE_STRING == cellType) {
                            softSummaryBVO.setMayversion(cell.getStringCellValue());
                            mapTemp.put("mayversion",cell.getStringCellValue());
                        } else {
                            sb.append("可使用版本格式错误!");
                        }
                    } else if (c == 9) {
                        if (Cell.CELL_TYPE_NUMERIC == cellType) {
                            softSummaryBVO.setPermisdead(new DecimalFormat("#").format(cell.getNumericCellValue()));
                            mapTemp.put("permisdead",(int)cell.getNumericCellValue());
                        } else {
                            sb.append("许可期限格式错误!");
                        }
                    } else if (c == 10) {
                        if (Cell.CELL_TYPE_STRING == cellType) {
                            softSummaryBVO.setSerialnum(cell.getStringCellValue());
                            mapTemp.put("serialnum",cell.getStringCellValue());
                        }else if (Cell.CELL_TYPE_NUMERIC ==cellType){
                            softSummaryBVO.setSerialnum(new DecimalFormat("#").format(cell.getNumericCellValue()));
                            mapTemp.put("serialnum",(int)cell.getNumericCellValue());
                        }else {
                            sb.append("序列号格式错误!");
                        }
                    } else if (c == 11) {
                        if (Cell.CELL_TYPE_NUMERIC == cellType) {
                            softSummaryBVO.setInstallnum((int) cell.getNumericCellValue());
                            mapTemp.put("installnum",(int) cell.getNumericCellValue());
                        } else {
                            sb.append("安装数量格式错误!");
                        }
                    }
                }
            }
            if (sb.toString().length() != 0) {
                dataMap.put("error", "第" + (r - 1) + "行" + sb.toString());
                break;
            }
            dataList.add(mapTemp);
            Map tempMap = FreeSoftUtil.hanldeUseSummaryB(softSummaryBVO, mapTemp, commonMap);
            if (tempMap.containsKey("error")) {
                dataMap.put("error", "第" + (r - 1) + "行" + tempMap.get("error"));
                break;
            }
            //添加客户
            softSummaryList.add((SoftSummaryBVO) tempMap.get("softSummaryBVO"));
        }
        dataMap.put("datalist", dataList);
        dataMap.put("softSummaryBVO", softSummaryList);
        return dataMap;
    }

    /**
     * 处理软件安装维护情况子表
     *
     * @param totalRows
     * @param sheet
     * @param totalCells
     * @param commonMap
     * @return
     */
    public static Map handleSoftMaindetail(int totalRows, Sheet sheet, int totalCells, Map commonMap) {
        List<SoftSummaryBVO> softSummaryList = new ArrayList<>();
        SoftMaindetailBVO softMaindetailBVO;
        Map dataMap = new HashMap<>();
        List<Map> dataList = new ArrayList<>();

        Row row = sheet.getRow(3);
        softMaindetailBVO = new SoftMaindetailBVO();
        Map mapTemp = new HashMap<>();
        StringBuffer sb = new StringBuffer();
        boolean b = isRowEmpty(row);
        if (!b) {
            return (Map) dataMap.put("error","请输入软件基本信息！");
        }
        //循环Excel的列
        for (int c = 0; c < totalCells; c++) {
            Cell cell = row.getCell(c);
            int cellType = cell.getCellType();
            if (null != cell) {
                if (c == 0) {
                    if (Cell.CELL_TYPE_NUMERIC == cellType) {
                        mapTemp.put("softcode", new DecimalFormat("#").format(cell.getNumericCellValue()));
                    } else if (Cell.CELL_TYPE_STRING == cellType && cell.getStringCellValue().toString().matches("^[0-9A-Za-z]+$")) {
                        mapTemp.put("softcode", cell.getStringCellValue());
                    } else {
                        sb.append("软件编码格式错误，");
                    }
                } else if (c == 1) {
                    if (Cell.CELL_TYPE_STRING == cellType) {
                        mapTemp.put("softname", cell.getStringCellValue());
                    } else {
                        sb.append("软件名称格式错误，");
                    }
                } else if (c == 2) {
                    if (Cell.CELL_TYPE_STRING == cellType) {
                        mapTemp.put("softversion", cell.getStringCellValue());
                    } else {
                        sb.append("软件版本格式错误，");
                    }
                } else if (c == 3) {
                    if (Cell.CELL_TYPE_STRING == cellType) {
                        mapTemp.put("softtype", cell.getStringCellValue());
                    } else {
                        sb.append("软件类型格式错误，");
                    }
                } else if (c == 4) {
                    if (Cell.CELL_TYPE_NUMERIC == cellType) {
                        softMaindetailBVO.setPermisnum((int) cell.getNumericCellValue());
                        mapTemp.put("permisnum",(int) cell.getNumericCellValue());
                    } else {
                        sb.append("许可数量格式错误，");
                    }
                } else if (c == 5) {
                    if (Cell.CELL_TYPE_STRING == cellType) {
                        softMaindetailBVO.setPermis(cell.getStringCellValue());
                        mapTemp.put("permis",cell.getStringCellValue());
                    } else {
                        sb.append("可使用版本格式错误，");
                    }
                }else if (c==6){
                    if (Cell.CELL_TYPE_NUMERIC ==cellType){
                        softMaindetailBVO.setPermisdead(new DecimalFormat("#").format(cell.getNumericCellValue()));
                        mapTemp.put("permisdead",new DecimalFormat("#").format(cell.getNumericCellValue()));
                        c=8;
                        continue;
                    }else {
                        sb.append("可使用期限本格式错误，");
                        c=8;
                        continue;
                    }
                }
            }
        }
        if (sb.toString().length() != 0) {
            dataMap.put("error", "软件基本信息，" + sb.toString()+"请重新输入！");
        }
        dataList.add(mapTemp);
        Map tempMap = FreeSoftUtil.hanldeSoftMaindetailBVOExcel(softMaindetailBVO, mapTemp, commonMap);
        if (tempMap.containsKey("error")) {
            dataMap.put("error", "软件基本信息，" + tempMap.get("error"));
        }else {
            dataMap = handleSoftMaindetailSub(totalRows,sheet,totalCells,commonMap);
            if (dataMap.get("error")!=null){
                return dataMap;
            }
        }
        //添加客户
        softMaindetailBVO =(SoftMaindetailBVO) tempMap.get("softMaindetailBVO");
        dataMap.put("datalist",dataList);
        dataMap.put("softMaindetailBVO",softMaindetailBVO);
        dataMap.put("softMaindetailSubList",dataMap.get("softMaindetailSubList"));
        return dataMap;
    }


    /**
     * 处理软件安装维护情况明细表表
     *
     * @return
     */
    public static Map handleSoftMaindetailSub(int totalRows, Sheet sheet, int totalCells, Map commonMap) {
        List<SoftMaindetailSubVO> softMaindetailSubList = new ArrayList<>();
        SoftMaindetailSubVO softMaindetailSubVO;
        Map dataMap = new HashMap<>();
        List<Map> dataList = new ArrayList<>();
        for (int r = 6; r < totalRows; r++) {
            Row row = sheet.getRow(r);
            softMaindetailSubVO = new SoftMaindetailSubVO();
            Map mapTemp = new HashMap<>();
            StringBuffer sb = new StringBuffer();
            boolean b = isRowEmpty(row);
            if (!b) {
                continue;
            }
            //循环Excel的列
            for (int c = 0; c < totalCells; c++) {
                Cell cell = row.getCell(c);
                int cellType = cell.getCellType();
                if (null != cell) {
                    if (c == 0) {
                        if (Cell.CELL_TYPE_STRING == cellType && "安装".equals(cell.getStringCellValue())) {
                            softMaindetailSubVO.setDetailtype(0);
                            mapTemp.put("detailtype",cell.getStringCellValue());
                        }else if (Cell.CELL_TYPE_BLANK ==cellType){
                            continue;
                        }else {
                            sb.append("安装格式格式错误，");
                        }
                    } else if (c == 1) {
                        if (Cell.CELL_TYPE_STRING == cellType && "安装".equals(cell.getStringCellValue())) {
                            softMaindetailSubVO.setDetailtype(1);
                            mapTemp.put("detailtype",cell.getStringCellValue());
                        }else if (Cell.CELL_TYPE_BLANK ==cellType){
                            continue;
                        }else {
                            sb.append("卸载格式格式错误，");
                        }
                    } else if (c == 2) {
                        if (Cell.CELL_TYPE_NUMERIC == cellType) {
                            mapTemp.put("computercode", new DecimalFormat("#").format(cell.getNumericCellValue()));
                        } else if (Cell.CELL_TYPE_STRING == cellType) {
                            mapTemp.put("computercode", cell.getStringCellValue());
                        } else {
                            sb.append("计算机编码格式格式错误，");
                        }
                    } else if (c == 3) {
                        if (Cell.CELL_TYPE_STRING == cellType) {
                            mapTemp.put("computerbrand", cell.getStringCellValue());
                        }else if (Cell.CELL_TYPE_NUMERIC ==cellType){
                            mapTemp.put("computerbrand",new DecimalFormat("#").format(cell.getNumericCellValue()));
                        }else {
                            sb.append("计算机品牌格式格式错误，");
                        }
                    } else if (c == 4) {
                        if (Cell.CELL_TYPE_STRING == cellType ) {
                            mapTemp.put("pk_psndoc", cell.getStringCellValue());
                        } else {
                            sb.append("使用人格式错误，");
                        }
                    } else if (c == 5) {
                        if (Cell.CELL_TYPE_STRING == cellType) {
                            mapTemp.put("pk_deptdoc", cell.getStringCellValue());
                        } else {
                            sb.append("使用部门格式错误，");
                        }
                    } else if (c == 6) {
                        if (Cell.CELL_TYPE_STRING == cellType) {
                            softMaindetailSubVO.setSoftversion(cell.getStringCellValue());
                            mapTemp.put("softversion",cell.getStringCellValue());
                        } else {
                            sb.append("安装/卸载版本格式错误，");
                        }
                    } else if (c == 7) {
                        if (Cell.CELL_TYPE_NUMERIC == cellType) {
                            SimpleDateFormat sdf = sdf = new SimpleDateFormat("yyyy-MM-dd");
                            Date date = cell.getDateCellValue();
                            String a = sdf.format(date);//根据需要取时间，date类型和String类型
                            softMaindetailSubVO.setSoftdate(a);
                            mapTemp.put("softdate", a);
                        } else {
                            sb.append("安装/卸载日期格式错误，");
                        }
                    } else if (c == 8) {
                        if (Cell.CELL_TYPE_NUMERIC == cellType) {
                            softMaindetailSubVO.setInstallnum((int) cell.getNumericCellValue());
                            mapTemp.put("installnum", (int) cell.getNumericCellValue());
                        } else {
                            sb.append("安装数量格式错误，");
                        }
                    }
                }
            }
            if (sb.toString().length() != 0) {
                dataMap.put("error", "软件基本信息,第" + (r - 5) + "行" + sb.toString()+"请重新输入！");
                break;
            }
            dataList.add(mapTemp);
            Map tempMap = FreeSoftUtil.hanldeSoftMaindetailSubExcel(softMaindetailSubVO, mapTemp, commonMap);
            if (tempMap.containsKey("error")) {
                dataMap.put("error", "软件安装卸载明细,第" + (r - 5) + "行" + tempMap.get("error"));
                break;
            }
            //添加客户
            softMaindetailSubList.add((SoftMaindetailSubVO) tempMap.get("softMaindetailSubVO"));
        }
        dataMap.put("sublist", dataList);
        dataMap.put("softMaindetailSubList", softMaindetailSubList);
        return dataMap;
    }
}
