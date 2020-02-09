package com.baobao.test;

import com.baobao.dao.IDepartmentDao;
import com.baobao.dao.IDepartmentDaoDynamicSql;
import com.baobao.dao.ILocationDao;
import com.baobao.domain.Department;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author baobao
 * @create 2020-02-05 16:44
 * @description
 */
public class MybatisTestDynamicSql {
    private InputStream config;
    private SqlSessionFactory factory;
    private SqlSession sqlSession;
    private IDepartmentDaoDynamicSql departmentDao;
    private ILocationDao locationDao;

    @Before
    public void init() throws Exception{
        //1.读取mybatis主配置文件
        config = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.根据主配置文件创建SqlSessionFactory
        factory = new SqlSessionFactoryBuilder().build(config);
        //3.通过SqlSessionFactory得到SqlSession,并设置自动提交
        sqlSession = factory.openSession(true);
        //4.通过SqlSession得到IDepartmentDao的代理实现类
        departmentDao = sqlSession.getMapper(IDepartmentDaoDynamicSql.class);
        locationDao = sqlSession.getMapper(ILocationDao.class);
    }

    @After
    public void destroy() throws Exception{
        //6.释放资源
        sqlSession.close();
        config.close();
    }

    @Test
    public void testIf(){
        Department department = new Department(null, "%Ad%", null, null);
        List<Department> departments = departmentDao.selectWithIf(department);
        for (Department dept : departments){
            System.out.println(dept);
        }
    }

    @Test
    public void testWhere(){
        Department department = new Department(null, "%Ad%", null, null);
        List<Department> departments = departmentDao.selectWithWhere(department);
        for (Department dept : departments){
            System.out.println(dept);
        }
    }

    @Test
    public void testTrim(){
        Department department = new Department(null, "%Ad%", null, null);
        List<Department> departments = departmentDao.selectWithTrim(department);
        for (Department dept : departments){
            System.out.println(dept);
        }
    }

    @Test
    public void testChoose(){
        Department department = new Department(10, "%Ad%", null, null);
        List<Department> departments = departmentDao.selectWithChoose(department);
        for (Department dept : departments){
            System.out.println(dept);
        }
    }

    @Test
    public void testSet(){
        Department department = new Department(10, "Adms", null, null);
        departmentDao.updateDepartment(department);
    }

    @Test
    public void testForeachSelect(){
        //查询id为10,20,30的department
        List<Integer> ids = Arrays.asList(10, 20, 30);
        List<Department> departments = departmentDao.selectWithForeach(ids);
        for (Department department : departments){
            System.out.println(department);
        }
    }

    @Test
    public void testForeachInsert(){
        //插入3条Department
        ArrayList<Department> depts = new ArrayList<>();
        depts.add(new Department("A", 201, 1700));
        depts.add(new Department("B", 203, 1800));
        depts.add(new Department("C", 205, 2500));
        departmentDao.insertWithForeach(depts);
    }


}
