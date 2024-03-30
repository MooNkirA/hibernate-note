package com.moon.hibernate.test;

import com.moon.hibernate.entity.Customer;
import com.moon.hibernate.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.Test;

import java.util.List;

/**
 * HQL 查询
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2024-03-09 12:33
 * @description
 */
public class HibernateHQLTest {

    // 1.需求：查询客户表的所有数据，通过hql
    @SuppressWarnings("unchecked")
    @Test
    public void findAll() {
        // 获取Session对象
        Session session = HibernateUtil.getSession();
        // 获取Query查询接口
        Query query = session.createQuery("from Customer");
        // Query query = session.createQuery("select c from Customer c");

        // 调用Query查询方法
        List<Customer> list = query.list();
        // 关闭资源
        session.close();

        // 遍历输出结果
        for (Customer c : list) {
            System.out.println(c.getCustName());
        }
    }

    // 2.需求：查询有名字"剑"的客户
    @SuppressWarnings("unchecked")
    @Test
    public void findByName() {
        // 获取Session对象
        Session session = HibernateUtil.getSession();
        // 获取Query查询接口
        // Query query = session.createQuery("from Customer c where c.custName like ?");
        // 声明一个命名参数，:name(注意有冒号（：）)
        Query query = session.createQuery("from Customer c where c.custName like :name");

        // 设置参数？的值，如果是多个？号，索引是从0开始
        // query.setString(0, "%剑%");
        // 设置命名参数的值，注意：调用命名参数，不用冒号
        query.setString("name", "%剑%");

        // 调用Query查询方法
        List<Customer> list = query.list();
        // 关闭资源
        session.close();

        // 遍历输出结果
        for (Customer c : list) {
            System.out.println(c.getCustName());
        }
    }

    // 3.需求：返回表里面的记录数
    @Test
    public void count() {
        // 获取Session对象
        Session session = HibernateUtil.getSession();
        // 获取Query查询接口
        Query query = session.createQuery("select count(*) from Customer");

        // 调用Query查询方法,查询返回只有一条数据uniqueResult
        Long count = (Long) query.uniqueResult();
        // 关闭资源
        session.close();
        // 输出结果
        System.out.println(count);
    }

    // 分页：需求从第3条数据取4条数据
    @SuppressWarnings("unchecked")
    @Test
    public void findByPage() {
        // 获取Session对象
        Session session = HibernateUtil.getSession();
        // 获取Query查询接口
        Query query = session.createQuery("from Customer");

        // 设置分页条件，分页的开始位置，下标从0开始
        query.setFirstResult(2);
        // 设置每页显示的数量
        query.setMaxResults(4);

        // 调用Query查询方法
        List<Customer> list = query.list();
        // 关闭资源
        session.close();

        // 遍历输出结果
        for (Customer c : list) {
            System.out.println(c.getCustName());
        }
    }

    // 需求：查询客户表，只需要,custName,custSource
    @SuppressWarnings("unchecked")
    @Test
    public void findCustomer() {
        // 获取Session对象
        Session session = HibernateUtil.getSession();
        // 获取Query查询接口
        Query query = session.createQuery("select new Customer(c.custName, c.custSource) from Customer c");

        // 调用Query查询方法
        List<Customer> list = query.list();
        // 关闭资源
        session.close();

        // 遍历输出结果
        for (Customer c : list) {
            System.out.println(c.getCustName());
        }
    }

    @SuppressWarnings("unchecked")
    @Test
    public void findCustomer2() {
        // 获取Session对象
        Session session = HibernateUtil.getSession();
        // 获取Query查询接口
        Query query = session.createQuery("select c.custName,c.custSource from Customer c");

        // 调用Query查询方法 返回的是一个Object[]类型。取数据不方便
        List<Object[]> list = query.list();
        // 关闭资源
        session.close();

        // 遍历输出结果
        for (Object[] obj : list) {
            System.out.println(obj[0] + "===" + obj[1]);
        }
    }

    // 删除，通过客户名字删除
    @Test
    public void delete() {
        // 获取Session对象
        Session session = HibernateUtil.getSession();
        // 增删改操作，需要开启事务
        Transaction transaction = session.beginTransaction();
        // 获取Query操作接口
        Query query = session.createQuery("delete from Customer c where c.custName = :name");

        // 设置命名参数的值
        query.setString("name", "拿来删除");
        // 执行操作,返回成功影响的行数
        int row = query.executeUpdate();
        System.out.println(row);
        // 提交事务
        transaction.commit();
        // 关闭资源
        session.close();
    }

    // 更新一个字段，需求，更新，客户名为剑圣的客户，的来源为：Dota2
    @Test
    public void update() {
        // 获取Session对象
        Session session = HibernateUtil.getSession();
        // 增删改操作，需要开启事务
        Transaction transaction = session.beginTransaction();
        // 获取Query操作接口
        Query query = session.createQuery("update Customer c set c.custSource = :source where c.custName = :name");

        // 设置命名参数的值
        query.setString("source", "Dota2");
        query.setString("name", "剑圣");
        // 执行操作,返回成功影响的行数
        int row = query.executeUpdate();
        System.out.println(row);
        // 提交事务
        transaction.commit();
        // 关闭资源
        session.close();
    }

}
