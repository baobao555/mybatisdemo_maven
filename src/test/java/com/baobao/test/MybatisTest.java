package com.baobao.test;

import com.baobao.dao.IDepartmentDao;
import com.baobao.dao.ILocationDao;
import com.baobao.domain.Department;
import com.baobao.domain.Location;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.rmi.server.ExportException;
import java.util.List;

/**
 * @author baobao
 * @create 2019-11-16 22:55
 * @description
 */
public class MybatisTest {


    private InputStream config;
    private SqlSessionFactory factory;
    private SqlSession sqlSession;
    private IDepartmentDao departmentDao;
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
        departmentDao = sqlSession.getMapper(IDepartmentDao.class);
        locationDao = sqlSession.getMapper(ILocationDao.class);
    }

    @After
    public void destroy() throws Exception{
        //6.释放资源
        sqlSession.close();
        config.close();
    }

    @Test
    public void testSelectAll() throws Exception {
        //5.通过代理实现类完成查询的方法
        List<Department> departments = departmentDao.selectAll();
        for (Department department : departments){
            System.out.println(department);
        }

    }

    @Test
    public void testSelectById(){
        Department department = departmentDao.selectById(120);
        System.out.println(department);
    }

    @Test
    public void testInsertOne(){
        //主键重复、不满足外键约束等都会导致插入失败
        departmentDao.insertOne(new Department(301, "bao", 205, 1700));
    }

    @Test
    public void testUpdate(){
        Department department = new Department(300, "ba", 200, 1700);
        departmentDao.update(department);
    }

    @Test
    public void testDelete(){
        departmentDao.delete(300);
    }

    @Test
    public void testSelectByName(){
        List<Department> departments = departmentDao.selectByName("A%");
        for (Department department : departments){
            System.out.println(department);
        }
    }

    @Test
    public void testSelectTotalCount(){
        int totalCount = departmentDao.selectTotalCount();
        System.out.println(totalCount);
    }

    @Test
    public void testSelectAllWithLocation(){
        List<Department> departments = departmentDao.selectAllWithLocation();
        for (Department department : departments){
            System.out.println(department);
        }
    }

    @Test
    public void testSelectAllLocationWithDepartments(){
        List<Location> locations = locationDao.selectAllWithDepartments();
        for (Location location : locations){
            System.out.println(location);
        }
    }

    @Test
    public void selectAllWithLocationLazy(){
        //只查询Department并封装到集合，并没有用到Location，懒加载不会发起Location的查询
        List<Department> departments = departmentDao.selectAllWithLocationLazy();
    }
}
