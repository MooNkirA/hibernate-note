package com.moon.hibernate.dao;

import com.moon.hibernate.entity.Customer;
import com.moon.hibernate.utils.HibernateUtil;
import org.hibernate.Session;

/**
 * hibernate dao 层
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2024-03-09 12:13
 * @description
 */
public class HibernateDao {

    // 增加数据方法
    public void save(Customer customer) {
        Session session = HibernateUtil.getSession();
        System.out.println(session.hashCode() + "--dao层");
        // 保存数据
        session.save(customer);
    }

}
