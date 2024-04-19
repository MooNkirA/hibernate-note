package com.moon.hibernate.test;

import com.moon.hibernate.dao.HibernateDao;
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
 * @date 2024-03-09 12:15
 * @description
 */
public class HibernateTest {

    @Test
    public void testSession() {
        // 获取会话对象
        Session session = HibernateUtil.getSession();
        System.out.println(session.hashCode());
        // 开启事务 配置了session绑定到当前线程，必须要启动事务。包括查询都需要启动事务
        Transaction transaction = session.beginTransaction();

        // 新增数据
        HibernateDao dao = new HibernateDao();

        for (int i = 1; i < 11; i++) {
            Customer c = new Customer();
            c.setCustName("batch insert-" + i);
            dao.save(c);
        }
        Customer c3 = session.get(Customer.class, 3L);
        System.out.println(c3);

        // 提交事务
        transaction.commit();
        // 关闭资源
        // Hibernate绑定的线程，session已经实现随线程启动而打开，随线程结束而关闭
        session.close();
    }

}
