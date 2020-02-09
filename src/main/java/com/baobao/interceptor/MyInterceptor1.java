package com.baobao.interceptor;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;

import java.sql.Statement;
import java.util.Properties;

/**
 * @author baobao
 * @create 2020-02-08 11:26
 * @description
 */

/*
@Intercepts告诉MyBatis当前插件用来拦截哪个对象的哪个方法
假设拦截：StatementHandler中的void parameterize(Statement statement)*/
@Intercepts({
     @Signature(type = StatementHandler.class,method = "parameterize",args = Statement.class)
})
public class MyInterceptor1 implements Interceptor {
    /**
     * 拦截目标方法
     * @param invocation 拦截到的目标对象
     * @return 方法执行结果
     * @throws Throwable
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        //打印拦截到的方法
        System.out.println("MyInterceptor1 intercept：" + invocation.getMethod());
        //打印拦截到的对象
        System.out.println("MyInterceptor1当前拦截到的对象：" + invocation.getTarget());
        //执行目标方法
        Object result = invocation.proceed();
        //返回目标方法执行后的返回值
        return result;
    }

    /**
     * 把目标对象包装成1个代理对象
     * @param target 目标对象
     * @return 包装后的代理对象
     */
    @Override
    public Object plugin(Object target) {
        //打印要包装的对象
        System.out.println("MyInterceptor1 plugin将要包装的对象：" + target);
        //利用工具方法Plugin.wrap包装目标对象
        Object wrap = Plugin.wrap(target, this);
        //返回包装后的代理对象
        return wrap;
    }

    /**
     * 全局配置文件注册插件时设置的属性
     * @param properties 通过配置文件设置进来的属性
     */
    @Override
    public void setProperties(Properties properties) {
        System.out.println("MyInterceptor1 setProperties：" + properties);
    }
}
