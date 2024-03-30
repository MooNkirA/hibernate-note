package com.moon.hibernate.test;

import com.moon.hibernate.entity.Customer;
import com.moon.hibernate.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

/**
 * hibernate 测试类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2024-03-03 13:16
 * @description
 */
public class HibernateBasicTest {

    @Test
    public void testSession() {
        // 获取会话对象
        Session session = HibernateUtil.getSession();
        System.out.println(session);
    }

    @Test
    public void save() {
        // 获取会话
        Session session = HibernateUtil.getSession();
        // 开启事务
        Transaction transaction = session.beginTransaction();

        // 进行添加数据操作
        Customer c = new Customer();
        c.setCustName("MooN");
        session.save(c);

        // 提交事务
        transaction.commit();
        // 关闭资源
        session.close();
    }

    @Test
    public void update() {
        // 获取会话
        Session session = HibernateUtil.getSession();
        // 开启事务
        Transaction transaction = session.beginTransaction();

        // 进行修改数据操作
        Customer c = new Customer();
        c.setCustId(1L);
        // c.setCustName("xxoo");
        c.setCustLevel("一级");
        session.saveOrUpdate(c);

        // 提交事务
        transaction.commit();
        // 关闭资源
        session.close();
    }

    @Test
    public void update2() {
        // 获取会话
        Session session = HibernateUtil.getSession();
        // 开启事务
        Transaction transaction = session.beginTransaction();

        // 进行修改数据操作，如果想修改某个字段，保留其他字段，必须先查询再修改
        Customer c = session.get(Customer.class, 1L);
        // 持久态对象修改了属性，直接提交就可以更新到数据库里面
        c.setCustIndustry("xxoo");
        // session.saveOrUpdate(c);

        // 提交事务
        transaction.commit();
        // 关闭资源
        session.close();
    }

    @Test
    public void delete() {
        // 获取会话
        Session session = HibernateUtil.getSession();
        // 开启事务
        Transaction transaction = session.beginTransaction();

        // 进行修改数据操作，如果想修改某个字段，保留其他字段，必须先查询再修改
        Customer c = new Customer();
        c.setCustId(1L);
        session.delete(c);

        // 提交事务
        transaction.commit();
        // 关闭资源
        session.close();
    }

    @Test
    public void get() {
        // 获取会话
        Session session = HibernateUtil.getSession();
        // 开启事务
        Transaction transaction = session.beginTransaction();

        // 进行修改数据操作，如果想修改某个字段，保留其他字段，必须先查询再修改
        Customer c = session.get(Customer.class, 1L);
        System.out.println(c);
        Customer c2 = session.get(Customer.class, 1L);
        System.out.println(c2);
        Customer c3 = session.get(Customer.class, 1L);
        System.out.println(c3);
        Customer c4 = session.get(Customer.class, 1L);
        System.out.println(c4);
        // 提交事务
        transaction.commit();
        // 关闭资源
        session.close();
    }

}
