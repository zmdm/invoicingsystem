package com.bjfe.genuine.software.invoicingsystem.model.pub.exception;

import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2017-11-9.
 */
public class GlobalExceptionResolver {

    public ModelAndView resolveException(HttpServletRequest request,
                                         HttpServletResponse response, Object obj, Exception ex) {
        // handler就是处理器适配器执行 handler对象(只有method)
        //解析异常类型
//				String message=null;
//				if(ex instanceof CustomExcption){
//					message=((CustomExcption)ex).getMessage();
//				}else{
//					message="未知错误";
//				}
        //上面代码变为
        GlobalException globalException=null;
        if(ex instanceof GlobalException){
            globalException=(GlobalException)ex;
        }else{
            globalException=new GlobalException("未知错误");
        }
        //错误信息
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("message", globalException.getMessage());
        modelAndView.setViewName("error");
        return modelAndView;
    }
}
