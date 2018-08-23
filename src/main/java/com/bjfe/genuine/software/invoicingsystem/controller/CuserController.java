package com.bjfe.genuine.software.invoicingsystem.controller;

import com.bjfe.genuine.software.invoicingsystem.model.cuser.CuserVO;
import com.bjfe.genuine.software.invoicingsystem.model.pub.MemoryData;
import com.bjfe.genuine.software.invoicingsystem.model.pub.exception.GlobalException;
import com.bjfe.genuine.software.invoicingsystem.service.cuser.ICuserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
//表现层：Controller层负责具体业务的业务模块流程控制  要调用Service层的接口来控制业务流程

//控制的配置也同样是在spring的配置文件进行
/**
 * Created by Administrator on 2018-01-03.
 */
/**
 * 用户控制器类
 * 编写人：李宗泽
 */
    @CrossOrigin(origins = "*",maxAge = 1000)
//   @CrossOrigin 可以处理跨域请求，让你能访问不是一个域的文件
    @RequestMapping("/cuser")
//    RequestMapping是一个用来处理请求地址映射的注解，可用于类或方法上。用于类上，表示类中的所有响应请求的方法都是以该地址作为父路径。
    @Controller
//表示这是一个@Controller层
    public class CuserController {
        @Resource(name = "iCuserService")
        private ICuserService iCuserService;

        /**
         * 添加用户
         * @param cuserVO
         * @return
         * @throws GlobalException
         */
        @RequestMapping(value = "/insertcuser",method = RequestMethod.POST)
//        value：指定请求的实际地址
        @ResponseBody
//      @responseBody注解的作用是将controller的方法返回的对象通过适当的转换器转换为指定的格式之后，
//      写入到response对象的body区，通常用来返回JSON数据或者是XML
        public Object insertCuser(@RequestBody CuserVO cuserVO)throws GlobalException{
            Map map = iCuserService.insertCuser(cuserVO);
            return map;
        }

        /**
         * 查询显示用户
         * @param whereMap
         * @return
         * @throws GlobalException
         */
        @RequestMapping(value = "/selectcuserlist",method = RequestMethod.POST)
        @ResponseBody
        public Object selectCuserList(@RequestBody Map whereMap)throws GlobalException{
            Map map = iCuserService.selectCuserList(whereMap);
            return map;
        }

        /**
         * 修改用户
         * @param cuserVO
         * @return
         * @throws GlobalException
         */
        @RequestMapping(value = "/updatecuser",method = RequestMethod.POST)
        @ResponseBody
        public Object updateCuser(@RequestBody CuserVO cuserVO)throws GlobalException{
            Map map = iCuserService.updateCuser(cuserVO);
            return map;
        }

        /**
         * 删除用户
         * @param map
         * @return
         * @throws GlobalException
         */
        @RequestMapping(value = "/deletecuser",method = RequestMethod.POST)
        @ResponseBody
        public Object deleteCuser(@RequestBody Map<String,String> map)throws GlobalException{
            String cd=map.get("cuserid");
            String[]cuserid=cd.split(",");
            Map resultMap = iCuserService.deleteCuser(cuserid);
            return resultMap;
        }

        /**
         * 修改用户状态
         * @param whereMap
         * @return
         * @throws GlobalException
         */
        @RequestMapping(value = "/updateuserstate",method = RequestMethod.POST)
        @ResponseBody
        public Object updateUserState(@RequestBody Map whereMap)throws GlobalException{
            Map map = iCuserService.updateCuserState(whereMap);
            return map;
        }

        /**
         * 通过角色查询用户是否分配
         * @return
         */
        @RequestMapping(value = "/userrole",method = RequestMethod.GET)
        @ResponseBody
        public Object editUserNoRole(@RequestParam("pk_role") String pk_role)throws GlobalException{
            Map map = iCuserService.editUserNoRole(pk_role);
            return map;
        }

        /**
         * 用户登陆判断
         * @param cuserVO
         * @param session
         * @return
         * @throws GlobalException
         */
        @RequestMapping(value = "/usercustomer",method = RequestMethod.POST)
        @ResponseBody
        public Object userCustomer(@RequestBody CuserVO cuserVO, HttpSession session)throws GlobalException{
            Map map = iCuserService.customerByCodeAndPass(cuserVO);
            if (map.get("sessioncustomer")!=null){
                session.setAttribute(cuserVO.getUsername(),map.get("sessioncustomer"));
                if (!MemoryData.getUserIDMap().containsKey(cuserVO.getUsername())){
                    MemoryData.getUserIDMap().put(cuserVO.getUsername(),session.getId());
                }else {
                    MemoryData.getUserIDMap().remove(cuserVO.getUsername());
                    MemoryData.getUserIDMap().put(cuserVO.getUsername(),session.getId());
                }
                System.out.println("managesession:"+session.getId());
            }
            return map;
        }
        /**
         * 系统退出登陆
         * @param map
         * @return
         */
        @RequestMapping(value = "/logout.do",method = RequestMethod.POST)
        @ResponseBody
        public Object manageLogout(@RequestBody Map<String,String> map,HttpSession session){
            Map resultMap = new HashMap<>();
            if (map.get("username")!=null){
                session.removeAttribute(map.get("username"));
                MemoryData.getUserIDMap().remove(map.get("username"));
                resultMap.put("success",true);
            }else {
                resultMap.put("success",false);
            }
            return resultMap;
        }
}


