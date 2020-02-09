package com.baobao.dao;

import com.baobao.domain.Department;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author baobao
 * @create 2020-02-05 16:31
 * @description 演示动态sql
 */
public interface IDepartmentDaoDynamicSql {
    List<Department> selectWithIf(Department department);

    List<Department> selectWithWhere(Department department);

    List<Department> selectWithTrim(Department department);

    List<Department> selectWithChoose(Department department);

    void updateDepartment(Department department);

    List<Department> selectWithForeach(@Param("ids") List<Integer> ids);

    void insertWithForeach(@Param("depts") List<Department> depts);
}
