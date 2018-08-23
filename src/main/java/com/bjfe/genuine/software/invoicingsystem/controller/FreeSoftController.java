package com.bjfe.genuine.software.invoicingsystem.controller;

import com.bjfe.genuine.software.invoicingsystem.model.cuser.CuserVO;
import com.bjfe.genuine.software.invoicingsystem.model.pub.exception.GlobalException;
import com.bjfe.genuine.software.invoicingsystem.service.mfrj.IMfrjService;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/22.
 */

/**
 * 可使用免费软件清单控制类
 * 编写人：宋超洋
 */
@CrossOrigin(origins = "*",maxAge = 1000)
@RequestMapping("/freesoft")
@Controller
public class FreeSoftController {
    @Resource(name = "iMfrjService")
    private IMfrjService iMfrjService;

    /**
     * 查询可使用免费软件清单
     * @param whereMap
     * @return
     * @throws GlobalException
     */
    @RequestMapping(value = "/selectfreesoft",method = RequestMethod.POST)
    @ResponseBody
    public Object selectFreeSoftBVO(@RequestBody Map<String,String> whereMap)throws GlobalException{
        Map map = iMfrjService.querySoftHBByID(whereMap);
        return map;
    }

    /**
     * 导入excel
     * @return
     * @throws GlobalException
     */
    @RequestMapping(value = "/importecexl",method = RequestMethod.POST)
    @ResponseBody
    public Object importEcexl(@RequestParam("uploadfile") MultipartFile file,HttpServletRequest request,HttpSession session)throws GlobalException{
        Map map = new HashMap<>();
        String fileName =null;
        CuserVO cuserVO = (CuserVO) session.getAttribute(request.getParameter("username"));
        if (!file.isEmpty()) {
            Map dataMap = new HashMap<>();
            fileName = file.getOriginalFilename();//report.xls
            dataMap.put("pk_org",cuserVO.getPk_org());
            dataMap.put("creator",cuserVO.getPk_psndoc());
            dataMap.put("telphone",cuserVO.getTelphone());
            map = iMfrjService.importEcexl(file,fileName,dataMap);
        }else {
            map.put("success",false);
            map.put("info","请上传文件！");
        }

        return map;
    }

    /**
     * 下载excel
     * @return
     * @throws IOException
     */
    @RequestMapping("download")
    public ResponseEntity<byte[]> download() throws IOException {
        String path="D:\\upload\\excel\\中国信保正版软件管理相关表—需要导入功能.xls";
        File file=new File(path);
        HttpHeaders headers = new HttpHeaders();
        String fileName=new String("中国信保正版软件管理相关表—需要导入功能.xls".getBytes("UTF-8"),"iso-8859-1");//为了解决中文名称乱码问题
        headers.setContentDispositionFormData("attachment", fileName);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),
                headers, HttpStatus.CREATED);
    }
}
