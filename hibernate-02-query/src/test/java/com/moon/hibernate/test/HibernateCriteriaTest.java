package com.moon.hibernate.test;

import com.moon.hibernate.entity.Customer;
import com.moon.hibernate.utils.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;

import java.util.List;

/**
 * Criteria 查询
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2024-03-09 12:32
 * @description
 */
public class HibernateCriteriaTest {

    // 1.查询所有的数据，QBC
    @SuppressWarnings("unchecked")
    @Test
    public void findAll() {
        // 获取Session对象
        Session session = HibernateUtil.getSession();
        // 获取Criteria对象，必须指定查询的类.class
        Criteria criteria = session.createCriteria(Customer.class);
        // 调用list方法，查询所有数据，返回对象list集合
        List<Customer> list = criteria.list();

        // 遍历集合
        for (Customer c : list) {
            System.out.println(c.getCustName());
        }
        // 关闭资源
        session.close();
    }

    // 通过条件查询,通过客户名模糊查询
    @SuppressWarnings("unchecked")
    @Test
    public void findByName() {
        // 获取Session对象
        Session session = HibernateUtil.getSession();
        // 获取Criteria对象，必须指定查询的类.class
        Criteria criteria = session.createCriteria(Customer.class);

        // 设置查询条件
        criteria.add(Restrictions.like("custName", "%剑%"));

        // 调用list方法，查询所有数据，返回对象list集合
        List<Customer> list = criteria.list();

        // 遍历集合
        for (Customer c : list) {
            System.out.println(c.getCustName());
        }
        // 关闭资源
        session.close();
    }

    // 查询第3条开始，取4条件数据
    @SuppressWarnings("unchecked")
    @Test
    public void findByPage() {
        // 获取Session对象
        Session session = HibernateUtil.getSession();
        // 获取Criteria对象，必须指定查询的类.class
        Criteria criteria = session.createCriteria(Customer.class);

        // 设置首页开始索引
        criteria.setFirstResult(2);
        // 设置分页显示的数据条数
        criteria.setMaxResults(4);

        // 调用list方法，查询所有数据，返回对象list集合
        List<Customer> list = criteria.list();

        // 遍历集合
        for (Customer c : list) {
            System.out.println(c.getCustName());
        }
        // 关闭资源
        session.close();
    }

    // QBC使用聚合函数:统计查询
    @Test
    public void testProjection() {
        // 获取Session对象
        Session session = HibernateUtil.getSession();
        // 开启事务
        Transaction tx = session.beginTransaction();

        // 获取Criteria对象,相当于from Customer 或 select * from cst_customer
        Criteria c = session.createCriteria(Customer.class);
        // 想办法把 select * 变成 select count(*)
        c.setProjection(Projections.count("custId"));
        Long total = (Long) c.uniqueResult();
        System.out.println(total);

        // 提交事务
        tx.commit();
        // 关闭资源
        session.close();
    }

    // 离线查询
    @Test
    public void testDetachedCriteria() {
        // 模拟一次web操作: 浏览器发送请求——调用servlet——调用service——调用dao——拿到结果到jsp上展示
        List<Customer> list = servletFindAllCustomer();
        for (Object o : list) {
            System.out.println(o);
        }
    }

    // 模拟 servlet
    public List<Customer> servletFindAllCustomer() {
        // 离线对象
        DetachedCriteria dCriteria = DetachedCriteria.forClass(Customer.class);
        // 设置条件：和 Criteria 是一样的
        dCriteria.add(Restrictions.like("custName", "%剑%"));
        return serviceFindAllCustomer(dCriteria);
    }

    public List<Customer> serviceFindAllCustomer(DetachedCriteria dCriteria) {
        return daoFindAllCustomer(dCriteria);
    }

    @SuppressWarnings("unchecked")
    public List<Customer> daoFindAllCustomer(DetachedCriteria dCriteria) {
        Session s = HibernateUtil.getSession();
        Transaction tx = s.beginTransaction();
        // 把离线对象使用可用 Session 激活
        Criteria c = dCriteria.getExecutableCriteria(s);
        List<Customer> list = c.list();
        tx.commit();
        return list;
    }

}
