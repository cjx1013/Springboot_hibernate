package cn.itcast.test;

import cn.itcast.entity.Customer;
import cn.itcast.utils.HibernateUtils;
import org.hibernate.*;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;

import java.util.List;

/**
 * QBC查询
 */
public class HibernateQBC {
    //查询所有
    @Test
    public void testSelect1(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        try {
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            //1、创建Criteria对象
            Criteria criteria = session.createCriteria(Customer.class);
            //2、调用list方法
            List<Customer> list = criteria.list();
            for (Customer customer:list) {
                System.out.println(customer);
            }
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
            sessionFactory.close();
        }

    }

    //条件查询和模糊查询
    @Test
    public void testSelect2(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        try {
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            //1、创建Criteria对象
            Criteria criteria = session.createCriteria(Customer.class);
            //2、调用Criteria里的方法设置条件值
            //在add方法里面使用类的方法实现条件设置
            //相当于cid = ？ and custName = ？
//            criteria.add(Restrictions.eq("cid",1));
//            criteria.add(Restrictions.eq("custName","传智播客"));
            //模糊查询
            criteria.add(Restrictions.like("custName","%百%"));
            List<Customer> list = criteria.list();
            for (Customer customer:list) {
                System.out.println(customer);
            }
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
            sessionFactory.close();
        }

    }

    //排序查询
    @Test
    public void testSelect3(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        try {
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            //1、创建Criteria对象
            Criteria criteria = session.createCriteria(Customer.class);
            //2、设置对哪个属性进行排序，设置排序规则
            criteria.addOrder(Order.desc("cid"));
            List<Customer> list = criteria.list();
            for (Customer customer:list) {
                System.out.println(customer);
            }
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
            sessionFactory.close();
        }

    }

    //分页查询
    @Test
    public void testSelect4(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        try {
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            //1、创建Criteria对象
            Criteria criteria = session.createCriteria(Customer.class);
            //2、设置分页数据
            //设置开始位置
            criteria.setFirstResult(0);
            //设置每页记录数
            criteria.setMaxResults(3);
            List<Customer> list = criteria.list();
            for (Customer customer:list) {
                System.out.println(customer);
            }
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
            sessionFactory.close();
        }

    }

    //统计记录数
    @Test
    public void testSelect5(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        try {
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            //1、创建Criteria对象
            Criteria criteria = session.createCriteria(Customer.class);
            //2、设置操作
            criteria.setProjection(Projections.rowCount());
            //3、调用方法得到结果
            Object o = criteria.uniqueResult();
            Long lo = (Long) o;
            int count = lo.intValue();
            System.out.println(count);

            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
            sessionFactory.close();
        }

    }

    //离线查询（在servlet中实现对数据库操作，不在dao）
    @Test
    public void testSelect6(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        try {
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            //1、创建对象
            DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Customer.class);
            //2、最终执行的时候才用到session
            Criteria criteria = detachedCriteria.getExecutableCriteria(session);
            List<Customer> list = criteria.list();
            for (Customer customer:list) {
                System.out.println(customer);
            }
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
