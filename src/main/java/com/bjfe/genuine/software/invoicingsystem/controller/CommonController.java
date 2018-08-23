package com.bjfe.genuine.software.invoicingsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/30.
 */

/**
 * 公共控制层
 */
@CrossOrigin(origins = "*", maxAge = 1000)
@Controller
@RequestMapping("/manage")
public class CommonController {
    @RequestMapping(value = "/common",method = RequestMethod.GET)
    @ResponseBody
    public Object toMyData(){
        Map map = new HashMap<>();
        map.put("success",true);
        //return "redirect:http://192.168.1.164:9000/#/MyData/basicInf";
        return map;
    }
}
