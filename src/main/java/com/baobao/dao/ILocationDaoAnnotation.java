package com.baobao.dao;

import com.baobao.domain.Location;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * @author baobao
 * @create 2019-11-20 23:15
 * @description 注解方式配置Dao
 */
public interface ILocationDaoAnnotation {

    @Select("select * from locations where location_id = #{id}")
    @Results(value = {@Result(id = true,column = "location_id",property = "id"),
    @Result(column = "street_address",property = "streetAddress"),
    @Result(column = "postal_code",property = "postalCode"),
    @Result(column = "city",property = "city"),
    @Result(column = "state_province",property = "stateProvince"),
    @Result(column = "country_id",property = "country")})
    Location selectById(int id);


    //查询所有所在地并包含每个所在地有哪些部门
    @Select("select * from locations")
    @Results(value = {@Result(id = true,column = "location_id",property = "id"),
            @Result(column = "street_address",property = "streetAddress"),
            @Result(column = "postal_code",property = "postalCode"),
            @Result(column = "city",property = "city"),
            @Result(column = "state_province",property = "stateProvince"),
            @Result(column = "country_id",property = "country"),
            @Result(column = "location_id",property = "departments",
                    many = @Many(select = "com.baobao.dao.IDepartmentDaoAnnotation.selectByLocationId",fetchType = FetchType.LAZY))})
    List<Location> selectAllWithDepartments();
}
