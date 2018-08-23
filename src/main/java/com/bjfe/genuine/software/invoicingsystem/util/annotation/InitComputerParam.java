package com.bjfe.genuine.software.invoicingsystem.util.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Administrator on 2018/2/27.
 */

/**
 * 自定义注解
 * 编写人：宋超洋
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface InitComputerParam {
}
