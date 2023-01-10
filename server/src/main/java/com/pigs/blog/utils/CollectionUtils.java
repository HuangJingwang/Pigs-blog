package com.pigs.blog.utils;

import com.pigs.blog.contract.response.ArticlesTagsListResponse;
import org.omg.CORBA.OBJ_ADAPTER;
import org.omg.CORBA.Object;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CollectionUtils {
    public static Object mapToFiled(Map<String,Object> map, Object obj) throws IllegalAccessException {
        //第一步,通过反射放入值
        Class clazz=obj.getClass();

        Field[] fields = clazz.getDeclaredFields();

        //第二步:
        for(Field filed : fields){
            //允许外界访问
            filed.setAccessible(true);
            //1.map中没有对应该字段的值
            //2.Map中的值是Object类型的，会不会遇到存放值不匹配的，报异常的情况
            if(map.get(filed.getName())!=null && filed.getType()!= BigDecimal.class){
                filed.set(obj,map.get(filed.getName()));
            }
            //3.存放BigDecimal的时候报错
            //4.扩展问题，如果遇到还有其他属性不能放入进去，那么继续 && ?
            if(filed.getType()== BigDecimal.class){
                String val =map.get(filed.getName()).toString();
                BigDecimal bigDecimal = new BigDecimal(val);
                filed.set(obj,bigDecimal);
            }

        }
        return obj;
    }
}
