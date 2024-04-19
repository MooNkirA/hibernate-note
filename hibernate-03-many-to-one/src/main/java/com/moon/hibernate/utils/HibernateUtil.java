package com.moon.hibernate.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * hibernate 工具类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2024-03-09 12:03
 * @description
 */
public class HibernateUtil {
    // 使用static的关键字，整个项目共享一个对象。
    // 如果一个项目里面出现多个连接池，有可能导致事务处理不同步。事务同步提交的前提是同一个连接。
    // 创建静态Session工厂成员变量
    public static SessionFactory sessionFactory = HibernateUtil.createSessionFactory();
    // 创建线程共享对象
    private static ThreadLocal<Session> local = new ThreadLocal<Session>();

    // 获得会话工厂
    private static SessionFactory createSessionFactory() {
        // 1.创建Configuration对象
        Configuration cfg = new Configuration();
        // 2.读取配置文件，获得框架信息，默认读取classpath根目录hibernate.cfg.xml
        cfg.configure();
        // 3.获得会话工厂,如果要连接数据库必须需要连接数据库的信息（四要素）
        // 获得会话工厂必须要在读取配置文件之后
        return cfg.buildSessionFactory();
    }

    // 获取会话操作对象
    public static Session getSession() {
        // 判断线程共享对象是否有值
        if (local.get() == null) {
            Session session = sessionFactory.openSession();
            // 将Session存入线程共享对象中
            local.set(session);
        }
        // 返回线路共享值
        return local.get();
    }
}
