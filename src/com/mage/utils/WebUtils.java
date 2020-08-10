package com.mage.utils;

import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @author qzp
 * @create 2020-04-14 11:05
 * @Description: 将参数注入对象工具类
 */
public class WebUtils {
    /**
     * @Description: 将参数注入对象的方法
     * @Param: [value, bean]
     * @return: T
     * @Author: qzp
     * @Date: 2020/4/14
     **/
    public static <T> T copyParmToBean(Map value,T bean){
        try {
            System.out.println("注入之前"+bean);
            /*把所有请求的参数都注入到 bean 对象中*/
            BeanUtils.populate(bean, value);
            System.out.println("注入之后"+bean);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return bean;
    }
    /*
     * @Description: 将字符串转变成int类型
     * @Param: [str, defaultvalue]
     * @return: int
     * @Author: qzp
     * @Date: 2020/4/14
     **/
    public static int parseInt(String str,int defaultvalue){
        try {
            if(str !=null){
                return Integer.parseInt(str);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return defaultvalue;
    }
}
