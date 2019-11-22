package com.baobao.domain;

import java.io.Serializable;

/**
 * @author baobao
 * @create 2019-11-18 21:05
 * @description Department的bean
 */
public class Department  implements Serializable{
    //整型的定义最好都用Integer，不要用int，用int会导致碰到数据库中为null值时，封装对应bean的字段变为0
    private Integer id;
    private String name;
    private Integer managerId;
    private Integer locationId;

    private Location location;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    //一定要有空参构造，否则mybatis无法将查询结果反射到bean
    public Department() {
    }

    public Department(String name, Integer managerId, Integer locationId) {
        this.name = name;
        this.managerId = managerId;
        this.locationId = locationId;
    }

    public Department(Integer id, String name, Integer managerId, Integer locationId) {
        this.id = id;
        this.name = name;
        this.managerId = managerId;
        this.locationId = locationId;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location=" + location +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }
}
