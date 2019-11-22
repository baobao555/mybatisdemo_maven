package com.baobao.dao;

import com.baobao.domain.Department;
import com.baobao.domain.Location;

import java.util.List;

/**
 * @author baobao
 * @create 2019-11-18 21:08
 * @description
 */
public interface IDepartmentDao {
    //查询所有
    List<Department> selectAll();

    //根据Id查询
    Department selectById(int id);

    //插入
    void insertOne(Department department);

    //更新
    void update(Department department);

    //删除
    void delete(int id);

    //模糊查询
    List<Department> selectByName(String name);

    //聚合函数查询总记录数
    int selectTotalCount();

    //查询所有部门并且带有部门所在地信息
    List<Department> selectAllWithLocation();



    //查询所有部门并且带有部门所在地信息，使用懒加载
    List<Department> selectAllWithLocationLazy();
}
