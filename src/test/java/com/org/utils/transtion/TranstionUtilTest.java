package com.org.utils.transtion;

import com.org.utils.bean.User;
import com.org.utils.transition.TranstionUtil;
import net.sf.json.JSONObject;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Copyright (c) 2017,　新大陆软件工程公司 All rights reserved。
 *
 * @author cby
 * @version V1.0
 * @Title: TranstionUtilTest
 * @Package com.org.utils.transtion
 * @Description:
 * @date 2017/9/19
 */
public class TranstionUtilTest {
    @Test
    public void mapToBean() throws Throwable {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", "张三");
        map.put("age", 12);
        map.put("home_city", 591);
        Object object = TranstionUtil.MapToBean(map, User.class);
        System.out.println(JSONObject.fromObject(object));

    }
}
