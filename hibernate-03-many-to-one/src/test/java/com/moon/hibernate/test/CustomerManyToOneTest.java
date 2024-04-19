package com.moon.hibernate.test;

import com.moon.hibernate.entity.Linkman;
import com.moon.hibernate.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

/**
 * 多对一查询测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2024-03-09 13:14
 * @description
 */
public class CustomerManyToOneTest {

    // 需求：通过 ID 查询一条联系人表的记录，同时查询该联系人对应的客户的信息！
    @Test
    public void getById() {
        // 获取session操作对象
        Session session = HibernateUtil.getSession();

        // 查询Linkman对象
        Linkman linkman = session.get(Linkman.class, 3L);
        if (linkman != null) {
            System.out.println("联系人：" + linkman.getLkmName());
            System.out.println("客户是：" + linkman.getCustomer().getCustName());
        }

        // 关闭资源
        session.close();
    }

    // 多表连接操作（多对一不同配置的操作区别）
    @Test
    public void save() {
        // 获取session操作对象
        Session session = HibernateUtil.getSession();
        // 开启事务
        Transaction transaction = session.beginTransaction();

        // 使用方式一新增数据：保留一个数据库外键字段列
        Linkman l = new Linkman();
        // l.setLkmName("方式一");
        // Customer c = new Customer();
        // c.setCustId(3L);
        // l.setCustomer(c);

        // 使用方式二新增数据：保留两个数据库外键字段列
        l.setLkmName("方式二");
        l.setLkmCustid(19L);

        // 保存数据
        session.save(l);

        // 提交事务
        transaction.commit();
        // 关闭资源
        session.close();
    }

}
