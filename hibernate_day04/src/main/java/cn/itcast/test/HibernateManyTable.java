package cn.itcast.test;

import cn.itcast.utils.HibernateUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.Test;

import java.util.List;

/**
 * HQL多表查询
 */
public class HibernateManyTable {
    //内连接和迫切内连接
    @Test
    public void testSelect1(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        try {
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            //1、创建Query对象
            //内连接，返回的list每部分是数组
//            Query query = session.createQuery("from Customer c inner join c.linkManSet");
            //迫切内连接，返回的list每部分是对象
            Query query = session.createQuery("from Customer c inner join fetch c.linkManSet");
            List list = query.list();
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
            sessionFactory.close();
        }

    }

    //左（右）外连接连接和迫切左（右）外连接
    @Test
    public void testSelect2(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        try {
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            //1、创建Query对象
            //左（右）外连接连接，返回的list每部分是数组
//            Query query = session.createQuery("from Customer c left outer join c.linkManSet");
            //迫切左（右）外连接连接，返回的list每部分是对象
            Query query = session.createQuery("from Customer c left outer join fetch c.linkManSet");
            List list = query.list();
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
            sessionFactory.close();
        }

    }
}
