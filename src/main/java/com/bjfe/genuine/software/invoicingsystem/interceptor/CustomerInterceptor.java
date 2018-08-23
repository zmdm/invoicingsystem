package com.bjfe.genuine.software.invoicingsystem.interceptor;

import com.bjfe.genuine.software.invoicingsystem.model.cuser.CuserVO;
import com.bjfe.genuine.software.invoicingsystem.model.pub.MemoryData;

import net.sf.json.JSONObject;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/10/30.
 */

/**
 * 后台管理系统拦截器类
 * 编写人：宋超洋
 */
public class CustomerInterceptor implements HandlerInterceptor{
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setContentType("textml;charset=UTF-8");
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "0");
        response.setHeader("Access-Control-Allow-Headers", "Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With,userId,token");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("XDomainRequestAllowed","1");
        CuserVO cuserVO = (CuserVO) request.getSession().getAttribute(request.getParameter("username"));
//      System.out.println("sessionid2:"+request.getSession().getId()+"+"+natrualPersonVO2.getUsercode());

       if (cuserVO !=null ){
            String userSessionID = request.getSession().getId();
            if (userSessionID.equals(MemoryData.getUserIDMap().get(request.getParameter("username")))){
                return true;
            }else {
                response.setCharacterEncoding("UTF-8");
                Map map = new HashMap<>();
                Writer out = response.getWriter();
                map.put("logout",false);
                JSONObject jsonObject = net.sf.json.JSONObject.fromObject(map);
                out.write(jsonObject.toString());
                out.flush();
                return false;
            }
        }else {
            Map map = new HashMap<>();
            Writer out = response.getWriter();
            map.put("logout",false);
            JSONObject jsonObject = JSONObject.fromObject(map);
            out.write(jsonObject.toString());
            out.flush();
            return false;
        }

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
