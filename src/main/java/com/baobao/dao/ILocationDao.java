package com.baobao.dao;

import com.baobao.domain.Location;

import java.util.List;

/**
 * @author baobao
 * @create 2019-11-20 23:15
 * @description
 */
public interface ILocationDao {
    //查询所有所在地并包含每个所在地有哪些部门
    List<Location> selectAllWithDepartments();

    Location selectById(int id);
}
