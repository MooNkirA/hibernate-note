package com.moon.hibernate.test;

import com.moon.hibernate.entity.Customer;
import com.moon.hibernate.utils.HibernateUtil;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.junit.Test;

import java.math.BigInteger;
import java.util.List;

/**
 * SQL 查询
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2024-03-09 12:33
 * @description
 */
public class HibernateSQLTest {

    // 1.查询所有的数据
    @SuppressWarnings("unchecked")
    @Test
    public void findAll() {
        // 获取session操作对象
        Session session = HibernateUtil.getSession();

        // 获取SQLQuery操作对象
        SQLQuery sqlQuery = session.createSQLQuery("select * from cst_customer");

        // 使用SQL需要手动关联实体类
        // 由于SQL查询返回的是一个Object数组，如果需要指定的实体类接收，需求强制绑定
        sqlQuery.addEntity(Customer.class);
        List<Customer> list = sqlQuery.list();

        // 关闭资源
        session.close();
        for (Customer c : list) {
            System.out.println(c.getCustName());
        }
    }

    // 2.需求：查询客户表客户名有“剑”数据，通过SQL
    @SuppressWarnings("unchecked")
    @Test
    public void findByName() {
        // 获取session操作对象
        Session session = HibernateUtil.getSession();

        // 获取SQLQuery操作对象,Hibernate支持命名参数
        SQLQuery sqlQuery = session.createSQLQuery("select * from cst_customer c where c.cust_name like :name");

        // 使用SQL需要手动关联实体类
        // 由于SQL查询返回的是一个Object数组，如果需要指定的实体类接收，需求强制绑定
        sqlQuery.addEntity(Customer.class);
        sqlQuery.setString("name", "%剑%");
        List<Customer> list = sqlQuery.list();

        // 关闭资源
        session.close();
        for (Customer c : list) {
            System.out.println(c.getCustName());
        }
    }

    // 需求3：查询第3条开始，取4条件数据
    @SuppressWarnings("unchecked")
    @Test
    public void findByPage() {
        // 获取session操作对象
        Session session = HibernateUtil.getSession();

        // 获取SQLQuery操作对象,Hibernate支持命名参数
        SQLQuery sqlQuery = session.createSQLQuery("select * from cst_customer");

        // 使用SQL需要手动关联实体类
        // 由于SQL查询返回的是一个Object数组，如果需要指定的实体类接收，需求强制绑定
        sqlQuery.addEntity(Customer.class);

        // 设置分页参数
        sqlQuery.setFirstResult(2);
        sqlQuery.setMaxResults(4);

        List<Customer> list = sqlQuery.list();

        // 关闭资源
        session.close();
        for (Customer c : list) {
            System.out.println(c.getCustName());
        }
    }

    // 需求：查询返回表里面的记录数
    @Test
    public void count() {
        // 获取session操作对象
        Session session = HibernateUtil.getSession();

        // 获取SQLQuery操作对象,Hibernate支持命名参数
        SQLQuery sqlQuery = session.createSQLQuery("select count(*) from cst_customer");

        // 使用SQL需要手动关联实体类
        // 由于SQL查询返回的是一个Object数组，如果需要指定的实体类接收，需求强制绑定
        BigInteger count = (BigInteger) sqlQuery.uniqueResult();

        // 关闭资源
        session.close();
        System.out.println(count);
    }

}
