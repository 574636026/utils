package com.org.utils.transition;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Copyright (c) 2017,　新大陆软件工程公司 All rights reserved。
 *
 * @author cby
 * @version V1.0
 * @Title: Transtion
 * @Package com.org.utils.transition
 * @Description:
 * @date 2017/9/19
 */
public class TranstionUtil {
    public static <T> T MapToBean(Map<String, Object> map, Class<T> beanClass) throws Throwable {
        T obj = beanClass.newInstance();
        BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor property : propertyDescriptors) {
            Method setter = property.getWriteMethod();
            if (setter != null) {
                System.out.println(toLowerCase(property.getName()));
                Object name = map.get(toLowerCase(property.getName()));
                System.out.println(name);
                setter.invoke(obj, name);
            }
        }
        return obj;
    }

    public static Map<String, Object> objectToMap(Object obj) throws Exception {
        if (obj == null)
            return null;
        Map<String, Object> map = new HashMap<String, Object>();
        BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor property : propertyDescriptors) {
            String key = property.getName();
            if (key.compareToIgnoreCase("class") == 0) {
                continue;
            }
            Method getter = property.getReadMethod();
            Object value = getter != null ? getter.invoke(obj) : null;
            map.put(key, value);
        }
        return map;
    }


    public static String toUpperCase(String value) {
        String[] strs = value.split("_");
        StringBuffer stringBuffer = new StringBuffer();
        boolean flag = true;
        for (String str : strs) {
            if (flag) {
                stringBuffer.append(str);
                flag = false;
            } else {
                char chars[] = str.toCharArray();
                chars[0] = Character.toUpperCase(chars[0]);
                stringBuffer.append(new String(chars));
            }
        }
        return stringBuffer.toString();
    }

    public static String toLowerCase(String value) {
        StringBuffer stringBuffer = new StringBuffer();
        char chars[] = value.toCharArray();
        for (char c : chars) {
            if (Character.isUpperCase(c)) {
                stringBuffer.append("_");
                stringBuffer.append(Character.toLowerCase(c));
            } else
                stringBuffer.append(c);
        }
        return stringBuffer.toString();
    }


}
