package com.biz.std.utils;

import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.List;


/**
 * Created by Administrator on 2017/5/6 0006.
 */
public class BeanUtilsBean{
    //vo和po的转换
    public static void VOConvertPO(Object dest,Object orig){
        try {
            BeanUtils.copyProperties(dest,orig);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
