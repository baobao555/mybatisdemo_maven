package com.baobao.interceptor;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;

import java.sql.Statement;
import java.util.Properties;

/**
 * @author baobao
 * @create 2020-02-08 11:27
 * @description
 */
@Intercepts({
        @Signature(type = StatementHandler.class,method = "parameterize",args = Statement.class)
})
public class MyInterceptor2 implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        //打印拦截到的方法
        System.out.println("MyInterceptor2 intercept：" + invocation.getMethod());
        //打印拦截到的对象
        System.out.println("MyInterceptor2当前拦截到的对象：" + invocation.getTarget());
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        //打印要包装的对象
        System.out.println("MyInterceptor2 plugin将要包装的对象：" + target);
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
        System.out.println("MyInterceptor2 setProperties：" + properties);
    }
}
