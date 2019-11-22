package com.baobao.dao;

import com.baobao.domain.Department;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * @author baobao
 * @create 2019-11-18 21:08
 * @description 注解方式配置Dao
 */

//注解形式开启二级缓存
@CacheNamespace(blocking = true)
public interface IDepartmentDaoAnnotation {
    //查询所有
    @Select("select * from departments")
    @Results(id = "departmentMap",value = {
            @Result(id = true,column = "department_id",property = "id"),
            @Result(column = "department_name",property = "name"),
            @Result(column = "manager_id",property = "managerId"),
            @Result(column = "location_id",property = "locationId")})
    List<Department> selectAll();

    //根据Id查询
    @Select("select * from departments where department_id = #{id}")
    @ResultMap("departmentMap")
    Department selectById(int id);

    //插入
    @Insert("insert into departments (department_name,manager_id,location_id) values(#{name},#{managerId},#{locationId})")
    void insertOne(Department department);

    //更新
    @Update("update departments set department_name = #{name},manager_id = #{managerId} where department_id = #{id}")
    void update(Department department);

    //删除
    @Delete("delete from departments where department_id = #{id}")
    void delete(int id);

    //模糊查询
    @Select("select * from departments where department_name like #{name}")
    @ResultMap("departmentMap")
    List<Department> selectByName(String name);

    //聚合函数查询总记录数
    @Select("select count(1) from departments")
    int selectTotalCount();

    //查询所有部门并且带有部门所在地信息
    List<Department> selectAllWithLocation();



    //查询所有部门并且带有部门所在地信息，使用懒加载
    @Select("select * from departments")
    @Results(value = {@Result(id = true,column = "department_id",property = "id"),
            @Result(column = "department_name",property = "name"),
            @Result(column = "manager_id",property = "managerId"),
            @Result(column = "location_id",property = "locationId"),
            @Result(property = "location",column = "location_id",
                    one = @One(select = "com.baobao.dao.ILocationDaoAnnotation.selectById",fetchType = FetchType.LAZY))})
    List<Department> selectAllWithLocationLazy();

    //查询指定地点的部门
    @Select("select * from departments where location_id = #{id}")
    @ResultMap("departmentMap")
    List<Department> selectByLocationId(int id);
}
