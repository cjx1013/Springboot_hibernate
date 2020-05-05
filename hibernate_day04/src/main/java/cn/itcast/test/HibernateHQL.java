package cn.itcast.test;

import cn.itcast.entity.Customer;
import cn.itcast.utils.HibernateUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.Test;

import java.util.List;

/**
 * HQL查询
 */
public class HibernateHQL {
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
            Query query = session.createQuery("from Customer");
            List<Customer> list = query.list();
            for (Customer customer:list) {
                System.out.println(customer.getCid()+":"+customer.getCustName());
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

    //条件查询
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
            //select * from Customer where cid = ? and custName = ?
            Query query = session.createQuery("from Customer where cid = ? and custName = ? ");
            //2、向占位符设置值
            //setParameter：参数1：占位符的位置，从0开始，参数2：占位符的值
            query.setParameter(0,1);
            query.setParameter(1,"传智播客");
            //3、调用query的list方法
            List<Customer> list = query.list();
            for (Customer customer:list) {
                System.out.println(customer.getCid()+":"+customer.getCustName());
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

    //模糊查询
    @Test
    public void testSelect3(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        try {
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            //1、创建Query对象
            Query query = session.createQuery("from Customer where custName like ? ");
            //2、向占位符设置值
            query.setParameter(0,"%度%");
            //3、调用query的list方法
            List<Customer> list = query.list();
            for (Customer customer:list) {
                System.out.println(customer.getCid()+":"+customer.getCustName());
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
    public void testSelect4(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        try {
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            //1、创建Query对象
            Query query = session.createQuery("from Customer order by cid desc ");
            //2、调用query的list方法
            List<Customer> list = query.list();
            for (Customer customer:list) {
                System.out.println(customer.getCid()+":"+customer.getCustName());
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
    public void testSelect5(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        try {
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            //1、创建Query对象
            Query query = session.createQuery("from Customer");
            //2、设置分页数据
            //设置开始位置
            query.setFirstResult(0);
            //设置结束位置
            query.setMaxResults(2);
            //3、调用query的list方法
            List<Customer> list = query.list();
            for (Customer customer:list) {
                System.out.println(customer.getCid()+":"+customer.getCustName());
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

    //投影查询（查询部分字段）
    @Test
    public void testSelect6(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        try {
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            //1、创建Query对象
            Query query = session.createQuery("select custName from Customer");
            //2、调用query的list方法
            List<Object> list = query.list();
            for (Object Object:list) {
                System.out.println(Object);
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

    //聚集函数使用
    @Test
    public void testSelect7(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        try {
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            //1、创建Query对象
            Query query = session.createQuery("select count(*) from Customer");
            //2、调用query方法,直接返回对象
            Object o = query.uniqueResult();
            //先转化为long，再转换为int
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
}
