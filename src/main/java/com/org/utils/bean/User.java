package com.org.utils.bean;

/**
 * Copyright (c) 2017,　新大陆软件工程公司 All rights reserved。
 *
 * @author cby
 * @version V1.0
 * @Title: User
 * @Package com.org.utils.bean
 * @Description:
 * @date 2017/9/19
 */
public class User {
    private String name;
    private Integer age;
    private Integer homeCity;

    /**
     * Getter for property 'name'.
     *
     * @return Value for property 'name'.
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for property 'name'.
     *
     * @param name Value to set for property 'name'.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for property 'age'.
     *
     * @return Value for property 'age'.
     */
    public Integer getAge() {
        return age;
    }

    /**
     * Setter for property 'age'.
     *
     * @param age Value to set for property 'age'.
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * Getter for property 'homeCity'.
     *
     * @return Value for property 'homeCity'.
     */
    public Integer getHomeCity() {
        return homeCity;
    }

    /**
     * Setter for property 'homeCity'.
     *
     * @param homeCity Value to set for property 'homeCity'.
     */
    public void setHomeCity(Integer homeCity) {
        this.homeCity = homeCity;
    }
}
